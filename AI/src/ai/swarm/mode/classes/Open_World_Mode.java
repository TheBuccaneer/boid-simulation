package ai.swarm.mode.classes;

import java.util.ArrayList;

import ai.swarm.behaviors.Abstract_Behavior;
import ai.swarm.behaviors.classes.Flee;
import ai.swarm.behaviors.classes.Flock;
import ai.swarm.behaviors.classes.Obstacle;
import ai.swarm.behaviors.classes.PathFind;
import ai.swarm.behaviors.classes.Pursue;
import ai.swarm.behaviors.classes.Seek;
import ai.swarm.behaviors.classes.Wander;
import ai.swarm.boid.Boid;
import ai.swarm.mode.MyMode;
import ai.swarm.mode.classes.openWorld.MyDialog;
import ai.swarm.util.Vector;

public class Open_World_Mode extends MyMode
{

	public Flee flee;
	public Obstacle obstacle;
	public PathFind path;
	public Pursue pursue;
	public Seek seek;
	public Wander wander;

	private MyDialog myDialog;

	private final static int INIT_ARRAY_SIZE = 100;

	public Open_World_Mode()
	{
		ArrayList<Boid> boids = new ArrayList<Boid>(INIT_ARRAY_SIZE);
		Abstract_Behavior.initBoids(boids, INIT_ARRAY_SIZE);

		this.flock = new Flock(boids);
		this.flee = new Flee(boids);
		this.obstacle = new Obstacle(boids);
		this.path = new PathFind(boids);
		this.pursue = new Pursue(boids);
		this.seek = new Seek(boids);
		this.wander = new Wander(boids, flock);

		myDialog = new MyDialog(this);
	}

	@Override
	public void update()
	{
		if (this.flock.isFlocking())
			this.pursue.pursuing();
		for (int i = 0; i < this.flock.boids.size(); i++)
		{
			Boid active = this.flock.boids.get(i);
			super.updateVector.reset();

			if (this.flock.isFlocking())
			{
				super.updateVector.addWith(flock.flocking(active));
				if (this.obstacle.isObstacleAllowed())
					super.updateVector.addWith(obstacle.avoiding(active));
				if (this.path.isPathFinding())
					super.updateVector.addWith(path.pathfinding(active));
				super.updateVector.addWith(flee.fleeing(active));
				super.updateVector.addWith(seek.seeking(active));
				super.updateVector.addWith(wander.wandering(active, i));
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

	public boolean isWandering()
	{
		return this.wander.isWandering();
	}

	@Override
	public ArrayList<Boid> getBoids()
	{
		return this.flock.boids;
	}

	public Flock getFlock()
	{
		return super.flock;
	}

	public void close()
	{
		this.myDialog.setVisible(false);
		this.myDialog = null;

	}

	public void setNewBoid(int x, int y)
	{
		this.flock.addBoid(x, y);

	}

	public void setSeekingDistanceDraw()
	{
		this.seek.setSeekingDistanceDraw();

	}

	public void setSeekSeperationDistanceDraw()
	{
		this.seek.setSeekSeperationDistanceDraw();
	}

	public void setWandering()
	{
		if (this.flock.isFlocking())
			this.wander.setWandering();
	}

	public boolean isSeekingDistanceDraw()
	{
		return this.seek.isSeekingDistanceDraw();
	}

	public boolean isSeekSeperationDistanceDraw()
	{
		return this.seek.isSeekSeperationDistanceDraw();
	}

	public int getSeekingDistance()
	{
		return this.seek.getSeekingDistance();
	}

	public int getSeekSeperationDistance()
	{
		return this.seek.getSeekSeperationDistance();
	}

	public boolean isSeeking()
	{
		return this.seek.isSeeking();
	}

	public void setFleeing(boolean b)
	{
		this.flee.setFleeing(b);

	}

	public void setSeeking(boolean b)
	{
		this.seek.setSeeking(b);
	}

	public void setFleeing()
	{
		if (this.flock.isFlocking())
			this.flee.setFleeing();

	}

	public void setSeeking()
	{
		if (this.flock.isFlocking())
			this.seek.setSeeking();

	}

	public boolean isFleeing()
	{
		return this.flee.isFleeing();
	}

	public void setMouseVector(int x, int y)
	{
		if (this.seek.isSeeking())
			this.seek.setMouseVector(x, y);
		else if (this.flee.isFleeing())
			this.flee.setMouseVector(x, y);
	}

	public void setFleeingForceDraw()
	{
		this.flee.setFleeingForceDraw();

	}

	public boolean isFleeingForceDraw()
	{
		return this.flee.isFleeingForceDraw();
	}

	public int getFleeingDistance()
	{
		return this.flee.getFleeingDistance();
	}

	public boolean isPathFinding()
	{
		return this.path.isPathFinding();
	}

	public void setPathFinding()
	{
		this.path.setPathFinding();
	}

	public void setPathNode(int x, int y)
	{
		this.path.setPathNode(x, y);
	}

	public boolean isPathSet()
	{
		if (!this.path.isPathFinding())
			return false;
		return this.path.isPathSet();
	}

	public int getPathRadius()
	{
		return this.path.pathFindingRadius();
	}

	public int getPathSize()
	{
		return this.path.getPathSize();
	}

	public Vector getPathPos(int i)
	{
		return this.path.getPathPos(i);
	}

	public void setObstacleAllowed()
	{
		this.obstacle.setObstacleAllowed();
	}

	public boolean isObstacleAllowed()
	{
		return this.obstacle.isObstacleAllowed();
	}

	public void setObstacleNode(int x, int y)
	{
		this.obstacle.setObstacleNode(x, y);
	}

	public int getObstacleSize()
	{
		return this.obstacle.getObstacleSize();
	}

	public int getObstacleRadius()
	{
		return this.obstacle.getRadius();
	}

	public Vector getObstaclePosition(int i)
	{
		return this.obstacle.getObstaclePos(i);
	}

}
