package ai.swarm.behaviors;

import java.util.ArrayList;
import java.util.Random;

import ai.swarm.boid.Boid;
import ai.swarm.util.Vector;

/*
 * Oberklasse für die Verhaltensweisen der Boids. Alle Verhaltensweisen sind in behaviors.classes zu finden
 */
public abstract class Abstract_Behavior
{

	protected boolean flocking;
	protected boolean scattering;
	private boolean sepDistDraw;
	private boolean neighborhoodDraw;

	protected boolean seekingDistanceDraw;
	protected boolean seekSeperationDistanceDraw;
	protected boolean seekingForceDraw;
	protected boolean fleeingForceDraw;

	public ArrayList<Boid> boids = new ArrayList<Boid>();
	protected final static Random RAND = new Random();

	public Abstract_Behavior(ArrayList<Boid> boids)
	{
		this.boids = boids;
	}

	public static void initBoids(ArrayList<Boid> boids, int size)
	{
		for (int i = 0; i < size; i++)
		{
			boids.add(new Boid(i));
		}
	}

	public boolean isSeekingDistanceDraw()
	{
		return seekingDistanceDraw;
	}

	public void setSeekingDistanceDraw()
	{
		if (this.seekingDistanceDraw)
		{
			this.seekingDistanceDraw = false;
		} else
		{
			this.seekingDistanceDraw = true;
		}
	}

	public boolean isSeekSeperationDistanceDraw()
	{
		return seekSeperationDistanceDraw;
	}

	public void setSeekSeperationDistanceDraw()
	{
		if (this.seekSeperationDistanceDraw)
		{
			this.seekSeperationDistanceDraw = false;
		} else
		{
			this.seekSeperationDistanceDraw = true;
		}
	}

	public boolean isSeekingForceDraw()
	{
		return seekingForceDraw;
	}

	public void setSeekingForceDraw()
	{
		if (this.seekingForceDraw)
		{
			this.seekingForceDraw = false;
		} else
		{
			this.seekingForceDraw = true;
		}
	}

	public void setSepDistDraw()
	{
		if (this.sepDistDraw)
		{
			this.sepDistDraw = false;
		} else
		{
			this.sepDistDraw = true;
		}
	}

	public void setNeighborhoodDraw()
	{
		if (this.neighborhoodDraw)
		{
			this.neighborhoodDraw = false;
		} else
		{
			this.neighborhoodDraw = true;
		}
	}

	public void setFleeingForceDraw()
	{
		if (this.fleeingForceDraw)
		{
			this.fleeingForceDraw = false;
		} else
		{
			this.fleeingForceDraw = true;
		}
	}

	public boolean isFleeingForceDraw()
	{
		return this.fleeingForceDraw;
	}

	public boolean isSepDistDraw()
	{
		return this.sepDistDraw;
	}

	public boolean isNeighborhoodDraw()
	{
		return this.neighborhoodDraw;
	}

	public int getSeperationDistance()
	{
		return 0;
	}

	public boolean isPathSet()
	{
		return false;
	}

	public Vector getPathPos(int i)
	{
		return null;
	}

	public int getPathSize()
	{
		return -1;
	}

	public int pathFindingRadius()
	{
		return -1;
	}

	public boolean isObstacleSet()
	{
		return false;
	}

	public int getObstacleSize()
	{
		return -1;
	}

	public void setObstacleNode(int x, int y)
	{

	}

	public int obstacleRadius()
	{
		// TODO Auto-generated method stub
		return -1;
	}

	public Vector getObstaclePos(int i)
	{
		return null;
	}

	public boolean isPursuing()
	{
		return false;
	}

	public int getPursuerSize()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	public Vector getPursuerPos(int i)
	{
		return null;
	}

	public int getNeiborhoodDistance()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	public int getSeekingDistance()
	{
		return 0;
	}

	public int getSeekSeperationDistance()
	{
		return 0;
	}

	public int getSeekingForce()
	{
		return 0;
	}

	public boolean isObstacleAllowed()
	{
		return false;
	}

	public void setObstacleAllowed()
	{
	}

}
