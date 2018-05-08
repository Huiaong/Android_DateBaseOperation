package com.example.databaseoperation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnAdd,btnDelete,btnUpdate,btnSelect;
    private Intent it;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {

        btnAdd = findViewById(R.id.btn_Add);
        btnDelete = findViewById(R.id.btn_Delete);
        btnUpdate = findViewById(R.id.btn_Update);
        btnSelect = findViewById(R.id.btn_Select);

        btnAdd.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnSelect.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_Add:
                it = new Intent(MainActivity.this,AddActivity.class);
                break;
            case R.id.btn_Delete:
            case R.id.btn_Update:
            case R.id.btn_Select:
                it = new Intent(MainActivity.this,OperationActivity.class);
                it.putExtra("BtnId",v.getId());
                break;
        }
        startActivity(it);
    }
}
