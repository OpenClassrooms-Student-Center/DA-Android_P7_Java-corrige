package com.openclassrooms.arista.domain.model;


import com.openclassrooms.arista.data.entity.ExerciseDto;

import org.threeten.bp.LocalDateTime;
import org.threeten.bp.ZoneOffset;

import java.util.Objects;

public class Exercise {

    private long id;
    private LocalDateTime startTime;
    private int duration;
    private ExerciseCategory category;
    private int intensity;

    public Exercise(long id, LocalDateTime startTime, int duration, ExerciseCategory category, int intensity) {
        this.id = id;
        this.startTime = startTime;
        this.duration = duration;
        this.category = category;
        this.intensity = intensity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public ExerciseCategory getCategory() {
        return category;
    }

    public void setCategory(ExerciseCategory category) {
        this.category = category;
    }

    public int getIntensity() {
        return intensity;
    }

    public void setIntensity(int intensity) {
        this.intensity = intensity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exercise exercise = (Exercise) o;
        return id == exercise.id && duration == exercise.duration && intensity == exercise.intensity && Objects.equals(startTime, exercise.startTime) && category == exercise.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startTime, duration, category, intensity);
    }

    public ExerciseDto toDto() {
        return new ExerciseDto(
                id,
                this.getStartTime().toEpochSecond(ZoneOffset.UTC), // Convertir en timestamp
                this.getDuration(),
                this.getCategory().name(), // Convertir Enum en String
                this.getIntensity()
        );
    }

}