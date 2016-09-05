package ai.swarm.behaviors.classes;

import java.util.ArrayList;

import ai.swarm.behaviors.Abstract_Behavior;
import ai.swarm.boid.Boid;
import ai.swarm.util.Vector;

public class Flock extends Abstract_Behavior
{
	protected float seperationDistance = 20;
	protected float neighborhood_distance = 150;
	protected float seperationForce = 10.0f;
	protected float cohesionForce = 0.03f;
	protected float alignmentForce = 7.0f;
	private Vector flockingVector;
	private Boid active;

	public Flock(ArrayList<Boid> boids)
	{
		super(boids);
		this.flockingVector = new Vector();
	}

	public boolean isFlocking()
	{
		return this.flocking;
	}

	public void setFlocking()
	{
		if (this.flocking)
		{
			this.flocking = false;
		} else
		{
			this.flocking = true;
		}
	}

	public Vector flocking(Boid active)
	{
		this.flockingVector.reset();
		this.active = active;

		this.seperation();
		this.cohesion();
		this.alignment();

		return this.flockingVector;
	}

	private void seperation()
	{
		int count = 0;
		float distance = 0.0f;

		Boid temp;
		Vector v;
		Vector seperation = new Vector();

		for (int i = 0; i < this.boids.size(); i++)
		{
			temp = this.boids.get(i);

			distance = Boid.distance(active.pos, temp.pos);
			if (distance > 0 && distance <= seperationDistance)
			{
				v = Boid.distanceVector(active.pos, temp.pos);
				v.normalize();
				v.devideWith(distance);
				seperation.addWith(v);
				count++;
			}
		}

		if (count > 0)
			seperation.devideWith(count);

		seperation.multiplyWith(this.seperationForce);

		this.flockingVector.addWith(seperation);

	}

	private void cohesion()
	{
		int count = 0;
		float distance = 0.0f;

		Boid temp;
		Vector cohesion = new Vector();

		for (int i = 0; i < this.boids.size(); i++)
		{
			temp = this.boids.get(i);

			distance = Boid.distance(active.pos, temp.pos);

			if (distance > 0 && distance <= neighborhood_distance)
			{
				cohesion.addWith(temp.pos);
				++count;
			}
		}

		if (count > 0)
		{
			cohesion.devideWith(count);

		}

		cohesion = seek(cohesion, active);
		cohesion.multiplyWith(this.cohesionForce);
		if (this.scattering)
		{
			cohesion.multiplyWith(-1);
		}
		this.flockingVector.addWith(cohesion);

	}

	private void alignment()
	{
		int count = 0;
		float distance = 0.0f;

		Boid temp;
		Vector alignment = new Vector();

		for (int i = 0; i < this.boids.size(); i++)
		{
			temp = this.boids.get(i);

			distance = Boid.distance(active.pos, temp.pos);

			if (distance > 0 && distance <= neighborhood_distance)
			{
				alignment.addWith(temp.vel);
				count++;
			}
		}

		if (count > 0)
		{
			alignment.devideWith(count);
			if (alignment.betrag() > 0.03)
			{
				alignment.normalize();
				alignment.multiplyWith(0.03f);
			}

		}

		alignment.multiplyWith(this.alignmentForce);

		this.flockingVector.addWith(alignment);
	}

	private Vector seek(Vector cohesion, Boid a)
	{
		Vector des = Boid.distanceVector(cohesion, a.pos);
		Vector direction = Boid.distanceVector(des, a.vel);
		direction.limit();

		return direction;
	}

	public void setScattering()
	{
		if (this.scattering)
		{
			this.scattering = false;
		} else
		{
			this.scattering = true;
		}

	}

	public int getSeperationDistance()
	{
		return (int) this.seperationDistance;
	}

	public int getNeiborhoodDistance()
	{
		return (int) this.neighborhood_distance;
	}

	public boolean isScattering()
	{
		return this.scattering;
	}

	public void setSeperationDistance(float seperationDistance)
	{
		this.seperationDistance = seperationDistance;
	}

	public void setNeighborhood_distance(float neighborhood_distance)
	{
		this.neighborhood_distance = neighborhood_distance;
	}

	public void setSeperationForce(float seperationForce)
	{
		this.seperationForce = seperationForce;
	}

	public void setCohesionForce(float cohesionForce)
	{
		this.cohesionForce = cohesionForce;
	}

	public void setAlignmentForce(float alignmentForce)
	{
		this.alignmentForce = alignmentForce;
	}

	public void addBoid(int x, int y)
	{
		if (super.boids.size() < 180)
		{
			super.boids.add(new Boid(x, y));
		}

	}

}
