package de.makiart.phosphatapp;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import de.makiart.phosphatapp.data.FoodData;
import de.makiart.phosphatapp.logic.Food;

/**
 * Ansicht für die Auswahl der Speisen und Menge.
 * @author mat
 */

public class ChooseFoodActivity extends Activity implements OnItemSelectedListener{
	
	private List<Food> listOfFood = new ArrayList<Food>();
	private Food selectedFood;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choose_meal);
		
//		spinnerArray.addAll(new FoodData(getAssets(), getResources()).getListOfCategories());
		FoodData foodData = FoodData.getFoodData(getAssets(), getResources());
		List<String> spinnerArray = foodData.getListOfFoodStrngs();
		listOfFood.clear();
		listOfFood.addAll(foodData.getListOfFood());

		Spinner spinner = (Spinner) findViewById(R.id.spinner);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, spinnerArray);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(this);
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
		Intent intent = new Intent(ChooseFoodActivity.this, PhosphatActivity.class);
		intent.putExtra(Food.FOOD_ATTRIBUTE_ID, getIntent().getIntExtra("id", -1));
		intent.putExtra(Food.FOOD_ATTRIBUTE_NAME, selectedFood.getName());
		intent.putExtra(Food.FOOD_ATTRIBUTE_PEVALUE, selectedFood.getPeValue());
		intent.putExtra(Food.FOOD_ATTRIBUTE_AMOUNT, selectedFood.getAmount());
		setResult(RESULT_OK, intent);
		finish();
	}

	@Override
	public void onItemSelected(AdapterView<?> spinner, View arg1, int pos, long id) {
		selectedFood = listOfFood.get(pos);
		Log.i("itemselect", "name: " + selectedFood.getName());
		Log.i("itemselect", ": " + selectedFood.getId());
		Log.i("itemselect", "amount: " + selectedFood.getAmount());
		Log.i("itemselect", "pe: " + selectedFood.getPeValue());
		
		((TextView) findViewById(R.id.selectedPeValueId)).setText(String.valueOf(selectedFood.getPeValue()) + " PE");
		((TextView) findViewById(R.id.selectedAmountId)).setText(String.valueOf(selectedFood.getAmount()) + " " + selectedFood.getMeasurement());
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		Log.i("on nothing", "nothing");
	}
}
