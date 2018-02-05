package model;

public class Weapon extends Item {

	int power;  //Increases Damage dealt by this amount
	
	public Weapon(String n, String desc, int p) {
		super(n, desc);
		power=p;
	}
	

}
