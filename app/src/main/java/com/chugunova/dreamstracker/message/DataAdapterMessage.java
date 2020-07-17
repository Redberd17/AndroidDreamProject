package com.chugunova.dreamstracker.message;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.*;
import android.widget.ImageView;
import android.widget.TextView;

import com.chugunova.dreamstracker.R;
import com.chugunova.dreamstracker.model.Message;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class DataAdapterMessage extends RecyclerView.Adapter<DataAdapterMessage.ViewHolder> {

    private LayoutInflater inflater;
    private List<Message> messagesList;
    private String username;

    public void setMessagesList(List<Message> messagesList) {
        this.messagesList = messagesList;
    }

    public DataAdapterMessage(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    public DataAdapterMessage(Context context, List<Message> messagesList, String username) {
        this.messagesList = messagesList;
        this.inflater = LayoutInflater.from(context);
        this.username = username;
    }


    @Override
    public DataAdapterMessage.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.item_message, parent, false);

        return new ViewHolder(view);
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(DataAdapterMessage.ViewHolder holder, final int position) {

        Message message = messagesList.get(position);

        String msgFrom = message.getMesFrom();

        if (msgFrom.equals(username)) {

            holder.messageText.setBackgroundResource(R.drawable.message_text_background_out);
            holder.messageText.setTextColor(Color.BLACK);
            holder.messageText.setGravity(Gravity.RIGHT);

            holder.mesDate.setBackgroundResource(R.drawable.message_text_background_out);
            holder.mesDate.setTextColor(Color.BLACK);
            holder.mesDate.setGravity(Gravity.RIGHT);
            holder.mesDate.setPadding(0, 0, 20, 0);

            holder.mesFrom.setBackgroundResource(R.drawable.message_text_background_out);
            holder.mesFrom.setTextColor(Color.BLACK);
            holder.mesFrom.setGravity(Gravity.RIGHT);
            holder.mesFrom.setPadding(0, 0, 20, 0);

            holder.triangleOne.setVisibility(View.INVISIBLE);
            holder.triangleTwo.setVisibility(View.VISIBLE);
        } else {
            holder.messageText.setBackgroundResource(R.drawable.message_text_background_in);
            holder.messageText.setTextColor(Color.BLACK);
            holder.messageText.setGravity(Gravity.LEFT);

            holder.mesDate.setBackgroundResource(R.drawable.message_text_background_in);
            holder.mesDate.setTextColor(Color.BLACK);
            holder.mesDate.setGravity(Gravity.LEFT);
            holder.mesDate.setPadding(20, 0, 0, 0);

            holder.mesFrom.setBackgroundResource(R.drawable.message_text_background_in);
            holder.mesFrom.setTextColor(Color.BLACK);
            holder.mesFrom.setGravity(Gravity.LEFT);
            holder.mesFrom.setPadding(20, 0, 0, 0);

            holder.triangleTwo.setVisibility(View.INVISIBLE);
            holder.triangleOne.setVisibility(View.VISIBLE);
        }

        holder.messageText.setText(message.getMesText());
        holder.mesDate.setText(message.getMesDateTime());
        holder.mesFrom.setText(msgFrom);
    }


    @Override
    public int getItemCount() {
        return messagesList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView messageText, mesDate, mesFrom;
        ImageView triangleOne, triangleTwo;

        ViewHolder(final View view) {
            super(view);
            messageText = view.findViewById(R.id.messageText);
            mesDate = view.findViewById(R.id.mesDate);
            mesFrom = view.findViewById(R.id.mesFrom);
            triangleOne = view.findViewById(R.id.triangleOne);
            triangleTwo = view.findViewById(R.id.triangleTwo);
        }
    }
}
