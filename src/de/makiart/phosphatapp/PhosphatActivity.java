package de.makiart.phosphatapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import de.makiart.phosphatapp.logic.Food;
import de.makiart.phosphatapp.logic.PECalc;

public class PhosphatActivity extends Activity {

	private PECalc logic;
	private int idCount = 1;
	private LinearLayout mealList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_phosphat);
		logic = new PECalc();
		mealList = (LinearLayout) findViewById(R.id.mealListId);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_phosphat, menu);
		return true;
	}

	/**
	 * Hinzuf�gen einer Speise. Wird dem LinearLayout hinzugef�gt
	 * @param view
	 */
	public void addMeal(View view) {
		mealList.addView(createMealListItem(logic.addFood(idCount)));
		idCount++;	// Z�hler f�r die id benennung der Listitems erh�hen
		((TextView) findViewById(R.id.peTextId)).setText(String.valueOf(logic.getPhosphatCount()));
	}
	
	/**
	 * Entfernen einer Speise. Wird vom LinearLayout entfernt.
	 * @param view
	 */
	public void removeMeal(View view) {
		logic.removeFood(view.getId());
		mealList.removeView((View) view.getParent());
		((TextView) findViewById(R.id.peTextId)).setText(String.valueOf(logic.getPhosphatCount()));
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
		mealListItem.setId(idCount);
		
		return mealListItem;
	}
}
