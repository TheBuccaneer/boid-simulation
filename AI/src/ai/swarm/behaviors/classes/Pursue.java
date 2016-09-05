package ai.swarm.behaviors.classes;

import java.util.ArrayList;

import ai.swarm.behaviors.Abstract_Behavior;
import ai.swarm.boid.Boid;
import ai.swarm.boid.Pursuer;
import ai.swarm.util.Vector;

public class Pursue extends Abstract_Behavior
{

	private Vector pursuingVector = new Vector();
	private Seek seek;
	private ArrayList<Pursuer> pursuer = new ArrayList<Pursuer>();
	private Pursuer p;

	public static Boid active;

	public Pursue(ArrayList<Boid> boids)
	{
		super(boids);
		this.seek = new Seek(boids);
		this.seek.setSeeking();

		active = boids.get(0);
	}

	public void pursuing()
	{
		if (this.pursuer.size() > 0)
		{
			for (int i = 0; i < pursuer.size(); i++)
			{
				p = pursuer.get(i);
				this.pursuingVector.reset();

				float dynamicDistance = Boid.distance(active.pos, p.pos)
						/ Boid.MAXSPEED;

				this.pursuingVector.addWith(active.vel);
				this.pursuingVector.multiplyWith(dynamicDistance);
				this.pursuingVector.addWith(active.pos);

				this.pursuingVector = this.calculate(this.pursuingVector);

				p.vel.addWith(this.pursuingVector);
				p.vel.limit();
				p.vel.multiplyWith(0.6f);
				p.pos.addWith(p.vel);
				Boid.correctPosition(p);

				if (Boid.distance(active.pos, p.pos) < 5)
				{
					boids.remove(0);
					active = boids.get(0);
				}
			}
		}
	}

	public Vector calculate(Vector active)
	{
		Vector calculate = new Vector();

		calculate = Boid.distanceVector(active, p.pos);
		calculate.normalize();
		calculate.multiplyWith(Boid.MAXSPEED);
		calculate.devideWith(2);

		return calculate;
	}

	public void setPursuer(int x, int y)
	{
		this.pursuer.add(new Pursuer(x, y));

	}

	public boolean isPursuing()
	{
		return this.pursuer.size() > 0;
	}

	public int getPursuerSize()
	{
		return this.pursuer.size();
	}

	public Vector getPursuerPos(int i)
	{
		return this.pursuer.get(i).pos;
	}
}
