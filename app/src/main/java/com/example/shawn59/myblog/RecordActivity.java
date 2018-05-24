package com.example.shawn59.myblog;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class RecordActivity extends AppCompatActivity {

    Intent in;
    Button btnOper;
    Button btnCancel;
    EditText edtTitle;
    EditText edtText;
    public static int ID_BLOG = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        btnOper = (Button) findViewById(R.id.idButtonOper);
        btnCancel = (Button) findViewById(R.id.idButtonCancel);
        edtTitle = (EditText) findViewById(R.id.idEditTitile);
        edtText = (EditText) findViewById(R.id.idEditText);
        in = getIntent();
        int idRecord = 0;
        idRecord = in.getIntExtra("idRecord", idRecord);
        ID_BLOG = in.getIntExtra("idBlog", ID_BLOG);
        //создание
        if (idRecord != -1) {
            Sqlite sql = new Sqlite(this);
            Cursor cur = sql.getOneRecord(idRecord);
            if (cur.moveToFirst()) {
                int id = cur.getInt(0);
                edtTitle.setText(cur.getString(1));
                edtText.setText(cur.getString(2));
            }
        }
        btnOper.setId(idRecord);
        btnOper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int idRecord = view.getId();
                String title = ((EditText) findViewById(R.id.idEditTitile)).getText().toString();
                String text = ((EditText) findViewById(R.id.idEditText)).getText().toString();
                Sqlite sql = new Sqlite(getApplicationContext());
                if (idRecord == -1) {
                    //создание
                    sql.insert(title, text, ID_BLOG);
                } else {
                    sql.update(idRecord, title, text, ID_BLOG);
                    //редактирование
                }
                Intent intent = new Intent(getApplicationContext(), viewBlogActivity.class);
                intent.putExtra("id", ID_BLOG);
                startActivity(intent);
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), viewBlogActivity.class);
                intent.putExtra("id", ID_BLOG);
                startActivity(intent);
            }
        });
    }
}
