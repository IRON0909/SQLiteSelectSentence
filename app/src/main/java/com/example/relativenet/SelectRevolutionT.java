package com.example.relativenet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerTabStrip;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SelectRevolutionT extends AppCompatActivity {
    private ArrayList<String> TeacherNames;
    private ArrayList<Fragment> Teacheres;
    private ViewPager mViewPager;
    private PagerTabStrip mPagerTabStrip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_revolution_t);
        //mPagerTabStrip.
        findById();
        initList();
    }
    public void findById(){
        mViewPager=findViewById(R.id.SelectTeacher);
        mPagerTabStrip=findViewById(R.id.TeacherNum);
        //Fragment Use
        AssessContext.setmContext(getApplicationContext());
    }
    public void setTitleEvent(){
       // mPagerTabStrip
    }
    public void initList(){
        Teacheres = new ArrayList<>();
        Teacheres.add(new TeacherFragment(0));
        Teacheres.add(new TeacherFragment(1));
        Teacheres.add(new TeacherFragment(2));
        Teacheres.add(new TeacherFragment(3));

        TeacherNames=new ArrayList<>();

        TeacherNames.add("Mao");
        TeacherNames.add("Lenin");
        TeacherNames.add("Marx");
        TeacherNames.add("Stalin");

        MyFragmentAdapter adapter = new MyFragmentAdapter(getSupportFragmentManager(),TeacherNames, Teacheres);
        mViewPager.setAdapter(adapter);
    }
}
class AssessContext{
    private static Context mContext;

    public static Context getmContext() {
        return mContext;
    }

    public static void setmContext(Context mContext) {
        AssessContext.mContext = mContext;
    }
}
