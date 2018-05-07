package com.example.sidrajawaid.retrofitfinal;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class DeleteFragment extends Fragment {
    private ApiInterface mAPIInterface;
    Button button;
    EditText editText;
    public static final String TAG = "DeleteFragment";
    DeleteFragment.onFragmentDataSend listener;


    public DeleteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_delete, container, false);
        button=(Button)v.findViewById(R.id.btn);
        editText=v.findViewById(R.id.eddelete);
        mAPIInterface = ApiClient.getApiClient().create(ApiInterface.class);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = editText.getText().toString().trim();
                if(!TextUtils.isEmpty(id)) {
                    deletePost(Long.parseLong(id));
                }
            }
        });
        return v;
    }
    public void deletePost(long id) {
        mAPIInterface.deletePost(id).enqueue(new Callback<Posts>() {

            @Override
            public void onResponse(Call<Posts> call, Response<Posts> response) {
                Log.d(TAG,"DELETE: onResponce method called");
                if(response.isSuccessful()){
                    Log.i(TAG, "DELETE responce code= "+response.code());
                    if(listener!=null) {
                        listener.sendDataToActivity(""+response.body());
                    }
                    Log.i(TAG, "DELETED" + response.body().toString());
                }
            }
            @Override
            public void onFailure(Call<Posts> call, Throwable t) {
                Log.e(TAG, "Unable to DELETE ");
            }
        });
    }
    public interface onFragmentDataSend{
        void sendDataToActivity(String s);
    }
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            listener=(DeleteFragment.onFragmentDataSend)context;
        }
        catch (ClassCastException e)
        {

        }
    }

}
