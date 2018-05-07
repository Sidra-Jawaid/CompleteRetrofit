package com.example.sidrajawaid.retrofitfinal;


import android.content.Context;
import android.content.Intent;
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

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class PostFragment extends Fragment {
    private ApiInterface mAPIInterface;
    Button button;
    EditText ed3;
    EditText ed4;
    public static final String TAG = "PostFragment";
    onFragmentDataSend dataSender;
    public PostFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_post, container, false);
        ed3=v.findViewById(R.id.ed3);
        ed4=v.findViewById(R.id.ed4);
        button=(Button)v.findViewById(R.id.btn);
        mAPIInterface = ApiClient.getApiClient().create(ApiInterface.class);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = ed3.getText().toString().trim();
                String body = ed4.getText().toString().trim();
                if(!TextUtils.isEmpty(title) && !TextUtils.isEmpty(body)) {
                    sendPost(title, body);
                }
                //Calling other Fragment
                callingFragment();

            }
        });
        return v;
    }
    public void sendPost(String title, String body) {
        mAPIInterface.savePost(title, body, 1).enqueue(new Callback<Posts>() {
            @Override
            public void onResponse(Call<Posts> call, Response<Posts> response) {
                Log.d(TAG,"POST: onResponce method called");
                if(response.isSuccessful()) {
                    Log.d(TAG,"POST: Sending data.........");
                    dataSender.sendDataToActivity(""+response.body());
                    Log.i(TAG, "POST responce code= " + response.code());

                }
            }
            @Override
            public void onFailure(Call<Posts> call, Throwable t) {
                Log.e(TAG, "Unable to submit ");
            }
        });
    }
    private void bindData(String responce)
    {if(getActivity()!=null)
    {Intent i = new Intent(getActivity().getBaseContext(), MainActivity.class);
        i.putExtra("SENDER_KEY", TAG);
        i.putExtra("data",responce);
        getActivity().startActivity(i);}
    }
    public void callingFragment()
    {
        FragmentManager fm=getFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        PatchFragment patchFragment=new PatchFragment();
        ft.replace(R.id.frag,patchFragment);
        ft.commit();
    }
    public interface onFragmentDataSend{
         void sendDataToActivity(String s);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            dataSender=(onFragmentDataSend)context;
        }
        catch (ClassCastException e)
        {

        }
    }
}
