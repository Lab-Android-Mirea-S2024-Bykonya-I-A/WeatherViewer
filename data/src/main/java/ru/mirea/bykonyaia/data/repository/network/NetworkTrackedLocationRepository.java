package ru.mirea.bykonyaia.data.repository.network;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import ru.mirea.bykonyaia.domain.dto.Geoposition;
import ru.mirea.bykonyaia.domain.repository.ITrackedLocationRepository;

public class NetworkTrackedLocationRepository implements ITrackedLocationRepository {
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
