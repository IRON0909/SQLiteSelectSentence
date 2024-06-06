package com.example.relativenet;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class MyFragmentAdapter extends FragmentPagerAdapter  {
    private ArrayList<String> TeacherNames;
    private ArrayList<Fragment> Teacheres;

    public MyFragmentAdapter(@NonNull FragmentManager fm,ArrayList<String> teacherNames,ArrayList<Fragment> teacheres) {
        super(fm);
        Teacheres=teacheres;
        TeacherNames=teacherNames;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return Teacheres.get(position);
    }

    @Override
    public int getCount() {
        return TeacherNames.size();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return TeacherNames.get(position);
    }
}
