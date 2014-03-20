package com.ocd.dev.actionoverlaymap;

import android.app.Activity;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.TypedValue;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;

public class MapActivity extends Activity {
	private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        
        setUpMapIfNeeded();
    }
    
    @Override
    protected void onResume() {
    	super.onResume();
    	setUpMapIfNeeded();
    }
    
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
                                .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                // The Map is verified. It is now safe to manipulate the map.
            	mMap.setMyLocationEnabled(true);
            	
            	//?android:attr/actionBarSize
            	int actionBarSize = obtainActionBarHeight();
            	//mMap.setPadding(0, actionBarSize, 0, 0);
            }
        }
    }
    
    private int obtainActionBarHeight() {
    	int[] textSizeAttr = new int[] { android.R.attr.actionBarSize };
    	TypedValue typedValue = new TypedValue(); 
    	TypedArray a = obtainStyledAttributes(typedValue.data, textSizeAttr);
    	int textSize = a.getDimensionPixelSize(0, -1);
    	a.recycle();
    	
    	return textSize;
    }

}
