package com.example.relativenet;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

import java.io.IOException;

public class TeacherFragment extends Fragment implements View.OnClickListener {
    private Context mContext;
    private View mView;
    private int Index;

    public TeacherFragment(int index) {
        Index = index;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mContext = AssessContext.getmContext();
        mView = inflater.inflate(R.layout.view_teachers, null);
        ImageView img_teacher;
        GetPerImg getPerImg = new GetPerImg(Index);
        String ImgLink;
        try {
            ImgLink = getPerImg.getTxtStringFromRaw(mContext, R.raw.img_link);
            System.out.println("get "+ImgLink);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        img_teacher = mView.findViewById(R.id.TeacherImage);

        Glide.with(mView)
                .load(ImgLink)
                .into(img_teacher);
        img_teacher.setOnClickListener(this::onClick);
        return mView;
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(mContext, "COME_RALLY!", Toast.LENGTH_SHORT).show();
    }
}
