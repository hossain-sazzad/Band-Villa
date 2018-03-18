package com.example.user.bd_bands;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class UploadSong extends AppCompatActivity {

    private String songname;
    private String albumname;
    private String videolink;
    private String lyric;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_song);


        Button b=(Button) findViewById(R.id.uploadsong);



        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText=(EditText) findViewById(R.id.uploadsongname);
                EditText editText2=(EditText) findViewById(R.id.songalbumname);
                EditText editText3=(EditText) findViewById(R.id.vediolink);
                EditText editText4=(EditText) findViewById(R.id.lyric);

                songname=editText.getText().toString();
                albumname=editText2.getText().toString();
                videolink=editText3.getText().toString();
              //  videolink=videolink.substring(31);
                lyric=editText4.getText().toString();


                String url ="http://10.0.2.2:8000/song/";

Log.d("successful","hoise");
                StringRequest sq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                       if(response.equals("\"null\""))
                       {
                           Toast.makeText(getBaseContext(),"Album does not exist",Toast.LENGTH_LONG).show();
                       }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
                    protected Map<String, String > getParams(){
                        Map<String, String> parr = new HashMap<String, String>();

                        parr.put("name",songname);
                        parr.put("album", albumname);
                        parr.put("vl", videolink);
                        parr.put("lyric",lyric);
                        parr.put("bandid",Integer.toString(SingletonClass.bandid));
Log.d("newbandid",Integer.toString(SingletonClass.bandid));
                        return parr;

                    }

                };



                AppController.getInstance().addToRequestQueue(sq);

Toast.makeText(getApplicationContext(),"uploaded",Toast.LENGTH_SHORT).show();



            }
        });

    }
}
