package com.ra4king.spacegame;

import com.ra4king.spacegame.resources.Resource;
import com.ra4king.spacegame.resources.ResourceBank;

public class Ship {
	private ResourceBank resources;
	
	private int shipLevel, metalToUpgrade, oilToUpgrade, slavesToUpgrade;
	private int health = 50, maxHealth = 50, metalToFix, oilToFix, slavesToFix;
	
	private int planetsDestroyed;
	
	public Ship() {
		resources = new ResourceBank();
		
		upgradeShip();
		fixShip();
	}
	
	public ResourceBank getResources() {
		return resources;
	}
	
	public int getPlanetsDestroyedNum() {
		return planetsDestroyed;
	}
	
	public void destroyPlanet() {
		planetsDestroyed++;
	}
	
	public int getMetalToUpgrade() {
		return metalToUpgrade;
	}
	
	public int getOilToUpgrade() {
		return oilToUpgrade;
	}
	
	public int getSlavesToUpgrade() {
		return slavesToUpgrade;
	}
	
	public int getMetalToFix() {
		return metalToFix;
	}
	
	public int getOilToFix() {
		return oilToFix;
	}
	
	public int getSlavesToFix () {
		return slavesToFix;
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
	
	public boolean canFix() {
		return !(health == maxHealth ||
			   resources.getQuantity(Resource.METAL) < metalToFix ||
			   resources.getQuantity(Resource.SLAVES)< slavesToFix ||
			   resources.getQuantity(Resource.OIL) < oilToFix);
	}
	
	public void fixShip() {
		if(!canFix())
			return;
		
		resources.subtractQuantity(Resource.METAL, metalToFix);
		resources.subtractQuantity(Resource.OIL, oilToFix);
		
		health = maxHealth;
	}
	
	public boolean canUpgrade() {
		return !(resources.getQuantity(Resource.METAL) < metalToUpgrade ||
			   resources.getQuantity(Resource.SLAVES)< slavesToUpgrade ||
			resources.getQuantity(Resource.OIL) < oilToUpgrade);
	}
	
	public void upgradeShip() {
		if(!canUpgrade())
			return;
		
		shipLevel++;
		
		maxHealth += 50;
		
		health += 50;
		
		if(shipLevel == 1) {
			resources.setMaximumQuantity(Resource.WOOD, 200);
			resources.setMaximumQuantity(Resource.STONE, 100);
			resources.setMaximumQuantity(Resource.METAL, 100);
			resources.setMaximumQuantity(Resource.OIL, 100);
			resources.setMaximumQuantity(Resource.SLAVES, 50);
		}
		else {
			resources.setMaximumQuantity(Resource.WOOD, resources.getMaximumQuantity(Resource.WOOD) + 200);
			resources.setMaximumQuantity(Resource.STONE, resources.getMaximumQuantity(Resource.STONE) + 100);
			resources.setMaximumQuantity(Resource.METAL, resources.getMaximumQuantity(Resource.METAL) + 100);
			resources.setMaximumQuantity(Resource.OIL, resources.getMaximumQuantity(Resource.OIL) + 100);
			resources.setMaximumQuantity(Resource.SLAVES, resources.getMaximumQuantity(Resource.SLAVES) + 50);
		}
		
		resources.subtractQuantity(Resource.METAL, metalToUpgrade);
		resources.subtractQuantity(Resource.OIL, oilToUpgrade);
		
		metalToUpgrade += 50;
		oilToUpgrade += 20;
		slavesToUpgrade += 10;
		
		metalToFix += 20;
		oilToFix += 10;
		slavesToFix += 5;
	}
}
