package com.ra4king.spacegame;

import com.ra4king.spacegame.resources.Resource;
import com.ra4king.spacegame.resources.ResourceBank;

public class Ship {
	private ResourceBank resources;
	private ResourceBank maximum;
	
	private int shipLevel, health, metalNeededToUpgrade, oilNeededToUpgrade, slavesNeededToUpgrade;
	
	public Ship() {
		resources = new ResourceBank();
		maximum = new ResourceBank();
		
		upgradeShip();
	}
	
	public ResourceBank getResources() {
		return resources;
	}
	
	public ResourceBank getMaximumValues() {
		return maximum;
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
	
	public boolean upgradeShip() {
		if(resources.getQuantity(Resource.METAL) < metalNeededToUpgrade ||
		   resources.getQuantity(Resource.SLAVES)< slavesNeededToUpgrade ||
		   resources.getQuantity(Resource.OIL) < oilNeededToUpgrade)
			return false;
		
		shipLevel++;
		
		maximum.addQuantity(Resource.WOOD, 200);
		maximum.addQuantity(Resource.STONE, 100);
		maximum.addQuantity(Resource.METAL, 100);
		maximum.addQuantity(Resource.OIL, 100);
		maximum.addQuantity(Resource.SLAVES, 50);
		
		resources.subtractQuantity(Resource.METAL, metalNeededToUpgrade);
		resources.subtractQuantity(Resource.OIL, oilNeededToUpgrade);
		
		metalNeededToUpgrade += 100;
		slavesNeededToUpgrade += 50;
		
		return true;
	}
}
