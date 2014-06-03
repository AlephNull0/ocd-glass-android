package com.ocd.dev.simplegrocerylist;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.content.Context;

import com.google.android.glass.app.Card;
import com.google.android.glass.app.Card.ImageLayout;

public class GroceryItemCards {
	private Context mContext;
	private List<Card> mCards;
	
	public GroceryItemCards(Context context) {
		mContext = context;
		mCards = new ArrayList<Card>();
	}
	
	public void addGroceryItem(GroceryItem groceryItem) {
		Card card = new Card(mContext);
		card.setText(groceryItem.getName());

		// add background image to card, if available
		if(groceryItem.getImageResource() >= 0) {
			card.addImage(groceryItem.getImageResource());
			card.setImageLayout(ImageLayout.FULL);
		}

		mCards.add(card);
	}
	
	public List<Card> getCards() {
		int numCards = mCards.size();
		for(int i=0; i<numCards; ++i) {
			// show the user what item he is on and how many there are. E.g., 3 of 10
			mCards.get(i).setFootnote(String.format(Locale.US, "%d of %d", i+1, numCards));
		}
		
		return mCards;
	}
}