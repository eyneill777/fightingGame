package Fighter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Position 
{	
	public String positionName;
	double rShoulder,lShoulder, rHip, lHip, rElbow, lElbow, rKnee, lKnee, cAngle;
	
	public Position(String positionName)
	{
		this.positionName = positionName;
		loadAngles();
	}
	public Position()
	{
		
	}
	
	private void loadAngles()
	{
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File("src/Fighter/Positions/"+positionName+".txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cAngle = Double.parseDouble(scanner.nextLine());
		rShoulder = Double.parseDouble(scanner.nextLine());
		lShoulder = Double.parseDouble(scanner.nextLine());
		rHip = Double.parseDouble(scanner.nextLine());
		lHip = Double.parseDouble(scanner.nextLine());
		rElbow = Double.parseDouble(scanner.nextLine());
		lElbow = Double.parseDouble(scanner.nextLine());
		rKnee = Double.parseDouble(scanner.nextLine());
		lKnee = Double.parseDouble(scanner.nextLine());
		scanner.close();
	}
}
