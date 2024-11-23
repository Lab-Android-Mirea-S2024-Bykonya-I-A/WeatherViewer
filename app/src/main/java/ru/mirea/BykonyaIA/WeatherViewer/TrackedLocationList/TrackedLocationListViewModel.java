package ru.mirea.BykonyaIA.WeatherViewer.TrackedLocationList;

import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import ru.mirea.BykonyaIA.WeatherViewer.login.LoginActivity;
import ru.mirea.bykonyaia.domain.dto.Geoposition;
import ru.mirea.bykonyaia.domain.dto.TrackedLocationInfo;
import ru.mirea.bykonyaia.domain.repository.ITrackedLocationInfoRepository;
import ru.mirea.bykonyaia.domain.repository.ITrackedLocationRepository;
import ru.mirea.bykonyaia.domain.usecase.tl.ListTrackedLocation;
import ru.mirea.bykonyaia.domain.usecase.tl.LoadTrackedLocationInfo;

public class TrackedLocationListViewModel extends ViewModel {
    private final MutableLiveData<List<TrackedLocationInfo>> trackedLocationInfoList = new MutableLiveData<>();
    private final ITrackedLocationInfoRepository trackedLocationInfoRepository;
    private final ITrackedLocationRepository trackedLocationRepository;
    private class GetFullInfoTask extends AsyncTask<Void, Void, List<TrackedLocationInfo>> {
        @Override
        protected List<TrackedLocationInfo> doInBackground(Void... voids) {
            return new ListTrackedLocation(trackedLocationRepository).execute().stream()
                    .map(g -> new LoadTrackedLocationInfo(trackedLocationInfoRepository).execute(g))
                    .collect(Collectors.toList());
        }
        @Override
        protected void onPostExecute(List<TrackedLocationInfo> result) {
            trackedLocationInfoList.postValue(result);
        }
    }


    public TrackedLocationListViewModel(ITrackedLocationInfoRepository trackedLocationInfoRepository, ITrackedLocationRepository trackedLocationRepository) {
        Log.d("HE_HE", "TrackedLocationListViewModel created");
        this.trackedLocationInfoRepository = trackedLocationInfoRepository;
        this.trackedLocationRepository = trackedLocationRepository;

        for(var g: trackedLocationRepository.listGeoposition())
            Log.i("HE_HE", g.toString());

        new GetFullInfoTask().execute();
//        var mockItems = new ArrayList<TrackedLocationInfo>();
//        mockItems.add(new TrackedLocationInfo(new Geoposition(5, 6), new Date(), 14));
//        mockItems.add(new TrackedLocationInfo(new Geoposition(12, 5), new Date(), 42));
//        mockItems.add(new TrackedLocationInfo(new Geoposition(45, 7), new Date(), 534));
//        mockItems.add(new TrackedLocationInfo(new Geoposition(32, 58), new Date(), 16));
//        trackedLocationInfoList.postValue(mockItems);

//        trackedLocationRepository.appendGeoposition(new Geoposition(55, 37));
//        trackedLocationRepository.appendGeoposition(new Geoposition(50, 34));
//        trackedLocationRepository.appendGeoposition(new Geoposition(10, 34));
    }
    @Override
    protected void onCleared() {
        Log.d("HE_HE", "TrackedLocationListViewModel cleared");
        super.onCleared();
    }
    public LiveData<List<TrackedLocationInfo>> getTrackedLocataionInfoList() {
        return trackedLocationInfoList;
    }
}
