package com.openclassrooms.arista.data.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.openclassrooms.arista.domain.model.Sleep;

import org.threeten.bp.Instant;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.ZoneId;


@Entity(tableName = "sleep")
public class SleepDto {

    @PrimaryKey(autoGenerate = true)
    private long id;

    private long startTime;
    private int duration;
    private int quality;

    public SleepDto(long startTime, int duration, int quality) {
        this.startTime = startTime;
        this.duration = duration;
        this.quality = quality;
    }

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

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public Sleep toSleep() {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(
                Instant.ofEpochMilli(startTime),
                ZoneId.systemDefault()
        );
        return new Sleep(localDateTime, duration, quality);
    }
}
