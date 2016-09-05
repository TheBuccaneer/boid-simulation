package ai.gui.view.classes;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ai.MainApp;
import ai.gui.model.Model;
import ai.gui.view.MyInput;
import ai.gui.view.MyView;
import ai.swarm.mode.MyMode;
import ai.swarm.mode.classes.Obstacle_Avoidance_Mode;
import ai.swarm.mode.classes.Open_World_Mode;
import ai.swarm.mode.classes.Path_Finding_Mode;
import ai.swarm.mode.classes.Pursuing_Mode;
import ai.swarm.mode.classes.Reynold_Rules_Mode;
import ai.swarm.mode.classes.Seek_and_Flee_Mode;
import ai.swarm.mode.classes.Wanderer_Mode;

/*
 * Inhalte für das Linke JPanel mit Adjustment Überschrift
 */
public class Adjustment extends JPanel implements MyView, MyInput
{

	private Model model;
	private JLabel flockingLabel = new JLabel("Start/Stop Flocking: 'd'");
	private JLabel sepDistance = new JLabel("SepDistance: '1'");
	private JLabel neighborhood = new JLabel("Nachbarschaft: '2'");
	private JLabel scatteringLabel = new JLabel("Scattering: 's'");
	private JLabel seekingLabel = new JLabel("Seeking: 'a'");
	private JLabel fleeingLabel = new JLabel("Fleeing: 'y'");
	private JLabel wanderingLabel = new JLabel("Wandering: 'w'");
	private JLabel pathLabel = new JLabel("PathFinding: 'r'");
	private JLabel obstacleLabel = new JLabel("Obstacle: 't'");
	private JLabel mouseClickLabel = new JLabel("Boid mit Mausklick");
	private JLabel innerSeekLabel = new JLabel("Seek Inn. Grenze: '4'");
	private JLabel outerSeekLabel = new JLabel("Seeking Max Radius: '3'");
	private JLabel fleeingForceLabel = new JLabel("Fleeing-Distanz: '5'");

	public Adjustment(Model model)
	{
		this.model = model;
		this.model.addMyView(this);
		this.model.addMyInput(this);
		this.setBackground(MainApp.BACK);
		this.initLabels();
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	}

	@Override
	public void changed()
	{
		MyMode mode = model.getMyMode();
		this.removeAll();
		this.flockingLabel.setForeground(MainApp.FRONT);
		if (mode instanceof Reynold_Rules_Mode)
		{
			this.add(flockingLabel);
			this.add(scatteringLabel);
			this.add(sepDistance);
			this.add(neighborhood);
			scatteringLabel.setEnabled(false);
			this.revalidate();
		} else if (mode instanceof Seek_and_Flee_Mode)
		{
			this.add(flockingLabel);
			this.add(scatteringLabel);
			this.add(seekingLabel);
			this.add(fleeingLabel);
			scatteringLabel.setEnabled(false);
			seekingLabel.setEnabled(false);
			fleeingLabel.setEnabled(false);
			this.revalidate();
		} else if (mode instanceof Wanderer_Mode)
		{
			this.add(flockingLabel);
			this.add(wanderingLabel);
			wanderingLabel.setEnabled(false);
			this.revalidate();
		} else if (mode instanceof Path_Finding_Mode)
		{
			this.add(flockingLabel);
			this.add(scatteringLabel);
			scatteringLabel.setEnabled(false);
			this.revalidate();
		} else if (mode instanceof Obstacle_Avoidance_Mode)
		{
			this.add(flockingLabel);
			this.revalidate();
		} else if (mode instanceof Pursuing_Mode)
		{
			this.removeAll();
			this.add(flockingLabel);
			this.revalidate();
		} else if (mode instanceof Open_World_Mode)
		{
			this.add(flockingLabel);
			this.add(scatteringLabel);
			this.add(seekingLabel);
			this.add(fleeingLabel);
			this.add(wanderingLabel);
			this.add(pathLabel);
			this.add(obstacleLabel);

			this.scatteringLabel.setEnabled(false);
			this.seekingLabel.setEnabled(false);
			this.fleeingLabel.setEnabled(false);
			this.wanderingLabel.setEnabled(false);
			this.pathLabel.setEnabled(false);
			this.obstacleLabel.setEnabled(false);

			this.add(mouseClickLabel);
			this.add(sepDistance);
			this.add(neighborhood);
			this.add(outerSeekLabel);
			this.add(innerSeekLabel);
			this.add(fleeingForceLabel);
			this.add(this.initButton());
		}
	}

