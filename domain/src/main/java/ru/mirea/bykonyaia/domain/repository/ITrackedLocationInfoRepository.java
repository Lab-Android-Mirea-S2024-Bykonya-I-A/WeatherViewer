package ru.mirea.bykonyaia.domain.repository;

import ru.mirea.bykonyaia.domain.dto.Geoposition;
import ru.mirea.bykonyaia.domain.dto.TrackedLocationInfo;

public interface ITrackedLocationInfoRepository {
    TrackedLocationInfo get(Geoposition geoposition);
}
