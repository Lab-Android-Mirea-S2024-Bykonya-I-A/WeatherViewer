package ru.mirea.bykonyaia.domain.usecase.tl;

import java.util.Collection;

import ru.mirea.bykonyaia.domain.dto.Geoposition;
import ru.mirea.bykonyaia.domain.repository.ITrackedLocationRepository;

public class ListTrackedLocation {
    private final ITrackedLocationRepository trackedLocationRepository;
    public ListTrackedLocation(ITrackedLocationRepository trackedLocationRepository) {
        this.trackedLocationRepository = trackedLocationRepository;
    }

    public Collection<Geoposition> execute() {
        return trackedLocationRepository.listGeoposition();
    }
}
