package com.ocd.dev.simplegrocerylist;

import java.util.List;

import android.view.View;
import android.view.ViewGroup;

import com.google.android.glass.app.Card;
import com.google.android.glass.widget.CardScrollAdapter;

public class ListCardScrollAdapter extends CardScrollAdapter {
	private List<Card> mCards;
	
	public ListCardScrollAdapter(List<Card> cards) {
		mCards = cards;
	}

	@Override
	public int getPosition(Object item) {
		return mCards.indexOf(item);
	}

	@Override
	public int getCount() {
		return mCards.size();
	}

	@Override
	public Object getItem(int position) {
		return mCards.get(position);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return mCards.get(position).getView();
	}
	
}
