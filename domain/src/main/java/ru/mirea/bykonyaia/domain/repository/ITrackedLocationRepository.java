package ru.mirea.bykonyaia.domain.repository;

import java.util.Collection;
import java.util.List;

import ru.mirea.bykonyaia.domain.dto.Geoposition;

public interface ITrackedLocationRepository {
    Collection<Geoposition> listGeoposition();
    void appendGeoposition(Geoposition geoposition);
    void removeGeoposition(Geoposition geoposition);
    Boolean containsGeoposition(Geoposition geoposition);
}
