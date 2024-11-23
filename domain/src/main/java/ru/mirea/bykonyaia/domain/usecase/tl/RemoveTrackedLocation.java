package ru.mirea.bykonyaia.domain.usecase.tl;

import ru.mirea.bykonyaia.domain.dto.Geoposition;
import ru.mirea.bykonyaia.domain.dto.TrackedLocationInfo;
import ru.mirea.bykonyaia.domain.repository.ITrackedLocationInfoRepository;
import ru.mirea.bykonyaia.domain.repository.ITrackedLocationRepository;

public class RemoveTrackedLocation {
    private final ITrackedLocationRepository trackedLocationRepository;
    public RemoveTrackedLocation(ITrackedLocationRepository trackedLocationRepository) {
        this.trackedLocationRepository = trackedLocationRepository;
    }

    public void execute(Geoposition geoposition) {
        trackedLocationRepository.removeGeoposition(geoposition);
    }
}
