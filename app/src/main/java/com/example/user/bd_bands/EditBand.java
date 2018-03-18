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

public class EditBand extends AppCompatActivity {
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
        setContentView(R.layout.activity_edit_band);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        name = extras.getString("names");
        Log.d("names",name);
        int iry = extras.getInt("fy");
        fy=Integer.toString(iry);
        Log.d("names",fy);

        lu = extras.getString("lu");
        Log.d("names",lu);

        id=Integer.toString(SingletonClass.bandid);
        country = extras.getString("country");
        history = extras.getString("history");

        genre = extras.getString("genre");


        final EditText bandname=(EditText) findViewById(R.id.editbandname);
        final EditText formyear=(EditText) findViewById(R.id.editformationyear);
        final EditText lineup=(EditText) findViewById(R.id.editlineup);
        final EditText ecountry=(EditText) findViewById(R.id.editcountry);
        final EditText egenre=(EditText) findViewById(R.id.editgenre);
        final EditText ehistory=(EditText) findViewById(R.id.edithistory);
        bandname.setText(name, TextView.BufferType.EDITABLE);
        formyear.setText(fy, TextView.BufferType.EDITABLE);
        lineup.setText(lu, TextView.BufferType.EDITABLE);
        ecountry.setText(country, TextView.BufferType.EDITABLE);
        egenre.setText(genre, TextView.BufferType.EDITABLE);
        ehistory.setText(history, TextView.BufferType.EDITABLE);
        Button b=(Button) findViewById(R.id.editbandbutton);
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



                String url ="http://10.0.2.2:8000/updateband/";


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
                        parr.put("fy", fy);
                        parr.put("lu", lu);
                        parr.put("genre", genre);
                        parr.put("history", history);
                        parr.put("country", country);
                        parr.put("id", id);

                        return parr;

                    }

                };



                AppController.getInstance().addToRequestQueue(sq);
                Toast.makeText(getApplicationContext(), " Successfull!", Toast.LENGTH_LONG).show();
            }
        });


    }
}
