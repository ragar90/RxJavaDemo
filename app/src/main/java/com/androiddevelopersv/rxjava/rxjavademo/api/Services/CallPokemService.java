package com.androiddevelopersv.rxjava.rxjavademo.api.Services;

import com.androiddevelopersv.rxjava.rxjavademo.api.Models.Pokemon;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CallPokemService {

  @GET("pokemon/{idOrName}")
  Call<Pokemon> getPokemon(@Path("idOrName") String idOrName);
}
