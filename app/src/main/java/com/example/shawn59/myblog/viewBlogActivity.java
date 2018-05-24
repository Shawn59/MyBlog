package com.example.shawn59.myblog;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class viewBlogActivity extends AppCompatActivity {
    LinearLayout linLayout;
    Intent in;
    Button btnCreate;
    public static int ID_BLOG = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_blog);
        btnCreate = (Button) findViewById(R.id.idButtonCreate);
        linLayout = (LinearLayout) findViewById(R.id.idLinLayout);
        // получаем параметры переданные через intent
        in = getIntent();
        int idBlog = 0;
        idBlog = in.getIntExtra("id", idBlog);
        ID_BLOG = idBlog;
        this.showRecords(idBlog);
    }
    public void showRecords(final int idBlog) {
        LayoutInflater inflater = getLayoutInflater();
        Sqlite sql = new Sqlite(this);
        Cursor cur = sql.getFull(idBlog);
        cur.moveToFirst();
        while (cur.getPosition() != cur.getCount()) {
            int idRecord = cur.getInt(0);
            String title = cur.getString(1);
            String text = cur.getString(2);
            View item = inflater.inflate(R.layout.activity_blog, linLayout, false);
            //задаем weight and height
            ViewGroup.LayoutParams lpView = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            //заголовок
            TextView txtTitle = new TextView(getApplicationContext());
            txtTitle.setText(title);
            txtTitle.setTextColor(Color.RED);
            txtTitle.setLayoutParams(lpView);
            txtTitle.setGravity(Gravity.CENTER);
            txtTitle.setTextSize(24);
            txtTitle.setId(idRecord);
            //текст
            TextView txtText = new TextView(getApplicationContext());
            txtText.setText(text);
            txtText.setLayoutParams(lpView);
            txtText.setId(idRecord);
            txtText.setTextColor(Color.BLACK);
            txtText.setTextSize(16);
            //изменить
            Button btnEdit = new Button(getApplicationContext());
            btnEdit.setText("Изменить");
            btnEdit.setLayoutParams(lpView);
            btnEdit.setId(idRecord);
            btnEdit.setTextColor(Color.BLACK);
            //удалить
            Button btnDel = new Button(getApplicationContext());
            btnDel.setText("Удалить");
            btnDel.setLayoutParams(lpView);
            btnDel.setId(idRecord);
            btnDel.setTextColor(Color.BLACK);
            btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int idRecord = view.getId();
                    Intent intent = new Intent(getApplicationContext(), RecordActivity.class);
                    intent.putExtra("idRecord", idRecord);
                    intent.putExtra("idBlog", ID_BLOG);
                    startActivity(intent);
                }
            });
            btnDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int idRecord = view.getId();
                    Sqlite sql = new Sqlite(getApplicationContext());
                    sql.delete(idRecord);
                    Intent intent = new Intent(getApplicationContext(), viewBlogActivity.class);
                    intent.putExtra("id", ID_BLOG);
                    startActivity(intent);
                }
            });
            // Добавление элемента
            linLayout.addView(txtTitle);
            linLayout.addView(txtText);
            linLayout.addView(btnEdit);
            linLayout.addView(btnDel);
            cur.moveToNext();
        }
    }

    public void pageIntentRecordClick(View v) {
        Intent intent = new Intent(this, RecordActivity.class);
        intent.putExtra("idBlog", ID_BLOG);
        intent.putExtra("idRecord", -1);
        startActivity(intent);
    }
}
