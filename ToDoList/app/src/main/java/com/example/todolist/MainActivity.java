package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private static final String SHARED_PREFS_NAME = "MY_SHARED_PREF";
    private EditText item;
    private Button button;
    private ListView itemList;

    private ArrayList<String> items = new ArrayList<String>();
    private ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        items = getList();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        item = findViewById(R.id.item_edit_text);
        button = findViewById(R.id.add_btn);
        itemList = findViewById(R.id.item_list);
        itemList.setAdapter(adapter);

        itemList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int position, long arg3) {

                adapter.remove(items.get(position));
                adapter.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(),"Item Completed", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch(view.getId()) {
                    case R.id.add_btn:
                        String itemEntered = item.getText().toString();
                        adapter.add(itemEntered);
                        item.setText("");
                        break;
                }
            }
        });
    }

    private ArrayList<String> getList() {
        SharedPreferences sp = this.getSharedPreferences(SHARED_PREFS_NAME, Activity.MODE_PRIVATE);

        //NOTE: if shared preference is null, the method return empty Hashset and not null
        Set<String> set = sp.getStringSet("list", new HashSet<String>());

        return new ArrayList<String>(set);
    }

    public boolean saveList() {
        SharedPreferences sp = this.getSharedPreferences(SHARED_PREFS_NAME, Activity.MODE_PRIVATE);
        SharedPreferences.Editor mEdit1 = sp.edit();
        Set<String> set = new HashSet<String>();
        set.addAll(items);
        mEdit1.putStringSet("list", set);
        return mEdit1.commit();
    }

    public void onStop() {
        saveList();
        super.onStop();
    }

}
