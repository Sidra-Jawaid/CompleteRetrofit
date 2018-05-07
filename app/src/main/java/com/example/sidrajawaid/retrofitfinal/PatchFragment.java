package com.example.sidrajawaid.retrofitfinal;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PatchFragment extends Fragment {

    private ApiInterface mAPIInterface;
    Button button;
    EditText ed4;
    onFragmentDataSend listener ;
    public static final String TAG="PatchFragment";
    public PatchFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_patch, container, false);
        ed4=v.findViewById(R.id.ed4);
        button=(Button)v.findViewById(R.id.btn);
        mAPIInterface = ApiClient.getApiClient().create(ApiInterface.class);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String body = ed4.getText().toString().trim();
                if( !TextUtils.isEmpty(body)) {
                    patchPost(body);
                }
                callingFragment();
            }
        });
        return v;
    }
    public void callingFragment()
    {
        FragmentManager fm=getFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        GetFragment getFragment=new GetFragment();
        ft.replace(R.id.frag,getFragment);
        ft.commit();
    }
    public void patchPost(String body) {
        mAPIInterface.PatchPost(body).enqueue(new Callback<Posts>() {

            @Override
            public void onResponse(Call<Posts> call, Response<Posts> response) {
                Log.d(TAG,"PATCH: onResponce method called");
                if(response.isSuccessful()){
                    Log.i(TAG, "PATCH responce code= "+response.code());
                    if(listener!=null) {
                        listener.sendDataToActivity(""+response.body());
                    }
                    Log.i(TAG, "Updated" + response.body().toString());
                }
            }
            @Override
            public void onFailure(Call<Posts> call, Throwable t) {
                Log.e(TAG, "Unable to Update ");
            }
        });
    }
    public interface onFragmentDataSend{
        void sendDataToActivity(String s);
    }
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            listener=(PatchFragment.onFragmentDataSend)context;
        }
        catch (ClassCastException e)
        {

        }
    }
}
