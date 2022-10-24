package ru.javarush.golf.Gineika.IslandModel.island;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class Island {
    private int x;
    private int y;
    private Location[][] islandArea;

    public Island(int x, int y) {
        this.x = x;
        this.y = y;
        islandArea = new Location[x][y];
        creatIsland(x, y);
    }

    // creat island
    private void creatIsland(int x, int y) {
        for (int i = 0; i < islandArea.length; i++) {
            for (int j = 0; j < islandArea[i].length; j++) {
                islandArea[i][j] = new Location(i, j);
            }
        }
    }

    // get borders island
    public Map<String, Integer> getBordersIsland() {
        return new HashMap<String, Integer>() {{
            put("x", x-1);
            put("y", y-1);
        }};
    }

    // get location by coordinates
    public Location getLocationCast(int x, int y) {
        return islandArea[x][y];
    }
}
