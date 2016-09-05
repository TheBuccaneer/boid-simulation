package ai.gui.control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import ai.gui.model.Model;
import ai.swarm.behaviors.classes.Obstacle;
import ai.swarm.mode.MyMode;
import ai.swarm.mode.classes.Obstacle_Avoidance_Mode;
import ai.swarm.mode.classes.Open_World_Mode;
import ai.swarm.mode.classes.Path_Finding_Mode;
import ai.swarm.mode.classes.Pursuing_Mode;
import ai.swarm.mode.classes.Seek_and_Flee_Mode;
import ai.swarm.mode.classes.Wanderer_Mode;

/*
 * InputController fängt alle Maus und Key Events ab, die vom Nutzer erzeugt werden
 */
public class InputControl implements KeyListener, MouseMotionListener,
		MouseListener
{

	private Model model;

	public InputControl(Model model)
	{
		this.model = model;
	}

	@Override
	public void keyPressed(KeyEvent arg0)
	{
	}

	@Override
	public void keyReleased(KeyEvent arg0)
	{
	}

	@Override
	public void keyTyped(KeyEvent arg0)
	{
		char c = arg0.getKeyChar();

		if (c == 'd')
		{
			this.model.setFlocking();
			return;
		}
		if (c == 's')
		{
			this.model.setScattering();
			return;
		}
		if (c == '1')
		{
			this.model.setSepDistDraw();
			return;
		}
		if (c == '2')
		{
			this.model.setNeighborhoodDraw();
			return;
		}

		if (this.model.getMyMode() instanceof Seek_and_Flee_Mode)
		{
			if (c == 'a')
				this.model.setSeeking();
			else if (c == 'y')
				this.model.setFleeing();
			return;
		} else if (this.model.getMyMode() instanceof Wanderer_Mode)
		{
			if (c == 'w')
				this.model.setWandering();
			return;
		} else if (this.model.getMyMode() instanceof Obstacle_Avoidance_Mode)
		{
			return;
		} else if (this.model.getMyMode() instanceof Open_World_Mode)
		{
			if (c == 'a')
				this.model.setSeeking();
			else if (c == 'y')
				this.model.setFleeing();
			else if (c == '3')
				model.setSeekingDistanceDraw();
			else if (c == '4')
				model.setSeekSeperationDistanceDraw();
			else if (c == '5')
				model.setFleeingForceDraw();
			else if (c == 'w')
				this.model.setWandering();
			else if (c == 'r')
				this.model.setPathFinding();
			else if (c == 't')
				this.model.setObstacleAllowed();
		}

	}

	@Override
	public void mouseDragged(MouseEvent arg0)
	{

	}

	@Override
	public void mouseMoved(MouseEvent arg0)
	{
		if (model.getMyMode() instanceof Seek_and_Flee_Mode)
		{
			model.setMouseVector(arg0.getX(), arg0.getY());
		} else if (this.model.getMyMode() instanceof Open_World_Mode)
		{
			model.setMouseVector(arg0.getX(), arg0.getY());
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0)
	{
		if (model.getMyMode() instanceof Path_Finding_Mode)
		{
			model.setPathNode(arg0.getX(), arg0.getY());
			return;
		} else if (this.model.getMyMode() instanceof Obstacle_Avoidance_Mode)
		{
			model.setObstacleNode(arg0.getX(), arg0.getY());
			return;
		} else if (this.model.getMyMode() instanceof Pursuing_Mode)
		{
			model.setPursuer(arg0.getX(), arg0.getY());
		} else if (this.model.getMyMode() instanceof Open_World_Mode)
		{
			if (this.model.isPathFinding())
			{
				this.model.setPathNode(arg0.getX(), arg0.getY());
			} else if (this.model.isObstacleAllowed())
			{
				this.model.setObstacleNode(arg0.getX(), arg0.getY());
			} else
			{
				this.model.setNewBoid(arg0.getX(), arg0.getY());
			}

		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0)
	{
		// TODO Auto-generated method stub

	}

}
