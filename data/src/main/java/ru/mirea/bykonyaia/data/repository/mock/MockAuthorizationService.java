package ru.mirea.bykonyaia.data.repository.mock;

import java.util.Objects;

import ru.mirea.bykonyaia.domain.repository.IAuthorizationService;

public class MockAuthorizationService implements IAuthorizationService {
    private static final String USERNAME = "qwe";
    private static final String PASSWORD = "123";
    private UserStatus status = UserStatus.None;

    @Override
    public UserStatus GetUserStatus() {
        return status;
    }
    @Override
    public String GetUsername() {
        switch (status) {
            case Client -> {
                return USERNAME;
            }
            case Guest -> {
                return "Guest";
            }
            case None -> {
                return "Non authorized";
            }
            default -> {
                throw new IllegalStateException("Invalid UserStatus value: " + status);
            }
        }
    }

    @Override
    public void AuthorizeUser(String username, String password) {
        if(!username.equals(USERNAME) || !password.equals(PASSWORD)) {
            throw new RuntimeException("Invalid username or password");
        }
        status = UserStatus.Client;
    }
    @Override
    public void RegisterUser(String username, String password) {
        if(!username.equals(USERNAME) || !password.equals(PASSWORD)) {
            throw new RuntimeException("Invalid username or password");
        }
    }
    @Override
    public void ContinueAsGuest() {
        status = UserStatus.Guest;
    }
    @Override
    public void LogOut() {
        status = UserStatus.None;
    }
}
