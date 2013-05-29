package de.makiart.phosphatapp;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import de.makiart.phosphatapp.data.FoodData;
import de.makiart.phosphatapp.logic.Food;

/**
 * Ansicht für die Auswahl der Speisen und Menge.
 * @author mat
 */

public class ChooseFoodActivity extends Activity {
	List<String> spinnerArray = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choose_meal);
		
		spinnerArray.addAll(new FoodData(getAssets()).getListOfFoodStrngs());
		
		Spinner spinner = (Spinner) findViewById(R.id.spinner);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, spinnerArray);
		spinner.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_choose_meal, menu);
		return true;
	}
	
	public void cancel(View view) {
		// Abbrechen und Eingabe verwerfen
		setResult(RESULT_CANCELED);
		finish();
	}
	
	public void save(View view) {
		// save configuration
		Food food = new FoodData(getAssets()).getRandomFood();
		Intent intent = new Intent(ChooseFoodActivity.this, PhosphatActivity.class);
		intent.putExtra(Food.FOOD_ATTRIBUTE_ID, getIntent().getIntExtra("id", -1));
		intent.putExtra(Food.FOOD_ATTRIBUTE_NAME, food.getName());
		intent.putExtra(Food.FOOD_ATTRIBUTE_PEVALUE, food.getPeValue());
		intent.putExtra(Food.FOOD_ATTRIBUTE_AMOUNT, food.getAmount());
		setResult(RESULT_OK, intent);
		finish();
	}
	
	public void randomFood(View view) {
		// generate random food
	}
	
}
