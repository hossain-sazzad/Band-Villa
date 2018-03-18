package com.example.user.bd_bands;

import android.content.Intent;
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

public class CreateBand extends AppCompatActivity {

    private String name;
    private String fy;
    private String lu;
    private String id;
    private String genre;
    private String country;
    private String history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_band);


        final EditText bandname=(EditText) findViewById(R.id.createbandname);
        final EditText formyear=(EditText) findViewById(R.id.createformationyear);
        final EditText lineup=(EditText) findViewById(R.id.createlineup);
        final EditText ecountry=(EditText) findViewById(R.id.createcountry);
        final EditText egenre=(EditText) findViewById(R.id.creategenre);
        final EditText ehistory=(EditText) findViewById(R.id.createhistory);

        Button b=(Button) findViewById(R.id.createbandbutton);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name=bandname.getText().toString();
                Log.d("aname",name);
                fy=formyear.getText().toString();
                lu=lineup.getText().toString();
                country=ecountry.getText().toString();
                history=ehistory.getText().toString();
                genre=egenre.getText().toString();



                String url ="http://10.0.2.2:8000/createband/";


                StringRequest sq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                         int bid= Integer.parseInt(response);
                        id=response;
                        SingletonClass.bandid=bid;
                        Log.d("id",id);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
                    protected Map<String, String > getParams(){
                        Map<String, String> parr = new HashMap<String, String>();

                        parr.put("name",name);
                        parr.put("fy", fy);
                        parr.put("lu", lu);
                        parr.put("genre", genre);
                        parr.put("history", history);
                        parr.put("country", country);
                        parr.put("userid",Integer.toString(SingletonClass.userid));


                        return parr;

                    }

                };



                AppController.getInstance().addToRequestQueue(sq);
                Toast.makeText(getApplicationContext(), " Successfull!", Toast.LENGTH_LONG).show();
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
