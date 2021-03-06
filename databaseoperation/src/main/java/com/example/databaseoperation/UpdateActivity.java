package com.example.databaseoperation;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView Tv_UserName;
    private EditText edt_PassWord;
    private Button btn_Update;
    private Intent it;
    private int ItemPosition;
    private Cursor cursor;
    private Dao dao;
    private String UserName;
    private String PassWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        init();
    }

    private void init() {
        dao = new Dao(this);
        cursor = dao.select();
        Tv_UserName = findViewById(R.id.Update_tv_UserName);
        edt_PassWord = findViewById(R.id.Update_edt_PassWord);
        btn_Update = findViewById(R.id.Update_btn_Update);

        it = getIntent();
        ItemPosition = it.getIntExtra("ItemPosition",0);

        if (cursor.moveToPosition(ItemPosition)) {
            Tv_UserName.setText(cursor.getString(cursor.getColumnIndex("UserName")));
            edt_PassWord.setHint(cursor.getString(cursor.getColumnIndex("PassWord")));
        }
        btn_Update.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        UserName = cursor.moveToPosition(ItemPosition) ? cursor.getString(cursor.getColumnIndex("UserName")) : null;
        PassWord = edt_PassWord.getText().toString();
        dao.updata(UserName,PassWord);
        Toast.makeText(UpdateActivity.this, "数据更新成功", Toast.LENGTH_SHORT).show();
    }
}
