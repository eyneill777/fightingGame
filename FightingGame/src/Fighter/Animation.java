package Fighter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Animation 
{
	String name;
	ArrayList<Position> frames = new ArrayList<Position>();
	
	public Animation(String name)
	{
		this.name = name;
		loadFrames(name);
	}
	
	private void loadFrames(String file)
	{
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File("src/Fighter/Animations/"+file+".txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int i = 0;
		while(scanner.hasNextLine())
		{
			frames.add(new Position());
			frames.get(i).positionName = name+i;
			frames.get(i).cAngle = Double.parseDouble(scanner.nextLine());
			frames.get(i).rShoulder = Double.parseDouble(scanner.nextLine());
			frames.get(i).lShoulder = Double.parseDouble(scanner.nextLine());
			frames.get(i).rHip = Double.parseDouble(scanner.nextLine());
			frames.get(i).lHip = Double.parseDouble(scanner.nextLine());
			frames.get(i).rElbow = Double.parseDouble(scanner.nextLine());
			frames.get(i).lElbow = Double.parseDouble(scanner.nextLine());
			frames.get(i).rKnee = Double.parseDouble(scanner.nextLine());
			frames.get(i).lKnee = Double.parseDouble(scanner.nextLine());
			i++;
			scanner.nextLine();
		}
		scanner.close();
	}
}
