package dev.quinn.androicommonstuff.asynctask;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import dev.quinn.androicommonstuff.myinterface.IDelegate;
import dev.quinn.androicommonstuff.utils.MyConstant;
import dev.quinn.androicommonstuff.utils.MyDialog;
import dev.quinn.androicommonstuff.utils.MyProgressHUD;



public abstract class BaseAsync extends AsyncTask<String, Void, String>{

	protected IDelegate delegate;
	protected MyProgressHUD hud;
	protected FragmentManager fManager;
	protected boolean isShowHUD;
	protected FragmentActivity act;
	
	public BaseAsync(FragmentActivity act,
			IDelegate anInterface) {
		this.act = act;
		this.hud = new MyProgressHUD(act);
		this.fManager = act.getSupportFragmentManager();
		this.delegate = anInterface;
		this.isShowHUD = true;
				
	}
	

	protected abstract String getDoInBackground(String...params);

	@Override
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub

		return getDoInBackground(params);
	}

	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		if (isShowHUD){
			hud.dismissHUD();
		}
		
		Log.e("Async Task Obj", result);
		
		if(isCancelled()) { return; }
		if (result != null) {
			JSONObject obj = null;
			try {
				obj = new JSONObject(result);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if (obj == null) {
					MyDialog dialog = new MyDialog("Error",
							MyConstant.INTERNET_CONNECT_ERROR, "OK", "", null);
					dialog.show(fManager, "dialog");
				} else {
					if (obj.get("status").equals("success")) {
						this.delegate.onSuccess(obj);
					} else {
						if (!this.delegate.onError(obj)){
							MyDialog dialog = new MyDialog("Error",
									obj.getString("msg"), "OK", "", null);
							dialog.show(fManager, "dialog");
						}
					}
				}

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			MyDialog dialog = new MyDialog("Error",
					MyConstant.INTERNET_CONNECT_ERROR, "OK", "", null);
			dialog.show(fManager, "dialog");
		}
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		if (isShowHUD){
			hud.showHUD();
		}
	}
}
