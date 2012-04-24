package com.ra4king.spacegame;

import com.ra4king.spacegame.resources.Resource;
import com.ra4king.spacegame.resources.ResourceBank;

public class Ship {
	private ResourceBank resources;
	private ResourceBank maximum;
	
	private int shipLevel, metalNeededToUpgrade, oilNeededToUpgrade, slavesNeededToUpgrade;
	private int health = 50, maxHealth = 50, metalNeededToFix, oilNeededToFix, slavesNeededToFix;
	
	private int planetsDestroyed;
	
	public Ship() {
		resources = new ResourceBank();
		maximum = new ResourceBank();
		
		upgradeShip();
		fixShip();
	}
	
	public ResourceBank getResources() {
		return resources;
	}
	
	public ResourceBank getMaximumValues() {
		return maximum;
	}
	
	public int getPlanetsDestroyedNum() {
		return planetsDestroyed;
	}
	
	public void destroyPlanet() {
		planetsDestroyed++;
	}
	
	public int getMetalNeededToUpgrade() {
		return metalNeededToUpgrade;
	}
	
	public int getOilNeededToUpgrade() {
		return oilNeededToUpgrade;
	}
	
	public int getSlavesNeededToUpgrade() {
		return slavesNeededToUpgrade;
	}
	
	public int getShipLevel() {
		return shipLevel;
	}
	
	public int getHealth() {
		return health;
	}
	
	public void applyDamage(int damage) {
		health = Math.max(health - damage, 0);
	}
	
	public boolean fixShip() {
		if(health == maxHealth ||
		   resources.getQuantity(Resource.METAL) < metalNeededToFix ||
		   resources.getQuantity(Resource.SLAVES)< slavesNeededToFix ||
		   resources.getQuantity(Resource.OIL) < oilNeededToFix)
			return false;
		
		resources.subtractQuantity(Resource.METAL, metalNeededToUpgrade);
		resources.subtractQuantity(Resource.OIL, oilNeededToUpgrade);
		
		health = maxHealth;
		
		return true;
	}
	
	public boolean upgradeShip() {
		if(resources.getQuantity(Resource.METAL) < metalNeededToUpgrade ||
		   resources.getQuantity(Resource.SLAVES)< slavesNeededToUpgrade ||
		   resources.getQuantity(Resource.OIL) < oilNeededToUpgrade)
			return false;
		
		shipLevel++;
		
		maxHealth += 50;
		
		health += 50;
		
		maximum.addQuantity(Resource.WOOD, 200);
		maximum.addQuantity(Resource.STONE, 100);
		maximum.addQuantity(Resource.METAL, 100);
		maximum.addQuantity(Resource.OIL, 100);
		maximum.addQuantity(Resource.SLAVES, 50);
		
		resources.subtractQuantity(Resource.METAL, metalNeededToUpgrade);
		resources.subtractQuantity(Resource.OIL, oilNeededToUpgrade);
		
		metalNeededToUpgrade += 100;
		oilNeededToUpgrade += 70;
		slavesNeededToUpgrade += 50;
		
		metalNeededToFix += 50;
		oilNeededToFix += 20;
		slavesNeededToFix += 10;
		
		return true;
	}
}
