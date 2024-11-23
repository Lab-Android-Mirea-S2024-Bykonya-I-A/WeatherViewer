package ru.mirea.bykonyaia.data.repository.room;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.stream.Collectors;

import kotlin.NotImplementedError;
import ru.mirea.bykonyaia.data.repository.room.dao.RoomTrackedLocationDao;
import ru.mirea.bykonyaia.data.repository.room.dto.RoomTrackedLocationDto;
import ru.mirea.bykonyaia.domain.dto.Geoposition;
import ru.mirea.bykonyaia.domain.repository.ITrackedLocationRepository;

public class RoomTrackedLocationRepository implements ITrackedLocationRepository {
    private final RoomTrackedLocationDao roomTrackedLocationDao;
    public RoomTrackedLocationRepository(RoomTrackedLocationDao roomTrackedLocationDao) {
        this.roomTrackedLocationDao = roomTrackedLocationDao;
    }


    @Override
    public Collection<Geoposition> listGeoposition() {
        return roomTrackedLocationDao.list().stream().
                map(tl -> new Geoposition(tl.Latitude, tl.Longitude)).
                collect(Collectors.toSet());
    }
    @Override
    public void appendGeoposition(Geoposition geoposition) {
        var trackedLocation = new RoomTrackedLocationDto();
        trackedLocation.Latitude = geoposition.Latitude();
        trackedLocation.Longitude = geoposition.Longitude();
        roomTrackedLocationDao.insert(trackedLocation);
    }
    @Override
    public void removeGeoposition(Geoposition geoposition) {
        var trackedLocation = new RoomTrackedLocationDto();
        trackedLocation.Latitude = geoposition.Latitude();
        trackedLocation.Longitude = geoposition.Longitude();
        roomTrackedLocationDao.delete(trackedLocation);
    }
    @Override
    public Boolean containsGeoposition(Geoposition geoposition) {
        // Уфф, какая оптимизация, уровень гугла
        return roomTrackedLocationDao.list().stream().
                map(tl -> new Geoposition(tl.Latitude, tl.Longitude)).
                collect(Collectors.toSet()).
                contains(geoposition);
    }
}
