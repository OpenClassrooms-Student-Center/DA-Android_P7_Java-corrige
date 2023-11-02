package com.openclassrooms.arista.domain.model;


import org.threeten.bp.LocalDateTime;

public class Sleep {
    private LocalDateTime startTime;
    private int duration;
    private int quality;

    public Sleep(LocalDateTime startTime, int duration, int quality) {
        this.startTime = startTime;
        this.duration = duration;
        this.quality = quality;
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

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }
}
