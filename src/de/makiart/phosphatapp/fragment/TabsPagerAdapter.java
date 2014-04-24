package de.makiart.phosphatapp.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsPagerAdapter extends FragmentPagerAdapter  {

	private int numberOfTabs;
	
	public TabsPagerAdapter(FragmentManager fm, int numberOfTabs) {
		super(fm);
		this.numberOfTabs = numberOfTabs;
	}

	@Override
	public Fragment getItem(int index) {
		return new FoodChooseFragment();
	}

	@Override
	public int getCount() {
		return numberOfTabs;
	}
}
