package com.example.user.bd_bands;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;




public class Artcellinfo extends AppCompatActivity {


    public  static int bandid=1;



    public int getid()
    {

        Log.d("bandid2", " "+bandid);
        return  bandid;
    }
    public Intent getintent()
    {
        return  getIntent();
    }

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artcellinfo);


        Intent intent=getIntent();
       bandid= intent.getIntExtra("value",-1);
        Log.d("bandid"," "+bandid);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

public void onnosomoyonclick(View v)
{


    Intent myIntent = new Intent(getApplicationContext(),Onnosomoysongs.class);
    myIntent.putExtra("value", 2);
    startActivity(myIntent);
}

    public void oniketprantoronclick(View v)
    {
        Integer val=2;
        Intent myIntent = new Intent(getApplicationContext(),Oniketprantorsongs.class);
        myIntent.putExtra("value", val.toString());
        startActivity(myIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_artcellinfo, menu);
        return true;
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        Artcellinfo a= new Artcellinfo();
        //int bandid=a.getid();
        int[] id;
       int[] ry;
        String[] images2;
        String[] names2;
        int[] songscount;
        int isend=0;


        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {

        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            final Context ct= this.getContext();



            if (getArguments().getInt(ARG_SECTION_NUMBER) == 1) {
                View rootView = inflater.inflate(R.layout.fragment_historyfragment, container, false);
                Artcellinfo a=new Artcellinfo();

                // if(isend==0) {
                int bandid;
                bandid = a.getid();
                final TextView textView=(TextView) rootView.findViewById(R.id.historyid);
                final ImageView imageView=(ImageView) rootView.findViewById(R.id.imageid);

                String url="http://10.0.2.2:8000/bands/"+bandid+"/";
                JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        int len=response.length();
                        int[] id=new int[len];
                        String[] history = new String[len];
                        String[] image = new String[len];


                        for(int i=0;i<response.length();i++)
                        {
                            try {


                                JSONObject jsonObject=(JSONObject) response.get(i);
                                id[i]=jsonObject.getInt("id");
                                Log.d("idnibo ",""+id[i]);
                                history[i]=jsonObject.getString("history");
                                image[i]=jsonObject.getString("image");


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                      textView.setText(history[0]);
                        Picasso.with(getContext()).load("http://10.0.2.2:8000"+image[0]).into(imageView);

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d("volley log hoise",error);
                    }
                });
                com.example.user.bd_bands.AppController.getInstance().addToRequestQueue(jsonArrayRequest);

                return rootView;
            } else {
                if (getArguments().getInt(ARG_SECTION_NUMBER) == 2) {
                    View rootView = inflater.inflate(R.layout.fragment_lineupfragment, container, false);
                    Artcellinfo a = new Artcellinfo();

                    // if(isend==0) {
                    int bandid;
                    bandid = a.getid();
                    final TextView textView = (TextView) rootView.findViewById(R.id.lineupid);
                    final ImageView imageView = (ImageView) rootView.findViewById(R.id.lineupimageid);

                    String url = "http://10.0.2.2:8000/bands/" + bandid + "/";
                    Log.d("urllll", url);
                    JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {

                            int len = response.length();
                            int[] id = new int[len];
                            String[] lineup = new String[len];
                            String[] image = new String[len];


                            for (int i = 0; i < response.length(); i++) {
                                try {


                                    JSONObject jsonObject = (JSONObject) response.get(i);
                                    id[i] = jsonObject.getInt("id");
                                    Log.d("idnibo ", "" + id[i]);
                                    lineup[i] = jsonObject.getString("lineup");
                                    image[i] = jsonObject.getString("image");


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            textView.setText(lineup[0]);
                            Log.d("lineup", lineup[0]);
                            Picasso.with(getContext()).load("http://10.0.2.2:8000" + image[0]).into(imageView);

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            VolleyLog.d("volley log hoise", error);
                        }
                    });
                    AppController.getInstance().addToRequestQueue(jsonArrayRequest);
                    return rootView;
                } else if (getArguments().getInt(ARG_SECTION_NUMBER) == 3) {

                    final View rootView = inflater.inflate(R.layout.fragment_albumlist, container, false);

                    final ListView lv = (ListView) rootView.findViewById(R.id.albumlistview);
                    Artcellinfo a = new Artcellinfo();

                    // if(isend==0) {
                    int bandid;
                    bandid = a.getid();
                    String url = "http://10.0.2.2:8000/album/" + bandid + "/";


                    Log.d("fick", url);

                    ServerResponse serverResponse = new ServerResponse();
                    serverResponse.youFunctionForVolleyRequest(ct, url, new ServerCallback() {
                        @Override
                        public void onSuccess(JSONArray response) {

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
                            isend = 1;
                            Customadapterforalbumlist2 myadap = new Customadapterforalbumlist2(ct, id, names2, ry, songscount, images2);

                            lv.setAdapter(myadap);

                        }
                    });
              /*  }
                else {
                    Log.d("isend",""+isend++);
                    Customadapterforalbumlist2 myadap = new Customadapterforalbumlist2(ct, id, names2, ry, songscount, images2);

                    lv.setAdapter(myadap);

                }*/


                    return rootView;
                }
            }

            return null;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {


        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "History";
                case 1:
                    return "Line Up";
                case 2:
                    return "Albums";
            }
            return null;
        }
    }
}
