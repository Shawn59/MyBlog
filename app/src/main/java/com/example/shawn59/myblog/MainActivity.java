package com.example.shawn59.myblog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    EditText editLogin;
    EditText editPassword;

    TextView textLogin;
    TextView textPassword;

    Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // элементы
        editLogin = (EditText) findViewById(R.id.idEditLogin);
        editPassword = (EditText) findViewById(R.id.idEditPassword);
        textLogin = (TextView) findViewById(R.id.idTextLogin);
        textPassword = (TextView) findViewById(R.id.idTextPassword);
        textPassword = (TextView) findViewById(R.id.idTextPassword);
        /*buttonLogin = (Button) findViewById(R.id.idButtonLogin);*/
    }
    //обработчик
    public void onLoginClick(View v) {
        String login = editLogin.getText().toString();
        String password = editPassword.getText().toString();
        if (login != "" && password != "") {

        }
    }
}
