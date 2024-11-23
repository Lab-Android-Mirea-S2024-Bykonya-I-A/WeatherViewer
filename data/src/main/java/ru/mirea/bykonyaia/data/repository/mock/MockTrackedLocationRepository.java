package ru.mirea.bykonyaia.data.repository.mock;

import java.util.Collection;
import java.util.Collections;
import java.util.Dictionary;
import java.util.HashSet;
import java.util.Set;

import ru.mirea.bykonyaia.domain.dto.Geoposition;
import ru.mirea.bykonyaia.domain.repository.ITrackedLocationRepository;

public class MockTrackedLocationRepository implements ITrackedLocationRepository {
    private final Set<Geoposition> geopositions = new HashSet<>();

    @Override
    public Collection<Geoposition> listGeoposition() {
        return geopositions;
    }
    @Override
    public void appendGeoposition(Geoposition geoposition) {
        geopositions.add(geoposition);
    }
    @Override
    public void removeGeoposition(Geoposition geoposition) {
        geopositions.remove(geoposition);
    }
    @Override
    public Boolean containsGeoposition(Geoposition geoposition) {
        return geopositions.contains(geoposition);
    }
}
