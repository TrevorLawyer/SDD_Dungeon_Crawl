package model;

public class Enemy_Bat extends EnemyType
{
	
	public Enemy_Bat()
	{
		super.xp = 10;
	    super.health = 2;
	    super.attack = 2;
	    super.pain = 0;
	    if(pain==0)super.picString = "bat.jpg";
	    if(pain==1)super.picString = "hit.png";
	    super.range_of_sight = 3;
	}
}
