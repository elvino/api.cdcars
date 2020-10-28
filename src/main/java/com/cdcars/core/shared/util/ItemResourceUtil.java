package com.cdcars.core.shared.util;

public class ItemResourceUtil implements Comparable<ItemResourceUtil>{
	
	private long idItem;
	private int numRating;
	
	
	
	public ItemResourceUtil(long idItem, int numRating) {

		this.idItem = idItem;
		this.numRating = numRating;
	}


	public long getIdItem() {
		return idItem;
	}

	public void setIdItem(long idItem) {
		this.idItem = idItem;
	}


	public float getNumRating() {
		return numRating;
	}



	public void setNumRating(int numRating) {
		this.numRating = numRating;
	}



	@Override
	public int compareTo(ItemResourceUtil o) {
		if (this.numRating < o.numRating) {
            return 1;
        }
        if (this.numRating > o.numRating) {
            return -1;
        }
        return 0;
	}
	
	
	
	
	

}
