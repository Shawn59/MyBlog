package com.example.shawn59.myblog.model;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import android.util.Log;

public class AuthUser extends Thread {
    String login = "";
    String password = "";
    BufferedReader reader = null;
    public void run() {
        try{
            String link = "http://myshoplh.000webhostapp.com/index.php?fun=auth&login=" + this.login + "&password=" + this.password;
            URL url = new URL(link);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setReadTimeout(10000);
            con.setConnectTimeout(10000);
            con.connect();
            //получаем ответ от сервера
            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder buf = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                buf.append(line + "\n");
            }
            Log.d("Result", buf.toString());
        }
        catch(Exception e){
            String d = new String("Exception: " + e.getMessage());
            Log.d("d", "Exception: " + e.getMessage());
        }
    }

    public void start(String login, String password) {
        this.login = login;
        this.password = password;
        this.start();
    }
}
