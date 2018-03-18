package com.example.user.bd_bands;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Profile extends AppCompatActivity {

    private static String[] albumname;
    private static int[] releaseyear;
    private static int[] totalsong;
    private static int[] albumid;

    private static String bandname;
    private static String fy;
    private static String lu;
    private static String history;
    private static String country;
    private static String genre;
    private static String id;

    private static String image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        bandname = extras.getString("names");
        int iry = extras.getInt("fy");
        fy=Integer.toString(iry);
        lu = extras.getString("lu");
        id=Integer.toString(SingletonClass.bandid);
        country = extras.getString("country");
        history = extras.getString("history");
        image = extras.getString("image");

        genre = extras.getString("genre");




        final ListView lv = (ListView) findViewById(R.id.profilealbumlist);
        TextView textView=(TextView) findViewById(R.id.profilebandname) ;
        final TextView textView2=(TextView) findViewById(R.id.profilecountalbums) ;

        textView.setText(bandname);

        Button b=(Button) findViewById(R.id.updateband) ;
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent openThree = new Intent(getApplicationContext(),EditBand.class);
                Bundle extras = new Bundle();
                extras.putString("names", bandname);
                extras.putString("lu", lu);
                extras.putString("fy", fy);
                extras.putString("country", country);
                extras.putString("genre", genre);
                extras.putString("history", history);


                openThree.putExtras(extras);
                startActivity(openThree);
                startActivity(openThree);





            }
        });

        String url = "http://10.0.2.2:8000/album/" +SingletonClass.bandid+ "/";


        Log.d("fick", url);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {


            @Override
            public void onResponse(JSONArray response) {




                albumname = new String[response.length()];
                releaseyear = new int[response.length()];
                totalsong = new int[response.length()];
                albumid = new int[response.length()];
                textView2.setText(response.length()+" album");

                for (int i = 0; i < response.length(); i++) {
                    try {


                        JSONObject jsonObject = (JSONObject) response.get(i);

                        albumname[i] = jsonObject.getString("name");
                        releaseyear[i] = jsonObject.getInt("releaseyear");
                        totalsong[i] = jsonObject.getInt("songscount");
                        albumid[i] = jsonObject.getInt("id");





                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                ArrayAdapter adapter= new ArrayAdapter(getApplicationContext(),R.layout.songlist,R.id.songname,albumname);
                lv.setAdapter(adapter);


            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("volley log hoise", error);
            }
        });

        com.example.user.bd_bands.AppController.getInstance().addToRequestQueue(jsonArrayRequest);



        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

               /* Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.parse("http://10.0.2.2:8000/media/Anmonesumirbd.mobi.mp3"), "audio/mp3");
                startActivity(intent);
*/

                Intent openThree = new Intent(getApplicationContext(),EditAlbum.class);
                Bundle extras = new Bundle();
                extras.putString("names", albumname[position]);
                extras.putInt("ry", releaseyear[position]);
                extras.putInt("sc", totalsong[position]);
                extras.putInt("id", albumid[position]);

                openThree.putExtras(extras);
                startActivity(openThree);
                startActivity(openThree);

            }
        });
       /* String[] songs = new String[] { "অন্য সময়", "ভুল জন্ম", "পথ চলা",
                "রুপক একটি গান", "মুখোশ", "রাহুর গ্রাস", "ইতিহাস-সময়/অদৃষ্ট", "কৃত্রিম মানুষ",
                "অবশ অনুভূতির দেয়াল", "অলস সময়ের পারে"};




        ArrayAdapter adapter= new ArrayAdapter(this,R.layout.songlist,R.id.songname,songs);
        lv.setAdapter(adapter);

        */


    }


}
