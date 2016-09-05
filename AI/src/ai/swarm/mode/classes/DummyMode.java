package ai.swarm.mode.classes;

import java.util.ArrayList;

import ai.swarm.behaviors.Abstract_Behavior;
import ai.swarm.behaviors.classes.Flock;
import ai.swarm.boid.Boid;
import ai.swarm.mode.MyMode;

/*
 * Dummy Klasse, welcher als Vorauswahl dient, um NullPointerException zu vermeiden
 */
public class DummyMode extends MyMode
{

	public ArrayList<Boid> boids = new ArrayList<Boid>();

	public DummyMode()
	{
		super();
		super.flock = new Flock(new ArrayList<Boid>());
	}

	public void update()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<Boid> getBoids()
	{
		return boids;
	}

	public void setSepDistDraw()
	{
	}

	public Abstract_Behavior getBehavior()
	{
		return flock;
	}

}
