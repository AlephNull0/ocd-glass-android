package com.ocd.dev.customlistitems;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ListAdapter;

public class MainActivity extends ListActivity {
	private List<Person> mPeople;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		initPeopleList();
		
		// the second parameter in this constructor is useless.
		//ListAdapter adapter = new BadPeopleAdapter(this, 0, mPeople);
		
		// although this alternate constructor is better, it still overrides
		// ArrayAdapter, which is misleading.
		//ListAdapter adapter = new BadPeopleAdapter(this, mPeople);
		
		// this final version extends BaseAdapter and makes the most
		// sense semantically. 
		ListAdapter adapter = new GoodPeopleAdapter(this, mPeople);
		
		setListAdapter(adapter);
	}

	private void initPeopleList() {
		mPeople = new ArrayList<Person>();
		addPersonWith("Bruce Wayne", "Harvey Dent... can he be trusted?", R.drawable.bruce);
		addPersonWith("Mumbo", "Wait, Mumbo need new boots! Only kidding...", R.drawable.mumbo);
		addPersonWith("Gruntilda", "Tiny creatures far below, which of you will be the first to go?", R.drawable.gruntilda);
		addPersonWith("Zero", "You may even become as powerful as I am.", R.drawable.zero);
	}

	private void addPersonWith(String name, String latestMessage, int avatarRes) {
		Bitmap avatar = BitmapFactory.decodeResource(getResources(), avatarRes);
		Person person = new Person(name);
		person.setLatestMessage(latestMessage);
		person.setAvatar(avatar);
		mPeople.add(person);
	}
}
