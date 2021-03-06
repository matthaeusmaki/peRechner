package de.makiart.phosphatapp.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import de.makiart.phosphatapp.data.FoodData;

public class TabsPagerAdapter extends FragmentPagerAdapter  {

	private FoodData foodData;
	
	public TabsPagerAdapter(FragmentManager fm, FoodData foodData) {
		super(fm);
		this.foodData = foodData;
	}

	@Override
	public Fragment getItem(int index) {
		Fragment newFragment = new SelectFoodFragment();
		((SelectFoodFragment) newFragment).setFoodList(foodData.getFoodOfCategory(foodData.getListOfCategoriesAsStrings().get(index)));
		return newFragment;
	}

	@Override
	public int getCount() {
		return foodData.getListOfCategoriesAsStrings().size();
	}
}
