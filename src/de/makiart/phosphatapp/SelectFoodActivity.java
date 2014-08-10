package de.makiart.phosphatapp;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import de.makiart.phosphatapp.data.FoodData;
import de.makiart.phosphatapp.fragment.TabsPagerAdapter;
import de.makiart.phosphatapp.logic.Food;

public class SelectFoodActivity extends FragmentActivity implements ActionBar.TabListener, OnSeekBarChangeListener {

	private ViewPager viewPager;
	private TabsPagerAdapter pagerAdapter;
	private ActionBar actionBar;
	
	private Food selectedFood;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_food);

		FoodData foodData = FoodData.getFoodData(getAssets(), getResources());
		
		actionBar = getActionBar();
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setDisplayShowHomeEnabled(false);
		viewPager = (ViewPager) findViewById(R.id.pager);
		pagerAdapter = new TabsPagerAdapter(getSupportFragmentManager(), foodData);
		
		viewPager.setAdapter(pagerAdapter);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		for (String tabName : foodData.getListOfCategoriesAsStrings()) {
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
		
		// Activity als Lister für die SeekBar setzen
		((SeekBar) findViewById(R.id.seekBarAmountId)).setOnSeekBarChangeListener(this);
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

	/**
	 * Methoden für das Overlay zum Einstellen der Menge
	 */
	
	@Override
	public void onBackPressed() {
		if (selectedFood != null) {
			cancel();
		} else {
			super.onBackPressed();
		}
	}
	
	public void selectFood(Food selected) {
		this.selectedFood = selected;
		LinearLayout amountView = (LinearLayout) findViewById(R.id.amountId);
		amountView.setVisibility(View.VISIBLE);
	}
	
	public void save(View view) {
		Log.i("Fragment", "Save");
		Intent intent = new Intent();
		intent.putExtra(Food.FOOD_ATTRIBUTE_NAME, selectedFood.getName());
		intent.putExtra(Food.FOOD_ATTRIBUTE_PEVALUE, selectedFood.getPeValue());
		intent.putExtra(Food.FOOD_ATTRIBUTE_AMOUNT, selectedFood.getAmount());
		intent.putExtra(Food.FOOD_ATTRIBUTE_AMOUNT_TIMES, selectedFood.getTimes());
		this.setResult(Activity.RESULT_OK, intent);
		this.finish();
	}
	
	public void cancel() {
		this.selectedFood = null;
		LinearLayout amountView = (LinearLayout) findViewById(R.id.amountId);
		amountView.setVisibility(View.INVISIBLE);
	}

	/**
	 * SeekBar Methoden
	 */
	
	@Override
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
		Log.i("SelectFoodActivity.onProgressChanged", "progress " + progress);
		this.selectedFood.setTimes(progress);
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
	}
}
