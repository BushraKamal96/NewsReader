package com.example.acer.newsreader;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.acer.newsreader.Adapter.MyAdapter;

public class MainActivity extends AppCompatActivity {

    RecyclerView rview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rview = (RecyclerView) findViewById(R.id.recyclerview);
        rview.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        MyAdapter adapter = new MyAdapter(Common.sources, MainActivity.this);
        rview.setAdapter(adapter);


    }
}


