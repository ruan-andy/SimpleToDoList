package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

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

public class MainActivity extends AppCompatActivity {

    private EditText item;
    private Button button;
    private ListView itemList;

    private ArrayList<String> items;
    private ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        items = new ArrayList<String>();
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

}
