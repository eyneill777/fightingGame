package Fighter.Attack;

public abstract class PowerModifier 
{
	int key;
	String name;
	
	public PowerModifier(int key, String name)
	{
		this.key = key;
		this.name = name;
	}
}
