package de.makiart.phosphatapp;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import de.makiart.phosphatapp.logic.Food;
import de.makiart.phosphatapp.logic.PECalc;

/**
 * Ansicht f�r Hinzuf�gen einer Speise und Anzeigen ob bereits etwas hinzugef�gt wurde.
 * Und das Ergebnis der Berechnung (Phosphat Einheit)
 * @author mat
 *
 */

public class PhosphatActivity extends Activity {

	private int idCount = 0;
	private int phosphatCount = 0;
	private LinearLayout foodViewList;
	private List<Food> foodList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_phosphat);
		foodViewList = (LinearLayout) findViewById(R.id.mealListId);
		foodList = new ArrayList<Food>();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_phosphat, menu);
		return true;
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent in) {
		super.onActivityResult(requestCode, resultCode, in);
		Log.i("onActivityResult", "asdfasfdasfd");
		
		if (requestCode == 1) {
			if (resultCode == RESULT_OK) {
				Food food = new Food();
				int foodId = in.getIntExtra(Food.FOOD_ATTRIBUTE_ID, -1);
				food.setId(foodId > 0 ? foodId : idCount++);
				food.setName(in.getStringExtra(Food.FOOD_ATTRIBUTE_NAME));
				food.setPeValue(in.getIntExtra(Food.FOOD_ATTRIBUTE_PEVALUE, 0));
				food.setAmount(in.getIntExtra(Food.FOOD_ATTRIBUTE_AMOUNT, 0));
				foodList.add(food);
				foodViewList.addView(createMealListItem(food));
				calculatePe();
				((TextView) findViewById(R.id.peTextId)).setText(String.valueOf(phosphatCount));
			}
		}
	}

	/**
	 * Hinzuf�gen einer Speise. 
	 * �ffnet die Ansicht f�r die Essens Auswahl.
	 * @param view
	 */
	public void addMeal(View view) {
		Intent intent = new Intent(PhosphatActivity.this, ChooseFoodActivity.class);
		intent.putExtra("id", idCount++);
		this.startActivityForResult(intent, 1);
	}
	
	/**
	 * Entfernen einer Speise. Wird vom LinearLayout entfernt.
	 * @param view
	 */
	public void removeMeal(View view) {
		int id = ((View) view.getParent()).getId();
		Log.i("PECalc.removeFood()", "remove food with id: " + id);
		List<Food> tmp = new ArrayList<Food>();
		tmp.addAll(foodList);
		for (Food food : tmp) {
			if (food.getId() == id) {
				foodList.remove(food);
				break;
			}
		}
		calculatePe();
		foodViewList.removeView((View) view.getParent());
		((TextView) findViewById(R.id.peTextId)).setText(String.valueOf(phosphatCount));
	}
	
	private LinearLayout createMealListItem(Food addedFood) {
		TextView peTxt = new TextView(this);
		peTxt.setText(String.valueOf(addedFood.getPeValue()) + " ");
		
		TextView nameTxt = new TextView(this);
		nameTxt.setText(addedFood.getName() + " ");
		
		Button removeButton = new Button(this);
		removeButton.setText(R.string.buttonRemoveMeal);
		removeButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				removeMeal(v);
			}
		});
		
		LinearLayout mealListItem = new LinearLayout(this);
		mealListItem.addView(removeButton);
		mealListItem.addView(nameTxt);
		mealListItem.addView(peTxt);
		mealListItem.setId(addedFood.getId());
		return mealListItem;
	}
	
	private void calculatePe() {
		phosphatCount = 0;
		boolean isOnePeZero = false;
		for (Food f : foodList) {
			int value = f.getPeValue();
			if (isOnePeZero && value == 0) {
				phosphatCount += 1;
				isOnePeZero = false;
			} else {
				phosphatCount += value;
				if (value == 0) {
					isOnePeZero = true;
				}
			}
		}
	}
}
