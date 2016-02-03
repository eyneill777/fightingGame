package Main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import Voxel.Material;

public class DeveloperOptions implements MouseListener, MouseMotionListener
{
	//Developer Options
	public static boolean voxelTest = true;
	public static boolean drawCollisionMap = false;
	public static boolean drawFighterHitboxes = false;
	
	//Class Variables
	private static boolean mousePressed = false;
	private static Material voxelTestMaterial = Material.Fire;
	
	public static void update()
	{
		if(voxelTest)
		{
			if(mousePressed)
			{
				
			}
		}
	}
	
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) 
	{
		mousePressed = true;
	}
	@Override
	public void mouseReleased(MouseEvent e) 
	{
		mousePressed = false;
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
