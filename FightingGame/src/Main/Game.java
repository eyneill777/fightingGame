package Main;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import Fighter.Fighter;
import Fighter.Player;
import Stage.Stage;
import Voxel.VoxelManager;

public class Game 
{
	public Stage stage;
	Player player;
	VoxelManager voxelManager;
	public static float gravity = (float) .3;
	public Main main;
	ArrayList<Fighter> fighterList = new ArrayList<Fighter>();//player will always be the first one
	public Rectangle camera;
	
	public Game(Main main)
	{
		stage = new Stage();
		player = new Player((stage.height-2)*stage.sectorSize, 500, this);
		voxelManager = new VoxelManager();
		fighterList.add(player);
		fighterList.add(new Fighter(800, 600, this));
		this.main = main;
		camera = new Rectangle(Main.windowDim.width,Main.windowDim.height);
		camera.x = 0;
		camera.y = 0;
		DeveloperOptions devOptions = new DeveloperOptions();
		main.addMouseListener(devOptions);
		main.addMouseMotionListener(devOptions);
	}
	
	public void draw(Graphics2D g)
	{
		stage.draw(g,camera);
		for(int i = 0;i<fighterList.size();i++)
		{
			if(camera.intersects(fighterList.get(i).hitBox))
				fighterList.get(i).draw(g,camera);
		}
		voxelManager.draw(g);
	}
	
	public void update()
	{
		updateCamera();
		for(int i = 0;i<fighterList.size();i++)
		{
			fighterList.get(i).update();
		}
		stage.update(camera);
		voxelManager.update();
		DeveloperOptions.update(this);
	}
	
	public void updateCamera()
	{
		camera.x = (int) (player.x-camera.width/2+.5);
		camera.y = (int) (player.y-camera.height/2+.5);
	}
}
