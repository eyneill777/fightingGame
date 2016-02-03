package Voxel;

import java.awt.Graphics2D;

import Main.GameObject;
import Stage.StageObject;

public class Voxel
{
	Material material;
	float x,y;
	double xVel, yVel;

	public Voxel(float x, float y, double xVel, double yVel, Material material) 
	{
		this.material = material;
		this.x = x;
		this.y = y;
		this.xVel = xVel;
		this.yVel = yVel;
	}
	
	public void update()
	{
		checkCollisions();  //todo
		updatePhysics();	//todo
	}
	
	private void checkCollisions()
	{
		
	}
	
	private void updatePhysics()
	{
		
	}
	
	public void draw(Graphics2D g)
	{
		g.setColor(material.getColor());
		g.drawRect((int)x, (int)y, 0, 0);
	}
}
