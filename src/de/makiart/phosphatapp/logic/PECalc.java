package de.makiart.phosphatapp.logic;

/**
 * Main Activity
 * @author mat
 *  
 */

import java.util.ArrayList;
import java.util.List;

import android.content.res.AssetManager;
import android.util.Log;
import de.makiart.phosphatapp.data.Edibles;
import de.makiart.phosphatapp.data.FoodData;

public class PECalc {

	private List<Edibles> meal = new ArrayList<Edibles>();
	
	private int phosphatCount = 0;
//	private int pillCount;
	
	private FoodData data;
	
	public PECalc(AssetManager assets) {
//		data = new FoodData(assets);
	}
	
	public Edibles addFood() {
		Log.i("PECalc.addFood()", "add food");
		
		Edibles selected = data.getRandomFood();
		meal.add(selected);
		this.calculatePe();
		
		return selected;
	}
	
	public void removeFood(int id) {
		Log.i("PECalc.removeFood()", "remove food with id: " + id);
		
		List<Edibles> tmp = new ArrayList<Edibles>();
		tmp.addAll(meal);
		for (Edibles food : tmp) {
			if (food.getId() == id) {
				meal.remove(food);
				break;
			}
		}
		calculatePe();
	}
	
	/**
	 * Berechnen der Phosphateinheiten.
	 * Drei 0 PE Werte ergeben 1 PE.
	 */
	private void calculatePe() {
		phosphatCount = 0;
		int peZeroCount = 0;
		for (Edibles f : meal) {
			int value = f.getPeValue();
			if (peZeroCount >= 3 && value == 0) {
				phosphatCount += 1;
				peZeroCount = 0;
			} else {
				phosphatCount += value;
				if (value == 0) {
					peZeroCount++;
				}
			}
		}
	}
	
	public int getPhosphatCount() {
		return phosphatCount;
	}
}
