package Fighter;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

public class BodyPart 
{
	ArrayList<Joint> jointList;
	String name;
	int length;
	double angle;
	float x,y, endx, endy;
	
	public BodyPart(String name, int length)
	{
		this.name=name;
		this.length = length;
		jointList = new ArrayList<Joint>();
		angle = .5*Math.PI;
	}
	
	public void setjointList(ArrayList<Joint> jointList)
	{
		this.jointList = jointList;
	}
	
	public void draw(Graphics2D g, Rectangle camera)
	{
		if(!name.equalsIgnoreCase("head"))
		{
			g.setColor(Color.black);
			g.drawLine((int)x-camera.x, (int)y-camera.y, (int)endx-camera.x, (int)endy-camera.y);
			for(Joint j:jointList)
			{
				j.bp2.draw(g,camera);
			}
		}
		else
		{
			g.setColor(Color.black);
			g.fillOval((int)(x-camera.x)-length/2, (int)(y-camera.y)-length, length, length);
		}
	}
	
	public void updateJoints()
	{
		endx = (int)(x+Math.cos(angle)*length+.5);
		endy = (int)(y+Math.sin(angle)*length+.5);
		for(Joint j:jointList)
		{
			if(!name.equalsIgnoreCase("chest"))
				j.update(endx, endy);
			else
			{
				if(j.bp2.name.contains("Thigh"))
					j.update(endx, endy);
				else
					j.update(x, y);
					
			}
		}
	}
}
