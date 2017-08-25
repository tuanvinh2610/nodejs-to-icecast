package com.example.tuanvinh.weatherapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListWeatherActivity extends AppCompatActivity {


    private ListView listView;
//    DatabaseHandler databaseHandler;

//    TextView result1;
//
//    EditText cityName;

    private final List<Weather> weatherList = new ArrayList<Weather>();
    private ArrayAdapter<Weather> listViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_weather);

        // Get ListView object from xml
//        listView = (ListView) findViewById(R.id.listView);
//
//        DatabaseHandler db = new DatabaseHandler(this);
////        db.createDefaultNotesIfNeed();
//
//        List<Weather> list=  db.getAllWeather();
//        this.weatherList.addAll(list);
//
//        this.listViewAdapter = new ArrayAdapter<Weather>(this,
//                android.R.layout.simple_list_item_1, android.R.id.text1, this.weatherList);
//
//
//        // Đăng ký Adapter cho ListView.
//        this.listView.setAdapter(this.listViewAdapter);
//
//        // Đăng ký Context menu cho ListView.
//        registerForContextMenu(this.listView);
    }
}
