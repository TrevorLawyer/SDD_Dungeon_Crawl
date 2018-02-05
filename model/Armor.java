package model;

public class Armor extends Item {
	
	int defense;	//Defense reduces damage taken by this amount

	public Armor(String n, String desc, int def) {
		super(n, desc);
		defense = def;
	}

}
