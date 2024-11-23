package ru.mirea.bykonyaia.domain.usecase.tl;

import ru.mirea.bykonyaia.domain.dto.Geoposition;
import ru.mirea.bykonyaia.domain.dto.TrackedLocationInfo;
import ru.mirea.bykonyaia.domain.repository.ITrackedLocationInfoRepository;
import ru.mirea.bykonyaia.domain.repository.ITrackedLocationRepository;

public class AppendTrackedLocation {
    private final ITrackedLocationRepository trackedLocationRepository;
    public AppendTrackedLocation(ITrackedLocationRepository trackedLocationRepository) {
        this.trackedLocationRepository = trackedLocationRepository;
    }

    public void execute(Geoposition geoposition) {
        trackedLocationRepository.appendGeoposition(geoposition);
    }
}
