package ru.mirea.bykonyaia.data.repository.room.dto;

import androidx.room.Entity;

import java.util.Date;

import ru.mirea.bykonyaia.domain.dto.Geoposition;

@Entity(tableName = "tracked_location_info", primaryKeys = { "Latitude", "Longitude" })
public class RoomTrackedLocationInfoDto {
    public float Latitude;
    public float Longitude;
    public Date Timestamp;
    public float Temperature;
    public String IconUri;
}
