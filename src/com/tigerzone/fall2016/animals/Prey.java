package com.tigerzone.fall2016.animals;

import com.tigerzone.fall2016.area.Area;

/**
 * Created by matthewdiaz on 11/11/16.
 */
public abstract class Prey extends Animal{
    @Override
    public void addToArea(Area area){ area.addAnimalFromAreaTile(this);}
}

