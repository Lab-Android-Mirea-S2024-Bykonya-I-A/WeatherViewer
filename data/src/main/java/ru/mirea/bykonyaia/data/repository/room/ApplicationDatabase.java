package ru.mirea.bykonyaia.data.repository.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import ru.mirea.bykonyaia.data.repository.room.dao.RoomTrackedLocationDao;
import ru.mirea.bykonyaia.data.repository.room.dao.RoomTrackedLocationInfoDao;
import ru.mirea.bykonyaia.data.repository.room.dto.RoomTrackedLocationDto;
import ru.mirea.bykonyaia.data.repository.room.dto.RoomTrackedLocationInfoDto;

@Database(entities = { RoomTrackedLocationDto.class, RoomTrackedLocationInfoDto.class }, version = 2)
@TypeConverters({DateConverter.class})
public abstract class ApplicationDatabase extends RoomDatabase {
    public abstract RoomTrackedLocationInfoDao roomTrackedLocationInfoDao();
    public abstract RoomTrackedLocationDao roomTrackedLocationDao();
}
