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
	
	public static final String MEATS = "meats";
	public static final String SAUSAGES = "sausages";
	public static final String PASTRIES = "pastries";
	public static final String VEGETABLES = "vegetables";
	public static final String FRUITS = "fruits";
	public static final String OTHER = "other";
	
	
	private int idCount = 0;
	private List<Food> selectableFood = new ArrayList<Food>();
	private List<String> foodCategories = new ArrayList<String>();
	
	private AssetManager assets;
	private Resources resources;
	
	public static FoodData getFoodData(AssetManager assets, Resources resources) {
		if (foodDataInstance == null) {
			foodDataInstance = new FoodData(assets, resources);
		}
		return foodDataInstance;
	}
	
	private FoodData(AssetManager assets, Resources resources) {
		this.assets = assets;
		this.resources = resources;
		loadFoodData();
		translateCategoryNames();
	}
	
	private void loadFoodData() {
		try {
			InputStream in = this.assets.open(FOODDATA_PATH);
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
            		selectableFood.add(readFood(parser));
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
	    String name = "";
	    String category = "";
	    String measurement = "";
	    int peValue = 0;
	    int amount = 0;
	    while (parser.next() != XmlPullParser.END_TAG) {
	        if (parser.getEventType() != XmlPullParser.START_TAG) {
	            continue;
	        }
	        String tag = parser.getName();
	        if (tag.equals(NAME_TAG)) {
	        	name = parseTag(parser, NAME_TAG);
	        } else if (tag.equals(PEVALUE_TAG)) {
	        	peValue = parseIntegerTag(parser, PEVALUE_TAG);
	        } else if (tag.equals(AMOUNT_TAG)) {
	        	amount = parseIntegerTag(parser, AMOUNT_TAG);
	        } else if (tag.equals(CATEGORY_TAG)) {
	        	category = parseTag(parser, CATEGORY_TAG);
	        } else if (tag.equals(MEASUREMENT_TAG)) {
	        	measurement = parseTag(parser, MEASUREMENT_TAG);
	        } else {
	            skip(parser);
	        }
	    }
	    return new Food(-1, name, category, peValue, amount, measurement);
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
	
	public List<Food> ListOfFoodByCaterie(String category) {
		ArrayList<Food> ret = new ArrayList<Food>();
		for (Food food : selectableFood) {
			if (food.getCategory().equals(category)) {
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
	
	private void translateCategoryNames() {
		for (Food food : selectableFood) {
			String c = food.getCategory();
			if (c.equals(SAUSAGES)) {
				c = this.resources.getString(R.string.sausages);
			} else if (c.equals(MEATS)) {
				c = this.resources.getString(R.string.meats);
			} else if (c.equals(VEGETABLES)) {
				c = this.resources.getString(R.string.vegetables);
			} else if (c.equals(PASTRIES)) {
				c = this.resources.getString(R.string.pastries);
			} else if (c.equals(FRUITS)) {
				c = this.resources.getString(R.string.fruits);
			} else if (c.equals(OTHER)) {
				c = this.resources.getString(R.string.other);
			}
				
			if (!foodCategories.contains(c)) {
				foodCategories.add(c);
			}
		}
	}
	
	public List<Food> getFoodOfCategory(String categorString) {
		// TODO: lade korrektes Essen abhängig von Kategorie
		List<Food> list = new ArrayList<Food>();
		list.add(getRandomFood());
		list.add(getRandomFood());
		list.add(getRandomFood());
		return list;
	}
}
