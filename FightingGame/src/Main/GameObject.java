package Main;

import java.awt.Graphics;

public class GameObject 
{	
	public float x,y;
	public double xVel, yVel;
	public long lastGravityUpdate;
	public int terminalVelocity = 50;
	
	public GameObject(float x, float y)
	{
		this.x = x;
		this.y = y;
		xVel = 0;
		yVel = 0;
		lastGravityUpdate = System.currentTimeMillis();
	}
	
	public GameObject(int x, int y, double xVel, double yVel)
	{
		this.x = x;
		this.y = y;
		this.xVel = xVel;
		this.yVel = yVel;
	}
	
	public void update()
	{
		updatePhysics();
	}
	
	public void updatePhysics()
	{
		if(lastGravityUpdate <= System.currentTimeMillis()-1000)
			yVel+=Game.gravity;
		x+=xVel;
		y+=yVel;
		if (xVel > terminalVelocity)
			xVel = terminalVelocity;
		else if(xVel<-terminalVelocity)
			xVel = -terminalVelocity;
		if(yVel > terminalVelocity)
			yVel = terminalVelocity;
		else if(yVel < -terminalVelocity)
			yVel = -terminalVelocity;
	}
	
	public void draw(Graphics g)
	{
		
	}
}
