package model;

public class Enemy_Bat extends EnemyType
{
	
	public Enemy_Bat()
	{
		super.xp = 10;
	    super.health = 6;
	    super.attack = 2;
	    super.pain = 0;
	    super.picString = "bat.jpg";
	    super.range_of_sight = 3;
	}
}
