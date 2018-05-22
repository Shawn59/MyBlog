package com.example.shawn59.myblog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;

import com.example.shawn59.myblog.model.RegisterUser;

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
        if (login != "" && password != "") {
            RegisterUser request = new RegisterUser();
            request.start(fio, login, password);
        } else {

        }
    }
}
