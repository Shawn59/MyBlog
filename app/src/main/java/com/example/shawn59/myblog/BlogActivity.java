package com.example.shawn59.myblog;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.ViewGroup.LayoutParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class BlogActivity extends AppCompatActivity {

    LinearLayout linLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog);
        Ask ask = new Ask();
        ask.execute();
    }
    // вход данные, промежуточные и конечные
    class Ask extends AsyncTask <Void, JSONArray, Void> {
        // Начальный метод работает с UI
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        //входные данные(работает в отдельном потоке)
        @Override
        protected Void doInBackground(Void... params) {
            try {
                TimeUnit.SECONDS.sleep(2);
                try{
                    URL url = new URL("http://myshoplh.000webhostapp.com/index.php?fun=getAllBlogs");
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("GET");
                    con.setReadTimeout(10000);
                    con.setConnectTimeout(10000);
                    con.connect();
                    //получаем ответ от сервера
                    //массив обьектов
                    ArrayList<JSONObject> arrayList = new ArrayList<>();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    StringBuilder buf = new StringBuilder();
                    String result = reader.readLine();
                    if (result != "failed") {
                        JSONArray resultJson = new JSONArray(result);
                        publishProgress(resultJson);
                    }
                    Log.d("Result : ", result);
                    con.disconnect();
                }
                catch(Exception e){
                    Log.d("d", "Exception: " + e.getMessage());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        //промежуточный
        @Override
        protected void onProgressUpdate(JSONArray... values) {
            super.onProgressUpdate(values);
            try {
                linLayout = (LinearLayout) findViewById(R.id.idLinear);
                LayoutInflater inflater = getLayoutInflater();
                for (int i = 0; i < values[0].length(); i++) {
                    JSONObject object = values[0].getJSONObject(i);
                    int id = object.getInt("id");
                    String title = object.getString("title");
                    View item = inflater.inflate(R.layout.activity_blog, linLayout, false);
                    //задаем weight and height
                    LayoutParams lpView = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
                    Button btn = new Button(getApplicationContext());
                    btn.setText(title);
                    btn.setLayoutParams(lpView);
                    btn.setId(id);
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            int id = view.getId();
                        }
                    });
                    // Добавление элемента
                    linLayout.addView(btn);
                }
                Log.d("dddd", "" + values[0].length());
            } catch (Exception e) {
                String d = new String("Exception: " + e.getMessage());
                Log.d("d", "Exception: " + e.getMessage());
            }
        }

        //Конечный работает с UI
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
        }
    }

    public void onPageBlogClick(View v) {

    }
}
