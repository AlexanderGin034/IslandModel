package ru.javarush.golf.Gineika.IslandModel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.javarush.golf.Gineika.IslandModel.entity.Gender;
import ru.javarush.golf.Gineika.IslandModel.entity.animal.Animal;
import ru.javarush.golf.Gineika.IslandModel.entity.animal.herbivore.*;
import ru.javarush.golf.Gineika.IslandModel.entity.animal.predator.Bear;
import ru.javarush.golf.Gineika.IslandModel.entity.animal.predator.Fox;
import ru.javarush.golf.Gineika.IslandModel.entity.animal.predator.Predator;
import ru.javarush.golf.Gineika.IslandModel.entity.animal.predator.Wolf;
import ru.javarush.golf.Gineika.IslandModel.entity.plant.Plant;
import ru.javarush.golf.Gineika.IslandModel.island.Island;
import ru.javarush.golf.Gineika.IslandModel.island.Location;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StartSimulation {
    public static void main(String[] args) throws JsonProcessingException {
        // генерация животных на острове
        Statistics statistics = new Statistics();
        Island island = new Island(100, 100);
        Creator creator = new Creator(1000000, island);
        //System.out.println(creator.toString());
        creator.createUnits();
        int index = 0;
        for (int i = 0; i < island.getX(); i++){
            for (int j = 0; j < island.getY(); j++){
                Location locationCast = island.getLocationCast(i, j);
                StaticIslandModel.startLiving(locationCast.getPredators(), locationCast.getPredators().size(), island, statistics);
                StaticIslandModel.startLiving(locationCast.getHerbivores(), locationCast.getPredators().size(), island, statistics);
            }
        }
        System.out.println(statistics.toString());
    }

}
