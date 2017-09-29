/**
 * Course : PROP3210
 * Date: 9/28/2017
 * Created by Kiwoung Song
 */

package com.ksong7060.stalk;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mGroup;
    private ImageView mSchedule;
    private ImageView mMessage;
    private ImageView mNotification;

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        mGroup = (ImageView) findViewById(R.id.group);
        mSchedule = (ImageView) findViewById(R.id.schedule);
        mMessage = (ImageView) findViewById(R.id.message);
        mNotification = (ImageView) findViewById(R.id.notification);

        mSchedule.setOnClickListener(this);
    }

    /**
     * 
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.group:
                break;
            case R.id.schedule:
                Intent intent = new Intent(MenuActivity.this, ScheduleActivity.class);
                startActivity(intent);
                break;
            case R.id.message:
                break;
            case R.id.notification:
                break;
            default:
                break;
        }
    }
}
