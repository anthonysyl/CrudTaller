package com.example.crudtaller;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBmain extends SQLiteOpenHelper {
    public static final String DBNAME="crud.db";
    public static final String TABLENAME="personas";
    public static final int VER=1;
    String query;
    public DBmain(@Nullable Context context){
        super(context, DBNAME, null, VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        query="create table "+TABLENAME+"(id integer primary key, fname text, lname text)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
    query="drop table if exists "+TABLENAME+"";
    db.execSQL(query);
    onCreate(db);

    }
}
