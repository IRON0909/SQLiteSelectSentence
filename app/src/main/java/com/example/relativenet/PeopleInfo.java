package com.example.relativenet;

import android.media.Image;

public class PeopleInfo {
    private int Index;
    private  String Name;
    private Image PerImage;
    private String PerInfo;
    private String PerPhoneCode;
    private int EvaluateStar;
    public PeopleInfo(){

    }

    public PeopleInfo(int index, String name, String perInfo, String perPhoneCode, int evaluateStar) {
        Index = index;
        Name = name;
        PerInfo = perInfo;
        PerPhoneCode = perPhoneCode;
        EvaluateStar=evaluateStar;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Image getPerImage() {
        return PerImage;
    }

    public void setPerImage(Image perImage) {
        PerImage = perImage;
    }

    public String getPerInfo() {
        return PerInfo;
    }

    public void setPerInfo(String perInfo) {
        PerInfo = perInfo;
    }

    public String getPerPhoneCode() {
        return PerPhoneCode;
    }

    public void setPerPhoneCode(String perPhoneCode) {
        PerPhoneCode = perPhoneCode;
    }

    public int getEvaluateStar() {
        return EvaluateStar;
    }

    public void setEvaluateStar(int evaluateStar) {
        EvaluateStar = evaluateStar;
    }

    public int getIndex() {
        return Index;
    }

    public void setIndex(int index) {
        Index = index;
    }
}
