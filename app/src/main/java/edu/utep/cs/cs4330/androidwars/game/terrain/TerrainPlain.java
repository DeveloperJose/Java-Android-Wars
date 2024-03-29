/**
 * Author: Jose Perez <josegperez@mail.com> and Diego Reynoso
 */
package edu.utep.cs.cs4330.androidwars.game.terrain;

import android.graphics.Color;

import edu.utep.cs.cs4330.androidwars.util.Vector2;

public class TerrainPlain extends Terrain {
    public TerrainPlain(Vector2 mapPosition) {
        super("terrain_plain", Color.YELLOW, mapPosition);
    }
}
