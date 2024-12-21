package ru.mirea.BykonyaIA.WeatherViewer.Profile;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import ru.mirea.BykonyaIA.WeatherViewer.TrackedLocationList.TrackedLocationListViewModel;
import ru.mirea.bykonyaia.domain.repository.IAuthorizationService;
import ru.mirea.bykonyaia.domain.repository.ITrackedLocationInfoRepository;
import ru.mirea.bykonyaia.domain.repository.ITrackedLocationRepository;

public class ProfileViewModelFactory  implements ViewModelProvider.Factory {
    private final IAuthorizationService authorizationService;
    public ProfileViewModelFactory(IAuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ProfileViewModel(authorizationService);
    }
}
