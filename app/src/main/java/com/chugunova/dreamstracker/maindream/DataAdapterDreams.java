package com.chugunova.dreamstracker.maindream;

import android.content.Context;
import android.os.Bundle;
import android.view.*;
import android.widget.ImageView;
import android.widget.TextView;

import com.chugunova.dreamstracker.R;
import com.chugunova.dreamstracker.currentdream.CurrentDreamFragment;
import com.chugunova.dreamstracker.model.Dream;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import static com.chugunova.dreamstracker.login.LoginFragment.ARG_TOKEN;
import static com.chugunova.dreamstracker.maindream.AllDreamsFragment.ARG_DREAM_DATE;
import static com.chugunova.dreamstracker.maindream.AllDreamsFragment.ARG_DREAM_DURATION;
import static com.chugunova.dreamstracker.maindream.AllDreamsFragment.ARG_DREAM_ID;
import static com.chugunova.dreamstracker.maindream.AllDreamsFragment.ARG_DREAM_NAME;
import static com.chugunova.dreamstracker.maindream.AllDreamsFragment.ARG_DREAM_TEXT;

public class DataAdapterDreams extends RecyclerView.Adapter<DataAdapterDreams.ViewHolder> {

    private LayoutInflater inflater;
    private List<Dream> dreams;
    private String token;

    public DataAdapterDreams(Context context, List<Dream> dreams, String token) {
        this.inflater = LayoutInflater.from(context);
        this.dreams = dreams;
        this.token = token;
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
        holder.duration = dream.getDreamDuration();
        holder.token = token;
        holder.dreamId = dream.getDreamId();

        configDreamSmile(holder);
    }

    private void configDreamSmile(DataAdapterDreams.ViewHolder holder) {
        if (holder.duration <= 3) {
            holder.dreamSmile.setImageResource(R.drawable.bad_dream);
        } else if (3 < holder.duration && holder.duration <= 5) {
            holder.dreamSmile.setImageResource(R.drawable.normal_dream);
        } else if (5 < holder.duration && holder.duration <= 7) {
            holder.dreamSmile.setImageResource(R.drawable.good_dream);
        } else if (7 < holder.duration && holder.duration <= 9) {
            holder.dreamSmile.setImageResource(R.drawable.perfect_dream);
        } else if (holder.duration > 3) {
            holder.dreamSmile.setImageResource(R.drawable.good_dream);
        }
    }

    @Override
    public int getItemCount() {
        if (dreams != null) {
            return dreams.size();
        }
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView dateDream, nameDream, textDream;
        final ImageView dreamSmile;
        private Double duration;
        private Integer dreamId;
        private String token;

        ViewHolder(final View view) {
            super(view);
            dateDream = view.findViewById(R.id.date_dreams);
            nameDream = view.findViewById(R.id.name_dreams);
            textDream = view.findViewById(R.id.text_dreams);
            dreamSmile = view.findViewById(R.id.dream_smile);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle argument = new Bundle();
                    argument.putString(ARG_DREAM_DATE, dateDream.getText().toString());
                    argument.putString(ARG_DREAM_NAME, nameDream.getText().toString());
                    argument.putString(ARG_DREAM_TEXT, textDream.getText().toString());
                    argument.putDouble(ARG_DREAM_DURATION, duration);
                    argument.putString(ARG_TOKEN, token);
                    argument.putInt(ARG_DREAM_ID, dreamId);

                    showCurrentDreamFragment(argument, view);
                }
            });
        }

        private void showCurrentDreamFragment(Bundle argument, View view) {
            AppCompatActivity activity = (AppCompatActivity)view.getContext();
            CurrentDreamFragment currentDreamFragment = new CurrentDreamFragment();
            currentDreamFragment.setArguments(argument);
            FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction().replace(R.id.main, currentDreamFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }
}
