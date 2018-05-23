package com.example.shawn59.myblog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.view.View;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RegisterActivity extends AppCompatActivity {

    EditText editFIO;
    EditText editLogin;
    EditText editPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        editLogin = (EditText) findViewById(R.id.idEditLogin);
        editPassword = (EditText) findViewById(R.id.idEditPassword);
        editFIO = (EditText) findViewById(R.id.idEditFIO);
    }

    public void onAuthPage(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onRegister(View v) {
        String login = editLogin.getText().toString();
        String password = editPassword.getText().toString();
        String fio = editFIO.getText().toString();
        if (!login.equals("") && !password.equals("")) {
            RegisterUser request = new RegisterUser();
            request.start(fio, login, password);
        } else {

        }
    }

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
                Log.d("Result", line);
                con.disconnect();
                if (line.equals("success")) {
                    Intent intent = new Intent(getApplicationContext(), BlogActivity.class);
                    startActivity(intent);
                }
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
}
