package com.taixinkanghu.app.ui.shopping_page;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.taixinkanghu.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/7/23.
 */
public class ShoppingSrceeningFragment extends Fragment
{
	public ShoppingSrceeningFragment()
	{
		// Required empty public constructor
	}

	private ListView m_screeningListView;
	private ShoppingScreeningList m_shoppingScreeningList = null;
	private ArrayAdapter<String> m_arrayAdapterScreening;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.fragment_shopping_screening, container, false);

		m_screeningListView = (ListView)view.findViewById(R.id.screening_btn_list);

		m_arrayAdapterScreening = new ArrayAdapter<String>(container.getContext(),
														   R.layout.item_text_select_screening,
														   getScreeningListData()
		);
		m_screeningListView.setAdapter(m_arrayAdapterScreening);

		view.setOnClickListener(m_shoppingScreeningList);


//		m_screeningListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
//												   {
//													   @Override
//													   public void onItemClick(AdapterView<?> parent, View view, int position, long id)
//													   {
//														   getFragmentManager().popBackStack();
//														   ShoppingActivity activity = (ShoppingActivity)getActivity();
//
//														   activity.getSrceeningTextView().setText("" + m_screeningListView.getAdapter()
//																														   .getItem(
//																										   position
//																																				)
//																								  );
//													   }
//												   }
//												  );

		return view;
	}


	private List<String> getScreeningListData()
	{
		List<String> data = new ArrayList<String>();
		data.add(getResources().getString(R.string.all_hospital_list));
		data.add(getResources().getString(R.string.auxiliary_food_items));
		data.add(getResources().getString(R.string.relieve_symptoms_items));
		data.add(getResources().getString(R.string.prevention_of_infection_items));
		data.add(getResources().getString(R.string.auxiliary_drainage_items));
		data.add(getResources().getString(R.string.rehabilitation_exercise_equipment_items));
		data.add(getResources().getString(R.string.life_support_items));
		data.add(getResources().getString(R.string.assistant_action_items));
		data.add(getResources().getString(R.string.other_items));
		return data;
	}


}
