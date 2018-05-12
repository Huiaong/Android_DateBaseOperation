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
    private Dao dao;
    private Long flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        init();
    }

    private void init() {
        dao = new Dao(this);

        edtUserName = findViewById(R.id.edt_UserName);
        edtPassWord = findViewById(R.id.edt_PassWord);
        btnRegist = findViewById(R.id.btn_Regist);

        btnRegist.setOnClickListener(new btnLinistener());
    }

    private class btnLinistener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            flag = dao.insert(edtUserName.getText().toString(), edtPassWord.getText().toString());
            edtUserName.setText("");
            edtPassWord.setText("");
            if (flag != -1)
                Toast.makeText(AddActivity.this, "插入数据成功", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(AddActivity.this, "插入数据失败", Toast.LENGTH_SHORT).show();
        }
    }
}

