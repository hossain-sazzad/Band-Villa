package com.example.user.bd_bands;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by User on 14-Jul-17.
 */



import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;
import android.widget.RatingBar;



/**
 * Created by Lenovo on 11/28/2016.
 */

public class Customadapterforalbumlist2 extends BaseAdapter {

    public String[] names;
    public String[] year;

    public int[] images;
    public Context ct;
    private static LayoutInflater inflater=null;

    public Customadapterforalbumlist2(Context m, String[] n, String[] n2, int[] i) {
        names = n;
        year=n2;

        images = i;
        ct=m;
        inflater=(LayoutInflater) ct.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public  class Myholder{
        ImageView iv;
        TextView tv;
        TextView tv2;

    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Myholder mh;
        mh=new Myholder();
        View myview;
        myview= inflater.inflate(R.layout.albumslist,null);
        mh.iv=(ImageView) myview.findViewById(R.id.albumimage);
        mh.tv=(TextView) myview.findViewById(R.id.albumname);
        mh.tv2=(TextView) myview.findViewById(R.id.albumyear);



        mh.tv.setText(names[position]);
        mh.iv.setImageResource(images[position]);
        mh.tv2.setText(year[position]);

        myview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ct," "+ names[position],Toast.LENGTH_LONG).show();
                Intent openThree = new Intent(ct,Onnosomoysongs.class);
                ct.startActivity(openThree);
            }
        });
        return  myview;
    }

}

