  package com.androiddevelopersv.rxjava.rxjavademo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.androiddevelopersv.rxjava.rxjavademo.api.CallApiService;
import com.androiddevelopersv.rxjava.rxjavademo.api.Models.Pokemon;
import com.androiddevelopersv.rxjava.rxjavademo.api.RxApiService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

  public class MainActivity extends AppCompatActivity {
    private static final String TAG = "POKEMON";
  private static final String[] POKEMONS = {
      "bulbasaur",
      "charmander",
      "squirtle",
      "pikachu",
      "mew",
      "mewtwo",
  };

  Button rxButton;
  Button callButton;
  RecyclerView pokemonTeamRecyclerView;
  List<Pokemon> pokemonTeam;
  PokemonAdapter pokemonTeamAdapter;

    @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    pokemonTeam = new ArrayList<>();
    rxButton = findViewById(R.id.rxButton);
    callButton = findViewById(R.id.callButton);
    pokemonTeamRecyclerView = findViewById(R.id.pokemonTeamList);
    rxButton.setOnClickListener(v -> {
      pokemonTeamAdapter.releasePokemones();
      loadRxPokemones();
    });

    callButton.setOnClickListener(v -> {
      pokemonTeamAdapter.releasePokemones();
      loadCallPokemones();
    });
    pokemonTeamRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    pokemonTeamAdapter = new PokemonAdapter();
    pokemonTeamRecyclerView.setAdapter(pokemonTeamAdapter );
  }

  public void addPokemon(Pokemon pokemon){
    String talk = pokemon.getName() + "!";
    Log.d(TAG, "El Pokemon => " + talk);
    pokemonTeamAdapter.addPokemon(pokemon);
  }


  private void loadRxPokemones(){
    RxApiService.getInstance().getPokemon(POKEMONS[0])
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(pokemon -> {
          addPokemon(pokemon);
        }, error ->{
          Log.e(TAG, error.getLocalizedMessage());
        });
  }

  private void loadCallPokemones(){
    CallApiService.getInstance()
        .getPokemon(POKEMONS[1], new Callback<Pokemon>() {
          @Override
          public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
            if(response.isSuccessful()){
              Pokemon pokemon = response.body();
              addPokemon(pokemon);
            }
          }

          @Override
          public void onFailure(Call<Pokemon> call, Throwable t) {
            System.out.println("Something Wrong Happened");
            t.printStackTrace();
          }
        });
  }
}
