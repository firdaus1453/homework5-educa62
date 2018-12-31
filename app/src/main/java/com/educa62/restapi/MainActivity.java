package com.educa62.restapi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import com.educa62.restapi.adapter.ListUserAdapter;
import com.educa62.restapi.api.ApiClient;
import com.educa62.restapi.api.ApiInterface;
import com.educa62.restapi.model.Users;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements ListUserAdapter.onItemClickListener {

    private ApiInterface apiInterface;
    private RecyclerView recyclerView;
    private ListUserAdapter adapter;
    private List<Users> data = new ArrayList<>();
    private ProgressDialog progressDoalog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.myRec);
        initAdapter();
        showProgress();
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Users>> call = apiInterface.getListUser();
        call.enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                data.addAll(response.body());
                adapter.notifyDataSetChanged();
                progressDoalog.dismiss();
            }

            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {
                progressDoalog.dismiss();
                Log.d("RESPONSE", "ERROR");
                t.printStackTrace();
            }
        });
    }

    private void showProgress() {
        progressDoalog = new ProgressDialog(MainActivity.this);
        progressDoalog.setMessage("loading....");
        progressDoalog.setTitle("Harap Menunggu");
        progressDoalog.show();
    }

    private void initAdapter() {
        adapter = new ListUserAdapter(data, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(Users data) {
        Intent intent = new Intent(this, DetailUserActivity.class);
        intent.putExtra("id_users", data.id);
        startActivity(intent);
    }
}
