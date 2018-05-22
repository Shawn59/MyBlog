package com.example.shawn59.myblog;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.ViewGroup.LayoutParams;

public class BlogActivity extends AppCompatActivity {

    LinearLayout linLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog);
        int[] mas = {123, 12312, 4353, 34543};

        linLayout = (LinearLayout) findViewById(R.id.idLinear);
        LayoutInflater inflater = getLayoutInflater();

        for (int i = 0; i < mas.length; i++) {
            /*View item = inflater.inflate(R.layout.activity_blog, linLayout, false);*/
            //задаем weight and height
            LayoutParams lpView = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            Button btn = new Button(this);
            btn.setText("Text = " + mas[i]);
            btn.setLayoutParams(lpView);
            btn.setId(mas[i]);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int id = view.getId();
                }
            });

            // Добавление элемента
            linLayout.addView(btn);
        }
    }

    public void onPageBlogClick(View v) {

    }



}
