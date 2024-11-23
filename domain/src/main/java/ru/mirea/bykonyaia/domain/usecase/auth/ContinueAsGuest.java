package ru.mirea.bykonyaia.domain.usecase.auth;

import ru.mirea.bykonyaia.domain.repository.IAuthorizationService;

public class ContinueAsGuest {
    private final IAuthorizationService authorizationService;
    public ContinueAsGuest(IAuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }
    
    public void execute() {
        authorizationService.ContinueAsGuest();
    }
}
