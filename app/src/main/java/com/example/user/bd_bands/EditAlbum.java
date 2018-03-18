package com.example.user.bd_bands;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.MenuItem;

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

import static com.example.user.bd_bands.R.styleable.MenuItem;

public class EditAlbum extends AppCompatActivity {
    private String name;
    private String ry;
    private String totalsong;
    private String id;
    private int sid;
    private int i;
    private static String[] songname;
    private static String[] utubelink ;
    private static String[] lyric;
    private static int[] songid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_album);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
         name = extras.getString("names");
         int iry = extras.getInt("ry");
        ry=Integer.toString(iry);
         int itotalsong = extras.getInt("sc");
        totalsong=Integer.toString(itotalsong);
        int iid = extras.getInt("id");
        id=Integer.toString(iid);

        final EditText albumname=(EditText) findViewById(R.id.editalbumname);
        final EditText releaseyear=(EditText) findViewById(R.id.editreleaseyear);
        final EditText sc=(EditText) findViewById(R.id.edittotalsong);

        albumname.setText(name, TextView.BufferType.EDITABLE);
        releaseyear.setText(ry, TextView.BufferType.EDITABLE);
        sc.setText(totalsong, TextView.BufferType.EDITABLE);

        Button b=(Button) findViewById(R.id.editupdatebutton);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name=albumname.getText().toString();
                Log.d("aname",name);
                ry=releaseyear.getText().toString();
                totalsong=sc.getText().toString();



                String url ="http://10.0.2.2:8000/updatealbum/";


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

                        parr.put("name",name);
                        parr.put("ry", ry);
                        parr.put("sc", totalsong);
                        parr.put("id", id);


                        return parr;

                    }

                };



                AppController.getInstance().addToRequestQueue(sq);
                Toast.makeText(getApplicationContext(), " Successfull!", Toast.LENGTH_LONG).show();
            }
        });



        final ListView lv = (ListView) findViewById(R.id.profilesonglist);



        String url = "http://10.0.2.2:8000/song/" +iid+ "/";


        Log.d("fick", url);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {


            @Override
            public void onResponse(JSONArray response) {




                songname = new String[response.length()];
                songid = new int[response.length()];
                utubelink = new String[response.length()];
                lyric = new String[response.length()];


                for (int i = 0; i < response.length(); i++) {
                    try {


                        JSONObject jsonObject = (JSONObject) response.get(i);

                        songname[i] = jsonObject.getString("name");
                        utubelink[i] = jsonObject.getString("utubelink");
                        lyric[i] = jsonObject.getString("lyric");
                        songid[i] = jsonObject.getInt("id");





                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                ArrayAdapter adapter= new ArrayAdapter(getApplicationContext(),R.layout.songlist,R.id.songname,songname);
                lv.setAdapter(adapter);


            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("volley log hoise", error);
            }
        });

        com.example.user.bd_bands.AppController.getInstance().addToRequestQueue(jsonArrayRequest);



       /* lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

               /* Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.parse("http://10.0.2.2:8000/media/Anmonesumirbd.mobi.mp3"), "audio/mp3");
                startActivity(intent);


                Intent openThree = new Intent(getApplicationContext(),EditSong.class);
                Bundle extras = new Bundle();
                extras.putString("names", songname[position]);
                extras.putString("ul", utubelink[position]);
                extras.putString("lyric", lyric[position]);
                extras.putInt("id", songid[position]);

                openThree.putExtras(extras);
                startActivity(openThree);
                startActivity(openThree);

            }
        });



       /* lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

            String url ="http://10.0.2.2:8000/songdelete/";
             sid=i;

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

                    parr.put("id",Integer.toString(songid[sid]));


                    return parr;

                }

            };



            AppController.getInstance().addToRequestQueue(sq);
            Toast.makeText(getApplicationContext(), " Deleted!", Toast.LENGTH_LONG).show();
            finish();
            startActivity(getIntent());

            return true;
        }
    });

*/
        registerForContextMenu(lv);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v.getId()==R.id.profilesonglist) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_list, menu);
        }
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch(item.getItemId()) {

            case R.id.edit:
                int position=((AdapterView.AdapterContextMenuInfo)info).position;
                Intent openThree = new Intent(getApplicationContext(),EditSong.class);
                Bundle extras = new Bundle();
                extras.putString("names", songname[position]);
                extras.putString("ul", utubelink[position]);
                extras.putString("lyric", lyric[position]);
                extras.putInt("id", songid[position]);

                openThree.putExtras(extras);
                startActivity(openThree);
                startActivity(openThree);
                return true;
            case R.id.delete:
                String url ="http://10.0.2.2:8000/songdelete/";
                 i=((AdapterView.AdapterContextMenuInfo)info).position;

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

                        parr.put("id",Integer.toString(songid[i]));


                        return parr;

                    }

                };



                AppController.getInstance().addToRequestQueue(sq);
                Toast.makeText(getApplicationContext(), " Deleted!", Toast.LENGTH_LONG).show();
                finish();
                startActivity(getIntent());

                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

}
