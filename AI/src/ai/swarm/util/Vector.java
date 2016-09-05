package ai.swarm.util;

import ai.swarm.boid.Boid;

public class Vector
{

	public float x;
	public float y;

	public Vector(float x, float y)
	{
		this.x = x;
		this.y = y;
	}

	public Vector()
	{
	}

	public void reset()
	{
		this.x = 0;
		this.y = 0;
	}

	public void addWith(Vector v)
	{
		this.x += v.x;
		this.y += v.y;
	}

	public void subWith(Vector v)
	{
		this.x -= v.x;
		this.y -= v.y;
	}

	public void devideWith(Vector v)
	{
		this.x /= v.x;
		this.y /= v.y;
	}

	public void devideWith(float f)
	{
		if (f == 0)
			return;
		this.x /= f;
		this.y /= f;
	}

	public void multiplyWith(Vector v)
	{
		this.x *= v.x;
		this.y *= v.y;
	}

	public void multiplyWith(float f)
	{
		this.x *= f;
		this.y *= f;
	}

	public void limit()
	{
		if (betrag() > Boid.MAXSPEED)
		{
			this.normalize();
			this.multiplyWith(Boid.MAXSPEED);
		}
	}

	public float betrag()
	{
		return (float) Math.sqrt(x * x + y * y);
	}

	public void normalize()
	{
		float betrag = betrag();
		if (betrag > 0)
			this.devideWith(betrag);
	}

	public int getX()
	{
		return (int) this.x;
	}

	public int getY()
	{
		return (int) this.y;
	}

	public Vector clone(float z)
	{
		return new Vector(this.x * z, this.y * z);
	}
}
