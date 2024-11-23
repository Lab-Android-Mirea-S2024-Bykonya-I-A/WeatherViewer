package ru.mirea.bykonyaia.data.repository.room;

import androidx.room.Room;

import java.sql.Timestamp;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import ru.mirea.bykonyaia.data.repository.room.dao.RoomTrackedLocationInfoDao;
import ru.mirea.bykonyaia.data.repository.room.dto.RoomTrackedLocationInfoDto;
import ru.mirea.bykonyaia.domain.dto.Geoposition;
import ru.mirea.bykonyaia.domain.dto.TrackedLocationInfo;
import ru.mirea.bykonyaia.domain.repository.ITrackedLocationInfoRepository;

public class RoomTrackedLocationInfoRepository implements ITrackedLocationInfoRepository {
    private final ITrackedLocationInfoRepository trackedLocationInfoRepositoryOrigin;
    private final RoomTrackedLocationInfoDao roomTrackedLocationInfoDao;
    public RoomTrackedLocationInfoRepository(ITrackedLocationInfoRepository trackedLocationInfoRepositoryOrigin, RoomTrackedLocationInfoDao roomTrackedLocationInfoDao) {
        this.trackedLocationInfoRepositoryOrigin = trackedLocationInfoRepositoryOrigin;
        this.roomTrackedLocationInfoDao = roomTrackedLocationInfoDao;
    }

    @Override
    public TrackedLocationInfo get(Geoposition geoposition) {
        var trackedLocationInfoDto = getCurrentInfo(geoposition);
        return new TrackedLocationInfo(
                new Geoposition(trackedLocationInfoDto.Latitude, trackedLocationInfoDto.Longitude),
                trackedLocationInfoDto.Timestamp,
                trackedLocationInfoDto.Temperature,
                trackedLocationInfoDto.IconUri
        );
    }

    private RoomTrackedLocationInfoDto getLocalInfo(Geoposition geoposition) {
        return roomTrackedLocationInfoDao.getByGeoposition(geoposition.Latitude(), geoposition.Longitude());
    }
    private RoomTrackedLocationInfoDto setLocalInfo(TrackedLocationInfo info) {
        var newLocalInfo = new RoomTrackedLocationInfoDto();
        newLocalInfo.Latitude = info.Geoposition().Latitude();
        newLocalInfo.Longitude = info.Geoposition().Longitude();
        newLocalInfo.Timestamp = info.InfoTimestamp();
        newLocalInfo.Temperature = info.Temperature();
        newLocalInfo.IconUri = info.iconUri();
        roomTrackedLocationInfoDao.insert(newLocalInfo);
        return getLocalInfo(info.Geoposition());
    }
    private RoomTrackedLocationInfoDto getCurrentInfo(Geoposition geoposition) {
        var localInfo = roomTrackedLocationInfoDao.getByGeoposition(geoposition.Latitude(), geoposition.Longitude());
        if((localInfo != null) && (localInfo.Timestamp.after(new Date(localInfo.Timestamp.getTime() + TimeUnit.MINUTES.toMillis(5))))) {
            return localInfo;
        }

        var remoteInfo = trackedLocationInfoRepositoryOrigin.get(geoposition);
        if(remoteInfo != null) {
            return setLocalInfo(remoteInfo);
        } else {
            return localInfo;
        }
    }
}
