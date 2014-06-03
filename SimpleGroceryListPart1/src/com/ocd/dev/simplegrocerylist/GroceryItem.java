package com.ocd.dev.simplegrocerylist;

public class GroceryItem {
	private String mName;
	private int mImageResource;
	
	public GroceryItem(String name) {
		this(name, -1);
	}
	
	public GroceryItem(String name, int imageResource) {
		mName = name;
		mImageResource = imageResource;
	}
	
	public String getName() {
		return mName;
	}
	
	public int getImageResource() {
		return mImageResource;
	}
}
