package com.ksong7060.stalk;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by sean on 2018-01-01.
 */

public class sTalkFirebase extends FirebaseInstanceIdService{
    private  static final String TAG = "sTalkFirebase";

    @Override
    public void onTokenRefresh() {
        //super.onTokenRefresh();
        // Get update Token
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "New Token: "+refreshedToken);

        // You can save the token into third party server to do anything you want
    }
}
