package com.example.tempoclima.Data.Remote.Contract;

import com.example.tempoclima.Model.WeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IWeatherEndPoint {
    @GET("weather")
    Call<WeatherResponse>
    getWeather(@Query("q") String state,
               @Query("appid") String AppId,
               @Query("units") String units,
               @Query("lang") String lang);
}
