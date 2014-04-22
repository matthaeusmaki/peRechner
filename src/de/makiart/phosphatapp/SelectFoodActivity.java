package de.makiart.phosphatapp;

import de.makiart.phosphatapp.data.FoodData;
import de.makiart.phosphatapp.fragment.TabsPagerAdapter;
import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.app.ActionBar.Tab;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

public class SelectFoodActivity extends FragmentActivity implements ActionBar.TabListener {

	private ViewPager viewPager;
	private TabsPagerAdapter pagerAdapter;
	private ActionBar actionBar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Swipeable tabs
		actionBar = getActionBar();
		viewPager = (ViewPager) findViewById(R.id.pager);
		pagerAdapter = new TabsPagerAdapter(getSupportFragmentManager());
		
		viewPager.setAdapter(pagerAdapter);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		FoodData foodData = new FoodData(getAssets(), getResources());
		for (String tabName : foodData.getListOfCategories()) {
			actionBar.addTab(actionBar.newTab().setText(tabName).setTabListener(this));
		}
		
		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				actionBar.setSelectedNavigationItem(position);
			}
			
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
			}
			
			@Override
			public void onPageScrollStateChanged(int position) {
			}
		});
	}
	

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		viewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
	}
}
