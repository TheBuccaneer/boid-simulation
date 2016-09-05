package ai.swarm.mode.classes;

import java.util.ArrayList;

import ai.swarm.behaviors.Abstract_Behavior;
import ai.swarm.behaviors.classes.Flock;
import ai.swarm.behaviors.classes.PathFind;
import ai.swarm.boid.Boid;
import ai.swarm.mode.MyMode;

public class Path_Finding_Mode extends MyMode
{

	private PathFind pathfind;

	private final static int INIT_ARRAY_SIZE = 10;

	public Path_Finding_Mode()
	{
		ArrayList<Boid> boids = new ArrayList<Boid>(INIT_ARRAY_SIZE);
		Abstract_Behavior.initBoids(boids, INIT_ARRAY_SIZE);

		this.flock = new Flock(boids);
		this.pathfind = new PathFind(boids);
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

			super.updateVector.addWith(pathfind.pathfinding(active));

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

	public void setPathNode(int x, int y)
	{
		this.pathfind.setPathNode(x, y);
	}

	public Abstract_Behavior getBehavior()
	{
		return this.pathfind;
	}

	public void setPathFinding()
	{
		this.pathfind.setPathFinding();

	}

	public boolean isPathSet()
	{
		return this.pathfind.isPathSet();
	}

}
