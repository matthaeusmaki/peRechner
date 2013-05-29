package de.makiart.phosphatapp.data;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.content.res.AssetManager;
import android.util.Log;
import android.util.Xml;
import de.makiart.phosphatapp.logic.Food;

public class FoodData {

//	public static final PEVALUE_TAG = "peValue";
	
	private int idCount = 0;
	private List<Food> selectableFood = new ArrayList<Food>();
	private List<String> foodCategories = new ArrayList<String>();
	private AssetManager assets;
	
	public enum Category {
		BEEF,
		FRUIT,
		VEGETABLE,
		SAUSAGE,
		UNDEFNED;
	};
	
	public FoodData(AssetManager assets) {
		this.assets = assets;
		loadFoodData();
	}
	
	private void loadFoodData() {
		try {
			InputStream in = this.assets.open("foodData.xml");
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            parser.require(XmlPullParser.START_TAG, null, "foodUnits");
            while (parser.next() != XmlPullParser.END_TAG) {
            	if (parser.getEventType() != XmlPullParser.START_TAG) {
            		continue;
            	}
            	String name = parser.getName();
            	if (name.equals("food")) {
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
		parser.require(XmlPullParser.START_TAG, null, "food");
	    String name = "";
	    int peValue = 0;
	    int amount = 0;
	    while (parser.next() != XmlPullParser.END_TAG) {
	        if (parser.getEventType() != XmlPullParser.START_TAG) {
	            continue;
	        }
	        String tag = parser.getName();
	        if (tag.equals("name")) {
	            name = readName(parser);
	        } else if (tag.equals("peValue")) {
	            peValue = readPeValue(parser);
	        } else if (tag.equals("amount")) {
	            amount = readAmount(parser);
	        } else {
	            skip(parser);
	        }
	    }
	    return new Food(-1, name, peValue, amount);
	}
	
	private String readName(XmlPullParser parser) throws XmlPullParserException, IOException {
		parser.require(XmlPullParser.START_TAG, null, "name");
		if (parser.next() == XmlPullParser.TEXT) {
			String name = parser.getText();
			parser.nextTag();
			return name;
		}
		return "";
	}
	
	private int readPeValue(XmlPullParser parser) throws XmlPullParserException, IOException {
		parser.require(XmlPullParser.START_TAG, null, "peValue");
		if (parser.next() == XmlPullParser.TEXT) {
			String peValue = parser.getText();
			parser.nextTag();
			return Integer.valueOf(peValue);
		}
		return 0;
	}
	
	private int readAmount(XmlPullParser parser) throws XmlPullParserException, IOException {
		parser.require(XmlPullParser.START_TAG, null, "amount");
		if (parser.next() == XmlPullParser.TEXT) {
			String amount = parser.getText();
			parser.nextTag();
			return Integer.valueOf(amount);
		}
		return 0;
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
	
	public List<Food> ListOfFoodByCaterie(Category category) {
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
		int r = (int) (Math.random() * selectableFood.size());
		Log.i("FoodData.getRandromFood()", String.valueOf(r));
		
		Food prototype = selectableFood.get(r);
		return new Food(idCount++, prototype.getName(), prototype.getPeValue(), prototype.getAmount());
	}
}
