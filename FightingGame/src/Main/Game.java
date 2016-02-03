package Main;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import Fighter.Fighter;
import Fighter.Player;
import Stage.Stage;

public class Game 
{
	public Stage stage;
	Player player;
	public static float gravity = (float) .3;
	public Main main;
	ArrayList<Fighter> fighterList = new ArrayList<Fighter>();//player will always be the first one
	public Rectangle camera;
	
	public Game(Main main)
	{
		stage = new Stage();
		player = new Player((stage.height-2)*stage.sectorSize, 500, this);
		fighterList.add(player);
		fighterList.add(new Fighter(800, 600, this));
		this.main = main;
		camera = new Rectangle(Main.windowDim.width,Main.windowDim.height);
		camera.x = 0;
		camera.y = 0;
	}
	
	public void draw(Graphics2D g)
	{
		stage.draw(g,camera);
		for(int i = 0;i<fighterList.size();i++)
		{
			if(camera.intersects(fighterList.get(i).hitBox))
				fighterList.get(i).draw(g,camera);
		}
	}
	
	public void update()
	{
		updateCamera();
		for(int i = 0;i<fighterList.size();i++)
		{
			fighterList.get(i).update();
		}
		stage.update(camera);
	}
	
	public void updateCamera()
	{
		camera.x = (int) (player.x-camera.width/2+.5);
		camera.y = (int) (player.y-camera.height/2+.5);
	}
}
