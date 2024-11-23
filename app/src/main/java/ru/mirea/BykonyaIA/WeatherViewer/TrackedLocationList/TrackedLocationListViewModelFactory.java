package ru.mirea.BykonyaIA.WeatherViewer.TrackedLocationList;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import ru.mirea.bykonyaia.domain.repository.ITrackedLocationInfoRepository;
import ru.mirea.bykonyaia.domain.repository.ITrackedLocationRepository;

public class TrackedLocationListViewModelFactory implements ViewModelProvider.Factory {
    private final ITrackedLocationInfoRepository trackedLocationInfoRepository;
    private final ITrackedLocationRepository trackedLocationRepository;
    public TrackedLocationListViewModelFactory(ITrackedLocationInfoRepository trackedLocationInfoRepository, ITrackedLocationRepository trackedLocationRepository) {
        this.trackedLocationInfoRepository = trackedLocationInfoRepository;
        this.trackedLocationRepository = trackedLocationRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new TrackedLocationListViewModel(trackedLocationInfoRepository, trackedLocationRepository);
    }
}
