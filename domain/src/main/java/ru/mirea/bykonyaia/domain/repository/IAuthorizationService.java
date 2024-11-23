package ru.mirea.bykonyaia.domain.repository;

import android.os.AsyncTask;

public interface IAuthorizationService {
    public enum UserStatus {
        Client,
        Guest,
        None
    }

    UserStatus GetUserStatus();
    String GetUsername();

    void AuthorizeUser(String username, String password);
    void RegisterUser(String username, String password);
    void ContinueAsGuest();
    void LogOut();
}
