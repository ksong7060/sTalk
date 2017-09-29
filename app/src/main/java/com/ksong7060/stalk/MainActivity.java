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

public class MainActivity extends AppCompatActivity {

    private TextView loginId;
    private TextView loginPwd;
    private TextView membership;

    private Toolbar toolbar;

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout linear = (LinearLayout) inflater.inflate(R.layout.activity_main, null);
        linear.setBackgroundColor(Color.WHITE);

        super.onCreate(savedInstanceState);
        setContentView(linear);

        loginId = (TextView) findViewById(R.id.lbl_id);
        loginPwd = (TextView) findViewById(R.id.lbl_pwd);
        membership = (TextView) findViewById(R.id.lbl_member);

        Intent intent = getIntent();
        String u_id = intent.getStringExtra("u_id");
        String u_pwd = intent.getStringExtra("u_pwd");
        String u_member = intent.getStringExtra("u_member");

        loginId.setText("Hello, " +u_id);
        loginPwd.setText(u_pwd);
        membership.setText(u_id + " is " + u_member);

        /**
         * Toolbar
         */
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "Toolbar Home", Toast.LENGTH_SHORT).show();
                        Intent main = new Intent(MainActivity.this, MainActivity.class);
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
                Toast.makeText(MainActivity.this, "About", Toast.LENGTH_SHORT).show();
                Intent about = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(about);
                break;
            case R.id.contact:
                Toast.makeText(MainActivity.this, "Contact Us", Toast.LENGTH_SHORT).show();
                Intent contact = new Intent(MainActivity.this, ContactActivity.class);
                startActivity(contact);
                break;
            case R.id.settings:
                Toast.makeText(MainActivity.this, "Settings", Toast.LENGTH_SHORT).show();
                Intent settings = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(settings);
                break;
            case R.id.logout:
                Toast.makeText(MainActivity.this, "Logout Successfully", Toast.LENGTH_SHORT).show();
                Intent logout = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(logout);
                break;
            default:
                break;
        }

        return false;
    }
}