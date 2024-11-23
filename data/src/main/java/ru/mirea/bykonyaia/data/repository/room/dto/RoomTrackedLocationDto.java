package ru.mirea.bykonyaia.data.repository.room.dto;

import androidx.room.Entity;

@Entity(tableName = "tracked_location", primaryKeys = { "Latitude", "Longitude" })
public class RoomTrackedLocationDto {
    public float Latitude;
    public float Longitude;
}
