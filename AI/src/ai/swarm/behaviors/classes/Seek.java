package ai.swarm.behaviors.classes;

import java.util.ArrayList;

import ai.swarm.behaviors.Abstract_Behavior;
import ai.swarm.boid.Boid;
import ai.swarm.util.Vector;

public class Seek extends Abstract_Behavior
{
	private boolean seeking;
	protected float seekingOutDistance = 110.0f;
	protected float seekSeperationDistance = 70.0f;
	protected float seekingForce = 0.2f;

	private Vector seekingVector = new Vector();
	private Vector mouseVector = new Vector();

	public Seek(ArrayList<Boid> boids)
	{
		super(boids);
	}

	public Vector seeking(Boid active)
	{
		seekingVector.reset();
		if (this.seeking)
		{
			float distance = Boid.distance(active.pos, this.mouseVector);
			if (distance < this.seekingOutDistance
					&& distance > seekSeperationDistance)
			{
				seekingVector = Boid.distanceVector(this.mouseVector,
						active.pos);
				seekingVector.normalize();
				seekingVector.multiplyWith(Boid.MAXSPEED);
				seekingVector = Boid.distanceVector(seekingVector, active.vel);
				seekingVector.multiplyWith(seekingForce);
			}
		}
		return seekingVector;
	}

	public void setSeeking()
	{
		if (this.seeking)
		{
			this.seeking = false;
		} else
		{
			this.seeking = true;
		}

	}

	public void setMouseVector(int x, int y)
	{
		this.mouseVector.x = x;
		this.mouseVector.y = y;
	}

	public boolean isSeeking()
	{
		return this.seeking;
	}

	public void setSeeking(boolean b)
	{
		this.seeking = b;

	}

	public void setSeekingDistance(float seekingDistance)
	{
		if (seekingDistance > this.seekSeperationDistance)
			this.seekingOutDistance = seekingDistance;
	}

	public void setSeekSeperationDistance(float seekSeperationDistance)
	{
		if (seekSeperationDistance < this.seekingOutDistance)
			this.seekSeperationDistance = seekSeperationDistance;
	}

	public void setSeekingForce(float seekingForce)
	{
		this.seekingForce = seekingForce;
	}

	public int getSeekingDistance()
	{
		return (int) seekingOutDistance;
	}

	public int getSeekSeperationDistance()
	{
		return (int) seekSeperationDistance;
	}

	public int getSeekingForce()
	{
		return (int) seekingForce;
	}

}
