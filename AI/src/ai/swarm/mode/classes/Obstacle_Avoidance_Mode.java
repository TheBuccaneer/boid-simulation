package ai.swarm.mode.classes;

import java.util.ArrayList;

import ai.swarm.behaviors.Abstract_Behavior;
import ai.swarm.behaviors.classes.Flock;
import ai.swarm.behaviors.classes.Obstacle;
import ai.swarm.boid.Boid;
import ai.swarm.mode.MyMode;

public class Obstacle_Avoidance_Mode extends MyMode
{

	private final static int INIT_ARRAY_SIZE = 100;
	private Obstacle obstacle;

	public Obstacle_Avoidance_Mode()
	{
		ArrayList<Boid> boids = new ArrayList<Boid>(INIT_ARRAY_SIZE);
		Abstract_Behavior.initBoids(boids, INIT_ARRAY_SIZE);

		this.flock = new Flock(boids);
		this.obstacle = new Obstacle(boids);
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
			}
			super.updateVector.addWith(obstacle.avoiding(active));
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

	public void setObstacleNode(int x, int y)
	{
		if (this.flock.isFlocking())
		{
			this.obstacle.setObstacleNode(x, y);
		}
	}

	public Abstract_Behavior getBehavior()
	{
		return this.obstacle;
	}

}
