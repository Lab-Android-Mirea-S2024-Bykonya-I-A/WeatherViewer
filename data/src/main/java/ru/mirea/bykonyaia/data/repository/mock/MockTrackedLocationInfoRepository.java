package ru.mirea.bykonyaia.data.repository.mock;

import java.util.Date;

import ru.mirea.bykonyaia.domain.dto.Geoposition;
import ru.mirea.bykonyaia.domain.dto.TrackedLocationInfo;
import ru.mirea.bykonyaia.domain.repository.ITrackedLocationInfoRepository;

public class MockTrackedLocationInfoRepository implements ITrackedLocationInfoRepository {
    @Override
    public TrackedLocationInfo get(Geoposition geoposition) {
        return new TrackedLocationInfo(
                geoposition,
                new Date(),
                42,
                ""
        );
    }
}
