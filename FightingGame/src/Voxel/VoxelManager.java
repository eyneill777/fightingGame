package Voxel;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class VoxelManager 
{
	ArrayList<Voxel> voxelList = new ArrayList<Voxel>();
	
	public VoxelManager()
	{
		
	}
	
	public void update()
	{
		for(int i = 0;i<voxelList.size();i++)
		{
			voxelList.get(i).update();
		}
	}
	
	public void draw(Graphics2D g)
	{
		for(int i = 0;i<voxelList.size();i++)
		{
			voxelList.get(i).draw(g);
		}
	}
}
