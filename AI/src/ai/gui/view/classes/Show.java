package ai.gui.view.classes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import ai.MainApp;
import ai.gui.control.InputControl;
import ai.gui.model.Model;
import ai.gui.view.MyView;
import ai.swarm.behaviors.Abstract_Behavior;
import ai.swarm.boid.Boid;
import ai.swarm.mode.MyMode;

public class Show extends JPanel implements MyView, Runnable
{
	private MyMode mode;
	private Model model;
	private Abstract_Behavior ab;
	private Image image;

	private final static int REFRESH = 17;
	public final static int DIAMETER = 10;

	public Show(Model model)
	{
		this.model = model;
		this.mode = model.getMyMode();
		this.ab = mode.getBehavior();
		model.addMyView(this);
		this.setBackground(MainApp.BACK);
		this.setFocusable(true);
		this.setDoubleBuffered(true);

		InputControl sc = new InputControl(this.model);
		this.addMouseMotionListener(sc);
		this.addMouseListener(sc);
		final ImageIcon iReport = new ImageIcon(getClass().getResource(
				"Wallpaper.jpg"));

		image = iReport.getImage();

		new Thread(this).start();

	}

	@Override
	public void changed()
	{
		this.mode = model.getMyMode();
		this.ab = mode.getBehavior();
	}

	public synchronized void run()
	{
		long start = 0L;
		long delta = 0L;

		while (true)
		{
			start = System.currentTimeMillis();
			mode.update();
			delta = System.currentTimeMillis() - start;
			if (delta < REFRESH)
			{
				try
				{
					this.wait(REFRESH - delta);
				} catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
			this.repaint();
		}
	}

	public void paintComponent(Graphics g)
	{
		g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), null);
		g.setColor(Color.WHITE);
		g.drawLine(0, Boid.HEIGHT + 20, Boid.WIDTH + 20, Boid.HEIGHT + 20);
		g.drawLine(Boid.WIDTH + 20, 0, Boid.WIDTH + 20, Boid.HEIGHT + 20);
		Boid b;
		for (int i = 0; i < mode.getBoids().size(); i++)
		{
			b = mode.getBoids().get(i);
			g.setColor(b.color);
			g.fillOval(b.pos.getX() - DIAMETER / 2,
					b.pos.getY() - DIAMETER / 2, DIAMETER, DIAMETER);

			g.setColor(Color.WHITE);
			if (ab.isSepDistDraw())
			{
				int sepDist = ab.getSeperationDistance();
				g.drawOval(b.pos.getX() - sepDist / 2, b.pos.getY() - sepDist
						/ 2, sepDist, sepDist);
			}
			if (ab.isNeighborhoodDraw())
			{
				int neiDist = ab.getNeiborhoodDistance();
				g.drawOval(b.pos.getX() - neiDist / 2, b.pos.getY() - neiDist
						/ 2, neiDist, neiDist);
			}
			g.setColor(Color.YELLOW);

			if (mode.isSeekingDistanceDraw())
			{
				int radius = mode.getSeekingDistance();
				g.drawOval(b.pos.getX() - radius / 2,
						b.pos.getY() - radius / 2, radius, radius);
			}
			if (mode.isSeekSeperationDistanceDraw())
			{
				int radius = mode.getSeekSeperationDistance();
				g.drawOval(b.pos.getX() - radius / 2,
						b.pos.getY() - radius / 2, radius, radius);
			}
			g.setColor(Color.GREEN);
			if (mode.isFleeingForceDraw())
			{
				int radius = mode.getFleeingDistance();
				g.drawOval(b.pos.getX() - radius / 2,
						b.pos.getY() - radius / 2, radius, radius);
			}

		}

		g.setColor(Color.RED);
		if (mode.isPathSet())
		{
			int radius = mode.getPathRadius();
			for (int i = 0; i < mode.getPathSize(); i++)
			{
				g.drawOval(mode.getPathPos(i).getX() - radius / 2, mode
						.getPathPos(i).getY() - radius / 2, radius, radius);
			}
		}

		g.setColor(Color.BLUE);
		if (mode.isObstacleAllowed())
		{
			for (int i = 0; i < mode.getObstacleSize(); i++)
			{
				int radius = mode.getObstacleRadius();
				g.drawOval(mode.getObstaclePosition(i).getX() - radius / 2,
						mode.getObstaclePosition(i).getY() - radius / 2,
						radius, radius);
			}
		}

		g.setColor(Color.WHITE);
		if (ab.isPathSet())
		{
			for (int i = 0; i < ab.getPathSize(); i++)
			{
				int radius = ab.pathFindingRadius();
				g.drawOval(ab.getPathPos(i).getX() - radius / 4,
						ab.getPathPos(i).getY() - radius / 4, radius, radius);
			}
		}
		if (ab.isObstacleSet())
		{
			for (int i = 0; i < ab.getObstacleSize(); i++)
			{
				int radius = ab.obstacleRadius();
				g.drawOval(ab.getObstaclePos(i).getX() - radius / 4, ab
						.getObstaclePos(i).getY() - radius / 4, radius, radius);
			}
		}
		if (ab.isPursuing())
		{
			for (int i = 0; i < ab.getPursuerSize(); i++)
			{
				int radius = DIAMETER * 2;
				g.fillOval(ab.getPursuerPos(i).getX() - radius / 4, ab
						.getPursuerPos(i).getY() - radius / 4, radius, radius);
			}
		}
	}
}