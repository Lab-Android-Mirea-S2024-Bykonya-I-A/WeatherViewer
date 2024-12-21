package ru.mirea.BykonyaIA.WeatherViewer;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import ru.mirea.BykonyaIA.WeatherViewer.TrackedLocationList.TrackedLocationListViewAdapter;
import ru.mirea.BykonyaIA.WeatherViewer.TrackedLocationList.TrackedLocationListViewModel;
import ru.mirea.BykonyaIA.WeatherViewer.databinding.FragmentTrackedLocationInfoViewBinding;
import ru.mirea.BykonyaIA.WeatherViewer.databinding.FragmentTrackedLocationListViewBinding;
import ru.mirea.bykonyaia.domain.dto.Geoposition;


public class TrackedLocationInfoViewFragment extends Fragment {
    private FragmentTrackedLocationInfoViewBinding binding = null;
    public TrackedLocationInfoViewFragment() {}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentTrackedLocationInfoViewBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(getArguments() != null) {

            Picasso.get()
                    .load(requireArguments().getString("icon_uri"))
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_foreground)
                    .fit().into(binding.iconView);
            binding.titleText.setText(requireArguments().getString("title"));
            binding.descriptionText.setText(requireArguments().getString("description"));
        }
    }
}
