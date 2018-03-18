package layout;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.user.bd_bands.Artcellinfo;
import com.example.user.bd_bands.CustomAdapter;
import com.example.user.bd_bands.Customadapterforalbumlist2;
import com.example.user.bd_bands.MainActivity;
import com.example.user.bd_bands.Onnosomoysongs;
import com.example.user.bd_bands.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Albumfragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Albumfragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Albumfragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    int[] ry=new int[5];

    int[] id=new int[5];
    String[] images2= new String[5];
    String[] names2 = new String[5];
    int[] songscount = new int[5];

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Albumfragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Albumfragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Albumfragment newInstance(String param1, String param2) {
        Albumfragment fragment = new Albumfragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_albumlist, container, false);
        ListView lv= (ListView) rootView.findViewById(R.id.albumlistview);

        String url="http://10.0.2.2:8000/album/";
        Log.d("fuck",url);


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                Log.d("msg", "mymsg");

                for (int i = 0; i < response.length(); i++) {
                    try {


                        JSONObject jsonObject = (JSONObject) response.get(i);
                        id[i] = 4;
                        names2[i] = jsonObject.getString("name");
                        ry[i] = jsonObject.getInt("releaseyear");
                        images2[i] = jsonObject.getString("image");
                        songscount[i] = jsonObject.getInt("songscount");

                        Log.d("msg", "sc" + songscount[i]);
                        Log.d("msg", "ry" + ry[i]);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("volley log hoise", error);
            }
        });
        com.example.user.bd_bands.AppController.getInstance().addToRequestQueue(jsonArrayRequest);

        Customadapterforalbumlist2 myadap = new Customadapterforalbumlist2(this.getContext(),id, names2,ry,songscount,images2);
        lv.setAdapter(myadap);
        return  rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

  //  @Override
    /*public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }*/

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
/*
*/