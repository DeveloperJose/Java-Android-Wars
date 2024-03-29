/**
 * Author: Jose Perez <josegperez@mail.com> and Diego Reynoso
 */
package edu.utep.cs.cs4330.androidwars.game.terrain;

import android.util.Log;

import edu.utep.cs.cs4330.androidwars.game.Sprite;
import edu.utep.cs.cs4330.androidwars.util.Vector2;

public abstract class Terrain extends Sprite {
    private static String TAG = "AndroidWars.Terrain";

    public Terrain(String filename, int colorDebug, Vector2 mapPosition) {
        super(filename, colorDebug, mapPosition);
    }

    public static Terrain fromName(String name, Vector2 mapPosition) {
        if (name.equalsIgnoreCase("bv"))
            return new TerrainBridgeV(mapPosition);

        else if (name.equalsIgnoreCase("br"))
            return new TerrainBridgeR(mapPosition);

        else if (name.equalsIgnoreCase("f"))
            return new TerrainForest(mapPosition);

        else if (name.equalsIgnoreCase("m"))
            return new TerrainMountain(mapPosition);

        else if (name.equalsIgnoreCase("p"))
            return new TerrainPlain(mapPosition);

        else if (name.equalsIgnoreCase("r"))
            return new TerrainRiver(mapPosition);

        else if (name.equalsIgnoreCase("v"))
            return new TerrainValley(mapPosition);

        else if (name.equalsIgnoreCase("w"))
            return new TerrainWall(mapPosition);

        Log.d(TAG, "Attempted to load an invalid terrain: " + name);
        return null;
    }
}
