package de.makiart.phosphatapp.fragment;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import de.makiart.phosphatapp.R;
import de.makiart.phosphatapp.logic.Food;

public class SelectFoodFragment extends Fragment implements OnItemClickListener {

	private View parentView;

	private List<Food> foodList;
	
	private Food selectedFood;
	private int selectedAmount;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		parentView = inflater.inflate(R.layout.fragment_select_food, container, false);
		
		ListAdapter adapter = new ArrayAdapter<Food>(parentView.getContext(), android.R.layout.simple_list_item_1, foodList);
		ListView listView = (ListView) parentView.findViewById(R.id.categoryMealListId);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);
		
		return parentView;
	}
	
	@Override
	public void onItemClick(AdapterView<?> adapter, View view, int row, long arg3) {
		selectedFood = foodList.get(row);
		Log.i("SelectFoodFragment test", "Auswahl von : " + selectedFood.getName());
		
		LinearLayout amountView = (LinearLayout) this.parentView.findViewById(R.id.amountId);
		amountView.setVisibility(View.VISIBLE);
	}

//	public void save(View view) {
//		Intent intent = new Intent();
//		intent.putExtra(Food.FOOD_ATTRIBUTE_NAME, selectedFood.getName());
//		intent.putExtra(Food.FOOD_ATTRIBUTE_PEVALUE, selectedFood.getPeValue());
//		intent.putExtra(Food.FOOD_ATTRIBUTE_AMOUNT, selectedFood.getAmount());
//		getActivity().setResult(Activity.RESULT_OK, intent);
//		getActivity().finish();
//	}
	
	public void setFoodList(List<Food> foodList) {
		this.foodList = foodList;
	}
}
