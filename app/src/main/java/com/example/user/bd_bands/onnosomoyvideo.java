package com.example.user.bd_bands;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class onnosomoyvideo extends YouTubeBaseActivity{
YouTubePlayerView youTubePlayerView;
    Button b;
    TextView t;
    TextView t2;
    ImageButton imageButton;


    YouTubePlayer.OnInitializedListener onInitializedListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String lyric = extras.getString("lyric");
        final String utubelink = extras.getString("utubelink");
        final String songid = extras.getString("sid");

        setContentView(R.layout.activity_onnosomoyvideo);
        b=(Button) findViewById(R.id.playbutton);
         t2=(TextView) findViewById(R.id.likecount);

        String url ="http://10.0.2.2:8000/likecount/"+songid+"/";


        StringRequest sq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
             t2.setText(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            protected Map<String, String > getParams(){
                Map<String, String> parr = new HashMap<String, String>();

                parr.put("uid",Integer.toString(SingletonClass.userid));


                return parr;

            }

        };



        AppController.getInstance().addToRequestQueue(sq);

         imageButton=(ImageButton) findViewById(R.id.likebutton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(SingletonClass.loginstate==0)
                {
                    Toast.makeText(getApplicationContext(),"you are not logged in",Toast.LENGTH_SHORT).show();
                }
                String url ="http://10.0.2.2:8000/likes/"+songid+"/";


                StringRequest sq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        t2.setText(response);
                        finish();
                        startActivity(getIntent());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
                    protected Map<String, String > getParams(){
                        Map<String, String> parr = new HashMap<String, String>();

                        parr.put("uid",Integer.toString(SingletonClass.userid));

                        Log.d("userdata", Integer.toString(SingletonClass.userid));
                        return parr;

                    }

                };


            }
        });


        t=(TextView) findViewById(R.id.lyricid);
        t.setText(lyric);
        youTubePlayerView=(YouTubePlayerView) findViewById(R.id.youtube_playerview);
        onInitializedListener=new YouTubePlayer.OnInitializedListener(){
//"lLvG-_vv3Ok"
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
youTubePlayer.loadVideo(utubelink);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                youTubePlayerView.initialize(Playerconfig.api_key,onInitializedListener);
            }
        });
    }
}
