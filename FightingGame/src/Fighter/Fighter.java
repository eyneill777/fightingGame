package Fighter;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.ArrayList;

import Main.Game;
import Main.GameObject;
import Main.Tools;
import Stage.StageObject;
import Stage.StageObjectType;

public class Fighter extends GameObject
{
	BodyPart chest;
	public Rectangle hitBox;
	Game game;
	int totalHeight;
	String stance;
	Position falling, standing;
	boolean resting = false, jumpLocked = false;
	ArrayList<String> movementList = new ArrayList<String>();
	int jumpsLeft = 2;
	Animation currentAnimation = null;
	int animationCount = 0;
	long animationCounter = 0;
	Animation running = new Animation("run");
	
	public Fighter(float x, float y, Game game)
	{
		super(x,y);
		this.x = x;
		this.y = y;
		this.game = game;
		falling = new Position("falling");
		standing = new Position("standing");
		chest = new BodyPart("chest", 50);
		chest.x = x;
		chest.y = y;
		chest.setjointList(createBody());
		hitBox = null;
		setStance(falling);
		totalHeight = 0;
	}
	
	public void draw(Graphics2D g, Rectangle camera)
	{
		if(Main.DeveloperOptions.drawFighterHitboxes)
		{
			g.setColor(Color.red);
			g.fillRect(hitBox.x-camera.x, hitBox.y-camera.y, hitBox.width, hitBox.height); 
		}
		
		chest.draw(g, camera);
	}
	
	private ArrayList<Joint> createBody()
	{
		ArrayList<Joint> jointList = new ArrayList<Joint>();
		
		BodyPart h = new BodyPart("Head", 20);
		jointList.add(new Joint(chest, h,x,y));
		
		h = new BodyPart("Right Arm", 20);
		BodyPart i = new BodyPart("Right Wrist", 20);
		h.jointList.add(new Joint(h,i,x,y+h.length));
		jointList.add(new Joint(chest, h,x,y));
		
		h = new BodyPart("Left Arm", 20);
		i = new BodyPart("Left Wrist",20);
		h.jointList.add(new Joint(h,i,x,y+h.length));
		jointList.add(new Joint(chest, h,x,y));
		
		h = new BodyPart("Left Thigh",30);
		i = new BodyPart("Left Shin",30);
		h.jointList.add(new Joint(h,i,x,y+chest.length+h.length));
		jointList.add(new Joint(chest, h,x,y+chest.length));
		
		h = new BodyPart("Right Thigh",30);
		i = new BodyPart("Right Shin",30);
		h.jointList.add(new Joint(h,i,x,y+chest.length+h.length));
		jointList.add(new Joint(chest, h,x,y+chest.length));
		
		return jointList;
	}
	
	public void update()
	{
		chest.x = x;
		chest.y = y;
		chest.updateJoints();
		updateHitBox();
		updateMovement();
		updateAnimation();
		updatePhysics();
	}
	
	private void updateAnimation()
	{
		if(System.currentTimeMillis()-animationCounter>=1000/60 && currentAnimation!=null)
		{
			if(animationCount>=currentAnimation.frames.size())
			{
				animationCount = 0;
			}
			setStance(currentAnimation.frames.get(animationCount));
			animationCount++;
			animationCounter = System.currentTimeMillis();
		}
	}
	
	private void updateMovement()
	{
		if(resting && movementList.size() == 0)
			xVel*=.8;
		for(int i = 0;i<movementList.size();i++)
		{
			if(movementList.get(i).equalsIgnoreCase("left"))
			{
				if(resting)
				{
					xVel=-10;
					setAnimation(running);
				}
				else xVel-=.1;
			}
			else
			{
				if(resting)
				{
					xVel=10;
					setAnimation(running);
				}
				else xVel+=.1;
			}
		}
	}
	
