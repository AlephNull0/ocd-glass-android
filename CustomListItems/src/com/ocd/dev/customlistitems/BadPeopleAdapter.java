package com.ocd.dev.customlistitems;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class BadPeopleAdapter extends ArrayAdapter<Person> {
		private LayoutInflater mInflater;
		private List<Person> mPeople;

		public BadPeopleAdapter(Context context, int resource,
				List<Person> objects) {
			super(context, resource, objects);
			mInflater = LayoutInflater.from(context);
			mPeople = objects;
		}
		
		public BadPeopleAdapter(Context context, List<Person> objects) {
			// what on earth is this second argument to super??
			super(context, 0, objects);
			mInflater = LayoutInflater.from(context);
			mPeople = objects;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view;
			ViewHolder holder;
			if(convertView == null) {
				view = mInflater.inflate(R.layout.row_layout, parent, false);
				holder = new ViewHolder();
				holder.avatar = (ImageView)view.findViewById(R.id.avatar);
				holder.name = (TextView)view.findViewById(R.id.name);
				holder.latestMessage = (TextView)view.findViewById(R.id.latest_message);
				view.setTag(holder);
			} else {
				view = convertView;
				holder = (ViewHolder)view.getTag();
			}
			
			Person person = mPeople.get(position);
			holder.avatar.setImageBitmap(person.getAvatar());
			holder.name.setText(person.getName());
			holder.latestMessage.setText(person.getLatestMessage());
			
			return view;
		}
		
		private class ViewHolder {
			public ImageView avatar;
			public TextView name, latestMessage;
		}
	}