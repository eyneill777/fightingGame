package Fighter;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Main.Game;

public class Player extends Fighter implements KeyListener
{

	public Player(float x, float y, Game game) 
	{
		super(x, y, game);
		this.x = x;
		this.y = y;
		this.game = game;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		if(e.getKeyCode() == KeyEvent.VK_A)
			setMovement("left");
		else if(e.getKeyCode() == KeyEvent.VK_D)
			setMovement("right");
		else if(e.getKeyCode() == KeyEvent.VK_W)
			jump();
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{
		if(e.getKeyCode() == KeyEvent.VK_A)
			stopMovement("left");
		else if(e.getKeyCode() == KeyEvent.VK_D)
			stopMovement("right");
		else if(e.getKeyCode() == KeyEvent.VK_W)
			jumpLocked = false;
	}
	
}
