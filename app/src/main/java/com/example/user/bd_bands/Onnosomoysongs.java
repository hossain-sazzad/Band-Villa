package com.example.user.bd_bands;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

public class Onnosomoysongs extends AppCompatActivity {

    public int[] songid;
    public String[] names={};
    public String[] lyric={};
    public int[] likes={};

    public int[] dislikes={};
    public String[] youtubelink={};
    private int PICK_IMAGE_REQUEST = 1;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent mIntent = getIntent();
      final int albumid = mIntent.getIntExtra("value",-1);
       final Context ct= this.getApplicationContext();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onnosomoysongs);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        final ListView lv = (ListView) findViewById(R.id.onnosomoysonglist);



        String url = "http://10.0.2.2:8000/song/" + albumid + "/";


        Log.d("fick", url);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {


            @Override
            public void onResponse(JSONArray response) {


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
                ArrayAdapter adapter= new ArrayAdapter(ct,R.layout.songlist,R.id.songname,names);
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

                  Intent openThree = new Intent(getApplicationContext(),onnosomoyvideo.class);
                Bundle extras = new Bundle();
                extras.putString("names", names[position]);
                extras.putString("lyric", lyric[position]);
                extras.putString("utubelink", youtubelink[position]);
                extras.putString("sid", Integer.toString(songid[position]));

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
            showFileChooser();
            return true;
        }
        if (id == android.R.id.home) {
            this.finish();
            return true;
        }



        return super.onOptionsItemSelected(item);
    }

    private void showFileChooser() {
        Intent pickImageIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickImageIntent.setType("image/*");
        pickImageIntent.putExtra("aspectX", 1);
        pickImageIntent.putExtra("aspectY", 1);
        pickImageIntent.putExtra("scale", true);
        pickImageIntent.putExtra("outputFormat",
                Bitmap.CompressFormat.JPEG.toString());
        startActivityForResult(pickImageIntent, PICK_IMAGE_REQUEST);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                Bitmap lastBitmap = null;
                lastBitmap = bitmap;
                //encoding image to string
                String image = getStringImage(lastBitmap);
                Log.d("image",image);
                //passing the image to volley
                SendImage(image);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;

    }

    private void SendImage( final String image) {
        String url ="http://10.0.2.2:8000/band/";
        final StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("uploade",response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);

                        } catch (JSONException e1) {
                            e1.printStackTrace();
                        }
                    }

                },

            new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            //Toast.makeText(Edit_Profile.this, "No internet connection", Toast.LENGTH_LONG).show();

        }
    }) {
        @Override
        protected Map<String, String> getParams() throws AuthFailureError {

            Map<String, String> params = new Hashtable<String, String>();

            params.put("image", image);
            return params;
        }
    };
    {
        int socketTimeout = 30000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}

}
