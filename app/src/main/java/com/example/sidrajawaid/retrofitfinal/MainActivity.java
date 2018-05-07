package com.example.sidrajawaid.retrofitfinal;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements PostFragment.onFragmentDataSend,PatchFragment.onFragmentDataSend,GetFragment.onFragmentDataSend,
        DeleteFragment.onFragmentDataSend {
    TextView mResponseTv;
    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        callingFragment();
    }
    public void callingFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        PostFragment postFragment = new PostFragment();
        ft.add(R.id.frag, postFragment);
        ft.commit();
    }

    @Override
    public void sendDataToActivity(String s) {
        mResponseTv = findViewById(R.id.text1);
        if (mResponseTv.getVisibility() == View.GONE) {
            mResponseTv.setVisibility(View.VISIBLE);
        }
        mResponseTv.setText(" ");
        mResponseTv.setText(s);
    }
}
