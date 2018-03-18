package com.example.user.bd_bands;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;

import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

//import retrofit2.Call;
//import retrofit2.Response;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static String[] bandname;
    private static String[] fy;
    private static String[] lu;
    private static String[] history;
    private static String[] country;
    private static String[] genre;
    private static String[] image;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
       // apiInterface = RetrofitApiClient.getClient().create(ApiInterface.class);
        super.onCreate(savedInstanceState);
        final Context ct=this.getApplicationContext();
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        setSupportActionBar(toolbar);


        String[] names = {"Artcell", "James","Aurthohin", "Warfaze", "LRB"};
        String[] types = {"Rock and Metal", "Rock", "Rock and Metal","Rock", "pop"};

        String url="http://10.0.2.2:8000/band/";
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                ListView lv= (ListView) findViewById(R.id.bandlist);
                int len=response.length();
                int[] id=new int[len];
                String[] names2 = new String[len];
                String[] types2 = new String[len];
                String[] images2= new String[len];

                for(int i=0;i<response.length();i++)
                {
                    try {


                        JSONObject jsonObject=(JSONObject) response.get(i);
                       id[i]=jsonObject.getInt("id");
                        Log.d("idnibo ",""+id[i]);
                      names2[i]=jsonObject.getString("ticker");
                       types2[i]=jsonObject.getString("genre");
                        images2[i]=jsonObject.getString("image");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                CustomAdapter myadap = new CustomAdapter(ct,id, names2,types2, images2);
                lv.setAdapter(myadap);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("volley log hoise",error);
            }
        });
        com.example.user.bd_bands.AppController.getInstance().addToRequestQueue(jsonArrayRequest);





        int[] img = {R.drawable.artcell, R.drawable.james, R.drawable.aurthohin, R.drawable.warfaze,R.drawable.lrb};


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();


              String url ="http://10.0.2.2:8000/band/";







            }

        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);
       /* MenuItem item=(MenuItem)findViewById(R.id.uploadalbum);
        MenuItem item2=(MenuItem)findViewById(R.id.uploadsong);
        if(SingletonClass.loginstate==1)
        {
            item.setVisible(true);
            item2.setVisible(true);
        }*/
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu( Menu menu) {
        getMenuInflater().inflate( R.menu.main, menu);

        MenuItem myActionMenuItem = menu.findItem( R.id.search);
        final SearchView searchView = (SearchView) myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                Intent myIntent = new Intent(getApplicationContext(),Search.class);

                myIntent.putExtra("query", query);

                startActivity(myIntent);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
              /* if (TextUtils.isEmpty(newText)) {
                    adapter.filter("");
                    listView.clearTextFilter();
                } else {
                    adapter.filter(newText);
                }*/
                return true;
            }
        });

        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.bandexplore) {
            if(SingletonClass.loginstate==0) {
                Intent intent = new Intent(this.getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }

            else {

                String url2 ="http://10.0.2.2:8000/user/"+SingletonClass.userid+"/";

                Log.d("newbandd",Integer.toString(SingletonClass.userid));
                StringRequest sq = new StringRequest(Request.Method.POST, url2, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        SingletonClass.bandid = Integer.parseInt(response);
                        Log.d("newband", response);


                        String url = "http://10.0.2.2:8000/bands/" + SingletonClass.bandid + "/";


                        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {


                            @Override
                            public void onResponse(JSONArray response) {


                                if (response.length() == 0) {
                                    Intent intent = new Intent(getApplicationContext(), CreateBand.class);
                                    startActivity(intent);
                                } else {

                                    bandname = new String[response.length()];
                                    lu = new String[response.length()];
                                    fy = new String[response.length()];
                                    country = new String[response.length()];
                                    history = new String[response.length()];
                                    genre = new String[response.length()];
                                    image = new String[response.length()];


                                    for (int i = 0; i < response.length(); i++) {
                                        try {


                                            JSONObject jsonObject = (JSONObject) response.get(i);

                                            bandname[i] = jsonObject.getString("ticker");
                                            lu[i] = jsonObject.getString("lineup");
                                            fy[i] = jsonObject.getString("formationyear");
                                            history[i] = jsonObject.getString("history");
                                            country[i] = jsonObject.getString("country");
                                            genre[i] = jsonObject.getString("genre");
                                            image[i] = jsonObject.getString("image");


                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }


                                    Intent openThree = new Intent(getApplicationContext(), Profile.class);
                                    Bundle extras = new Bundle();
                                    extras.putString("names", bandname[0]);
                                    extras.putString("lu", lu[0]);
                                    extras.putString("fy", fy[0]);
                                    extras.putString("country", country[0]);
                                    extras.putString("genre", genre[0]);
                                    extras.putString("history", history[0]);
                                    extras.putString("image", image[0]);

                                    openThree.putExtras(extras);
                                    startActivity(openThree);
                                    startActivity(openThree);

                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                VolleyLog.d("volley log hoise", error);
                            }
                        });

                        com.example.user.bd_bands.AppController.getInstance().addToRequestQueue(jsonArrayRequest);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {


                };
                AppController.getInstance().addToRequestQueue(sq);

            }
        } else if (id == R.id.nav_send) {
            Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
            startActivity(intent);

        }
        else if(id==R.id.uploadalbum)
        {

            if(SingletonClass.loginstate==0)
            {
                Toast.makeText(getApplicationContext(),"You are not logged in",Toast.LENGTH_SHORT).show();
            }
            else {
                Intent intent = new Intent(getApplicationContext(), UploadAlbum.class);
                startActivity(intent);
            }
        }
        else if(id==R.id.uploadsong)
        {
            if(SingletonClass.loginstate==0)
            {
                Toast.makeText(getApplicationContext(),"You are not logged in",Toast.LENGTH_SHORT).show();
            }
            else {
                Intent intent = new Intent(getApplicationContext(), UploadSong.class);
                startActivity(intent);
            }
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}


//AIzaSyAZHvVoVrzEOyLTrhaZXbjTxkob_UljUv8