package ai.swarm.mode;

import java.util.ArrayList;

import ai.swarm.behaviors.Abstract_Behavior;
import ai.swarm.behaviors.classes.Flock;
import ai.swarm.boid.Boid;
import ai.swarm.util.Vector;

/*
 * Basisklasse für alle Mode Klassen, welche die Behaviors implementieren
 */
public abstract class MyMode
{

	protected Flock flock;
	protected Vector updateVector = new Vector();

	public abstract void update();

	public MyMode()
	{
	}

	public void setFlocking()
	{
		this.flock.setFlocking();
	}

	public void setScattering()
	{
		this.flock.setScattering();
	}

	public void setSeeking()
	{

	}

	public boolean isFleeingForceDraw()
	{
		return false;
	}

	public abstract ArrayList<Boid> getBoids();

	public void setMouseVector(int x, int y)
	{

	}

	public Abstract_Behavior getBehavior()
	{
		return flock;
	}

	public void setSepDistDraw()
	{
		this.flock.setSepDistDraw();

	}

	public void setFleeing()
	{

	}

	public boolean isSeeking()
	{
		// TODO Auto-generated method stub
		return false;
	}

	public void setFleeing(boolean b)
	{
		// TODO Auto-generated method stub

	}

	public boolean isFleeing()
	{
		// TODO Auto-generated method stub
		return false;
	}

	public void setSeeking(boolean b)
	{
		// TODO Auto-generated method stub
	}

	public void setWandering()
	{
		// TODO Auto-generated method stub

	}

	public void setPathNode(int x, int y)
	{
		// TODO Auto-generated method stub

	}

	public void setPathFinding()
	{

	}

	public void setObstacleNode(int x, int y)
	{
		// TODO Auto-generated method stub

	}

	public void setPursuer(int x, int y)
	{

	}

	public boolean isFlocking()
	{
		return this.flock.isFlocking();
	}

	public boolean isScattering()
	{
		return this.flock.isScattering();
	}

	public void setNeighborhoodDraw()
	{
		this.flock.setNeighborhoodDraw();

	}

	public void close()
	{
		// TODO Auto-generated method stub

	}

	public void setNewBoid(int x, int y)
	{
		// TODO Auto-generated method stub

	}

	public void setSeekingDistanceDraw()
	{
		// TODO Auto-generated method stub

	}

	public void setSeekSeperationDistanceDraw()
	{
		// TODO Auto-generated method stub

	}

	public boolean isSeekingDistanceDraw()
	{
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isSeekSeperationDistanceDraw()
	{
		// TODO Auto-generated method stub
		return false;
	}

	public int getSeekingDistance()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	public int getSeekSeperationDistance()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	public void setFleeingForceDraw()
	{
		// TODO Auto-generated method stub

	}

	public int getFleeingForce()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	public int getFleeingDistance()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean isWandering()
	{
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isPathFinding()
	{
		return false;

	}

	public boolean isPathSet()
	{
		// TODO Auto-generated method stub
		return false;
	}

	public int getPathRadius()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	public int getPathSize()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	public Vector getPathPos(int i)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public void setObstacleAllowed()
	{
		// TODO Auto-generated method stub

	}

	public boolean isObstacleAllowed()
	{
		// TODO Auto-generated method stub
		return false;
	}

	public int getObstacleSize()
	{
		return 0;
	}

	public int getObstacleRadius()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	public Vector getObstaclePosition(int i)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
