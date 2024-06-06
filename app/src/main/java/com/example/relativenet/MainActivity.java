package com.example.relativenet;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private Button ListButton,btn_Select;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findByIdInMain();
        setListButton();
    }
    public void findByIdInMain(){
        ListButton=findViewById(R.id.List);
        btn_Select=findViewById(R.id.Select);
    }
    public void setListButton(){
        ListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,PersonInformation.class);
                startActivity(intent);
            }
        });
        btn_Select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,SelectRevolutionT.class);
                startActivity(intent);
            }
        });
    }
}

/*
*   <LinearLayout
        android:layout_marginTop="20dp"
        android:layout_width="match_parent"
        android:layout_height="200dp"
        android:orientation="horizontal">
        <LinearLayout
            android:layout_width="200dp"
            android:layout_height="150dp"
            android:orientation="vertical">
            <TextView
                android:layout_marginTop="20dp"
                android:layout_width="200dp"
                android:layout_height="60dp"
                android:id="@+id/Index"
                android:textStyle="bold"
                android:textAlignment="textStart"
                android:text="001"
                android:textSize="30sp"
                android:paddingLeft="20dp"
                />
            <TextView
                android:layout_width="200dp"
                android:layout_height="80dp"
                android:id="@+id/Name"
                android:textStyle="bold"
                android:text="Mao"
                android:textSize="45sp"
                android:paddingLeft="20dp"
            />
        </LinearLayout>
        <ImageView
            android:id="@+id/ImagePerson"
            android:layout_marginLeft="18dp"
            android:layout_width="180dp"
            android:layout_height="200dp">

        </ImageView>
    </LinearLayout>*/