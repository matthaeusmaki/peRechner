package de.makiart.phosphatapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

/**
 * Ansicht für die Auswahl der Speise und Menge.
 * @author mat
 *
 */

public class ChooseFoodActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choose_meal);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_choose_meal, menu);
		return true;
	}
	
	public void cancel(View view) {
		Intent myIntent = new Intent(ChooseFoodActivity.this, PhosphatActivity.class);
		ChooseFoodActivity.this.startActivity(myIntent);
		finish();
	}
	
	public void save(View view) {
		// save configuration
	}
	
	public void randomFood(View view) {
		// generate random food
	}
}
