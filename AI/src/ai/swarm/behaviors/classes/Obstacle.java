package ai.swarm.behaviors.classes;

import java.util.ArrayList;

import ai.swarm.behaviors.Abstract_Behavior;
import ai.swarm.boid.Boid;
import ai.swarm.util.Vector;

public class Obstacle extends Abstract_Behavior
{

	private boolean obstacleAllowed;
	private int obstacle_radius = 40;
	private Vector obstacleVector = new Vector();
	protected ArrayList<Vector> obstacles = new ArrayList<Vector>();

	public Obstacle(ArrayList<Boid> boids)
	{
		super(boids);
	}

	public Vector avoiding(Boid active)
	{
		this.obstacleVector.reset();

		Vector ahead = new Vector();
		Vector center = new Vector();
		Vector temp = new Vector();

		ahead.addWith(active.vel);
		ahead.multiplyWith(Boid.MAXSPEED * 3);
		ahead.addWith(active.pos);

		float distance = Float.MAX_VALUE;

		for (int i = 0; i < this.obstacles.size(); i++)
		{
			temp = this.obstacles.get(i);
			if (distance > Boid.distance(ahead, temp))
			{
				distance = Boid.distance(ahead, temp);
				center = temp;
			}
		}

		if (distance < obstacle_radius || distance / 2 < obstacle_radius
				|| Boid.distance(active.pos, center) < obstacle_radius)
		{
			obstacleVector = Boid.distanceVector(ahead, center);
			obstacleVector.normalize();
			obstacleVector.multiplyWith(Boid.MAXSPEED / 2);
		}

		return obstacleVector;
	}

	public boolean isObstacleSet()
	{
		return this.obstacles.size() > 0;
	}

	public int getObstacleSize()
	{
		return this.obstacles.size();
	}

	public void setObstacleNode(int x, int y)
	{
		this.obstacles.add(new Vector(x, y));

	}

	public int obstacleRadius()
	{
		return this.obstacle_radius;
	}

	public Vector getObstaclePos(int i)
	{
		return this.obstacles.get(i);
	}

	public boolean isObstacleAllowed()
	{
		return obstacleAllowed;
	}

	public void setObstacleAllowed()
	{
		if (this.obstacleAllowed)
		{
			this.obstacleAllowed = false;
		} else
		{
			this.obstacleAllowed = true;
		}
	}

	public int getRadius()
	{
		return this.obstacle_radius;
	}

}
