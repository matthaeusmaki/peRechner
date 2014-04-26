package de.makiart.phosphatapp.data;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.util.Log;
import android.util.Xml;
import de.makiart.phosphatapp.R;
import de.makiart.phosphatapp.logic.Category;
import de.makiart.phosphatapp.logic.Food;

public class FoodData {
	
	private static FoodData foodDataInstance;
	
	private static final String FOODDATA_PATH = "foodData.xml";
	
	public static final String FOODUNITS_TAG = "foodUnits";
	public static final String FOOD_TAG = "food";
	public static final String PEVALUE_TAG = "peValue";
	public static final String NAME_TAG = "name";
	public static final String AMOUNT_TAG = "amount";
	public static final String MEASUREMENT_TAG = "measurement";
	public static final String CATEGORY_TAG = "category";
	
	public static final String CATEGORY_MEATS = "meats";
	public static final String CATEGORY_SAUSAGES = "sausages";
	public static final String CATEGORY_PASTRIES = "pastries";
	public static final String CATEGORY_VEGETABLES = "vegetables";
	public static final String CATEGORY_FRUITS = "fruits";
	public static final String CATEGORY_OTHER = "other";
	
	private int idCount = 0;
	private List<Food> selectableFood = new ArrayList<Food>();
	private List<String> foodCategories = new ArrayList<String>();
	
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
            	if (name.equals(FOOD_TAG)) {
            		Food food = readFood(parser);
            		if (food != null) {
            			selectableFood.add(food);
            		}
            	}
            }
        } catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	private Food readFood(XmlPullParser parser) throws XmlPullParserException, IOException {
		parser.require(XmlPullParser.START_TAG, null, FOOD_TAG);

		Food food = new Food();
	    food.setId(-1);
	    
	    try {
	    	while (parser.next() != XmlPullParser.END_TAG) {
	    		if (parser.getEventType() != XmlPullParser.START_TAG) {
	    			continue;
	    		}
	    		String tag = parser.getName();
	    		if (tag.equals(NAME_TAG)) {
	    			food.setName(parseTag(parser, NAME_TAG));
	    		} else if (tag.equals(PEVALUE_TAG)) {
	    			food.setPeValue(parseIntegerTag(parser, PEVALUE_TAG));
	    		} else if (tag.equals(AMOUNT_TAG)) {
	    			food.setAmount(parseIntegerTag(parser, AMOUNT_TAG));
	    		} else if (tag.equals(CATEGORY_TAG)) {
	    			String name = parseTag(parser, CATEGORY_TAG);
	    			Category cat = new Category(name, translateCategoryName(name));
	    			food.setCategory(cat);
	    			
	    			if (!foodCategories.contains(cat.getDisplayName())) {
	    				foodCategories.add(cat.getDisplayName());
	    			}
	    			
	    		} else if (tag.equals(MEASUREMENT_TAG)) {
	    			food.setMeasurement(parseTag(parser, MEASUREMENT_TAG));
	    		} else {
	    			skip(parser);
	    		}
	    	}
	    	return (food.isValidFood() ? food : null);
		} catch (Exception e) {
			Log.w("readFood", "Fehler beim Auslesen der Speisen!");
			return null;
		}
	}
		
	private int parseIntegerTag(XmlPullParser parser, String tag) throws NumberFormatException, XmlPullParserException, IOException {
		return Integer.valueOf(parseTag(parser, tag));
	}
	
	private String parseTag(XmlPullParser parser, String tag) throws XmlPullParserException, IOException {
		parser.require(XmlPullParser.START_TAG, null, tag);
		if (parser.next() == XmlPullParser.TEXT) {
			String value = parser.getText();
			parser.nextTag();
			return value;
		}
		return "";
	}
	
	private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
	   if (parser.getEventType() != XmlPullParser.START_TAG) {
	       throw new IllegalStateException();
	   }
	   int depth = 1;
	   while (depth != 0) {
	       switch (parser.next()) {
	       case XmlPullParser.END_TAG:
	           depth--;
	           break;
	       case XmlPullParser.START_TAG:
	           depth++;
	           break;
	       }
	   }
	}
	
	public Food getFoodByName(String name) {
		for (Food food : selectableFood) {
			if (food.getName().equals(name)) {
				return food;
			}
		}
		return null;
	}
	
	public List<String> getListOfFoodStrngs() {
		List<String> ret = new ArrayList<String>();
		for (Food food : selectableFood) {
			ret.add(food.getName());
		}
		return ret;
	}
	
	public List<Food> getListOfFood() {
		return selectableFood;
	}
	
	public List<Food> getFoodOfCategory(String category) {
		ArrayList<Food> ret = new ArrayList<Food>();
		for (Food food : selectableFood) {
			if (food.getCategory().getDisplayName().equals(category)) {
				ret.add(food);
			}
		}
		return ret;
	}
	
	public List<String> getListOfCategories() {
		return foodCategories;
	}
	
	public Food getRandomFood() {
		Food prototype = selectableFood.get((int) (Math.random() * selectableFood.size()));
		Log.i("FoodData.getRandromFood()", prototype.getName());
		return new Food(idCount++, prototype.getName(), prototype.getCategory(), prototype.getPeValue(), prototype.getAmount(), prototype.getMeasurement());
	}
	
	private String translateCategoryName(String name) {
		if (name.equals(CATEGORY_SAUSAGES)) {
			name = this.resources.getString(R.string.sausages);
		} else if (name.equals(CATEGORY_MEATS)) {
			name = this.resources.getString(R.string.meats);
		} else if (name.equals(CATEGORY_VEGETABLES)) {
			name = this.resources.getString(R.string.vegetables);
		} else if (name.equals(CATEGORY_PASTRIES)) {
			name = this.resources.getString(R.string.pastries);
		} else if (name.equals(CATEGORY_FRUITS)) {
			name = this.resources.getString(R.string.fruits);
		} else if (name.equals(CATEGORY_OTHER)) {
			name = this.resources.getString(R.string.other);
		}
		
		return name;
	}
}
