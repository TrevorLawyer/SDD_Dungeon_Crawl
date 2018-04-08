package model;

public class Enemy_Orc extends EnemyType
{
	
	public Enemy_Orc()
	{
		super.xp = 15;
	    super.health = 6;
	    super.range_of_sight = 3;
	    super.attack = 2;
	    super.pain = 0;
	    super.picString = "orc.jpg";
	}
}