	@Override
	public void input(MyMode mode)
	{
		if (mode instanceof Reynold_Rules_Mode)
			this.reynolds(mode);
		else if (mode instanceof Seek_and_Flee_Mode)
			this.seekAFlee(mode);
		else if (mode instanceof Wanderer_Mode)
			this.wander(mode);
		else if (mode instanceof Path_Finding_Mode)
			this.path(mode);
		else if (mode instanceof Obstacle_Avoidance_Mode)
			this.obstacle(mode);
		else if (mode instanceof Pursuing_Mode)
			this.pursue(mode);
		else if (mode instanceof Open_World_Mode)
			this.open(mode);

	}

	private void open(MyMode mode)
	{
		Open_World_Mode owm = (Open_World_Mode) mode;
		Color c;

		if (owm.isFlocking())
		{
			c = Color.GREEN;

			this.scatteringLabel.setEnabled(true);
			this.seekingLabel.setEnabled(true);
			this.fleeingLabel.setEnabled(true);
			this.wanderingLabel.setEnabled(true);
			this.pathLabel.setEnabled(true);
			this.obstacleLabel.setEnabled(true);

			if (mode.isScattering())
				this.scatteringLabel.setForeground(Color.GREEN);
			else
				this.scatteringLabel.setForeground(MainApp.FRONT);
			if (mode.isWandering())
				this.wanderingLabel.setForeground(Color.GREEN);
			else
				this.wanderingLabel.setForeground(MainApp.FRONT);

			if (owm.seek.isSeeking())
			{
				this.seekingLabel.setForeground(Color.GREEN);
				this.fleeingLabel.setForeground(MainApp.FRONT);
			} else
			{
				this.seekingLabel.setForeground(MainApp.FRONT);
			}
			if (owm.flee.isFleeing())
			{
				this.fleeingLabel.setForeground(Color.GREEN);
				this.seekingLabel.setForeground(MainApp.FRONT);
			} else
			{

				this.fleeingLabel.setForeground(MainApp.FRONT);
			}
			if (owm.path.isPathFinding())
				this.pathLabel.setForeground(Color.GREEN);
			else
				this.pathLabel.setForeground(MainApp.FRONT);
			if (owm.obstacle.isObstacleAllowed())
			{
				this.obstacleLabel.setForeground(Color.GREEN);
			} else
			{
				this.obstacleLabel.setForeground(MainApp.FRONT);
			}

		} else
		{
			c = MainApp.FRONT;
			this.scatteringLabel.setEnabled(false);
			this.seekingLabel.setEnabled(false);
			this.fleeingLabel.setEnabled(false);
			this.wanderingLabel.setEnabled(false);
			this.pathLabel.setEnabled(false);
			this.obstacleLabel.setEnabled(false);
		}
		this.flockingLabel.setForeground(c);

	}

	private void pursue(MyMode mode)
	{
		Pursuing_Mode pfm = (Pursuing_Mode) mode;
		Color c;
		if (pfm.isFlocking())
		{
			c = Color.GREEN;

			if (pfm.isScattering())
				scatteringLabel.setForeground(Color.GREEN);
			else
				scatteringLabel.setForeground(MainApp.FRONT);
		} else
		{
			c = MainApp.FRONT;
			scatteringLabel.setEnabled(false);
		}
		this.flockingLabel.setForeground(c);

	}

	private void obstacle(MyMode mode)
	{
		Obstacle_Avoidance_Mode pfm = (Obstacle_Avoidance_Mode) mode;
		Color c;
		if (pfm.isFlocking())
		{
			c = Color.GREEN;

			if (pfm.isScattering())
				scatteringLabel.setForeground(Color.GREEN);
			else
				scatteringLabel.setForeground(MainApp.FRONT);
		} else
		{
			c = MainApp.FRONT;
			scatteringLabel.setEnabled(false);
		}
		this.flockingLabel.setForeground(c);

	}

