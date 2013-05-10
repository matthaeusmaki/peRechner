package de.makiart.phosphatapp.data;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;
import de.makiart.phosphatapp.logic.Food;

public class FoodData {

	private List<Food> selectableFood = new ArrayList<Food>();
	
	public FoodData() {
		generateFoodData();
	}
	
	private void generateFoodData() {
		// TODO: Daten sollten nicht Hardcodiert werden
		// vielleicht als xml-file
		// diese Speisen stammen vom Phosphatmanagement Prospekt Seite 6
		this.selectableFood.add(new Food("Wiener Würstchen", 3, 150));
		this.selectableFood.add(new Food("Kartoffelsalat", 1, 200));
		this.selectableFood.add(new Food("Senf", 0, 15));
		this.selectableFood.add(new Food("Grüne Bohnen", 0, 100));
		this.selectableFood.add(new Food("Bratkartoffeln", 1, 200));
		this.selectableFood.add(new Food("Rindersteak", 3, 150));
	}
	
	public Food getRandomFood() {
		int r = (int) (Math.random() * selectableFood.size());
		Log.i("FoodData.getRandromFood()", String.valueOf(r));
		
		Food prototype = selectableFood.get(r);
		return new Food(prototype.getName(), prototype.getPeValue(), prototype.getPortion());
	}
}
