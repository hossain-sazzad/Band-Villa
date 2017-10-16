package com.example.user.bd_bands;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Oniketprantorsongs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Intent mIntent = getIntent();
        int intValue = mIntent.getIntExtra("value", 0);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oniketprantorsongs);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        final ListView lv = (ListView) findViewById(R.id.oniketprantorsonglist);
        String[] songs = new String[] { "ছায়ার নিনাদ", "গুনে খাওয়া রোদ", "গন্তব্যহীন",
                "লীন", "অনিকেত প্রান্তর", "পাথার বাগান", "শহিদ স্মরণী", "সৃতিসারক",
                "তোমাকে"};

        ArrayAdapter adapter= new ArrayAdapter(this,R.layout.songlist,R.id.songname,songs);
        lv.setAdapter(adapter);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
