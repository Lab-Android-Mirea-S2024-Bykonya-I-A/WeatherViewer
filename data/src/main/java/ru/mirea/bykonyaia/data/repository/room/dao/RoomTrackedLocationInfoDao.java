package ru.mirea.bykonyaia.data.repository.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.Collection;
import java.util.List;

import ru.mirea.bykonyaia.data.repository.room.dto.RoomTrackedLocationDto;
import ru.mirea.bykonyaia.data.repository.room.dto.RoomTrackedLocationInfoDto;
import ru.mirea.bykonyaia.domain.dto.Geoposition;

@Dao
public interface RoomTrackedLocationInfoDao {
    @Query("SELECT * FROM tracked_location_info")
    List<RoomTrackedLocationInfoDto> list();
    @Query("SELECT * FROM tracked_location_info WHERE Latitude = :Latitude AND Longitude = :Longitude")
    RoomTrackedLocationInfoDto getByGeoposition(float Latitude, float Longitude);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(RoomTrackedLocationInfoDto trackedLocationDto);
    @Update
    void update(RoomTrackedLocationInfoDto trackedLocationDto);
    @Delete
    void delete(RoomTrackedLocationInfoDto trackedLocationDto);
}
