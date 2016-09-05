package ai.swarm.behaviors.classes;

import java.util.ArrayList;

import ai.swarm.behaviors.Abstract_Behavior;
import ai.swarm.boid.Boid;
import ai.swarm.util.Vector;

public class Flee extends Abstract_Behavior
{

	private boolean fleeing;
	protected float fleeingDistance = 80.0f;
	protected float fleeingForce = 10.0f;

	private Vector fleeingVector = new Vector();
	private Vector mouseVector = new Vector();

	public Flee(ArrayList<Boid> boids)
	{
		super(boids);
	}

	public Vector fleeing(Boid active)
	{
		this.fleeingVector.reset();
		if (this.fleeing)
		{
			float distance = Boid.distance(active.pos, this.mouseVector);
			if (distance < this.fleeingDistance)
			{

				fleeingVector = Boid.distanceVector(active.pos,
						this.mouseVector);
				fleeingVector.normalize();
				fleeingVector.multiplyWith(Boid.MAXSPEED);
				fleeingVector = Boid.distanceVector(fleeingVector, active.vel);
				fleeingVector.devideWith(fleeingForce);

			}
		}
		return this.fleeingVector;
	}

	public void setFleeing()
	{
		if (this.fleeing)
		{
			this.fleeing = false;
		} else
		{
			this.fleeing = true;
		}

	}

	public boolean isFleeing()
	{
		return this.fleeing;
	}

	public void setMouseVector(int x, int y)
	{
		this.mouseVector.x = x;
		this.mouseVector.y = y;

	}

	public void setFleeing(boolean b)
	{
		this.fleeing = b;
	}

	public void setFleeingForce(float f)
	{
		this.fleeingForce = f;
	}

	public int getFleeingDistance()
	{
		return (int) this.fleeingDistance;
	}

}
