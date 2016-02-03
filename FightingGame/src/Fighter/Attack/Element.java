package Fighter.Attack;

import Voxel.Material;

public class Element extends PowerModifier
{
	public Element(int key, String name)
	{
		super(key, name);
	}
	
	public Material combineWith(Element e)
	{
		//todo
		if(name.equals("Earth"))
		{
			
		}
		else if(name.equals("Fire"))
		{
			
		}
		else if(name.equals("Water"))
		{
			
		}
		return null;
	}
}