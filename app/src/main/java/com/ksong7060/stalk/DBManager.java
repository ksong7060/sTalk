package com.ksong7060.stalk;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by sean on 2017-10-12.
 * 참조 소스1 : https://m.blog.naver.com/PostView.nhn?blogId=javaking75&logNo=140177619818&proxyReferer=https%3A%2F%2Fwww.google.ca%2F
 * 참조 소스2 : http://here4you.tistory.com/49
 * 참조 소스3 : http://itpangpang.xyz/175
 * 참조 소스3 : http://recipes4dev.tistory.com/123
 */

public class DBManager extends SQLiteOpenHelper{
    public static final int DATABASE_VERSION = 1;
    public DBManager(Context context){
        super(context, "stalkdb", null, DATABASE_VERSION);
    }

    // 최초의 한번만 호출됨
    // username, password, phone number, email, gender
    @Override
    public void onCreate(SQLiteDatabase db) {
        String profileSQL = "create table tb_profile(" +
                "pid integer primary key autoincrement," +
                "mid not null," +
                "phoneNumber not null," +
                "email not null," +
                "gender )";
        String memberSQL = "create table tb_member(" +
                "mid integer primary key autoincrement," +
                "username not null," +
                "password not null)";
        String groupMemberSQL = "create table tb_groupMember(" +
                "gmid integer primary key autoincrement," +
                "mid not null," +
                "gid not null )";
        String groupSQL = "create table tb_group(" +
                "gid integer primary key autoincrement," +
                "groupname not null)";
        db.execSQL(profileSQL);
        db.execSQL(memberSQL);
        db.execSQL(groupMemberSQL);
        db.execSQL(groupSQL);
    }

    // db schema가 변경되면 호출됨
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion != DATABASE_VERSION){
            db.execSQL("drop table tb_member"); // 운영 단계에서는 이것을 사용하면 안 되고, 데이터를 보존해야 한다.
            onCreate(db);
        }
    }
}
