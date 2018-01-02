/**
 * Course : PROP3210
 * Date: 9/28/2017
 * Created by Kiwoung Song
 */

package com.ksong7060.stalk;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
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
    private EditText mUsername;
    private EditText mPassword;

    private Toolbar toolbar;
    private CheckBox mCheckBox;

    private String loginUsername = "";
    private String loginPwd = "";

    DBManager manager = new DBManager(this);
    SQLiteDatabase db;

    double initTime;

    private BroadcastReceiver mRegistrationBroadcastReceiver;


    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
    }

    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter("registrationComplete"));
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.STR_PUSH));
    }

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

        mUsername = (EditText) findViewById(R.id.username);
        mPassword = (EditText) findViewById(R.id.pwd);

        mBtnLogin = (Button) findViewById(R.id.btn_login);
        mBtnRegister = (Button) findViewById(R.id.btn_register);

        mBtnLogin.setOnClickListener(this);
        mBtnRegister.setOnClickListener(this);

        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if(intent.getAction().equals(Config.STR_PUSH)){
                    String message = intent.getStringExtra("message");
                    showNotification("sTalkFirebase", message);
                }
            }
        };

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

    private void showNotification(String title, String message) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0 , intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Set sound of notification
        Uri notificationSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder b = new NotificationCompat.Builder(getApplicationContext());
        b.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(message)
                .setSound(notificationSound)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getBaseContext().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1 , b.build());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_register:
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
                break;
            case R.id.btn_login:
                CheckValues(mUsername.getText().toString(), mPassword.getText().toString());
                break;
            default:
                Intent j = new Intent(LoginActivity.this, SettingsActivity.class);
                startActivity(j);
                break;
        }
    }

    private void CheckValues(String username, String pwd){

        if(username.equals("")){
            Toast.makeText(LoginActivity.this, "There is no ID", Toast.LENGTH_SHORT).show();
        } else if (pwd.equals("")){
            Toast.makeText(LoginActivity.this, "There is no Password", Toast.LENGTH_SHORT).show();
        } else {
            loginUsername = mUsername.getText().toString();
            loginPwd = mPassword.getText().toString();

            db = manager.getWritableDatabase();
            Cursor cursor = db.rawQuery("select pid,username, password from tb_member", null);
            String cMid = "";
            String cUsername = "";
            String cPassword = "";
            int failLogin = 0;

            int count = cursor.getCount();
            for (int i = 0; i < count; i++) {
                cursor.moveToNext();
                cMid = cursor.getString(0);
                cUsername = cursor.getString(1);
                cPassword = cursor.getString(2);

                if (loginUsername.equals(cUsername) && loginPwd.equals(cPassword)) {

                    failLogin = 1;

                    // ===========CheckBox=============
                    String chkValue = "";
                    if (mCheckBox.isChecked()) {
                        chkValue = "VIP Membership";
                    } else {
                        chkValue = "Not VIP Membership";
                    }

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("pid", cMid);
                    intent.putExtra("username", loginUsername);
                    intent.putExtra("u_member", chkValue);
                    startActivity(intent);
                }
            }

            if (failLogin == 0) {
                Toast.makeText(LoginActivity.this, "Input the correct information", Toast.LENGTH_SHORT).show();
                mUsername.setText("");
                mPassword.setText("");
            }
            cursor.close();
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

    // back button을 3초 이내에 2번을 누르면 종료 앱 종료
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            if(System.currentTimeMillis() - initTime > 2000){
                Toast toast = Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT);
                toast.show();
            }else {
                finish();
            }
            initTime = System.currentTimeMillis();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}