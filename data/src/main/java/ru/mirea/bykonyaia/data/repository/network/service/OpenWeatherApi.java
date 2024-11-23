package ru.mirea.bykonyaia.data.repository.network.service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.mirea.bykonyaia.data.repository.network.dto.OpenWeatherTrackedLocationInfoDto;

public interface OpenWeatherApi {
    @GET("data/2.5/weather")
    Call<OpenWeatherTrackedLocationInfoDto> getCurrentWeather(@Query("lat") float latitude, @Query("lon") float longitude, @Query("appid") String apiKey);
}
