package ai.swarm.mode.classes;

import java.util.ArrayList;

import ai.swarm.behaviors.Abstract_Behavior;
import ai.swarm.behaviors.classes.Flock;
import ai.swarm.boid.Boid;
import ai.swarm.mode.MyMode;

public class Reynold_Rules_Mode extends MyMode
{

	private final static int INIT_ARRAY_SIZE = 40;

	public Reynold_Rules_Mode()
	{

		ArrayList<Boid> boids = new ArrayList<Boid>(INIT_ARRAY_SIZE);
		Abstract_Behavior.initBoids(boids, INIT_ARRAY_SIZE);
		super.flock = new Flock(boids);
	}

	public void update()
	{
		for (int i = 0; i < this.flock.boids.size(); i++)
		{
			Boid active = this.flock.boids.get(i);

			if (this.flock.isFlocking())
			{
				active.vel.addWith(flock.flocking(active));
			}
			active.vel.limit();
			active.pos.addWith(active.vel);
			Boid.correctPosition(active);
		}
	}

	public ArrayList<Boid> getBoids()
	{
		return this.flock.boids;
	}

	public void setScattering()
	{
		if (this.flock.isFlocking())
			this.flock.setScattering();
	}

}
