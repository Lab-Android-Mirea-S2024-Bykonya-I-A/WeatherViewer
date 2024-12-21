package ru.mirea.BykonyaIA.WeatherViewer;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import ru.mirea.BykonyaIA.WeatherViewer.Home.TrackedLocationInfoViewFragment;
import ru.mirea.BykonyaIA.WeatherViewer.Home.TrackedLocationListViewFragment;
import ru.mirea.BykonyaIA.WeatherViewer.Profile.ProfileViewModel;
import ru.mirea.BykonyaIA.WeatherViewer.Profile.ProfileViewModelFactory;
import ru.mirea.BykonyaIA.WeatherViewer.TrackedLocationList.TrackedLocationListViewModel;
import ru.mirea.BykonyaIA.WeatherViewer.TrackedLocationList.TrackedLocationListViewModelFactory;
import ru.mirea.BykonyaIA.WeatherViewer.databinding.ActivityMainBinding;
import ru.mirea.BykonyaIA.WeatherViewer.login.LoginActivity;
import ru.mirea.bykonyaia.data.repository.firebase.FirebaseAuthorizationService;
import ru.mirea.bykonyaia.data.repository.network.NetworkTrackedLocationInfoRepository;
import ru.mirea.bykonyaia.data.repository.room.RoomTrackedLocationInfoRepository;
import ru.mirea.bykonyaia.data.repository.room.RoomTrackedLocationRepository;
import ru.mirea.bykonyaia.domain.repository.IAuthorizationService;

public class MainActivity extends AppCompatActivity {
    private final static String OPENWEATHER_API_KEY = "ae53760321911b952e3646887ead8c6d";
    private TrackedLocationListViewModel vm = null;
    private ProfileViewModel profileViewModel = null;
    private IAuthorizationService auth = null;
    private ActivityMainBinding binding = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
//        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        binding.logOutButton.setOnClickListener(v -> OnLogOutButtonClicked());
        setContentView(binding.getRoot());

        auth = new FirebaseAuthorizationService(FirebaseAuth.getInstance());
        profileViewModel = new ViewModelProvider(this, new ProfileViewModelFactory(auth)).get(ProfileViewModel.class);
        profileViewModel.getStatus().observe(this, userStatus -> CheckIsUserAuthorized());

        var database = WeatherViewerApplication.getInstance().getDatabase();
        var trackedLocationInfoRepository = new RoomTrackedLocationInfoRepository(new NetworkTrackedLocationInfoRepository(OPENWEATHER_API_KEY), database.roomTrackedLocationInfoDao());
        var trackedLocationRepository = new RoomTrackedLocationRepository(database.roomTrackedLocationDao());
        vm = new ViewModelProvider(this, new TrackedLocationListViewModelFactory(trackedLocationInfoRepository, trackedLocationRepository)).get(TrackedLocationListViewModel.class);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_profile)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }
    @Override
    protected void onStart() {
        super.onStart();
        CheckIsUserAuthorized();
    }

    private void CheckIsUserAuthorized() {
        if(auth.GetUserStatus() == IAuthorizationService.UserStatus.None) {
            startActivity(new Intent(this, LoginActivity.class));
        }
    }
}