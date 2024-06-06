package com.example.relativenet;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.util.ArrayList;

public class PersonInformation extends Activity {

    private int InfoIndex=0;
    private TextView tv_Index,tv_Name,tv_PerInfo,tv_PerPhoneCode;
    private ImageView img_person;
    private ArrayList<PeopleInfo> peopleInfos=MainApplication.getPersons();
    private Button btn_Next,btn_Last;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_information);
        findByIdInMain();
        try {
            setInfo(0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        setButtonNextLast();
    }
    public void findByIdInMain(){
        tv_Index=findViewById(R.id.Index);
        tv_Name=findViewById(R.id.Name);
        tv_PerInfo=findViewById(R.id.PerInfo);
        tv_PerPhoneCode=findViewById(R.id.PerPhoneCode);
        img_person=findViewById(R.id.ImagePerson);
        btn_Next=findViewById(R.id.NextPerson);
        btn_Last=findViewById(R.id.LastPerson);
    }
    public void setButtonNextLast(){
        btn_Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InfoIndex++;
                try {
                    setInfo(InfoIndex);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                SQLiteControl sql=new SQLiteControl();
                //sql.setPersonInDataBase();
            }
        });
        btn_Last.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InfoIndex--;
                if(InfoIndex<0){
                    Toast.makeText(PersonInformation.this,"没有上一个了。",Toast.LENGTH_SHORT).show();
                    InfoIndex=0;
                    return;
                }
                try {
                    setInfo(InfoIndex);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
    public int  useDataSQL() throws IOException {
        //0为增加 1为查找 2为删除 3 为修改
        //AssessContextDB 再插入前必须执行设置Context操作
        AssessContextDB.setmContextDB(getApplicationContext());
        SQLiteControl sqLiteControl=new SQLiteControl();

       if(peopleInfos.size()==0) {
            ArrayList<PeopleInfo> NewpeopleInfos=new ArrayList<PeopleInfo>();
            PeopleInfo peopleInfo1 = new PeopleInfo(1, "毛泽东", "炮打资产阶级司令部，我的第一篇大字报。", "0", 5);
            PeopleInfo peopleInfo2 = new PeopleInfo(2, "Lenin", "帝国主义是资本主义发展的终极形态。", "0", 5);
            NewpeopleInfos.add(peopleInfo1);
            NewpeopleInfos.add(peopleInfo2);
            sqLiteControl.insertPersonsInDataBase(NewpeopleInfos);
        }
        /*PeopleInfo peopleInfo = new PeopleInfo(2, "Lenin", "无产阶级必须马上掌握政权。", "0", 5);
        sqLiteControl.UpdatePerInfo(2,peopleInfo);*/
        PeopleInfo SelectPer;
        SelectPer= sqLiteControl.SelectNameById(2);
        setInfoBySelect(SelectPer);

        //setInfo(SelectPer.getIndex());

       /*sqLiteControl.DeleteLine(1);
        sqLiteControl.DeleteLine(2);*/


        peopleInfos=sqLiteControl.readDB();

        /*SQLiteControl sqLiteControl=new SQLiteControl();
        sqLiteControl.DeleteLine(1);
        sqLiteControl.DeleteLine(2)*/;
        return 0;
    }
    public void setInfoBySelect(PeopleInfo peopleInfo) throws IOException {

        setIndex(peopleInfo);
        tv_Name.setText(peopleInfo.getName());
        tv_PerInfo.setText("个人介绍：\n\t\t"+peopleInfo.getPerInfo());
        PhoneCodeJudge(peopleInfo);
        GetPerImg getPerImg=new GetPerImg(peopleInfo.getIndex()-1);
        String ImgLink=getPerImg.getTxtStringFromRaw(this,R.raw.img_link);
        System.out.println("照片链接"+ImgLink);
        if(ImgLink==null){
            //Toast.makeText(this, "暂时无照片", Toast.LENGTH_SHORT).show();
            return;
        }
        //img_person
        Glide.with(getApplicationContext())
                .load(ImgLink)
                .into(img_person);
    }
    public void setInfo(int infoIndex) throws IOException {
        int Float=-1;
        Float=useDataSQL();
        if(Float==1){
            return;
        }
        if(infoIndex>=peopleInfos.size()){
            InfoIndex --;
            Toast.makeText(this,"没有下一个了。",Toast.LENGTH_SHORT).show();
            return;
        }
        System.out.println("Target:"+infoIndex+" "+"Size"+peopleInfos.size());
        PeopleInfo pi=peopleInfos.get(infoIndex);
        setIndex(pi);
        tv_Name.setText(pi.getName());
        tv_PerInfo.setText("个人介绍：\n\t\t"+pi.getPerInfo());
        PhoneCodeJudge(pi);
        GetPerImg getPerImg=new GetPerImg(infoIndex);
        String ImgLink=getPerImg.getTxtStringFromRaw(this,R.raw.img_link);
        System.out.println("照片链接"+ImgLink);
        if(ImgLink==null){
            //Toast.makeText(this, "暂时无照片", Toast.LENGTH_SHORT).show();
            return;
        }
        //img_person
        Glide.with(getApplicationContext())
                .load(ImgLink)
                .into(img_person);
    }
    public void PhoneCodeJudge(PeopleInfo peopleInfo){
        if(peopleInfo.getPerPhoneCode().length()<10){
            tv_PerPhoneCode.setText("电话号码:"+"无");
        }
        else {
            tv_PerPhoneCode.setText("电话号码:"+peopleInfo.getPerPhoneCode());
        }
    }
    public void  setIndex(PeopleInfo pi){
        if(String.valueOf(pi.getIndex()).length()==1){
            tv_Index.setText("0000"+String.valueOf(pi.getIndex()));
        }else if(String.valueOf(pi.getIndex()).length()==2){
            tv_Index.setText("000"+String.valueOf(pi.getIndex()));
        }else if(String.valueOf(pi.getIndex()).length()==3){
            tv_Index.setText("00"+String.valueOf(pi.getIndex()));
        }else if(String.valueOf(pi.getIndex()).length()==4){
            tv_Index.setText("0"+String.valueOf(pi.getIndex()));
        }else{
            tv_Index.setText(String.valueOf(pi.getIndex()));
        }
    }
}