package Fighter;

public class Joint 
{
	BodyPart bp1, bp2;
	float x, y;
	double angle;
	
	public Joint(BodyPart bp1, BodyPart bp2, float x, float y)
	{
		this.bp1 = bp1;
		this.bp2 = bp2;
		this.x = x;
		this.y = y;
		angle = 1.5*Math.PI;
	}
	
	public void update(float x, float y)
	{
		this.x = x;
		this.y = y;
		bp2.x=x;
		bp2.y=y;
		while(angle >= 2*Math.PI)
			angle-=2*Math.PI;
		while(angle < 0)
			angle+=2*Math.PI;
		bp2.angle=angle;
		bp2.updateJoints();
	}
}
