package ai.swarm.mode.classes;

import java.util.ArrayList;

import ai.swarm.behaviors.Abstract_Behavior;
import ai.swarm.behaviors.classes.Flee;
import ai.swarm.behaviors.classes.Flock;
import ai.swarm.behaviors.classes.Seek;
import ai.swarm.boid.Boid;
import ai.swarm.mode.MyMode;

public class Seek_and_Flee_Mode extends MyMode
{

	private final static int INIT_ARRAY_SIZE = 20;

	private Flee flee;
	private Seek seek;

	public Seek_and_Flee_Mode()
	{
		ArrayList<Boid> boids = new ArrayList<Boid>(INIT_ARRAY_SIZE);
		Abstract_Behavior.initBoids(boids, INIT_ARRAY_SIZE);

		this.flee = new Flee(boids);
		this.seek = new Seek(boids);
		this.flock = new Flock(boids);
	}

	@Override
	public void update()
	{
		for (int i = 0; i < this.flock.boids.size(); i++)
		{
			Boid active = this.flock.boids.get(i);
			super.updateVector.reset();
			if (this.flock.isFlocking())
			{
				super.updateVector.addWith(flock.flocking(active));
				super.updateVector.addWith(flee.fleeing(active));
				super.updateVector.addWith(seek.seeking(active));
			}

			active.vel.addWith(updateVector);
			active.vel.limit();
			active.pos.addWith(active.vel);
			Boid.correctPosition(active);
		}

	}

	@Override
	public ArrayList<Boid> getBoids()
	{
		return this.flock.boids;
	}

	public void setSeeking()
	{
		if (this.isFlocking())
			this.seek.setSeeking();
	}

	public void setMouseVector(int x, int y)
	{
		if (this.seek.isSeeking())
		{
			this.seek.setMouseVector(x, y);
		} else if (this.flee.isFleeing())
		{
			this.flee.setMouseVector(x, y);
		}
	}

	public void setFleeing()
	{
		if (this.isFlocking())
			this.flee.setFleeing();
	}

	public boolean isSeeking()
	{
		return this.seek.isSeeking();
	}

	public void setFleeing(boolean b)
	{
		this.flee.setFleeing(b);
	}

	public boolean isFleeing()
	{
		return this.flee.isFleeing();
	}

	public void setSeeking(boolean b)
	{
		this.seek.setSeeking(b);
	}

	public void setScattering()
	{
		this.flock.setScattering();
	}
}
