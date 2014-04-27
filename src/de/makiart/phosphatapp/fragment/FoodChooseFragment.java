package de.makiart.phosphatapp.fragment;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import de.makiart.phosphatapp.R;
import de.makiart.phosphatapp.logic.Food;

public class FoodChooseFragment extends Fragment {

	private List<Food> foodList;
	private LinearLayout foodViewList;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_choose_food, container, false);
		foodViewList = (LinearLayout) view.findViewById(R.id.categoryMealListId);
		createFoodView();
		return view;
	}
	
	private void createFoodView() {
		for (Food foodItem : foodList) {
			foodViewList.addView(createFoodItem(foodItem));
		}
	}
	
	private LinearLayout createFoodItem(Food food) {
		TextView peTxt = new TextView(getActivity());
		peTxt.setText(String.valueOf(food.getPeValue()) + " ");
		
		TextView nameTxt = new TextView(getActivity());
		nameTxt.setText(food.getName() + " ");

		LinearLayout mealListItem = new LinearLayout(getActivity());
		mealListItem.addView(nameTxt);
		mealListItem.addView(peTxt);
		
		return mealListItem;
	}
	
	public void setFoodList(List<Food> foodList) {
		this.foodList = foodList;
	}

}
