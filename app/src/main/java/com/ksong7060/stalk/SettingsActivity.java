/**
 * Course : PROP3210
 * Date: 9/28/2017
 * Created by Kiwoung Song
 */

package com.ksong7060.stalk;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnContactList;
    private Button mBtnWebSite;
    private Button mBtnPhone;

    private Toolbar toolbar;

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout linear = (LinearLayout) inflater.inflate(R.layout.activity_settings, null);
        linear.setBackgroundColor(Color.WHITE);

        super.onCreate(savedInstanceState);
        setContentView(linear);

        mBtnContactList = (Button) findViewById(R.id.btn_contactlist);
        mBtnWebSite = (Button) findViewById(R.id.btn_website);
        mBtnPhone = (Button) findViewById(R.id.btn_phone);

        mBtnContactList.setOnClickListener(this);
        mBtnWebSite.setOnClickListener(this);
        mBtnPhone.setOnClickListener(this);

        // =======Toolbar===============================================
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(SettingsActivity.this, "Toolbar Home", Toast.LENGTH_SHORT).show();
                        Intent main = new Intent(SettingsActivity.this, MainActivity.class);
                        startActivity(main);
                    }
                }
        );

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
    }

    /**
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        Intent i = null;
        Uri uri = null;
        switch (v.getId()){
            case R.id.btn_contactlist:
                uri = Uri.parse("content://contacts/people/");
                i = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(i);
                break;
            case R.id.btn_website:
                uri = Uri.parse("https://developer.android.com/develop/index.html");
                i = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(i);
                break;
            case R.id.btn_phone:
                uri = Uri.parse("tel:111-222-3333");
                i = new Intent(Intent.ACTION_DIAL, uri);
                startActivity(i);
                break;
            default:
                break;
        }
    }
}