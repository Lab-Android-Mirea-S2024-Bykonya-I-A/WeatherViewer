package ru.mirea.bykonyaia.data.repository.network.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenWeatherTrackedLocationInfoDto {
    public class Geoposition {
        @SerializedName("lat")
        @Expose
        private float Latitude;
        @SerializedName("lon")
        @Expose
        private float Longitude;

        public Geoposition(){}
        public Geoposition(float latitude, float longitude) {
            Latitude = latitude;
            Longitude = longitude;
        }

        public float getLatitude() {
            return Latitude;
        }
        public float getLongitude() {
            return Longitude;
        }
    }
    @JsonIgnoreProperties(ignoreUnknown = true)
    public class IconId {
        @SerializedName("icon")
        @Expose
        private String id;
        public IconId() {}
        public IconId(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }
    }
    @JsonIgnoreProperties(ignoreUnknown = true)
    public class MainInfo {
        @SerializedName("temp")
        @Expose
        private float Temperature;
        @SerializedName("pressure")
        @Expose
        private float Pressure;
        @SerializedName("humidity")
        @Expose
        private float Humidity;

        public MainInfo() {}
        public MainInfo(float temperature, float pressure, float humidity) {
            Temperature = temperature;
            Pressure = pressure;
            Humidity = humidity;
        }

        public float getTemperature() {
            return Temperature;
        }
        public float getPressure() {
            return Pressure;
        }
        public float getHumidity() {
            return Humidity;
        }
    }


    @SerializedName("coord")
    @Expose
    private Geoposition Geoposition;
    @SerializedName("weather")
    @Expose
    private List<IconId> IconId;
    @SerializedName("main")
    @Expose
    private MainInfo MainInfo;
    @SerializedName("dt")
    @Expose
    private int Timestamp;

    public OpenWeatherTrackedLocationInfoDto(OpenWeatherTrackedLocationInfoDto.Geoposition geoposition, List<OpenWeatherTrackedLocationInfoDto.IconId> iconId, OpenWeatherTrackedLocationInfoDto.MainInfo mainInfo, int timestamp) {
        Geoposition = geoposition;
        IconId = iconId;
        MainInfo = mainInfo;
        Timestamp = timestamp;
    }
    public OpenWeatherTrackedLocationInfoDto.Geoposition getGeoposition() {
        return Geoposition;
    }
    public int getTimestamp() {
        return Timestamp;
    }
    public OpenWeatherTrackedLocationInfoDto.MainInfo getMainInfo() {
        return MainInfo;
    }
    public List<OpenWeatherTrackedLocationInfoDto.IconId> getIconId() {
        return IconId;
    }
}