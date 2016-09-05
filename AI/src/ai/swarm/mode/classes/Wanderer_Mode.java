package ai.swarm.mode.classes;

import java.util.ArrayList;
import java.util.Random;

import ai.swarm.behaviors.Abstract_Behavior;
import ai.swarm.behaviors.classes.Flock;
import ai.swarm.behaviors.classes.Wander;
import ai.swarm.boid.Boid;
import ai.swarm.mode.MyMode;

public class Wanderer_Mode extends MyMode
{
	private Wander wander;

	private final static int INIT_ARRAY_SIZE = 100;

	protected final static Random RAND = new Random();

	public Wanderer_Mode()
	{
		ArrayList<Boid> boids = new ArrayList<Boid>(INIT_ARRAY_SIZE);
		Abstract_Behavior.initBoids(boids, INIT_ARRAY_SIZE);

		this.flock = new Flock(boids);
		this.wander = new Wander(boids, flock);
	}

	@Override
	public void update()
	{
		this.wander
				.setNextWanderPosition(RAND.nextInt(this.flock.boids.size()));
		for (int i = 0; i < this.flock.boids.size(); i++)
		{
			Boid active = this.flock.boids.get(i);
			super.updateVector.reset();
			if (this.flock.isFlocking())
			{
				super.updateVector.addWith(flock.flocking(active));
				super.updateVector.addWith(wander.wandering2(active, i));
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

	public void setWandering()
	{
		if (this.flock.isFlocking())
			this.wander.setWandering();
	}

	public boolean isWandering()
	{
		return this.wander.isWandering();
	}

	public void setScattering()
	{
	}

}
