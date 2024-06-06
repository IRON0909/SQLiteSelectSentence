package com.example.relativenet;

import android.app.Application;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class MainApplication extends Application {
    private static ArrayList<PeopleInfo> Persons=new ArrayList<PeopleInfo>();
    private SQLiteDatabase database=null;
    private String tableName="PeopleTable";
    private String DBName="/People.db";
    private int DBVersion=1;

    public static ArrayList<PeopleInfo> getPersons() {
        return Persons;
    }
    public static void setPersons(ArrayList<PeopleInfo> persons) {
        Persons = persons;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //UpdateTable();
        getPeopleFromDataBase();
        IDataBasePath.setDatabase(database);
        //setPeopleFromDataBase();
        //UpdateTable();

    }
    public void getPeopleFromDataBase(){
        if(database==null||!database.isOpen()){
            database=openOrCreateDatabase(getFilesDir()+DBName,Context.MODE_PRIVATE,null);
        }
        //若数据库表中没有数据，则新增一条数据
        PeopleInfo peopleInfo;
        Cursor c = database.rawQuery("SELECT * FROM "+tableName,null);
        //System.out.println(c.getCount());
        if(c.getCount() == 0){
            //database.execSQL("INSERT INTO "+tableName+" VALUES('0001','Mao','炮打资产阶级司令部，我的第一张大字报。','0','5')");
            c = database.rawQuery("SELECT * FROM "+tableName,null);
        }
        while (c.moveToNext()) {
            peopleInfo = new PeopleInfo(Integer.parseInt(c.getString(0).trim()), c.getString(1), c.getString(2), c.getString(3), Integer.parseInt(c.getString(4)));
            Persons.add(peopleInfo);
        }

    }
    public void UpdateTable(){
        if(database==null||!database.isOpen()){
            database=openOrCreateDatabase(getFilesDir()+DBName,Context.MODE_PRIVATE,null);
        }
        String Update0="UPDATE "+tableName+" SET PerInfo = '炮打资产阶级司令部，我的第一张大字报。' WHERE PerIndex = 1;";
        String Update1="UPDATE "+tableName+" SET Name = '毛泽东' WHERE PerIndex = 1;";
        String Update2="UPDATE "+tableName+" SET PerPhoneCode = '0' WHERE PerIndex = 1;";
        database.execSQL(Update0);
        database.execSQL(Update1);
        database.execSQL(Update2);
    }
    public void setPeopleFromDataBase(){
        if(database==null||!database.isOpen()){
            database=openOrCreateDatabase(getFilesDir()+DBName,Context.MODE_PRIVATE,null);
            //database.
            //String tableName="PeopleTable";
            String setData="CREATE TABLE IF NOT EXISTS " +
                    tableName+
                    "(PerIndex INT, " +
                    "Name VARCHAR(60), " +
                    "PerInfo VARCHAR(160), " +
                    "PerPhoneCode VARCHAR(20), " +
                    "LinkStar INT)";
            database.execSQL(setData);


        }
    }



}
