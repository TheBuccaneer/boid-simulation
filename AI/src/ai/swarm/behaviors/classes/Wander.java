package ai.swarm.behaviors.classes;

import java.util.ArrayList;

import ai.swarm.behaviors.Abstract_Behavior;
import ai.swarm.boid.Boid;
import ai.swarm.util.Vector;

public class Wander extends Abstract_Behavior
{
	private float wanderAngle = 0;
	private int nextWanderPosition = 0;
	private boolean wandering;
	private Vector wanderingVector = new Vector();

	public Wander(ArrayList<Boid> boids, Flock flock)
	{
		super(boids);
	}

	public boolean isWandering()
	{
		return this.wandering;
	}

	public void setWandering()
	{
		if (this.wandering)
		{
			this.wandering = false;
		} else
		{
			this.wandering = true;
		}
	}

	public Vector wandering(Boid active, int i)
	{
		this.wanderingVector.reset();

		int nextWanderPosition = RAND.nextInt(this.boids.size());
		if (this.wandering && nextWanderPosition == i)
		{
			Vector center = active.vel.clone(1);
			center.normalize();
			center.multiplyWith(10);

			Vector displace = new Vector(0, -1);
			displace.multiplyWith(10);

			float length = displace.betrag();

			displace.x = (float) (Math.cos(wanderAngle) * length);
			displace.y = (float) (Math.sin(wanderAngle) * length);

			wanderAngle += Math.random() * 2;

			center.addWith(displace);
			wanderingVector.addWith(center);
		}

		return this.wanderingVector;
	}

	public Vector wandering2(Boid active, int i)
	{
		this.wanderingVector.reset();

		if (this.wandering && nextWanderPosition == i)
		{
			wanderingVector = active.vel;
			wanderingVector.normalize();
			wanderingVector.multiplyWith(3);

			Vector displacement = new Vector(2 * RAND.nextInt(2) - 1,
					(2 * RAND.nextInt(2) - 1));
			displacement.multiplyWith(0.5f);

			wanderingVector.addWith(displacement);

		}
		return wanderingVector;
	}

	public void setNextWanderPosition(int pos)
	{
		this.nextWanderPosition = pos;
	}

}
