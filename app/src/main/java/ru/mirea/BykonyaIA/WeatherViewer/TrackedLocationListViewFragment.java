package ru.mirea.BykonyaIA.WeatherViewer;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import ru.mirea.BykonyaIA.WeatherViewer.TrackedLocationList.TrackedLocationListViewAdapter;
import ru.mirea.BykonyaIA.WeatherViewer.TrackedLocationList.TrackedLocationListViewModel;
import ru.mirea.BykonyaIA.WeatherViewer.databinding.FragmentTrackedLocationListViewBinding;
import ru.mirea.bykonyaia.domain.dto.Geoposition;

public class TrackedLocationListViewFragment extends Fragment {
    private TrackedLocationListViewAdapter.ItemClickedListener listener = null;
    public void SetItemClickedListener(TrackedLocationListViewAdapter.ItemClickedListener listener) {
        this.listener = listener;
    }

    private FragmentTrackedLocationListViewBinding binding = null;
    private TrackedLocationListViewModel vm = null;
    public TrackedLocationListViewFragment() {}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentTrackedLocationListViewBinding.inflate(inflater, container, false);
        binding.itemsListView.setLayoutManager(new LinearLayoutManager(getContext()));
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        var activity = requireActivity();
        Log.d("HE_HE", "Activity: " + activity);
        var vmp = new ViewModelProvider(activity);
        Log.d("HE_HE", "VMP: " + vmp);
        var vm = vmp.get(TrackedLocationListViewModel.class);
//        vm = new ViewModelProvider(requireActivity()).get(TrackedLocationListViewModel.class);
        Log.d("HE_HE", "VM: " + vm);
        vm.getTrackedLocataionInfoList().observe(getViewLifecycleOwner(), trackedLocationInfos -> {
            var adapter = new TrackedLocationListViewAdapter(getContext(), vm.getTrackedLocataionInfoList().getValue());
            binding.itemsListView.setAdapter(adapter);
            adapter.SetItemClickedListener(info -> {
                if(listener != null) {
                    listener.onClick(info);
                }
            });
            Log.i("HE_HE", "DATA MVVM CHANGED");
        });
    }
}