	public void updatePhysics()
	{		
		updateCollisions();
		//apply gravity
		if(!resting && lastGravityUpdate <= System.currentTimeMillis()-1000)
			yVel+=Game.gravity;
		//check if velocity is faster than terminal velocity
		if (xVel > terminalVelocity)
			xVel = terminalVelocity;
		else if(xVel<-terminalVelocity)
			xVel = -terminalVelocity;
		if(yVel > terminalVelocity)
			yVel = terminalVelocity;
		else if(yVel < -terminalVelocity)
			yVel = -terminalVelocity;
		//update position
		x+=xVel;
		y+=yVel;
	}
	
	private void updateCollisions()
	{
		ArrayList<StageObject> sectorsToCheck = new ArrayList<StageObject>();
		//get Hitbox dimensions and position in sector scale
		double sectorPosX = ((double)hitBox.x)/game.stage.sectorSize-.2;
		double sectorPosY = ((double)hitBox.y)/game.stage.sectorSize-.2;
		double sectorWidth = ((double)hitBox.width)/game.stage.sectorSize+.4;
		double sectorHeight = ((double)hitBox.height)/game.stage.sectorSize+.4;
		
		//calculate sectors to be checked
		for(int dy = (int)(sectorPosY);dy<sectorPosY+sectorHeight;dy++)
		{
			for(int dx = (int)(sectorPosX);dx<sectorPosX+sectorWidth;dx++)
			{
				if(dx>=0 && dy>=0 && dx<game.stage.width && dy<game.stage.height)
					sectorsToCheck.add(game.stage.sectors[dx][dy]);
			}
		}
		
		//check sectors
		boolean hasFloor = false;
		for(StageObject o:sectorsToCheck)
		{
			o.checked = true;
			if(o.type == StageObjectType.WALL)
			{
				if(o.shape.intersects(hitBox))
				{
					if(checkHorizontalCollisions(o))
						break;
					if(yVel >= -10 && checkDownWallCollision(o))
					{
						hasFloor = true;
						if(stance.equalsIgnoreCase("falling"))
						{
							yVel=0;
							resting = true;
							setStance(standing);
							y = (o.y)*game.stage.sectorSize-totalHeight+20;
						}
					}
					if(checkUpWallCollision(o))
					{
						y = o.y*game.stage.sectorSize+game.stage.sectorSize+20;
						yVel *=-.7;
						break;
					}
				}
				if(checkDownWallCollision(o))
					hasFloor = true;
			}
		}
		//check for floor (will need update for sub objects)
		if(!hasFloor)
		{
			setStance(falling);
		}
	}
	
	private boolean checkHorizontalCollisions(StageObject o)
	{
		if(xVel > 0)
		{
			if(checkRightWallCollision(o))
			{
				xVel*=-.3;
				x = o.x*game.stage.sectorSize-hitBox.width/2;
				return true;
			}
		}
		else if(xVel < 0)
		{
			if(checkLeftWallCollision(o))
			{
				x = o.x*game.stage.sectorSize+game.stage.sectorSize+hitBox.width/2;
				xVel*=-.3;
				return true;
			}
		}
		return false;
	}
	
	private boolean checkRightWallCollision(StageObject o)
	{
		if(o.shape.contains(new Rectangle(hitBox.x+hitBox.width, hitBox.y+20, 10, hitBox.height-40)))//right
		{
			return true;
		}
		return false;
	}
	private boolean checkLeftWallCollision(StageObject o)
	{
		if(o.shape.contains(new Rectangle(hitBox.x-10, hitBox.y+20, 10, hitBox.height-40)))//left
		{
			return true;
		}
		return false;
	}
	private boolean checkUpWallCollision(StageObject o)
	{
		if(o.shape.intersects(new Rectangle((int) x, hitBox.y-10, 1, 10)))//up
		{
			return true;
		}
		return false;
	}
	private boolean checkDownWallCollision(StageObject o)
	{
		if(o.shape.intersects(new Rectangle((int)x, hitBox.y+hitBox.height, 1, 10)))//down
		{
			return true;
		}
		return false;
	}
	
	private void updateHitBox()
	{
		totalHeight = (int)(getJoint("left knee").bp2.endy-y)+20;
		int t = (int)(getJoint("right knee").bp2.endy-y)+20;
		if(t>totalHeight)
			totalHeight = t;
		hitBox = new Rectangle((int)x-10, (int)y-20, 20, totalHeight);
	}
	
