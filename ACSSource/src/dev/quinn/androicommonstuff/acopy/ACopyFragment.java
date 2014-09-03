package dev.quinn.androicommonstuff.acopy;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import dev.quinn.androicommonstuff.MainActivity;
import dev.quinn.androicommonstuff.R;
import dev.quinn.androicommonstuff.asynctask.BaseAsync;
import dev.quinn.androicommonstuff.asynctask.MyAsync;
import dev.quinn.androicommonstuff.myinterface.IDelegate;


public class ACopyFragment extends Fragment {

	FragmentManager fManager;
	ArrayList<Object> listObj;
	ListView lv;
	ACopyAdapter adapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.layout_listview, null);

		lv = (ListView) v.findViewById(R.id.i_listview);
		
		listObj = new ArrayList<Object>();
		adapter = new ACopyAdapter(listObj, getActivity());
		lv.setAdapter(adapter);
		adapter.notifyDataSetChanged();

		setupView();

		return v;
	}

	@Override
	public void onResume() {
		super.onResume();
	}


	@Override
	public void onDestroyView() {
		super.onDestroyView();
	}


	private void setupView(){
		IDelegate delegate = new IDelegate() {

			@Override
			public void onSuccess(JSONObject obj) {			
				try {

					JSONArray data = obj.getJSONArray("result");

					for (int i = 0; i < data.length(); i++){
						JSONObject o = data.getJSONObject(i);
						Object g = (Object)o;
						listObj.add(g);
					}

					adapter.notifyDataSetChanged();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			@Override
			public boolean onError(JSONObject obj) {
				// TODO Auto-generated method stub
				return false;
			}
		};

		new MyAsync(getActivity(), delegate).execute();
	}
}
