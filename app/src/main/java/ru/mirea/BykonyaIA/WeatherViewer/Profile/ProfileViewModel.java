package ru.mirea.BykonyaIA.WeatherViewer.Profile;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import ru.mirea.bykonyaia.domain.dto.TrackedLocationInfo;
import ru.mirea.bykonyaia.domain.repository.IAuthorizationService;

public class ProfileViewModel extends ViewModel {
    private final MutableLiveData<IAuthorizationService.UserStatus> status = new MutableLiveData<>();
    private final MutableLiveData<String> username = new MutableLiveData<>();
    private final IAuthorizationService authorizationService;

    ProfileViewModel(IAuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
        status.setValue(this.authorizationService.GetUserStatus());
        username.setValue(this.authorizationService.GetUsername());
    }
    public void LogOut() {
        authorizationService.LogOut();
        username.setValue(authorizationService.GetUsername());
        status.setValue(authorizationService.GetUserStatus());
    }

    public MutableLiveData<IAuthorizationService.UserStatus> getStatus() {
        return status;
    }
    public MutableLiveData<String> getUsername() {
        return username;
    }
}
