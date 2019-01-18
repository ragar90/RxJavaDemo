package com.androiddevelopersv.rxjava.rxjavademo.api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {
  private static final String SERVICE_URL = "https://pokeapi.co/api/v2/";
  private static ApiService instance;
  private Retrofit retrofitInstance;

  private ApiService(boolean loggingEnabled) {
    this.retrofitInstance = initRetrofit(loggingEnabled);
  }

  //region Static Methods

  private static ApiService getInstance(){
    if(instance == null){
      instance = new ApiService(true);
    }
    return instance;
  }

  private static Retrofit initRetrofit(boolean loggingEnabled) {
    OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
        .readTimeout(30, TimeUnit.SECONDS)
        .connectTimeout(10, TimeUnit.SECONDS);

    if(loggingEnabled) {
      HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
      logging.setLevel(HttpLoggingInterceptor.Level.BODY);
      httpClient.addInterceptor(logging);  // <-- this is the important line!
    }

    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(SERVICE_URL)
        .addConverterFactory(GsonConverterFactory.create()) // <---- Agrega Soprte para Gson
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // <--- Agrega soporte para RxJava
        .client(httpClient.build()) // Utiliza Okhttp para interceptar requests
        .build();
    return retrofit;
  }
  //endregion
}