package com.example.user.bd_bands;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class EditSong extends AppCompatActivity {
    private static String songname;
    private static String utubelink ;
    private static String lyric;
    private static String songid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_song);


        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        songname = extras.getString("names");
        utubelink = extras.getString("ul");

         lyric = extras.getString("lyric");
        int iid = extras.getInt("id");
        songid=Integer.toString(iid);

        final EditText esong=(EditText) findViewById(R.id.editsongname);
        final EditText elink=(EditText) findViewById(R.id.editutubelink);
        final EditText elyric=(EditText) findViewById(R.id.editlyric);

        esong.setText(songname, TextView.BufferType.EDITABLE);
        elink.setText(utubelink, TextView.BufferType.EDITABLE);
        elyric.setText(lyric, TextView.BufferType.EDITABLE);

        Button b=(Button) findViewById(R.id.editsongupdatebutton);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                songname=esong.getText().toString();
                Log.d("aname",songname);
                utubelink=elink.getText().toString();
                lyric=elyric.getText().toString();



                String url ="http://10.0.2.2:8000/updatesong/";


                StringRequest sq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
                    protected Map<String, String > getParams(){
                        Map<String, String> parr = new HashMap<String, String>();

                        parr.put("name",songname);
                        parr.put("ul", utubelink);
                        parr.put("lyric", lyric);
                        parr.put("id", songid);


                        return parr;

                    }

                };



                AppController.getInstance().addToRequestQueue(sq);
                Toast.makeText(getApplicationContext(), " Successfull!", Toast.LENGTH_LONG).show();
            }
        });



    }
}
