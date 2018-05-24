package com.example.shawn59.myblog;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class viewBlogActivity extends AppCompatActivity {
    LinearLayout linLayout;
    Intent in;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_blog);
        in = getIntent();
        int idf = 0;
        int ids = in.getIntExtra("id", idf);
        linLayout = (LinearLayout) findViewById(R.id.idLinLayout);
        LayoutInflater inflater = getLayoutInflater();
        Sqlite sql = new Sqlite(this);
        Cursor cur = sql.getFull(ids);
        if (cur.moveToFirst()) {
            int id = cur.getInt(0);
            String title = cur.getString(1);
            String text = cur.getString(2);
            View item = inflater.inflate(R.layout.activity_blog, linLayout, false);
            //задаем weight and height
            ViewGroup.LayoutParams lpView = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            TextView txtTitle = new TextView(getApplicationContext());
            txtTitle.setText(title);
            txtTitle.setTextColor(Color.RED);
            txtTitle.setLayoutParams(lpView);
            txtTitle.setId(id);
            TextView txtText = new TextView(getApplicationContext());
            txtText.setText(text);
            txtText.setLayoutParams(lpView);
            txtText.setId(id);
            txtText.setTextColor(Color.BLACK);
            /*txt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int id = view.getId();
                }
            });*/
            // Добавление элемента
            linLayout.addView(txtTitle);
            linLayout.addView(txtText);
        }
    }
}
