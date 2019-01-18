package com.androiddevelopersv.rxjava.rxjavademo.api;

import com.androiddevelopersv.rxjava.rxjavademo.api.Models.Pokemon;
import com.androiddevelopersv.rxjava.rxjavademo.api.Services.RxPokemonService;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RxApiService {
  private static final String SERVICE_URL = "https://pokeapi.co/api/v2/";
  private static RxApiService instance;
  private Retrofit retrofitInstance;
  private RxPokemonService pokemonService;

  private RxApiService(boolean loggingEnabled) {
    this.retrofitInstance = initRetrofit(loggingEnabled);
    this.pokemonService = retrofitInstance.create(RxPokemonService.class);
  }

  //region Services Calls
  public Observable<Pokemon> getPokemon(String idOrNane){
    return pokemonService.getPokemon(idOrNane);
  }
  //endregion

  //region Static Setup Methods

  public static RxApiService getInstance(){
    if(instance == null){
      instance = new RxApiService(true);
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