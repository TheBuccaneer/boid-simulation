package ai.swarm.mode.classes;

import java.util.ArrayList;

import ai.swarm.behaviors.Abstract_Behavior;
import ai.swarm.behaviors.classes.Flee;
import ai.swarm.behaviors.classes.Flock;
import ai.swarm.behaviors.classes.Pursue;
import ai.swarm.boid.Boid;
import ai.swarm.mode.MyMode;

public class Pursuing_Mode extends MyMode
{

	private Pursue pursue;
	private Flee flee;

	private final static int INIT_ARRAY_SIZE = 10;

	public Pursuing_Mode()
	{
		ArrayList<Boid> boids = new ArrayList<Boid>(INIT_ARRAY_SIZE);
		Abstract_Behavior.initBoids(boids, INIT_ARRAY_SIZE);

		this.flock = new Flock(boids);
		this.pursue = new Pursue(boids);
		this.flee = new Flee(boids);
		this.flee.setFleeing();
		this.flee.setFleeingForce(4.0f);
	}

	@Override
	public void update()
	{
		this.pursue.pursuing();
		for (int i = 0; i < this.flock.boids.size(); i++)
		{
			Boid active = this.flock.boids.get(i);
			super.updateVector.reset();

			if (this.flock.isFlocking())
			{
				super.updateVector.addWith(flock.flocking(active));
			}
			if (active.equals(Pursue.active) && pursue.isPursuing())
			{
				for (int j = 0; j < pursue.getPursuerSize(); j++)
				{
					flee.setMouseVector(pursue.getPursuerPos(j).getX(), pursue
							.getPursuerPos(j).getY());
					updateVector.addWith(flee.fleeing(active));
				}
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

	public void setPursuer(int x, int y)
	{
		this.pursue.setPursuer(x, y);

	}

	public Abstract_Behavior getBehavior()
	{
		return this.pursue;
	}
}
