package com.ocd.dev.customlistitems;

import android.graphics.Bitmap;

public class Person {
	private String mName, mLatestMessage;
	private Bitmap mAvatar;
	
	public Person(String name) {
		mName = name;
	}
	
	public String getName() {
		return mName;
	}
	
	public String getLatestMessage() {
		return mLatestMessage;
	}
	
	public void setLatestMessage(String message) {
		mLatestMessage = message;
	}
	
	public Bitmap getAvatar() {
		return mAvatar;
	}
	
	public void setAvatar(Bitmap avatar) {
		mAvatar = avatar;
	}
}