package ru.mirea.bykonyaia.domain.usecase.auth;

import ru.mirea.bykonyaia.domain.repository.IAuthorizationService;

public class RegisterUser {
    private final IAuthorizationService authorizationService;
    public RegisterUser(IAuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }
    
    public void execute(String username, String password) {
        authorizationService.RegisterUser(username, password);
    }
}
