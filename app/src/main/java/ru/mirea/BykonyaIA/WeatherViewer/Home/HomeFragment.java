package ru.mirea.BykonyaIA.WeatherViewer.Home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.mirea.BykonyaIA.WeatherViewer.Profile.ProfileViewModel;
import ru.mirea.BykonyaIA.WeatherViewer.R;
import ru.mirea.BykonyaIA.WeatherViewer.TrackedLocationList.TrackedLocationListViewModel;
import ru.mirea.BykonyaIA.WeatherViewer.databinding.FragmentHomeBinding;
import ru.mirea.BykonyaIA.WeatherViewer.databinding.FragmentProfileBinding;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding = null;
    public HomeFragment() {}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();

        var trackedLocationsListFragment = new TrackedLocationListViewFragment();
        trackedLocationsListFragment.SetItemClickedListener(info -> {
            var bundle = new Bundle();
            bundle.putString("icon_uri", info.iconUri());
            bundle.putString("title", info.Geoposition().toString());
            bundle.putString("description", info.toString());

            Navigation.findNavController(requireView()).navigate(R.id.navigation_short_info, bundle);
        });
        getParentFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragment_container_view, trackedLocationsListFragment)
                .commit();
    }
}