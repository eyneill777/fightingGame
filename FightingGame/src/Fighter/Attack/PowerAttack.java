package Fighter.Attack;

import java.util.ArrayList;

public abstract class PowerAttack 
{
	String name;
	
	public PowerAttack(ArrayList<PowerModifier> modifiers)
	{
		combineModifiers(modifiers);
	}
	
	private void combineModifiers(ArrayList<PowerModifier> modifiers)
	{
		//todo
	}
}
