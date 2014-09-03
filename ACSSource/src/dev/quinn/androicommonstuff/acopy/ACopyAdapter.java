package dev.quinn.androicommonstuff.acopy;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import dev.quinn.androicommonstuff.R;

public class ACopyAdapter extends BaseAdapter{

	ArrayList<Object> listObj;
	LayoutInflater inflater;
	

	public ACopyAdapter(ArrayList<Object> listObjs, Context context){
		this.listObj = listObjs;
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listObj.size();
	}

	@Override
	public Object getItem(int pos) {
		// TODO Auto-generated method stub
		return listObj.get(pos);
	}

	@Override
	public long getItemId(int pos) {
		// TODO Auto-generated method stub
		return pos;
	}

	@Override
	public View getView(int pos, View v, ViewGroup parent) {
		Holder holder;	
		if (v == null){
			holder = new Holder();
			v = inflater.inflate(R.layout.i_item, null);
			
			holder.restName = (TextView) v.findViewById(R.id.tv_i_item);

			v.setTag(holder);
		}
		else {
			holder = (Holder) v.getTag();
		}
		
		Object obj = listObj.get(pos);
		
		display(holder, obj);
		
		return v;
	}
	
	private void display(Holder holder, Object obj){

	}


	private static class Holder{
		TextView restName, couponName, exp; 
	}
	
}
