package de.makiart.phosphatapp.data;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.util.Log;
import android.util.Xml;
import de.makiart.phosphatapp.R;

public class FoodData {
	
	private static FoodData foodDataInstance;
	
	private static final String FOODDATA_PATH = "foodData.xml";
	
	public static final String FOODUNITS_TAG = "foodUnits";
	public static final String CATEGORY_TAG = "category";
	public static final String FOOD_TAG = "food";
	
	public static final String PEVALUE_ATTRIBUTE = "peValue";
	public static final String NAME_ATTRIBUTE = "name";
	public static final String AMOUNT_ATTRIBUTE = "amount";
	public static final String MEASUREMENT_ATTRIBUTE = "measurement";
	
	public static final String CATEGORY_MEATS = "meats";
	public static final String CATEGORY_MILK = "milk";
	public static final String CATEGORY_PASTRIES = "pastries";
	public static final String CATEGORY_VEGETABLES = "vegetables";
	public static final String CATEGORY_DRINKS = "drinks";
	public static final String CATEGORY_OTHER = "other";
	
	private int idCount = 0;
	private List<Edibles> selectableFood = new ArrayList<Edibles>();
	private List<Category> foodCategories = new ArrayList<Category>();
	private List<String> foodCategoriesAsStrings = new ArrayList<String>();
	
	private Resources resources;
	
	public static FoodData getFoodData(AssetManager assets, Resources resources) {
		if (foodDataInstance == null) {
			foodDataInstance = new FoodData(assets, resources);
		}
		return foodDataInstance;
	}
	
	private FoodData(AssetManager assets, Resources resources) {
		this.resources = resources;
		loadFoodData(assets);
	}
	
	private void loadFoodData(AssetManager assets) {
		long t = System.currentTimeMillis();
		try {
			InputStream in = assets.open(FOODDATA_PATH);
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            parser.require(XmlPullParser.START_TAG, null, FOODUNITS_TAG);
            while (parser.next() != XmlPullParser.END_TAG) {
            	if (parser.getEventType() != XmlPullParser.START_TAG) {
            		continue;
            	}
            	String name = parser.getName();
            	if (name.equals(CATEGORY_TAG)) {
            		readCategory(parser);
            	}
            }
        } catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Log.i("loadFoodData", selectableFood.size() + " Lebensmittel in " + (System.currentTimeMillis() - t) + " ms geladen");
	}
	
	private void readCategory(XmlPullParser parser) {
		try {
			String catName = parser.getAttributeValue(0);
			Category cat = new Category(catName, translateCategoryName(catName));
			
			foodCategories.add(cat);
			foodCategoriesAsStrings.add(cat.getDisplayName());
			
			while (parser.next() != XmlPullParser.END_TAG) {
				if (parser.getEventType() != XmlPullParser.START_TAG) {
	    			continue;
	    		}
				String name = parser.getName();
				if (name.equals(FOOD_TAG)) {
            		readFood(parser, cat);
            	}
				parser.nextTag();
			}
			
		} catch (Exception e) {
			Log.w("readCategory", "Fehler beim Auslesen der Kategorie!");
		}
	}
	
	private void readFood(XmlPullParser parser, Category category) {
		String foodName = "";
		try {
			Edibles food = new Edibles();
			food.setId(-1);
			
			foodName = parser.getAttributeValue(0);
			
			food.setName(foodName);
			food.setPeValue(Short.valueOf(parser.getAttributeValue(1)));
			food.setAmount(Short.valueOf(parser.getAttributeValue(2)));
			food.setMeasurement(parser.getAttributeValue(3));
			food.setCategory(category);
			
			selectableFood.add(food);
		} catch (Exception e) {
			Log.w("readFood", "Fehler beim Auslesen des Essens in Kategorie " + category.getDisplayName() + "und dem Essen " + foodName);
		}
	}
	
	public Edibles getFoodByName(String name) {
		for (Edibles food : selectableFood) {
			if (food.getName().equals(name)) {
				return food;
			}
		}
		return null;
	}
	
	public List<String> getListOfFoodStrngs() {
		List<String> ret = new ArrayList<String>();
		for (Edibles food : selectableFood) {
			ret.add(food.getName());
		}
		return ret;
	}
	
	public List<Edibles> getListOfFood() {
		return selectableFood;
	}
	
	public List<Edibles> getFoodOfCategory(String category) {
		ArrayList<Edibles> ret = new ArrayList<Edibles>();
		for (Edibles food : selectableFood) {
			if (food.getCategory().getDisplayName().equals(category)) {
				ret.add(food);
			}
		}
		Collections.sort(ret);
		return ret;
	}
	
	public List<String> getListOfCategoriesAsStrings() {
		return foodCategoriesAsStrings;
	}
	
	public List<Category> getListOfCategories() {
		return foodCategories;
	}
	
	public Edibles getRandomFood() {
		Edibles prototype = selectableFood.get((int) (Math.random() * selectableFood.size()));
		Log.i("FoodData.getRandromFood()", prototype.getName());
		return new Edibles(idCount++, prototype.getName(), prototype.getCategory(), prototype.getPeValue(), prototype.getAmount(), prototype.getMeasurement());
	}
	
	private String translateCategoryName(String name) {
		if (name.equals(CATEGORY_MEATS)) {
			name = this.resources.getString(R.string.meats);
		} else if (name.equals(CATEGORY_MILK)) {
			name = this.resources.getString(R.string.milk);
		} else if (name.equals(CATEGORY_VEGETABLES)) {
			name = this.resources.getString(R.string.vegetables);
		} else if (name.equals(CATEGORY_PASTRIES)) {
			name = this.resources.getString(R.string.pastries);
		} else if (name.equals(CATEGORY_DRINKS)) {
			name = this.resources.getString(R.string.drinks);
		} else if (name.equals(CATEGORY_OTHER)) {
			name = this.resources.getString(R.string.other);
		}
		
		return name;
	}
}
