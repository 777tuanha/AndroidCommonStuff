package dev.quinn.androicommonstuff.myinterface;

import org.json.JSONObject;

public interface IDelegate {

	void onSuccess(JSONObject obj);
	
	// return false to do nothing;
	boolean onError(JSONObject obj);
}
