package com.openclassrooms.arista.ui.sleep;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.openclassrooms.arista.R;
import com.openclassrooms.arista.domain.model.Sleep;

import java.time.format.DateTimeFormatter;

import java.util.List;

public class SleepAdapter extends RecyclerView.Adapter<SleepAdapter.SleepViewHolder> {

    private List<Sleep> sleeps;

    public SleepAdapter(List<Sleep> sleeps) {
        this.sleeps = sleeps;
    }

    @NonNull
    @Override
    public SleepViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sleep, parent, false);
        return new SleepViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SleepViewHolder holder, int position) {
        Sleep sleep = sleeps.get(position);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        holder.tvStartTime.setText(String.format("Start Time: %s", sleep.getStartTime().format(formatter)));
        holder.tvDuration.setText(String.format("Duration: %d minutes", sleep.getDuration()));
        holder.tvQuality.setText(String.format("Quality: %d", sleep.getQuality()));
    }

    @Override
    public int getItemCount() {
        return sleeps.size();
    }

    public class SleepViewHolder extends RecyclerView.ViewHolder {
        TextView tvStartTime, tvDuration, tvQuality;

        public SleepViewHolder(@NonNull View itemView) {
            super(itemView);
            tvStartTime = itemView.findViewById(R.id.tv_start_time);
            tvDuration = itemView.findViewById(R.id.tv_duration);
            tvQuality = itemView.findViewById(R.id.tv_quality);
        }
    }
}
