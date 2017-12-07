/**
 * Course : PROP3210
 * Date: 9/28/2017
 * Created by Kiwoung Song
 */

package com.ksong7060.stalk;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity_LinearLayout extends AppCompatActivity {

    private TextView loginId;
    //private TextView loginPwd;
    private TextView membership;
    private TextView mDate;

    private Toolbar toolbar;

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout linear = (LinearLayout) inflater.inflate(R.layout.activity_main_LinearLayout, null);
        linear.setBackgroundColor(Color.WHITE);

        super.onCreate(savedInstanceState);
        setContentView(linear);

        loginId = (TextView) findViewById(R.id.lbl_id);
        //loginPwd = (TextView) findViewById(R.id.lbl_pwd);
        membership = (TextView) findViewById(R.id.lbl_member);
        mDate = (TextView) findViewById(R.id.lbl_date);

        Intent intent = getIntent();
        String u_id = intent.getStringExtra("u_id");
        //String u_pwd = intent.getStringExtra("u_pwd");
        String u_member = intent.getStringExtra("u_member");

        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String getTime = sdf.format(date);


        loginId.setText(u_id);
        //loginPwd.setText(u_pwd);
        membership.setText(u_id + " is " + u_member);
        mDate.setText(getTime);


        /**
         * Toolbar
         */
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity_LinearLayout.this, "Toolbar Home", Toast.LENGTH_SHORT).show();
                        Intent main = new Intent(MainActivity_LinearLayout.this, MainActivity_LinearLayout.class);
                        startActivity(main);
                    }
                }
        );

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
    }

    /**
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sub, menu);
        return true;
    }

    /**
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.about:
                Toast.makeText(MainActivity_LinearLayout.this, "About", Toast.LENGTH_SHORT).show();
                Intent about = new Intent(MainActivity_LinearLayout.this, AboutActivity.class);
                startActivity(about);
                break;
            case R.id.contact:
                Toast.makeText(MainActivity_LinearLayout.this, "Contact Us", Toast.LENGTH_SHORT).show();
                Intent contact = new Intent(MainActivity_LinearLayout.this, ContactActivity.class);
                startActivity(contact);
                break;
            case R.id.settings:
                Toast.makeText(MainActivity_LinearLayout.this, "Settings", Toast.LENGTH_SHORT).show();
                Intent settings = new Intent(MainActivity_LinearLayout.this, SettingsActivity.class);
                startActivity(settings);
                break;
            case R.id.logout:
                Toast.makeText(MainActivity_LinearLayout.this, "Logout Successfully", Toast.LENGTH_SHORT).show();
                Intent logout = new Intent(MainActivity_LinearLayout.this, LoginActivity.class);
                startActivity(logout);
                break;
            default:
                break;
        }

        return false;
    }
}