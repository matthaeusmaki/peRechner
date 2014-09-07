package de.makiart.phosphatapp.fragment;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import de.makiart.phosphatapp.R;
import de.makiart.phosphatapp.SelectFoodActivity;
import de.makiart.phosphatapp.data.Edibles;

public class SelectFoodFragment extends Fragment implements OnItemClickListener {

	private View parentView;

	private List<Edibles> foodList;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		parentView = inflater.inflate(R.layout.fragment_select_food, container, false);
		
		ListAdapter adapter = new ArrayAdapter<Edibles>(parentView.getContext(), android.R.layout.simple_list_item_1, foodList);
		ListView listView = (ListView) parentView.findViewById(R.id.categoryMealListId);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);
		
		return parentView;
	}
	
	@Override
	public void onItemClick(AdapterView<?> adapter, View view, int row, long arg3) {
		((SelectFoodActivity) getActivity()).selectFood(foodList.get(row));
	}

	public void setFoodList(List<Edibles> foodList) {
		this.foodList = foodList;
	}
}
