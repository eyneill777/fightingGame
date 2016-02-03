package Stage;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

public class StageObject 
{
	public Rectangle shape;
	public int x,y,width,height, screenX, screenY;
	public StageObjectType type;
	public boolean checked = false;
	
	public StageObject(int x, int y, int width, int height, StageObjectType type)
	{
		shape = new Rectangle(x*width, y*height, width, height);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.type = type;
	}
	
	public void draw(Graphics2D g)
	{
		if(type == StageObjectType.WALL)
		{
			g.setColor(Color.GRAY);
			g.fillRect(screenX, screenY, shape.width, shape.height);
		}
		if(checked)
		{
			g.setColor(new Color(0,0,255,70));
			g.fillRect(screenX, screenY, shape.width, shape.height);
		}
		checked = false;
	}
}