	private void path(MyMode mode)
	{
		Path_Finding_Mode pfm = (Path_Finding_Mode) mode;
		Color c;
		if (pfm.isFlocking())
		{
			c = Color.GREEN;

			if (pfm.isScattering())
				scatteringLabel.setForeground(Color.GREEN);
			else
				scatteringLabel.setForeground(MainApp.FRONT);
		} else
		{
			c = MainApp.FRONT;
			scatteringLabel.setEnabled(false);
		}
		this.flockingLabel.setForeground(c);

	}

	private void wander(MyMode mode)
	{
		Wanderer_Mode w = (Wanderer_Mode) mode;
		Color c;
		if (w.isFlocking())
		{
			c = Color.GREEN;
			wanderingLabel.setEnabled(true);

			if (w.isWandering())
				wanderingLabel.setForeground(Color.GREEN);
			else
				wanderingLabel.setForeground(MainApp.FRONT);
		} else
		{
			c = MainApp.FRONT;
			wanderingLabel.setEnabled(false);
		}
		this.flockingLabel.setForeground(c);

	}

	private void seekAFlee(MyMode mode)
	{
		Color c;
		Seek_and_Flee_Mode saf = (Seek_and_Flee_Mode) mode;

		if (saf.isFlocking())
		{
			c = Color.GREEN;
			scatteringLabel.setEnabled(true);
			seekingLabel.setEnabled(true);
			fleeingLabel.setEnabled(true);

			if (saf.isScattering())
				scatteringLabel.setForeground(Color.GREEN);
			else
				scatteringLabel.setForeground(MainApp.FRONT);
			if (saf.isFleeing())
			{
				fleeingLabel.setForeground(Color.GREEN);
				seekingLabel.setForeground(MainApp.FRONT);
			} else if (saf.isSeeking())
			{
				fleeingLabel.setForeground(MainApp.FRONT);
				seekingLabel.setForeground(Color.GREEN);
			}
		} else
		{
			c = MainApp.FRONT;
			scatteringLabel.setEnabled(false);
			seekingLabel.setEnabled(false);
			fleeingLabel.setEnabled(false);
		}
		flockingLabel.setForeground(c);

	}

	private void reynolds(MyMode mode)
	{
		Color c;
		Reynold_Rules_Mode rrm = (Reynold_Rules_Mode) mode;
		if (rrm.isFlocking())
		{
			c = Color.GREEN;
			scatteringLabel.setEnabled(true);
			if (rrm.isScattering())
				scatteringLabel.setForeground(Color.GREEN);
			else
				scatteringLabel.setForeground(MainApp.FRONT);
		} else
		{
			c = MainApp.FRONT;
			scatteringLabel.setEnabled(false);
		}
		flockingLabel.setForeground(c);
	}

	private void initLabels()
	{
		flockingLabel.setForeground(MainApp.FRONT);
		scatteringLabel.setForeground(MainApp.FRONT);
		fleeingLabel.setForeground(MainApp.FRONT);
		seekingLabel.setForeground(MainApp.FRONT);
		wanderingLabel.setForeground(MainApp.FRONT);
		pathLabel.setForeground(MainApp.FRONT);
		obstacleLabel.setForeground(MainApp.FRONT);
		sepDistance.setForeground(MainApp.FRONT);
		neighborhood.setForeground(MainApp.FRONT);
		mouseClickLabel.setForeground(MainApp.FRONT);
		innerSeekLabel.setForeground(MainApp.FRONT);
		outerSeekLabel.setForeground(MainApp.FRONT);
		fleeingForceLabel.setForeground(MainApp.FRONT);
	}

	public JButton initButton()
	{
		JButton b = new JButton("reset");
		b.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				model.getMyMode().close();
				model.setNewMode(new Open_World_Mode());

			}
		});
		return b;
	}

}