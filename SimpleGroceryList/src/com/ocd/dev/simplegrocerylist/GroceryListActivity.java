package com.ocd.dev.simplegrocerylist;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.google.android.glass.app.Card;
import com.google.android.glass.media.Sounds;
import com.google.android.glass.widget.CardScrollAdapter;
import com.google.android.glass.widget.CardScrollView;

public class GroceryListActivity extends Activity {
	public static final int ADD_ITEMS_REQUEST = 1;
	private GroceryItemCards mGroceryItemCards;
	private CardScrollAdapter mAdapter;
	private CardScrollView mCardScrollView;
	private AudioManager mAudioManager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mAudioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);

		
		// get a list of groceries from somewhere, perhaps a web service or a file
		// will use hardcoded values for this demo
		mGroceryItemCards = new GroceryItemCards(this);
		mGroceryItemCards.addGroceryItem(
				new GroceryItem("Black rasberry chip ice cream",
						R.drawable.icecream));
		mGroceryItemCards.addGroceryItem(new GroceryItem("potatoes"));
		mGroceryItemCards.addGroceryItem(new GroceryItem("chicken"));
		mGroceryItemCards.addGroceryItem(new GroceryItem("rice", R.drawable.rice));
		
		// will show empty card when there are no items
		Card emptyCard = new Card(this);
		emptyCard.setText("No grocery items available.");

		mAdapter = new ListCardScrollAdapter(mGroceryItemCards.getCards(), emptyCard);
		mCardScrollView = new CardScrollView(this);
		mCardScrollView.setAdapter(mAdapter);
		mCardScrollView.activate(); // important. do not forget.
		
		mCardScrollView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				openOptionsMenu();
				mAudioManager.playSoundEffect(Sounds.TAP);
			}
		});
		
		setContentView(mCardScrollView);
	}
	
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		super.onPrepareOptionsMenu(menu);
		if(mGroceryItemCards.isEmpty()) {
			menu.findItem(R.id.remove_item).setEnabled(false);
		} else {
			menu.findItem(R.id.remove_item).setEnabled(true);
		}
		
		return true;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.grocery_list, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
		case R.id.remove_item:
			int selectedItemPosition = mCardScrollView.getSelectedItemPosition();
			mGroceryItemCards.removeGroceryItemAt(selectedItemPosition);
			refreshGroceryItems();
			return true;
		case R.id.add_items:
			Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
			intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say the items to add: 'item1 and item2 and...'");
			startActivityForResult(intent, ADD_ITEMS_REQUEST);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode == ADD_ITEMS_REQUEST && resultCode == Activity.RESULT_OK) {
			List<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
			String spokenText = results.get(0);
			parseGroceryItems(spokenText);
		}

		super.onActivityResult(requestCode, resultCode, data);
	}

	private void parseGroceryItems(String spokenText) {
		String[] items = spokenText.split(" and ");
		for(String item : items) {
			mGroceryItemCards.addGroceryItem(new GroceryItem(item));
		}

		refreshGroceryItems();
	}

	private void refreshGroceryItems() {
		mGroceryItemCards.updateGroceryItemFootnotes();
		mAdapter.notifyDataSetChanged();
	}
}
