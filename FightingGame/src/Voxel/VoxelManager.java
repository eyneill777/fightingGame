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
		System.out.println(voxelList.size());
	}
	
	public void draw(Graphics2D g)
	{
		for(int i = 0;i<voxelList.size();i++)
		{
			voxelList.get(i).draw(g);
		}
	}
	
	public void spawnVoxel(Material material, float x, float y, float xVel, float yVel)
	{
		voxelList.add(new Voxel(x, y, xVel, yVel, material));
	}
	
	public void removeVoxel(Voxel voxel)
	{
		voxelList.remove(voxel);
	}
}
