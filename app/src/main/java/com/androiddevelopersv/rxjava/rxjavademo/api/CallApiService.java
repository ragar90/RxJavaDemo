package com.androiddevelopersv.rxjava.rxjavademo.api;

import com.androiddevelopersv.rxjava.rxjavademo.api.Models.Pokemon;
import com.androiddevelopersv.rxjava.rxjavademo.api.Services.CallPokemService;
import com.androiddevelopersv.rxjava.rxjavademo.api.Services.RxPokemonService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class CallApiService {

  private static final String SERVICE_URL = "https://pokeapi.co/api/v2/";
  private static CallApiService instance;
  private Retrofit retrofitInstance;
  private CallPokemService pokemonService;

  private CallApiService(boolean loggingEnabled) {
    this.retrofitInstance = initRetrofit(loggingEnabled);
    this.pokemonService = retrofitInstance.create(CallPokemService.class);
  }


  public void getPokemon(String idOrNane, Callback<Pokemon> callback){
    pokemonService.getPokemon(idOrNane).enqueue(callback);
  }

  //region Static Setup Methods

  public static CallApiService getInstance(){
    if(instance == null){
      instance = new CallApiService(true);
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
        .client(httpClient.build()) // Utiliza Okhttp para interceptar requests
        .build();
    return retrofit;
  }
  //endregion
}
