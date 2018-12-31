package com.educa62.restapi;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.educa62.restapi.api.ApiClient;
import com.educa62.restapi.api.ApiInterface;
import com.educa62.restapi.model.Users;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailUserActivity extends AppCompatActivity {

    private ApiInterface apiInterface;
    private List<Users> data = new ArrayList<>();
    private ProgressDialog progressDoalog;
    private String idUser;
    public TextView itemName, itemUsername, itemEmail,itemAddress,itemPhone,itemWebsite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_user);

        itemName = (TextView) findViewById(R.id.detail_item_name);
        itemUsername = (TextView) findViewById(R.id.detail_item_username);
        itemEmail = (TextView) findViewById(R.id.detail_item_email);
        itemAddress = (TextView) findViewById(R.id.detail_item_address);
        itemPhone = (TextView) findViewById(R.id.detail_item_phone);
        itemWebsite = (TextView) findViewById(R.id.detail_item_website);

        int idInt = getIntent().getIntExtra("id_users",0);
        idUser = String.valueOf(idInt);

        showProgress();
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Users>> call = apiInterface.getDetailUser(idUser);

        call.enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                data.addAll(response.body());
                progressDoalog.dismiss();
                setData();
            }

            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {
                progressDoalog.dismiss();
                Log.d("RESPONSE", "ERROR");
                t.printStackTrace();
            }
        });
    }

    private void setData() {
        itemName.setText(data.get(0).name);
        itemUsername.setText(data.get(0).username);
        itemEmail.setText(data.get(0).email);
        itemAddress.setText(data.get(0).address.city);
        itemPhone.setText(data.get(0).phone);
        itemWebsite.setText(data.get(0).website);
    }

    private void showProgress() {
        progressDoalog = new ProgressDialog(DetailUserActivity.this);
        progressDoalog.setMessage("loading....");
        progressDoalog.setTitle("Harap Menunggu");
        progressDoalog.show();
    }
}