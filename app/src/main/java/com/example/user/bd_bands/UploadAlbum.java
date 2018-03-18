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

public class UploadAlbum extends AppCompatActivity {

  private  String albumname;
    private String ry;
    private String sc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_album);


        Button b=(Button) findViewById(R.id.uploadbutton);



        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText=(EditText) findViewById(R.id.uploadalbumname);
                EditText editText2=(EditText) findViewById(R.id.uploadreleaseyear);
                EditText editText3=(EditText) findViewById(R.id.uploadsongscount);
                  albumname=editText.getText().toString();
                  ry=editText2.getText().toString();
                 sc=editText3.getText().toString();

                Log.d("name",editText.getText().toString());

                Log.d("ry",ry);
                Log.d("sc",sc);
                Log.d("emne","emne");
                String url ="http://10.0.2.2:8000/album/";


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
                        Log.d("name",albumname);
                        Log.d("ry",ry);
                        Log.d("sc",sc);

                        parr.put("name",albumname);
                        parr.put("ry", ry);
                        parr.put("sc", sc);
                        parr.put("id",Integer.toString(SingletonClass.bandid));

                        return parr;

                    }


                };


                Toast.makeText(getApplicationContext(),"Album added successfully ", Toast.LENGTH_LONG).show();

                AppController.getInstance().addToRequestQueue(sq);




            }
        });


    }
}
