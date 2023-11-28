package com.openclassrooms.arista.ui.exercise;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.openclassrooms.arista.R;
import com.openclassrooms.arista.domain.model.Exercise;

import java.time.format.DateTimeFormatter;

import java.util.List;

public class ExerciseAdapter extends ListAdapter<Exercise, ExerciseAdapter.ExerciseViewHolder> {

    private final DeleteExerciseInterface context;

    public ExerciseAdapter(DeleteExerciseInterface context) {
        super(DIFF_CALLBACK);
        this.context = context;
    }

    private static final DiffUtil.ItemCallback<Exercise> DIFF_CALLBACK = new DiffUtil.ItemCallback<Exercise>() {
        @Override
        public boolean areItemsTheSame(@NonNull Exercise oldItem, @NonNull Exercise newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Exercise oldItem, @NonNull Exercise newItem) {
            return oldItem.equals(newItem);
        }
    };

    @NonNull
    @Override
    public ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_exercise, parent, false);
        return new ExerciseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseViewHolder holder, int position) {
        Exercise exercise = getItem(position);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        holder.tvStartTime.setText(String.format("Start Time: %s", exercise.getStartTime().format(formatter)));
        holder.tvDuration.setText(String.format("Duration: %d minutes", exercise.getDuration()));
        holder.tvCategory.setText(String.format("Category: %s", exercise.getCategory().toString()));
        holder.tvIntensity.setText(String.format("Intensity: %d", exercise.getIntensity()));
        holder.ivDelete.setOnClickListener(view -> context.deleteExercise(exercise));
    }

    public static class ExerciseViewHolder extends RecyclerView.ViewHolder {
        TextView tvStartTime, tvDuration, tvCategory, tvIntensity;
        ImageView ivDelete;

        public ExerciseViewHolder(@NonNull View itemView) {
            super(itemView);
            tvStartTime = itemView.findViewById(R.id.tv_start_time);
            tvDuration = itemView.findViewById(R.id.tv_duration);
            tvCategory = itemView.findViewById(R.id.tv_category);
            tvIntensity = itemView.findViewById(R.id.tv_intensity);
            ivDelete = itemView.findViewById(R.id.delete);
        }
    }
}