	public Joint getJoint(String joint)
	{
		Joint j = null;
		if(joint.equalsIgnoreCase("left shoulder"))
		{
			for(int i=0;i<chest.jointList.size();i++)
			{
				if(chest.jointList.get(i).bp2.name.equalsIgnoreCase("left arm"))
					j = chest.jointList.get(i);
			}
		}
		else if(joint.equalsIgnoreCase("right shoulder"))
		{
			for(int i=0;i<chest.jointList.size();i++)
			{
				if(chest.jointList.get(i).bp2.name.equalsIgnoreCase("right arm"))
					j = chest.jointList.get(i);
			}
		}
		else if(joint.equalsIgnoreCase("left hip"))
		{
			for(int i=0;i<chest.jointList.size();i++)
			{
				if(chest.jointList.get(i).bp2.name.equalsIgnoreCase("left thigh"))
					j = chest.jointList.get(i);
			}
		}
		else if(joint.equalsIgnoreCase("right hip"))
		{
			for(int i=0;i<chest.jointList.size();i++)
			{
				if(chest.jointList.get(i).bp2.name.equalsIgnoreCase("right thigh"))
					j = chest.jointList.get(i);
			}
		}
		else if(joint.equalsIgnoreCase("left elbow"))
		{
			for(int i=0;i<chest.jointList.size();i++)
			{
				if(chest.jointList.get(i).bp2.name.equalsIgnoreCase("left arm"))
					j = chest.jointList.get(i).bp2.jointList.get(0);
			}
		}
		else if(joint.equalsIgnoreCase("right elbow"))
		{
			for(int i=0;i<chest.jointList.size();i++)
			{
				if(chest.jointList.get(i).bp2.name.equalsIgnoreCase("right arm"))
					j = chest.jointList.get(i).bp2.jointList.get(0);
			}
		}
		else if(joint.equalsIgnoreCase("left knee"))
		{
			for(int i=0;i<chest.jointList.size();i++)
			{
				if(chest.jointList.get(i).bp2.name.equalsIgnoreCase("left thigh"))
					j = chest.jointList.get(i).bp2.jointList.get(0);
			}
		}
		else if(joint.equalsIgnoreCase("right knee"))
		{
			for(int i=0;i<chest.jointList.size();i++)
			{
				if(chest.jointList.get(i).bp2.name.equalsIgnoreCase("right thigh"))
					j = chest.jointList.get(i).bp2.jointList.get(0);
			}
		}
		return j;
	}
	
	public void setStance(Position position)
	{
		stance = position.positionName;
		chest.angle = position.cAngle;
		getJoint("Right Shoulder").angle=position.rShoulder;
		getJoint("Left Shoulder").angle=position.lShoulder;
		getJoint("Right Hip").angle=position.rHip;
		getJoint("Left Hip").angle=position.lHip;
		getJoint("Right Elbow").angle=position.rElbow;
		getJoint("Left Elbow").angle=position.lElbow;
		getJoint("Right Knee").angle=position.rKnee;
		getJoint("Left Knee").angle=position.lKnee;
		chest.updateJoints();
		if(stance.equalsIgnoreCase("falling"))
		{
			resting = false;
			currentAnimation = null;
		}
		else if(stance.contains("run"))
		{
			resting = true;
		}
		if(hitBox != null)
		{
			int t = hitBox.height;
			updateHitBox();
			t-=hitBox.height;
			y+=t;
		}
	}
	
	public void setMovement(String direction)
	{
		if(!movementList.contains(direction))
			movementList.add(direction);
	}
	
	public void stopMovement(String direction)
	{
		movementList.remove(direction);
	}
	
	public void jump()
	{
		if(!jumpLocked)
		{
			yVel = -15;
			setStance(falling);
			jumpLocked = true;
		}
	}
	
	private void setAnimation(Animation animation)
	{
		animationCount = 0;
		this.currentAnimation = animation;
	}
}
