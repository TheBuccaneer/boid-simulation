package ai.gui.model;

import java.util.ArrayList;

import ai.gui.view.MyInput;
import ai.gui.view.MyView;
import ai.swarm.mode.MyMode;
import ai.swarm.mode.classes.DummyMode;

/*
 * Modelklasse für die GUI
 */
public class Model
{

	private ArrayList<MyView> myViews;
	private ArrayList<MyInput> myInputs;
	private MyMode mode;

	public Model()
	{
		this.myViews = new ArrayList<MyView>();
		this.myInputs = new ArrayList<MyInput>();
		this.mode = new DummyMode();
	}

	public void addMyView(MyView v)
	{
		this.myViews.add(v);
	}

	private void somethingChanged()
	{
		for (MyView view : myViews)
		{
			view.changed();
		}
	}

	private void inputMade()
	{
		for (MyInput i : myInputs)
		{
			i.input(mode);
		}
	}

	public boolean isPathFinding()
	{
		return this.mode.isPathFinding();
	}

	public boolean isObstacleAllowed()
	{
		return mode.isObstacleAllowed();
	}

	public MyMode getMyMode()
	{
		return this.mode;
	}

	public void setFlocking()
	{
		this.mode.setFlocking();
		inputMade();
	}

	public void setScattering()
	{
		this.mode.setScattering();
		inputMade();
	}

	public void setSeeking()
	{
		this.mode.setSeeking();
		if (this.mode.isSeeking())
		{
			this.mode.setFleeing(false);
		}
		inputMade();
	}

	public void setMouseVector(int x, int y)
	{
		mode.setMouseVector(x, y);
		inputMade();
	}

	public void setSepDistDraw()
	{
		this.mode.setSepDistDraw();
	}

	public void setNeighborhoodDraw()
	{
		this.mode.setNeighborhoodDraw();

	}

	public void setFleeing()
	{
		this.mode.setFleeing();
		if (this.mode.isFleeing())
		{
			this.mode.setSeeking(false);
		}
		inputMade();
	}

	public void setWandering()
	{
		this.mode.setWandering();
		inputMade();

	}

	public void setPathNode(int x, int y)
	{
		this.mode.setPathNode(x, y);
		inputMade();

	}

	public void setNewMode(MyMode mode)
	{
		this.mode = mode;
		this.somethingChanged();

	}

	public void setPathFinding()
	{
		this.mode.setPathFinding();
		inputMade();

	}

	public void setObstacleNode(int x, int y)
	{
		this.mode.setObstacleNode(x, y);
		inputMade();

	}

	public void setPursuer(int x, int y)
	{
		this.mode.setPursuer(x, y);
		inputMade();
	}

	public void addMyInput(MyInput input)
	{
		myInputs.add(input);
	}

	public void setNewBoid(int x, int y)
	{
		this.mode.setNewBoid(x, y);

	}

	public void setSeekingDistanceDraw()
	{
		this.mode.setSeekingDistanceDraw();

	}

	public void setSeekSeperationDistanceDraw()
	{
		this.mode.setSeekSeperationDistanceDraw();

	}

	public void setFleeingForceDraw()
	{
		this.mode.setFleeingForceDraw();

	}

	public void setObstacleAllowed()
	{
		this.mode.setObstacleAllowed();

	}
}
