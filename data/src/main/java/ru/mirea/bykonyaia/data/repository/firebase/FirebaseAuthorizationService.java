package ru.mirea.bykonyaia.data.repository.firebase;

import android.util.Log;

import com.google.android.gms.tasks.Tasks;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;
import java.util.concurrent.ExecutionException;

import ru.mirea.bykonyaia.domain.repository.IAuthorizationService;

public class FirebaseAuthorizationService implements IAuthorizationService {
    private final FirebaseAuth auth;
    public FirebaseAuthorizationService(FirebaseAuth auth) {
        this.auth = auth;
    }

    @Override
    public UserStatus GetUserStatus() {
        var user = auth.getCurrentUser();
        if(user == null) {
            return UserStatus.None;
        } else if(user.isAnonymous()) {
            return UserStatus.Guest;
        } else {
            return UserStatus.Client;
        }
    }
    @Override
    public String GetUsername() {
        if(auth == null || auth.getCurrentUser() == null) {
            return "Undefined...";
        }
        return Objects.requireNonNull(auth.getCurrentUser()).getUid();
    }

    @Override
    public void AuthorizeUser(String username, String password) {
        try {
            var task = auth.signInWithEmailAndPassword(username, password);
            Tasks.await(task);
            if(!task.isSuccessful()) {
                throw new RuntimeException("Invalid username or password");
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void RegisterUser(String username, String password) {
        try {
            var task = auth.createUserWithEmailAndPassword(username, password);
            Tasks.await(task);
            if(!task.isSuccessful()) {
                throw new RuntimeException("Invalid username or password");
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void ContinueAsGuest() {
        try {
            var task = auth.signInAnonymously();
            Tasks.await(task);
            if(task.getResult().getUser() == null) {
                throw new RuntimeException("Invalid username or password");
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void LogOut() {
        auth.signOut();
    }
}
