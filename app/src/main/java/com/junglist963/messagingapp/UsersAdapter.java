package com.junglist963.messagingapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {

    List<String>userList;
    String userName;
    Context mContext;

    FirebaseDatabase database;
    DatabaseReference reference;

    public UsersAdapter(List<String> userList, String userName, Context mContext) {
        this.userList = userList;
        this.userName = userName;
        this.mContext = mContext;

        database = FirebaseDatabase.getInstance();
        reference = database.getReference();
    }

    public  UsersAdapter(List<String> userList){
        this.userList = userList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.users_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        reference.child("Users").child(userList.get(position)).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String otherName =snapshot.child("userName").getValue().toString();
                String imgUrl = snapshot.child("image").getValue().toString();

                holder.txtUsers.setText(otherName);
                if (imgUrl.equals("null")){
                    holder.imgUsers.setImageResource(R.drawable.account);
                }else {
                    Glide.with(mContext)
                            .asBitmap()
                            .load(imgUrl)
                            .into(holder.imgUsers);
                }
                holder.cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, ChatActivity.class);
                        intent.putExtra("userName", userName);
                        intent.putExtra("otherName", otherName);
                        mContext.startActivity(intent);
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtUsers;
        private CircleImageView imgUsers;
        private CardView cardView;

        public ViewHolder (@NonNull View itemView){
            super(itemView);

            txtUsers = itemView.findViewById(R.id.txtUsers);
            imgUsers = itemView.findViewById(R.id.imgUsers);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
