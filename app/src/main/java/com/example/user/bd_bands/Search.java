package com.example.user.bd_bands;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Search extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Intent intent = getIntent();
        String query = intent.getStringExtra("query");
        Log.d("query", query);
        String url = "http://10.0.2.2:8000/bands/" + query + "/";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {


                ListView lv = (ListView) findViewById(R.id.bandlist);
                TextView tv = (TextView) findViewById(R.id.bandnull);

                int len = response.length();
                int[] id = new int[len];
                String[] names2 = new String[len];
                String[] types2 = new String[len];
                String[] images2 = new String[len];

                for (int i = 0; i < response.length(); i++) {
                    try {


                        JSONObject jsonObject = (JSONObject) response.get(i);
                        id[i] = jsonObject.getInt("id");
                        Log.d("idnibo ", "" + id[i]);
                        names2[i] = jsonObject.getString("ticker");
                        types2[i] = jsonObject.getString("genre");
                        images2[i] = jsonObject.getString("image");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                if(id.length!=0) {
                    CustomAdapter myadap = new CustomAdapter(getApplicationContext(), id, names2, types2, images2);
                    lv.setAdapter(myadap);
                }
                else {
                    tv.setVisibility(View.VISIBLE);
                    lv.setVisibility(View.GONE);

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("volley log hoise", error);
            }
        });
        com.example.user.bd_bands.AppController.getInstance().addToRequestQueue(jsonArrayRequest);
        String url2 = "http://10.0.2.2:8000/albums/" + query + "/";
        JsonArrayRequest jsonArrayRequest2 = new JsonArrayRequest(url2, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {


                ListView lv = (ListView) findViewById(R.id.albumlistview);
                TextView tv = (TextView) findViewById(R.id.albumnull);

                int[] id;
                int[] ry;
                String[] images2;
                String[] names2;
                int[] songscount;
                ry = new int[response.length()];
                id = new int[response.length()];
                images2 = new String[response.length()];
                names2 = new String[response.length()];
                songscount = new int[response.length()];

                for (int i = 0; i < response.length(); i++) {
                    try {


                        JSONObject jsonObject = (JSONObject) response.get(i);
                        id[i] = jsonObject.getInt("id");
                        names2[i] = jsonObject.getString("name");
                        ry[i] = jsonObject.getInt("releaseyear");
                        images2[i] = jsonObject.getString("image");
                        songscount[i] = jsonObject.getInt("songscount");

                        Log.d("msg", "id" + id[i]);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                if(id.length!=0) {

                    Customadapterforalbumlist2 myadap = new Customadapterforalbumlist2(getApplicationContext(), id, names2, ry, songscount, images2);
                    lv.setAdapter(myadap);
                }
                else {
                    tv.setVisibility(View.VISIBLE);
                    lv.setVisibility(View.GONE);

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("volley log hoise", error);
            }
        });
        com.example.user.bd_bands.AppController.getInstance().addToRequestQueue(jsonArrayRequest2);

        String url3 = "http://10.0.2.2:8000/songs/" + query + "/";
        JsonArrayRequest jsonArrayRequest3 = new JsonArrayRequest(url3, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                ListView lv = (ListView) findViewById(R.id.onnosomoysonglist);

                TextView tv = (TextView) findViewById(R.id.songnull);
                String[] lyric={};
                int[] likes={};

                int[] dislikes={};
                String[] youtubelink={};
                int[] songid;
                String[] names={};
                songid = new int[response.length()];

                names = new String[response.length()];
                lyric = new String[response.length()];
                likes = new int[response.length()];
                dislikes = new int[response.length()];
                youtubelink = new String[response.length()];

                for (int i = 0; i < response.length(); i++) {
                    try {


                        JSONObject jsonObject = (JSONObject) response.get(i);
                        songid[i] = jsonObject.getInt("id");
                        names[i] = jsonObject.getString("name");
                        lyric[i] = jsonObject.getString("lyric");
                        likes[i] = jsonObject.getInt("like");
                        dislikes[i] = jsonObject.getInt("dislike");
                        youtubelink[i] = jsonObject.getString("utubelink");

                        Log.d("songnames", "" + names[i]);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                if(songid.length!=0) {
                    ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), R.layout.songlist, R.id.songname, names);
                    lv.setAdapter(adapter);
                }
                else {
                    tv.setVisibility(View.VISIBLE);
                    lv.setVisibility(View.GONE);

                }


            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("volley log hoise", error);
            }
        });

        com.example.user.bd_bands.AppController.getInstance().addToRequestQueue(jsonArrayRequest3);


    }




}
