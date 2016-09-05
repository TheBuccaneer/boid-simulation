package ai.swarm.behaviors.classes;

import java.util.ArrayList;

import ai.swarm.behaviors.Abstract_Behavior;
import ai.swarm.boid.Boid;
import ai.swarm.util.Vector;

public class PathFind extends Abstract_Behavior
{

	private float radius = 40;
	private float pathForce = 5;
	private boolean pathfinding;

	private Vector pathFindingVector = new Vector();
	protected ArrayList<Vector> path = new ArrayList<Vector>();

	public PathFind(ArrayList<Boid> boids)
	{
		super(boids);
		path = new ArrayList<Vector>();
	}

	public Vector pathfinding(Boid active)
	{
		this.pathFindingVector.reset();
		if (path.size() > 0)
		{
			if (active.node == this.path.size())
				active.node = 0;
			Vector c = this.path.get(active.node);

			if (c != null)
			{
				if (Boid.distance(c, active.pos) < this.radius)
				{
					active.node++;
				}
				this.pathFindingVector = Boid.distanceVector(c, active.pos);
				this.pathFindingVector.limit();
				this.pathFindingVector.devideWith(pathForce);
			}
		}

		return this.pathFindingVector;

	}

	public void setPathNode(int x, int y)
	{
		path.add(new Vector(x, y));

	}

	public boolean isPathSet()
	{
		return path.size() > 0;
	}

	public int getPathSize()
	{
		return this.path.size();
	}

	public Vector getPathPos(int i)
	{
		return this.path.get(i);
	}

	public void setPathFinding()
	{
		if (this.pathfinding)
		{
			this.pathfinding = false;
		} else
		{
			this.pathfinding = true;
		}

	}

	public boolean isPathFinding()
	{
		return this.pathfinding;
	}

	public int pathFindingRadius()
	{
		return (int) this.radius;
	}

	public void setRadius(float value)
	{
		this.radius = value;

	}

	public void setForce(float value)
	{
		this.pathForce = value;

	}

}
