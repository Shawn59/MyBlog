package com.example.shawn59.myblog.model;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RegisterUser extends Thread{
    String login = "";
    String password = "";
    String fio = "";
    BufferedReader reader = null;
    public void run() {
        try{
            String link = "http://myshoplh.000webhostapp.com/index.php?fun=reg&fio=" + this.fio + "&login=" + this.login + "&password=" + this.password;
            URL url = new URL(link);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setReadTimeout(10000);
            con.setConnectTimeout(10000);
            con.connect();
            //получаем ответ от сервера
            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder buf = new StringBuilder();
            String line = reader.readLine();
            /*while ((line = reader.readLine()) != null) {
                buf.append(line + "\n");
            }*/
            Log.d("Result", line);
            con.disconnect();
        }
        catch(Exception e){
            String d = new String("Exception: " + e.getMessage());
            Log.d("d", "Exception: " + e.getMessage());
        }
    }

    public void start(String login, String password, String fio) {
        this.login = login;
        this.password = password;
        this.fio = fio;
        this.start();
    }
}
