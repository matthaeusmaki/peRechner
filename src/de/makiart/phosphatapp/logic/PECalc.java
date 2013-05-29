package de.makiart.phosphatapp.logic;

import java.util.ArrayList;
import java.util.List;

import android.content.res.AssetManager;
import android.util.Log;
import de.makiart.phosphatapp.data.FoodData;

public class PECalc {

	private List<Food> meal = new ArrayList<Food>();
	
	private int phosphatCount = 0;
//	private int pillCount;
	
	private FoodData data;
	
	public PECalc(AssetManager assets) {
//		data = new FoodData(assets);
	}
	
	public Food addFood() {
		Log.i("PECalc.addFood()", "add food");
		
		Food selected = data.getRandomFood();
		meal.add(selected);
		this.calculatePe();
		
		return selected;
	}
	
	public void removeFood(int id) {
		Log.i("PECalc.removeFood()", "remove food with id: " + id);
		
		List<Food> tmp = new ArrayList<Food>();
		tmp.addAll(meal);
		for (Food food : tmp) {
			if (food.getId() == id) {
				meal.remove(food);
				break;
			}
		}
		calculatePe();
	}
	
	/**
	 * Berechnen der Phosphateinheiten.
	 * Zwei 0 PE Werte ergeben 1 PE.
	 */
	private void calculatePe() {
		phosphatCount = 0;
		boolean isOnePeZero = false;
		for (Food f : meal) {
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
	
	public int getPhosphatCount() {
		return phosphatCount;
	}
}
