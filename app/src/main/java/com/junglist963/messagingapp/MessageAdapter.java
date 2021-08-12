package com.junglist963.messagingapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    List<ModelClass> list;
    String userName;

    boolean status;
    int send;
    int receive;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == send){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_send, parent, false);
        }else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_received, parent, false);

        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.textView.setText(list.get(position).getMessage());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public MessageAdapter(List<ModelClass> list, String userName) {
        this.list = list;
        this.userName = userName;

        status = false;
        send = 1;
        receive = 2;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            if (status){
                textView = itemView.findViewById(R.id.txtSend);
            }else {
                textView = itemView.findViewById(R.id.txtReceived);

            }
        }
    }

    @Override
    public int getItemViewType(int position) {
    if (list.get(position).getFrom().equals(userName)){
        status = true;
        return  send;
    }else {
        status = false;
        return receive;
    }
    }
}
