package com.example.emptyandroid;



import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiManager {
    public static String BASE_URL = "https://dataservice.accuweather.com";

    @GET("https://dataservice.accuweather.com/forecasts/v1/hourly/12hour/353412?apikey=8FbxPF1thSiMkKBtMc3BRlT4gxHwiG7p")
    Call<List<Weather>> gethour();

    @GET("https://dataservice.accuweather.com/forecasts/v1/daily/5day/353412?apikey=8FbxPF1thSiMkKBtMc3BRlT4gxHwiG7p")
    Call<List<Weather>> getDay();
}
