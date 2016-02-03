package Voxel;

import java.awt.Color;

public enum Material 
{
	Fire, Water, Earth;
	
	Material()
	{
		
	}
	
	public Color getColor()
	{
		if(this.equals(Fire))
		{
			return Color.red;
		}
		else if (this.equals(Water))
		{
			return Color.blue;
		}
		else if(this.equals(Earth))
		{
			return new Color(146,135,68);
		}
		else
			return null;
	}
}
