package Stage;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import Main.Main;

public class Stage 
{
	public StageObject[][] sectors;
	public int width, height;
	public int sectorSize = 300;
	
	public Stage()
	{
		loadStage();//reads stage from a file
	}
	
	public void loadStage()
	{
		Scanner in = null;
		try {
			in = new Scanner(new File("src/Stage/Stages/"+"World"+".txt"));
		} 
		catch (FileNotFoundException e) {e.printStackTrace();}
		String dimensions = in.nextLine();
		String[]  a = dimensions.split(" ");
		width = Integer.parseInt(a[0]);
		height = Integer.parseInt(a[1]);
		sectors = new StageObject[width][height];
		int index = 0;
		while(in.hasNextLine())
		{
			String line = in.nextLine();
			for(int i = 0;i<line.length();i++)
			{
				char c = line.charAt(i);
				if(c == '0')
				{
					sectors[i][index] = new StageObject(i, index, sectorSize, sectorSize, StageObjectType.AIR);
				}
				else if(c == '1')
				{
					sectors[i][index] = new StageObject(i, index,sectorSize, sectorSize,StageObjectType.WALL);
				}
			}
			index++;
		}
	}
	
	public void update(Rectangle camera)
	{
		for(int x = 0;x<width;x++)
		{
			for(int y = 0;y<height;y++)
			{
				sectors[x][y].screenX = x*sectorSize-camera.x;
				sectors[x][y].screenY = y*sectorSize-camera.y;
			}
		}
	}
	
	public void draw(Graphics2D g, Rectangle camera)
	{
		// draw background
		g.setColor(Color.white);
		g.fillRect(0, 0, Main.windowDim.width, Main.windowDim.height);
		
		//draw sectors onscreen
		for(double dx = ((double)camera.x)/sectorSize;dx<(((double)camera.x)/sectorSize+((double)camera.width)/sectorSize)+1;dx++)
		{
			int x = (int) dx;
			for(double dy = ((double)camera.y)/sectorSize;dy<(((double)camera.y)/sectorSize+((double)camera.height)/sectorSize)+1;dy++)
			{
				int y = (int) dy;
				if(x<sectors.length && y <sectors[0].length && x>=0 && y>=0)
					sectors[x][y].draw(g);
			}
		}
	}
}
