package ru.mirea.bykonyaia.data.repository.network;

import android.os.Debug;
import android.util.Log;

import java.util.Date;
import java.util.Objects;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.mirea.bykonyaia.data.repository.network.service.OpenWeatherApi;
import ru.mirea.bykonyaia.domain.dto.Geoposition;
import ru.mirea.bykonyaia.domain.dto.TrackedLocationInfo;
import ru.mirea.bykonyaia.domain.repository.ITrackedLocationInfoRepository;

public class NetworkTrackedLocationInfoRepository implements ITrackedLocationInfoRepository {
    private final OpenWeatherApi openWeatherApi;
    private final String apiKey;
    public NetworkTrackedLocationInfoRepository(String apiKey) {
        openWeatherApi = new Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(OpenWeatherApi.class);
        this.apiKey = apiKey;
    }


    @Override
    public TrackedLocationInfo get(Geoposition geoposition) {
        try {
            var result = openWeatherApi.
                    getCurrentWeather(geoposition.Latitude(), geoposition.Longitude(), apiKey).
                    execute();

            if(result.code() == 200) {
                var info = result.body();
                var iconUri = info.getIconId().isEmpty() ? "" : String.format("https://openweathermap.org/img/wn/%s.png", info.getIconId().get(0).getId());
                return new TrackedLocationInfo(
                        geoposition,
                        new Date(Objects.requireNonNull(info).getTimestamp() * 1000L),
                        info.getMainInfo().getTemperature(),
                        iconUri
                );
            } else {
                return null;
            }
        } catch (Exception e) {
            Log.i("HE_HE", "Error occured: " + e.getMessage());
            return null;
        }
    }
}
