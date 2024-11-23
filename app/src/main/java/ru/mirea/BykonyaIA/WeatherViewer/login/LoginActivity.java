package ru.mirea.BykonyaIA.WeatherViewer.login;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import ru.mirea.BykonyaIA.WeatherViewer.MainActivity;
import ru.mirea.BykonyaIA.WeatherViewer.databinding.ActivityLoginBinding;
import ru.mirea.bykonyaia.data.repository.firebase.FirebaseAuthorizationService;
import ru.mirea.bykonyaia.domain.repository.IAuthorizationService;

public class LoginActivity extends AppCompatActivity {
    private abstract class LogInTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPostExecute(Void result) {
            CheckIsUserNonAuthorized();
        }
    }
    private class AuthorizeUserTask extends LogInTask {
        private final String username;
        private final String password;
        private AuthorizeUserTask(String username, String password) {
            this.username = username;
            this.password = password;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            auth.AuthorizeUser(username, password);
            return null;
        }
    }
    private class RegisterUserTask extends LogInTask {
        private final String username;
        private final String password;
        private RegisterUserTask(String username, String password) {
            this.username = username;
            this.password = password;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            auth.RegisterUser(username, password);
            return null;
        }
    }
    private class ContinueAsGuestTask extends LogInTask {
        @Override
        protected Void doInBackground(Void... voids) {
            auth.ContinueAsGuest();
            return null;
        }
    }
    private ActivityLoginBinding binding = null;
    private IAuthorizationService auth = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth = new FirebaseAuthorizationService(FirebaseAuth.getInstance());
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.continueAsGuestButton.setOnClickListener(v -> OnContinueAsGuestButtonClicked());
        binding.authorizeButton.setOnClickListener(v -> OnAuthorizeButtonClicked());
        binding.registerButton.setOnClickListener(v -> OnRegisterButtonClicked());
    }
    @Override
    protected void onStart() {
        super.onStart();
        CheckIsUserNonAuthorized();
    }

    private void OnContinueAsGuestButtonClicked() {
        try {
            Log.i("HE_HE", "OnContinueAsGuestButtonClicked");
            new ContinueAsGuestTask().execute();
        } catch (Exception error) {
            Toast.makeText(this, error.toString(), Toast.LENGTH_SHORT).show();
        }
    }
    private void OnAuthorizeButtonClicked() {
        try {
            new AuthorizeUserTask(binding.username.getText().toString(), binding.password.getText().toString()).execute();
        } catch (Exception error) {
            Toast.makeText(this, error.toString(), Toast.LENGTH_SHORT).show();
        }
    }
    private void OnRegisterButtonClicked() {
        try {
            new RegisterUserTask(binding.username.getText().toString(), binding.password.getText().toString()).execute();
        } catch (Exception error) {
            Toast.makeText(this, error.toString(), Toast.LENGTH_SHORT).show();
        }
    }
    private void CheckIsUserNonAuthorized() {
        if(auth.GetUserStatus() != IAuthorizationService.UserStatus.None) {
            startActivity(new Intent(this, MainActivity.class));
        }
    }
}
