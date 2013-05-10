package de.makiart.phosphatapp.logic;

import java.util.ArrayList;
import java.util.List;

import de.makiart.phosphatapp.data.FoodData;

import android.util.Log;

public class PECalc {

	private List<Food> meal = new ArrayList<Food>();
	
	private int phosphatCount = 0;
//	private int pillCount;
	
	private FoodData data = new FoodData();
	
	public Food addFood(int id) {
		Log.i("PECalc.addFood()", "add food");
		
		Food selected = data.getRandomFood();
		selected.setId(id);
		meal.add(selected);
		calculatePe();
		
		return selected;
	}
	
	public void removeFood(int id) {
		Log.i("PECalc.addFood()", "remove food");
		
		for (Food food : meal) {
			if (food.getId() == id) {
				meal.remove(food);
				calculatePe();
			}
		}
	}
	
	private void calculatePe() {
		phosphatCount = 0;
		for (Food f : meal) {
			phosphatCount += f.getPeValue();
		}
	}
	
	public int getPhosphatCount() {
		return phosphatCount;
	}
}
