package com.chugunova.dreamstracker.dreams;

import android.content.Context;
import android.view.*;
import android.widget.TextView;

import com.chugunova.dreamstracker.R;
import com.chugunova.model.Dream;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DataAdapterDreams extends RecyclerView.Adapter<DataAdapterDreams.ViewHolder> {

    private LayoutInflater inflater;
    private List<Dream> dreams;

    public DataAdapterDreams(Context context, List<Dream> dreams) {
        this.inflater = LayoutInflater.from(context);
        this.dreams = dreams;
    }

    @NonNull
    @Override
    public DataAdapterDreams.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_dream, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataAdapterDreams.ViewHolder holder, int position) {
        Dream dream = dreams.get(position);
        holder.dateDream.setText(dream.getDreamDate());
        holder.nameDream.setText(dream.getDreamName());
        holder.textDream.setText(dream.getDreamText());
    }

    @Override
    public int getItemCount() {
        return dreams.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView dateDream, nameDream, textDream;

        ViewHolder(final View view) {
            super(view);
            dateDream = view.findViewById(R.id.date_dream);
            nameDream = view.findViewById(R.id.name_dream);
            textDream = view.findViewById(R.id.text_dream);

            /*view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });*/
        }
    }
}
