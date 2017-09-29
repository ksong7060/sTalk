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
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class ContactActivity extends AppCompatActivity {

    private Toolbar toolbar;

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout linear = (LinearLayout) inflater.inflate(R.layout.activity_contact, null);
        linear.setBackgroundColor(Color.WHITE);

        super.onCreate(savedInstanceState);
        setContentView(linear);

        /**
         * Toolbar
         */
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(ContactActivity.this, "Toolbar Home", Toast.LENGTH_SHORT).show();
                        Intent main = new Intent(ContactActivity.this, MainActivity.class);
                        startActivity(main);
                    }
                }
        );

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
    }
}
