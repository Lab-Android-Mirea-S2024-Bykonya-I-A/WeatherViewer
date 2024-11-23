package ru.mirea.bykonyaia.data.repository.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.Collection;
import java.util.List;

import ru.mirea.bykonyaia.data.repository.room.dto.RoomTrackedLocationDto;

@Dao
public interface RoomTrackedLocationDao {
    @Query("SELECT * FROM tracked_location")
    List<RoomTrackedLocationDto> list();
    @Insert
    void insert(RoomTrackedLocationDto trackedLocationDto);
    @Delete
    void delete(RoomTrackedLocationDto trackedLocationDto);
}
