package com.androiddevelopersv.rxjava.rxjavademo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.androiddevelopersv.rxjava.rxjavademo.api.Models.Pokemon;

public class PokemonViewHolder extends RecyclerView.ViewHolder {
  ImageView pokemonSprite;
  TextView pokemonName;
  public PokemonViewHolder(@NonNull View itemView) {
    super(itemView);
    pokemonName = itemView.findViewById(R.id.pokemonName);
    pokemonSprite = itemView.findViewById(R.id.pokemonSprite);
  }

  public void loadPokemon(Pokemon pokemon){
    pokemonName.setText(pokemon.getName());
    if(pokemon.getSprite() != null){
      pokemonSprite.setImageBitmap(pokemon.getSprite());
    }

  }
}
