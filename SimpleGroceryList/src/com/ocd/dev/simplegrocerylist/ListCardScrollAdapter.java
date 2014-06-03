package com.ocd.dev.simplegrocerylist;

import java.util.List;

import android.view.View;
import android.view.ViewGroup;

import com.google.android.glass.app.Card;
import com.google.android.glass.widget.CardScrollAdapter;

public class ListCardScrollAdapter extends CardScrollAdapter {
	private List<Card> mCards;
	private Card mEmptyCard;
	
	public ListCardScrollAdapter(List<Card> cards, Card emptyCard) {
		mCards = cards;
		mEmptyCard = emptyCard;
	}
	
	@Override
	public int getPosition(Object item) {
		return mCards.indexOf(item);
	}

	@Override
	public int getCount() {
		int size = mCards.size();
		if(size > 0) {
			return size;
		} else {
			return 1;
		}
	}

	@Override
	public Object getItem(int position) {
		if(mCards.size() > 0) {
			return mCards.get(position);
		} else {
			return mEmptyCard;
		}
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(mCards.size() > 0) {
			return mCards.get(position).getView();
		} else {
			return mEmptyCard.getView();
		}
	}

}
