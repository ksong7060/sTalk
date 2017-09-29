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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnRegister;
    private Button mBtnLogin;
    private EditText mId;
    private EditText mPassword;

    private Toolbar toolbar;
    private CheckBox mCheckBox;

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout linear = (LinearLayout) inflater.inflate(R.layout.activity_login, null);
        linear.setBackgroundColor(Color.WHITE);

        super.onCreate(savedInstanceState);
        setContentView(linear);

        mId = (EditText) findViewById(R.id.id);
        mPassword = (EditText) findViewById(R.id.pwd);

        mBtnLogin = (Button) findViewById(R.id.btn_login);
        mBtnRegister = (Button) findViewById(R.id.btn_register);

        mBtnLogin.setOnClickListener(this);
        mBtnRegister.setOnClickListener(this);

        /**
         *
         */
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(LoginActivity.this, "Toolbar Home", Toast.LENGTH_SHORT).show();
                        Intent login = new Intent(LoginActivity.this, LoginActivity.class);
                        startActivity(login);
                    }
                }
        );

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        /**
         *
         */
        mCheckBox = (CheckBox) findViewById(R.id.checkBox);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_register:
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
                break;
            case R.id.btn_login:
                CheckValues(mId.getText().toString(), mPassword.getText().toString());
                break;
            default:
                Intent j = new Intent(LoginActivity.this, SettingsActivity.class);
                startActivity(j);
                break;
        }
    }

    private void CheckValues(String id, String pwd){
        String loginId = mId.getText().toString();
        String loginPwd = mPassword.getText().toString();
        if(id.equals("")){
            Toast.makeText(LoginActivity.this, "There is no ID", Toast.LENGTH_SHORT).show();
        } else if (pwd.equals("")){
            Toast.makeText(LoginActivity.this, "There is no Password", Toast.LENGTH_SHORT).show();
        } else {
            // ===========CheckBox=============
            String chkValue = "";
            if(mCheckBox.isChecked()){
                chkValue = "VIP Membership";
            } else {
                chkValue = "Not VIP Membership";
            }

            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            i.putExtra("u_id", loginId);
            i.putExtra("u_pwd", loginPwd);
            i.putExtra("u_member", chkValue);
            startActivity(i);
        }
    }

    /**
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
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
                Toast.makeText(LoginActivity.this, "About", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(LoginActivity.this, AboutActivity.class);
                startActivity(intent1);
                break;
            case R.id.contact:
                Toast.makeText(LoginActivity.this, "Contact Us", Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(LoginActivity.this, ContactActivity.class);
                startActivity(intent2);
                break;
            default:
                break;
        }
        return false;
    }

}