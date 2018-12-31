package com.educa62.restapi.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import com.educa62.restapi.R;
import com.educa62.restapi.model.Users;

public class ListUserAdapter extends RecyclerView.Adapter<ListUserAdapter.MyViewHolder> {

    private List<Users> listData;
    private final onItemClickListener listener;


    public interface onItemClickListener {
        void onItemClick(Users users);
    }

    public ListUserAdapter(List<Users> listData, onItemClickListener listener) {
        this.listData = listData;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View viewRoot = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row, viewGroup, false);
        return new MyViewHolder(viewRoot);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Users myModel = listData.get(i);
        myViewHolder.bind(myModel, listener);
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView itemName, itemUsername, itemEmail;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = (TextView) itemView.findViewById(R.id.item_name);
            itemUsername = (TextView) itemView.findViewById(R.id.item_username);
            itemEmail = (TextView) itemView.findViewById(R.id.item_email);

        }


        private void bind(final Users data, final onItemClickListener listener) {
            itemName.setText(data.name);
            itemUsername.setText(data.username);
            itemEmail.setText(data.email);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(data);
                }
            });
        }


    }
}
