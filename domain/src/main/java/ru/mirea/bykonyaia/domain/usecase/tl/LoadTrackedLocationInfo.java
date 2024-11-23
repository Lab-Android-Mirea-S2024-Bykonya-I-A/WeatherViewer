package ru.mirea.bykonyaia.domain.usecase.tl;

import ru.mirea.bykonyaia.domain.dto.Geoposition;
import ru.mirea.bykonyaia.domain.dto.TrackedLocationInfo;
import ru.mirea.bykonyaia.domain.repository.ITrackedLocationInfoRepository;
import ru.mirea.bykonyaia.domain.repository.ITrackedLocationRepository;

public class LoadTrackedLocationInfo {
    private final ITrackedLocationInfoRepository trackedLocationInfoRepository;
    public LoadTrackedLocationInfo(ITrackedLocationInfoRepository trackedLocationInfoRepository) {
        this.trackedLocationInfoRepository = trackedLocationInfoRepository;
    }

    public TrackedLocationInfo execute(Geoposition geoposition) {
        return trackedLocationInfoRepository.get(geoposition);
    }
}
