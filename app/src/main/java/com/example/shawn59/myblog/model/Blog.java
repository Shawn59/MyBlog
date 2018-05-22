package com.example.shawn59.myblog.model;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Blog extends Thread{
    String link = "";
    String typeRequest = "";
    BufferedReader reader = null;
    public void run() {
        try{
            URL url = new URL(this.link);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setReadTimeout(10000);
            con.setConnectTimeout(10000);
            con.connect();
            //получаем ответ от сервера
            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            if (this.typeRequest == "allGet") {
                StringBuilder buf = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    buf.append(line + "\n");
                }
            }
            /*Log.d("Result", line);*/
            con.disconnect();
        }
        catch(Exception e){
            String d = new String("Exception: " + e.getMessage());
            Log.d("d", "Exception: " + e.getMessage());
        }
    }

    public void start(String user_id, String title, int id) {
        this.start();
    }

    public void allGet() {
        this.link = "http://myshoplh.000webhostapp.com/index.php?fun=getBlogs";
        this.typeRequest = "allGet";
        this.start();
    }
}
