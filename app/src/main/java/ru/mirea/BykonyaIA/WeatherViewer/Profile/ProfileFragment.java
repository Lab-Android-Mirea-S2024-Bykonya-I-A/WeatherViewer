package ru.mirea.BykonyaIA.WeatherViewer.Profile;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.mirea.BykonyaIA.WeatherViewer.R;
import ru.mirea.BykonyaIA.WeatherViewer.databinding.FragmentProfileBinding;

public class ProfileFragment extends Fragment {
    private FragmentProfileBinding binding = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        var vm = new ViewModelProvider(requireActivity()).get(ProfileViewModel.class);
        vm.getUsername().observe(getViewLifecycleOwner(), s -> binding.nameView.setText("Name: " + s));
        binding.nameView.setText("Name: " + vm.getUsername().getValue());
        binding.logOutButton.setOnClickListener(v -> vm.LogOut());
    }
}
