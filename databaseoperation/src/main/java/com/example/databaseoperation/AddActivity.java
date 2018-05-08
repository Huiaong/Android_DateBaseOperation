package com.example.databaseoperation;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {
    private EditText edtUserName, edtPassWord;
    private Button btnRegist;
    private SQLiteDatabase database;
    private ContentValues contentValues;
    private MyHelper myHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        init();
    }

    private void init() {
        contentValues = new ContentValues();

        myHelper = new MyHelper(this, "Data", null, 1);
        database = myHelper.getWritableDatabase();

        edtUserName = findViewById(R.id.edt_UserName);
        edtPassWord = findViewById(R.id.edt_PassWord);
        btnRegist = findViewById(R.id.btn_Regist);

        btnRegist.setOnClickListener(new btnLinistener());
    }

    private class btnLinistener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            contentValues.put("UserName", edtUserName.getText().toString());
            contentValues.put("PassWord", edtPassWord.getText().toString());
            database.insert("user", null, contentValues);
            edtUserName.setText("");
            edtPassWord.setText("");
            Toast.makeText(AddActivity.this, "插入数据成功", Toast.LENGTH_SHORT).show();
        }
    }
}

