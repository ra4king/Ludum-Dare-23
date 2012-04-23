package com.ra4king.spacegame.resources;

import java.util.HashMap;

public class ResourceBank {
	private HashMap<Resource,Integer> resources;
	
	public ResourceBank() {
		resources = new HashMap<Resource,Integer>();
		
		for(Resource r : Resource.values())
			setQuantity(r,0);
	}
	
	public int getQuantity(Resource r) {
		return resources.get(r);
	}
	
	public void setQuantity(Resource r, int quantity) {
		resources.put(r,quantity);
	}
	
	public void addQuantity(Resource r, int quantity) {
		setQuantity(r,getQuantity(r) + quantity);
	}
	
	public void subtractQuantity(Resource r, int quantity) {
		addQuantity(r,Math.max(-quantity,-getQuantity(r)));
	}
	
	public void transfer(ResourceBank other, Resource r, int quantity) {
		addQuantity(r,Math.min(other.getQuantity(r), quantity));
		other.subtractQuantity(r, quantity);
	}
}
