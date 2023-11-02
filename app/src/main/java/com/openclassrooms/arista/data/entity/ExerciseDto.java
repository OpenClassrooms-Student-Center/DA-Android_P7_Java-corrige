package com.openclassrooms.arista.data.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.openclassrooms.arista.domain.model.Exercise;
import com.openclassrooms.arista.domain.model.ExerciseCategory;

import org.threeten.bp.Instant;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.ZoneId;

@Entity(tableName = "exercise")
public class ExerciseDto {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private long startTime;
    private int duration;
    private String category;
    private int intensity;

    public ExerciseDto(long id, long startTime, int duration, String category, int intensity) {
        this.id = id;
        this.startTime = startTime;
        this.duration = duration;
        this.category = category;
        this.intensity = intensity;
    }

    public Exercise toExercise() {
        return new Exercise(
                id,
                LocalDateTime.ofInstant(Instant.ofEpochMilli(startTime), ZoneId.systemDefault()),
                duration,
                ExerciseCategory.valueOf(category),
                intensity
        );
    }

    // Getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getIntensity() {
        return intensity;
    }

    public void setIntensity(int intensity) {
        this.intensity = intensity;
    }
}
