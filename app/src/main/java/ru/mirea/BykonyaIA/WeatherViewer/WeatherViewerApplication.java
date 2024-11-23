package ru.mirea.BykonyaIA.WeatherViewer;

import androidx.room.Room;

import ru.mirea.bykonyaia.data.repository.room.ApplicationDatabase;

public class WeatherViewerApplication extends android.app.Application {
    private static WeatherViewerApplication instance = null;
    private ApplicationDatabase applicationDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        applicationDatabase = Room.databaseBuilder(this, ApplicationDatabase.class, "database")
                .allowMainThreadQueries()
                .build();
    }
    public static WeatherViewerApplication getInstance() {
        return instance;
    }
    public ApplicationDatabase getDatabase() {
        return applicationDatabase;
    }
}
