package Main;

public class Tools 
{
	public static double getAngle(int x1, int y1, int x2, int y2)
	{
		int xDiff = x1-x2, ydiff = y1-y2;
		return Math.atan2(ydiff, xDiff);
	}
	
	public static double getDistanceSquared(int x1, int y1, int x2, int y2)
	{
		double distance2 = Math.pow(x1-x2,2)+Math.pow(y1-y2, 2);
		return distance2;
	}
	
	public static double getDistanceSquared(float x1, float y1, float x2, float y2)
	{
		double distance2 = Math.pow(x1-x2,2)+Math.pow(y1-y2, 2);
		return distance2;
	}
}
