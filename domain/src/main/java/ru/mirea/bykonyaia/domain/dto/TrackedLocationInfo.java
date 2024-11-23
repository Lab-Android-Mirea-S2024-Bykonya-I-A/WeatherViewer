package ru.mirea.bykonyaia.domain.dto;


import java.util.Date;

public record TrackedLocationInfo(
    Geoposition Geoposition,
    Date InfoTimestamp,
    float Temperature,
    String iconUri
) {}

