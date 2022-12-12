package com.example.finalmimofun;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class badgeAdapter extends RecyclerView.Adapter<badgeAdapter.holder>
{

    ArrayList<Integer> data;
    ArrayList<Integer> data1;
    Context c;
    public badgeAdapter(Context c, ArrayList<Integer> data, ArrayList<Integer> data1) {

        this.c = c;
        this.data = data;
        this.data1 = data1;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(c);
        View view =inflater.inflate(R.layout.badgesinglerow,parent,false);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  holder holder, int position) {



        holder.img.setImageResource(data.get(position));
        
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class holder extends RecyclerView.ViewHolder
    {
        ImageView img;


        public holder(@NonNull View itemView) {
            super(itemView);
            img=(ImageView) itemView.findViewById(R.id.img1);
            itemView.setOnClickListener(new View.OnClickListener() {@Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getRootView().getContext());
                View dialogview = LayoutInflater.from(view.getRootView().getContext()).inflate(R.layout.badgedialog,null);
                ImageView diglogimage;
                diglogimage= dialogview.findViewById(R.id.diglogimage);
                diglogimage.setImageResource(data1.get(getAbsoluteAdapterPosition()));
                builder.setView(dialogview);
                builder.setCancelable(true);
                builder.show();
            }});

        }
    }


}
