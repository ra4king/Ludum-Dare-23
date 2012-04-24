package com.ra4king.spacegame.resources;

import java.util.HashMap;

public class ResourceBank {
	private HashMap<Resource,ResourceData> resources;
	
	public ResourceBank() {
		resources = new HashMap<Resource,ResourceData>();
		
		for(Resource r : Resource.values())
			resources.put(r, new ResourceData());
	}
	
	public int getQuantity(Resource r) {
		return resources.get(r).quantity;
	}
	
	public void setQuantity(Resource r, int quantity) {
		resources.get(r).quantity = Math.max(0,Math.min(quantity,resources.get(r).max));
	}
	
	public int addQuantity(Resource r, int quantity) {
		int oldQ = getQuantity(r);
		setQuantity(r,getQuantity(r) + quantity);
		return getQuantity(r) - oldQ;
	}
	
	public int subtractQuantity(Resource r, int quantity) {
		int oldQ = getQuantity(r);
		setQuantity(r,getQuantity(r) - quantity);
		return oldQ - getQuantity(r);
	}
	
	public void transferFrom(ResourceBank other, Resource r, int quantity) {
		other.subtractQuantity(r, addQuantity(r,Math.min(other.getQuantity(r), quantity)));
	}
	
	public void transferTo(ResourceBank other, Resource r, int quantity) {
		other.transferFrom(this, r, quantity);
	}
	
	public int getMaximumQuantity(Resource r) {
		return resources.get(r).max;
	}
	
	public void setMaximumQuantity(Resource r, int max) {
		resources.get(r).max = max;
	}
	
	private class ResourceData {
		private int quantity;
		private int max = Integer.MAX_VALUE;
	}
}
