package com.example.user.bd_bands;
        import android.content.Context;
        import android.content.Intent;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.util.Log;
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

        import java.io.IOException;
        import java.io.InputStream;
        import java.net.MalformedURLException;
        import java.net.URL;


/**
 * Created by Lenovo on 11/28/2016.
 */

public class CustomAdapter extends BaseAdapter {

    public String[] names;
    public String[] types;
    public  int[] id;
    public String[] images;
    public Context ct;
    private static LayoutInflater inflater=null;

    public CustomAdapter(Context m, int[] id,String[] n, String[] n2, String[] i) {
        names = n;
        types=n2;
         this.id=id;
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
        myview= inflater.inflate(R.layout.bandlistview,null);
        mh.iv=(ImageView) myview.findViewById(R.id.bandimage);
        mh.tv=(TextView) myview.findViewById(R.id.bandname);
        mh.tv2=(TextView) myview.findViewById(R.id.bandtype);



        mh.tv.setText(names[position]);
        mh.tv2.setText(types[position]);

        Picasso.with(ct).load("http://10.0.2.2:8000"+images[position]).into(mh.iv);
        myview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ct," "+ names[position],Toast.LENGTH_LONG).show();

                Intent myIntent = new Intent(ct,Artcellinfo.class);

                myIntent.putExtra("value", id[position]);

                ct.startActivity(myIntent);
            }
        });
        return  myview;
    }

}

