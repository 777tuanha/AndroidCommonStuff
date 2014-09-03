package dev.quinn.androicommonstuff.asynctask;

import android.support.v4.app.FragmentActivity;
import dev.quinn.androicommonstuff.myinterface.IDelegate;

public class MyAsync extends BaseAsync{

	public MyAsync(FragmentActivity act, IDelegate anInterface) {
		super(act, anInterface);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String getDoInBackground(String... params) {
		// TODO Auto-generated method stub
		return null;
	}

}
