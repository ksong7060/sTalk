package com.ksong7060.stalk;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

/**
 * Created by ksong7060 on 1/4/2018.
 */

@SuppressLint("ParcelCreator")
public class MessageReceiver extends ResultReceiver {

    private LoginActivity.Message message;

    public MessageReceiver(LoginActivity.Message message){
        super(new Handler());

        this.message = message;
    }

    protected void onReceiveResult(int resultCode, Bundle resultData){
        message.displayMessage(resultCode, resultData);
    }

}
