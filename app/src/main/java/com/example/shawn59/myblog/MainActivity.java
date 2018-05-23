package com.example.shawn59.myblog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static android.support.v4.content.ContextCompat.startActivity;


public class MainActivity extends AppCompatActivity {

    EditText editLogin;
    EditText editPassword;
    EditText editFIO;

    Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // элементы
        editLogin = (EditText) findViewById(R.id.idEditLogin);
        editPassword = (EditText) findViewById(R.id.idEditPassword);
    }
    //обработчик
    public void onLoginClick(View v) {
        String login = editLogin.getText().toString();
        String password = editPassword.getText().toString();
        if (!login.equals("") && !password.equals("")) {
            AuthUser request = new AuthUser();
            request.start(login, password);
        }
    }

    public void onRegPageClick(View v) {
        //перек
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

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
                String result = reader.readLine();
                if (result.equals("success")) {
                    Intent intent = new Intent(getApplicationContext(), BlogActivity.class);
                    startActivity(intent);
                } else {
                    /*Toast toast = Toast.makeText(getApplicationContext(),
                            "Пора покормить кота!", Toast.LENGTH_SHORT);
                    toast.show();*/
                }
                Log.d("Result", result);
                con.disconnect();
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
}
