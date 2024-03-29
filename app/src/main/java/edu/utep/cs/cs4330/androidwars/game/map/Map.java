/**
 * Author: Jose Perez <josegperez@mail.com> and Diego Reynoso
 */
package edu.utep.cs.cs4330.androidwars.game.map;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.utep.cs.cs4330.androidwars.game.terrain.Terrain;
import edu.utep.cs.cs4330.androidwars.resource.ResourceManager;
import edu.utep.cs.cs4330.androidwars.util.Vector2;

/**
 * A map representing a playing field of a specified size
 */
public final class Map implements Serializable {
    private static final String TAG = "AndroidWars.Map";
    private List<MapListener> listeners;
    private Place[][] places;
    public final int width;
    public final int height;

    /**
     * Reads a map from a given filename
     * The extension for the filename is not needed
     *
     * @param filename Name of file in the raw resources (no extension)
     * @return Map read from the file
     */
    public static Map fromFilename(String filename) {
        Scanner input = ResourceManager.getScanner(filename);

        final int width = input.nextInt();
        final int height = input.nextInt();
        final String separator = " ";

        input.nextLine(); // Eat empty newline (just Java things)

        // Begin map loading
        Map mapTemp = new Map(width, height);

        // Terrain loading
        for (int y = 0; y < height; y++) {
            String[] line = input.nextLine().split(separator);
            for (int x = 0; x < line.length; x++) {
                String terrainName = line[x];
                Vector2 pos = new Vector2(x, y);

                // Terrain loading
                Terrain terrain = Terrain.fromName(terrainName, pos);
                mapTemp.placeAt(pos).terrain = terrain;
            }
        }

        input.close();
        return mapTemp;
    }

    public Map(int width, int height) {
        this.width = width;
        this.height = height;

        places = new Place[width][height];
        for (int i = 0; i < places.length; i++)
            for (int j = 0; j < places[i].length; j++)
                places[i][j] = new Place(new Vector2(i, j));
    }

    private void notifyOnBoardUpdate() {
        for (MapListener listener : listeners)
            listener.onMapUpdate();
    }

    public void registerListener(MapListener listener) {
        listeners.add(listener);
    }

    public synchronized Place placeAt(Vector2 pos) {
        return placeAt(pos.X, pos.Y);
    }

    public synchronized Place placeAt(int x, int y) {
        if (!isValidPosition(x, y))
            return null;

        return places[x][y];
    }

    public boolean isValidPosition(int x, int y){
        return x >= 0 && y >= 0 && x < width && y < height;
    }

    public boolean isValidPosition(Vector2 pos){
        return isValidPosition(pos.X, pos.Y);
    }

    public List<Place> getPlaceNeighbors(Vector2 pos){
        List<Vector2> neighbors = getPositionNeighbors(pos);
        List<Place> placeList = new ArrayList<>();

        for(Vector2 vector : neighbors)
            placeList.add(placeAt(vector));

        return placeList;
    }

    public List<Vector2> getPositionNeighbors(Vector2 pos){
        List<Vector2> neighbors = new ArrayList<>();
        Vector2 up = new Vector2(pos.X, pos.Y-1);
        Vector2 down = new Vector2(pos.X, pos.Y+1);
        Vector2 left = new Vector2(pos.X-1, pos.Y);
        Vector2 right = new Vector2(pos.X+1, pos.Y);

        if(isValidPosition(up))
            neighbors.add(up);
        if(isValidPosition(down))
            neighbors.add(down);
        if(isValidPosition(left))
            neighbors.add(left);
        if(isValidPosition(right))
            neighbors.add(right);

        return neighbors;
    }
}
