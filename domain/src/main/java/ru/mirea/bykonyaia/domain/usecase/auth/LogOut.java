package ru.mirea.bykonyaia.domain.usecase.auth;

import ru.mirea.bykonyaia.domain.repository.IAuthorizationService;

public class LogOut {
    private final IAuthorizationService authorizationService;
    public LogOut(IAuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }
    
    public void execute() {
        authorizationService.LogOut();
    }
}
