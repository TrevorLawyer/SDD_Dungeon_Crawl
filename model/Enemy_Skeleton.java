package model;

public class Enemy_Skeleton extends EnemyType 
{
	public Enemy_Skeleton()
	{
		super.xp = 10;
	    super.health = 2;
	    super.attack = 2;
	    super.pain = 0;
	    super.picString = "skeleton.png";
	    super.range_of_sight = 2;
	}
}
