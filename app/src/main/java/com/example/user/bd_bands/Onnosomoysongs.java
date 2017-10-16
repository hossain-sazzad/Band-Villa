package com.example.user.bd_bands;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Onnosomoysongs extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent mIntent = getIntent();
      final int intValue = mIntent.getIntExtra("value",-1);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onnosomoysongs);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        final ListView lv = (ListView) findViewById(R.id.onnosomoysonglist);
        String[] songs = new String[] { "অন্য সময়", "ভুল জন্ম", "পথ চলা",
                "রুপক একটি গান", "মুখোশ", "রাহুর গ্রাস", "ইতিহাস-সময়/অদৃষ্ট", "কৃত্রিম মানুষ",
                "অবশ অনুভূতির দেয়াল", "অলস সময়ের পারে"};

        ArrayAdapter adapter= new ArrayAdapter(this,R.layout.songlist,R.id.songname,songs);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(intValue==1)
                {
                    Intent openThree = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(openThree);
                }
                else {
                    Intent openThree = new Intent(getApplicationContext(), onnosomoyvideo.class);
                    startActivity(openThree);
                }
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == android.R.id.home) {
            this.finish();
            return true;
        }



        return super.onOptionsItemSelected(item);
    }

}
