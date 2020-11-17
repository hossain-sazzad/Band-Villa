package com.example.user.bd_bands;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
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

import com.squareup.picasso.Picasso;


/**
 * Created by Lenovo on 11/28/2016.
 */

public class Customadapterforalbumlist2 extends BaseAdapter {
    public int[] albumid;
    public String[] names;
    public int[] year;
    public int[] songscount;
    public String[] images;
    private Context ct;
    private static LayoutInflater inflater=null;

    public Customadapterforalbumlist2(Context m,int[] id, String[] n, int[] n2, int[] sc, String[] i) {
        names = n;
        albumid=id;
        year=n2;
        songscount=sc;
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
        TextView tv3;

    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

       /* Log.d("aname",names[position]);
        Log.d("aname",""+year[position]);
        Log.d("aname",""+songscount[position]);*/
        Myholder mh;
        mh=new Myholder();
        View myview;
        myview= inflater.inflate(R.layout.albumslist,null);
        mh.iv=(ImageView) myview.findViewById(R.id.albumimage);
        mh.tv=(TextView) myview.findViewById(R.id.albumname);
        mh.tv2=(TextView) myview.findViewById(R.id.albumyear);

        mh.tv3=(TextView) myview.findViewById(R.id.songscount);

        mh.tv.setText(names[position]);

       // mh.iv.setImageResource(R.drawable.artcell);
        mh.tv2.setText(""+year[position]);
        mh.tv3.setText(""+songscount[position]);

        Picasso.with(ct).load("http://10.0.2.2:8000"+images[position]).into(mh.iv);
        Log.d("image","http://10.0.2.2:8000"+images[position]);

        myview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ct," "+ names[position],Toast.LENGTH_LONG).show();
                Intent openThree = new Intent(ct,Onnosomoysongs.class);
                Log.d("albumid",""+albumid[position]);
                openThree.putExtra("value", albumid[position]);
                ct.startActivity(openThree);
            }
        });
        return  myview;
    }
	public Context getCt() {
		return ct;
	}

}

