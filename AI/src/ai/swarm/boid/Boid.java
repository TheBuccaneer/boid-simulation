package ai.swarm.boid;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

import javax.imageio.ImageIO;

import ai.gui.view.classes.Show;
import ai.swarm.util.Vector;

public class Boid
{
	public int node;
	public Vector pos;
	public Vector vel;
	public Color color;

	private static final Random RAND = new Random();
	public static final int MAXSPEED = 3;
	private static final int NUMBER_OF_COLORS = 6;
	private static final Color[] COLORS = new Color[NUMBER_OF_COLORS];
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;

	public BufferedImage image;
	static
	{

		COLORS[0] = Color.RED;
		COLORS[1] = Color.YELLOW;
		COLORS[2] = Color.BLUE;
		COLORS[3] = Color.CYAN;
		COLORS[4] = Color.GREEN;
		COLORS[5] = Color.MAGENTA;
	}

	public Boid(int randomColor)
	{
		color = COLORS[randomColor % NUMBER_OF_COLORS];
		setIntVelocity(this);
		setInitPos(this);

	}

	public Boid()
	{

	}

	public Boid(int x, int y)
	{
		this(x * y);
		this.pos = new Vector(x, y);
		setIntVelocity(this);
		this.color = Color.WHITE;
	}

	public Vector getPos()
	{
		return pos;
	}

	public Vector getVel()
	{
		return vel;
	}

	public Color getColor()
	{
		return color;
	}

	public void setPos(Vector pos)
	{
		this.pos = pos;
	}

	public void setVel(Vector vel)
	{
		this.vel = vel;
	}

	public void setColor(Color color)
	{
		this.color = color;
	}

	public static void correctPosition(Boid agent)
	{
		if (agent.pos.x > WIDTH + Show.DIAMETER)
			agent.pos.x = 0 - Show.DIAMETER;
		else if (agent.pos.x < 0 - Show.DIAMETER)
			agent.pos.x = WIDTH + Show.DIAMETER;

		if (agent.pos.y > HEIGHT + Show.DIAMETER)
			agent.pos.y = 0 - Show.DIAMETER;
		else if (agent.pos.y < 0 - Show.DIAMETER)
			agent.pos.y = HEIGHT + Show.DIAMETER;
	}

	public static float distance(Vector one, Vector two)
	{
		float x = one.x - two.x;
		float y = one.y - two.y;

		return (float) Math.sqrt(x * x + y * y);
	}

	public static Vector distanceVector(Vector one, Vector two)
	{
		return new Vector(one.x - two.x, one.y - two.y);
	}

	public static void setInitPos(Boid boid)
	{
		boid.pos = new Vector(RAND.nextInt(WIDTH), RAND.nextInt(HEIGHT));
	}

	public static void setIntVelocity(Boid boid)
	{
		int x = RAND.nextInt(MAXSPEED);
		int y = RAND.nextInt(MAXSPEED);

		while (x == 0 && y == 0)
		{
			y = RAND.nextInt(MAXSPEED);
			x = RAND.nextInt(MAXSPEED);
		}

		if (RAND.nextInt() % 2 == 0)
			x *= -1;
		if (RAND.nextInt(2) % 2 == 0)
			y *= -1;

		boid.vel = new Vector(x, y);
		boid.vel.limit();
	}

	public static boolean isInSight(Boid a, Boid temp)
	{
		Vector distance = Boid.distanceVector(temp.pos, a.pos);

		float f1 = a.vel.x * distance.x + a.vel.y + distance.y;
		float f2 = a.vel.betrag() * distance.betrag();
		if (f2 == 0)
			return false;
		float degree = (float) Math.toDegrees(Math.acos(f1 / f2));

		if (degree < 120)
			return true;
		return false;

	}
}