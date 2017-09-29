/**
 * Course : PROP3210
 * Date: 9/28/2017
 * Created by Kiwoung Song
 */


package com.ksong7060.stalk;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private Button mBtnRegisterMember;
    private EditText mRegisterId;
    private EditText mRegisterPwd;
    private EditText mRegisterStudentNum;
    private EditText mRegisterEmail;

    private Toolbar toolbar;

    private RadioGroup mRGroup;
    private  String gender = "";

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout linear = (LinearLayout) inflater.inflate(R.layout.activity_register, null);
        linear.setBackgroundColor(Color.WHITE);

        super.onCreate(savedInstanceState);
        setContentView(linear);

        mBtnRegisterMember = (Button) findViewById(R.id.btn_registerMem);
        mRegisterId = (EditText) findViewById(R.id.edit_re_id);
        mRegisterPwd = (EditText) findViewById(R.id.edit_re_pwd);
        mRegisterStudentNum = (EditText) findViewById(R.id.edit_re_studentNum);
        mRegisterEmail = (EditText) findViewById(R.id.edit_re_email);

        /**
         * RadioGroup
         */
        mRGroup = (RadioGroup) findViewById(R.id.radiogroup);
        mRGroup.setOnCheckedChangeListener(this);

        /**
         * Toolbar
         */
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(RegisterActivity.this, "Toolbar Home", Toast.LENGTH_SHORT).show();
                        Intent main = new Intent(RegisterActivity.this, MainActivity.class);
                        startActivity(main);
                    }
                }
        );

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        /**
         * Event Listener for Register Member
         */
        mBtnRegisterMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mRegisterId.getText().toString().equals("")){
                    Toast.makeText(RegisterActivity.this, "Input ID" , Toast.LENGTH_SHORT).show();
                } else if(mRegisterPwd.getText().toString().equals("")){
                    Toast.makeText(RegisterActivity.this, "Input Password" , Toast.LENGTH_SHORT).show();
                } else if(mRegisterStudentNum.getText().toString().equals("")){
                    Toast.makeText(RegisterActivity.this, "Input Student Number" , Toast.LENGTH_SHORT).show();
                } else if(mRegisterEmail.getText().toString().equals("")){
                    Toast.makeText(RegisterActivity.this, "Input Email" , Toast.LENGTH_SHORT).show();
                } else {
                    new AlertDialog.Builder(RegisterActivity.this)
                            .setTitle("Registration")
                            .setMessage("Do you want to register now? \n" +
                                    "\nID: "+mRegisterId.getText().toString() +
                                    "\nPassword: "+mRegisterPwd.getText().toString() +
                                    "\nStudent Number: "+mRegisterStudentNum.getText().toString() +
                                    "\nEmail: "+mRegisterEmail.getText().toString() +
                                    "\nGender: "+gender)
                            .setIcon(R.drawable.ic_person_add_black_24dp)
                            .setPositiveButton("OK",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            Toast.makeText(RegisterActivity.this, "Registed Successfully", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                            startActivity(intent);
                                        }
                                    })
                            .setNegativeButton("NO",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            Toast.makeText(RegisterActivity.this, "Try it again", Toast.LENGTH_SHORT).show();
                                        }
                                    })
                            .show();
                }
            }
        });
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
                Toast.makeText(RegisterActivity.this, "About", Toast.LENGTH_SHORT).show();
                Intent about = new Intent(RegisterActivity.this, AboutActivity.class);
                startActivity(about);
                break;
            case R.id.contact:
                Toast.makeText(RegisterActivity.this, "Contact Us", Toast.LENGTH_SHORT).show();
                Intent contact = new Intent(RegisterActivity.this, ContactActivity.class);
                startActivity(contact);
                break;
            default:
                break;
        }

        return false;
    }

    /**
     *
     * @param radioGroup
     * @param i
     */
    @Override
    public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
        if(mRGroup == radioGroup)
        {
            if(R.id.man == i) {
                //Toast.makeText(LoginActivity.this, "Man", Toast.LENGTH_SHORT).show();
                gender = "MAN";
            } else if(R.id.woman == i) {
                //Toast.makeText(LoginActivity.this, "Woman", Toast.LENGTH_SHORT).show();
                gender = "WOMAN";
            } else {
                //Toast.makeText(LoginActivity.this, "Check RadioButton", Toast.LENGTH_SHORT).show();
                gender = "Mobody";
            }
        }
    }
}
