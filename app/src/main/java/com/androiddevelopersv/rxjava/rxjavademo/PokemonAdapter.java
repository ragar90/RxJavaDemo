package com.androiddevelopersv.rxjava.rxjavademo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androiddevelopersv.rxjava.rxjavademo.api.Models.Pokemon;

import java.util.ArrayList;
import java.util.List;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonViewHolder> {
  List<Pokemon> pokemones;

  public PokemonAdapter() {
    this.pokemones = new ArrayList<>();
  }

  @NonNull
  @Override
  public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.pokemon_row, viewGroup,false);
    PokemonViewHolder viewHolder = new PokemonViewHolder(v);
    return viewHolder;
  }

  @Override
  public void onBindViewHolder(@NonNull PokemonViewHolder pokemonViewHolder, int i) {
    Pokemon pokemon = pokemones.get(i);
    pokemonViewHolder.loadPokemon(pokemon);
  }

  @Override
  public int getItemCount() {
    return pokemones.size();
  }

  public void releasePokemones(){
    pokemones.clear();
    this.notifyDataSetChanged();
  }

  public void addPokemon(Pokemon pokemon){
    pokemones.add(pokemon);
    this.notifyDataSetChanged();
  }
}
