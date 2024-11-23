package ru.mirea.bykonyaia.domain.usecase.auth;

import ru.mirea.bykonyaia.domain.repository.IAuthorizationService;

public class AuthorizeUser {
    private final IAuthorizationService authorizationService;
    public AuthorizeUser(IAuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    public void execute(String username, String password) {
        authorizationService.AuthorizeUser(username, password);
    }
}
