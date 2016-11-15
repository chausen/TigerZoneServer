package com.tigerzone.fall2016.tileplacement.tile;

import com.tigerzone.fall2016.animals.Animal;
import com.tigerzone.fall2016.animals.Boar;
import com.tigerzone.fall2016.tileplacement.Direction;
import com.tigerzone.fall2016.tileplacement.terrain.Terrain;

import java.util.List;

/**
 * Created by lenovo on 11/14/2016.
 */
public class TerrainNode {

    Terrain terrain;
    BoardTile boardTile;
    List<TerrainNode> terrainNodeList;
    Animal animal;
    List<Integer> canConnectTo;
    private List<Integer> zones;

    public void rotateCCW(int degrees) {
        int rotations = degrees/90;
        for (int i=0; i<rotations; i++) {
            for (Integer zone: zones) {
                switch(zone) {
                    case 1: zone=3;
                        break;
                    case 2: zone=6;
                        break;
                    case 3: zone=9;
                        break;
                    case 4: zone=2;
                        break;
                    case 5: zone=5;
                        break;
                    case 6: zone=8;
                        break;
                    case 7: zone=1;
                        break;
                    case 8: zone=4;
                        break;
                    case 9: zone=7;
                        break;
                }
        }
        }


    }


}
