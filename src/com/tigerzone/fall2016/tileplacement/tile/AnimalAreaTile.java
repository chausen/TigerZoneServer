package com.tigerzone.fall2016.tileplacement.tile;

import com.tigerzone.fall2016.animals.Animal;
import com.tigerzone.fall2016.tileplacement.terrain.Terrain;

/**
 * Created by matthewdiaz on 11/12/16.
 */
public class AnimalAreaTile extends Tile {
    private Animal animal;

    public AnimalAreaTile(Edge northEdge, Edge eastEdge, Edge southEdge, Edge westEdge, Terrain center, Animal animal) {
        super(northEdge, eastEdge, southEdge, westEdge, center);
        this.animal = animal;
    }

    public Animal getAnimal(){
        return this.animal;
    }
}
