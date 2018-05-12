package com.example.databaseoperation;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OperationActivity extends AppCompatActivity implements AdapterView.OnItemLongClickListener, AdapterView.OnItemClickListener {

    private TextView textView;
    private ListView listView;
    private Cursor cursor;

    private Dao dao;
    private String UserName;
    private Intent it;
    private int BtnId;
    private SimpleAdapter adapter;
    private List list;
    private Map map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operation);

        init();
        initView();
    }

    private void init() {
        dao = new Dao(this);
        cursor = dao.select();
        it = getIntent();
        BtnId = it.getIntExtra("BtnId", -1);
        textView = findViewById(R.id.Tv_Tip);
        listView = findViewById(R.id.listView);
    }

    private void initView() {
        listView.setAdapter(getAdapter());
        switch (BtnId) {
            case R.id.btn_Delete:
                textView.setText("长按删除数据");
                listView.setOnItemLongClickListener(this);
                break;
            case R.id.btn_Update:
                textView.setText("点击更新数据");
                listView.setOnItemClickListener(this);
                break;
            case R.id.btn_Select:
                textView.setText("全部数据");
                break;
            default:
                Toast.makeText(this, "BtnId Send Error!", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private ListAdapter getAdapter() {
        adapter = new SimpleAdapter(
                this,
                getData(),
                R.layout.item_layout,
                new String[]{"UserName", "PassWord"},
                new int[]{R.id.item_UserName, R.id.item_PassWord});

        return adapter;
    }

    private List<? extends Map<String, ?>> getData() {
        list = new ArrayList<Map<String, Object>>();
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            map = new HashMap<String, String>();
            map.put("UserName", cursor.getString(cursor.getColumnIndex("UserName")));
            map.put("PassWord", cursor.getString(cursor.getColumnIndex("PassWord")));
            list.add(map);
        }
        return list;
    }


    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(OperationActivity.this);
        builder.setMessage("确定删除?");
        builder.setTitle("提示");

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                UserName = cursor.moveToPosition(position) ? cursor.getString(cursor.getColumnIndex("UserName")) : null;
                dao.delete(UserName);
                list.remove(position);
                adapter.notifyDataSetChanged();
            }
        });

        builder.setNegativeButton("取消", null);

        builder.create().show();
        return false;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        it = new Intent(OperationActivity.this, UpdateActivity.class);
        it.putExtra("ItemPosition", position);
        startActivity(it);
    }
}
