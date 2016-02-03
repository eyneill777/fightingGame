package Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Main extends JFrame implements Runnable
{
	public static Dimension windowDim;
	BufferedImage buffer;
	Game game;
	long lastUpdate;
	Graphics2D g;
	
	public static void main(String args[])
	{
		windowDim = Toolkit.getDefaultToolkit().getScreenSize();
		Main f = new Main();
		f.setSize(windowDim);
		f.setUndecorated(true);
		f.setDefaultCloseOperation(EXIT_ON_CLOSE);
		f.setVisible(true);
	}
	
	public Main()
	{
		buffer = new BufferedImage(windowDim.width, windowDim.height, BufferedImage.TYPE_INT_ARGB);
		game = new Game(this);
		addKeyListener(game.player);
		lastUpdate = System.currentTimeMillis();
		g = (Graphics2D) buffer.getGraphics();
		
		Thread thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() 
	{
		for(;;)
		{
			if(System.currentTimeMillis()-lastUpdate>=1000/60)
			{
				update();
				repaint();
				lastUpdate = System.currentTimeMillis();
			}
		}
	}
	
	public void paint(Graphics gfx)
	{
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		game.draw(g);
		
		gfx.drawImage(buffer, 0, 0, null);
	}
	
	public void update()
	{
		game.update();
	}
}
