package com.example.user.bd_bands;


import android.content.Context;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ServerResponse {
    public void youFunctionForVolleyRequest(final Context context , final String URL, final ServerCallback callback)
    {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("volley log hoise", error);
            }
        });
        com.example.user.bd_bands.AppController.getInstance().addToRequestQueue(jsonArrayRequest);



    }

}