package com.example.relativenet;

import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;

public class SQLiteControl {
    private String ContorlSQL;

    private SQLiteDatabase database=IDataBasePath.getDatabase();


    private String TableName;

    private String UseName;

    private PeopleInfo UsePer;


    public int  insertPersonsInDataBase(ArrayList<PeopleInfo> persons){

        if(database==null||!database.isOpen()){
            return -1;
        }
        TableName="PeopleTable";
        //ArrayList<PeopleInfo> persons=new ArrayList<PeopleInfo>();
        Iterator<PeopleInfo> iterator= persons.iterator();
        while(iterator.hasNext()){
            PeopleInfo pi=iterator.next();
            insertPeopleInfoToDB(pi);
        }
        Toast.makeText(AssessContextDB.getmContextDB(), "添加完成。", Toast.LENGTH_SHORT).show();
        return 0;

    }
    public void insertPeopleInfoToDB(PeopleInfo pi){
        TableName="PeopleTable";
        String SQL =new String("INSERT INTO "+TableName+" VALUES('"+pi.getIndex()+"','"+pi.getName()+"','"+pi.getPerInfo()+"','"+pi.getPerPhoneCode()+"','"+pi.getEvaluateStar()+"')");
        database.execSQL(SQL);
        //Toast.makeText(AssessContextDB.getmContextDB(), "添加完成。", Toast.LENGTH_SHORT).show();
        return;
    }
    public void UpdatePerInfo(int id,PeopleInfo peopleInfo){
        TableName="PeopleTable";
        String str=new String("UPDATE "+TableName+" SET PerIndex="+peopleInfo.getIndex()+", Name= '"+peopleInfo.getName()+"', PerInfo=' "+peopleInfo.getPerInfo()+" ' ,PerPhoneCode=' "+peopleInfo.getPerPhoneCode()+"',LinkStar="+peopleInfo.getEvaluateStar()+" WHERE PerIndex ="+id+" ;");
        database.execSQL(str);
        return;
    }
    public PeopleInfo SelectNameById(int id){
        TableName="PeopleTable";
        PeopleInfo peopleInfo;

        //String str=new String("SELECT Name,PerInfo,PerPhoneCode,LinkStar From "+TableName+" WHERE PerIndex="+id);
        Cursor c = database.rawQuery("SELECT *  FROM "+TableName,null);
        //String str=new String("UPDATE "+TableName+" SET Name= '"+peopleInfo.getName()+"', PerInfo=' "+peopleInfo.getPerInfo()+" ' ,PerPhoneCode=' "+peopleInfo.getPerPhoneCode()+"',LinkStar= "+peopleInfo.getEvaluateStar()+" WHERE PerIndex = "+ id+" ;");
        //database.execSQL(str);
        while (c.moveToNext()) {
            if(Integer.valueOf(c.getString(0).trim())==id){
                peopleInfo = new PeopleInfo(Integer.parseInt(c.getString(0)), c.getString(1), c.getString(2), c.getString(3), Integer.parseInt(c.getString(4)));
                return peopleInfo;
            }
        }
        return null;
    }
    public PeopleInfo SelectNameByName(String PerName){
        TableName="PeopleTable";
        PeopleInfo peopleInfo;
        //String str=new String("UPDATE "+TableName+" SET Name= '"+peopleInfo.getName()+"', PerInfo=' "+peopleInfo.getPerInfo()+" ' ,PerPhoneCode=' "+peopleInfo.getPerPhoneCode()+"',LinkStar= "+peopleInfo.getEvaluateStar()+" WHERE PerIndex = "+ id+" ;");
        //database.execSQL(str);
        Cursor c = database.rawQuery("SELECT * FROM "+TableName,null);
        while (c.moveToNext()) {
            if(c.getString(1)==PerName){
                peopleInfo = new PeopleInfo(Integer.parseInt(c.getString(0)), c.getString(1), c.getString(2), c.getString(3), Integer.parseInt(c.getString(4)));
                return peopleInfo;
            }
        }
        return null;

    }

    public ArrayList<PeopleInfo> readDB(){
        PeopleInfo peopleInfo;
        TableName="PeopleTable";
        ArrayList<PeopleInfo> Persons=new ArrayList<>();
        Cursor c = database.rawQuery("SELECT * FROM "+TableName,null);
        //System.out.println(c.getCount());
        if(c.getCount() == 0){
            //database.execSQL("INSERT INTO "+tableName+" VALUES('0001','Mao','炮打资产阶级司令部，我的第一张大字报。','0','5')");
            c = database.rawQuery("SELECT * FROM "+TableName,null);
        }
        while (c.moveToNext()) {
            peopleInfo = new PeopleInfo(Integer.parseInt(c.getString(0)), c.getString(1), c.getString(2), c.getString(3), Integer.parseInt(c.getString(4)));
            Persons.add(peopleInfo);
        }
        return Persons;
    }
    public void DeleteLine(int id){
        TableName="PeopleTable";
        String sql=new String("DELETE FROM "+TableName  + " WHERE PerIndex = "+id +";");
        database.execSQL(sql);
    }

}

class AssessContextDB{
    private static Context mContextDB;

    public static Context getmContextDB() {
        return mContextDB;
    }

    public static void setmContextDB(Context mContextDB) {
        AssessContextDB.mContextDB = mContextDB;
    }
}
