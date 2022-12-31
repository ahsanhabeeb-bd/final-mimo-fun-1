package com.manu.finalmimofun;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainAdapter extends FirebaseRecyclerAdapter<MainModel,MainAdapter.myviewholder>
{
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public MainAdapter(@NonNull FirebaseRecyclerOptions<MainModel> options)
    {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull MainModel model)
    {
        holder.name.setText(model.getName());
        holder.id_number.setText("ID: "+model.getId_number());
        holder.email.setText(model.getEmail());
        holder.phone.setText(model.getPhone());

        Glide.with(holder.img.getContext())
                .load(model.getPicture())
                .placeholder(R.drawable.ladis2)
                .circleCrop()
                .error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_light)
                .into(holder.img);

        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Details.class);
                intent.putExtra("uid",model.getUid());
                view.getContext().startActivity(intent);
            }
        });



    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item,parent,false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder
    {
        CircleImageView img;
        TextView name,id_number,email,phone;
        View v;

        public myviewholder(@NonNull View itemView) {
            super(itemView);

            img = (CircleImageView) itemView.findViewById(R.id.img1);
            name = (TextView) itemView.findViewById(R.id.nametext);
            id_number = (TextView) itemView.findViewById(R.id.coursetext);
            email = (TextView) itemView.findViewById(R.id.emailtext);
            phone = (TextView) itemView.findViewById(R.id.mobile);

            v=itemView;
        }
    }


}
