package com.androiddevelopersv.rxjava.rxjavademo.api.Services;

import com.androiddevelopersv.rxjava.rxjavademo.api.Models.Pokemon;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RxPokemonService {

  @GET("pokemon/{idOrName}")
  Observable<Pokemon> getPokemon( @Path("idOrName") String idOrName);


}
