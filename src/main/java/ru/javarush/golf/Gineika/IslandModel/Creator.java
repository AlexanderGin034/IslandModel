package ru.javarush.golf.Gineika.IslandModel;

import ru.javarush.golf.Gineika.IslandModel.entity.Type;
import ru.javarush.golf.Gineika.IslandModel.entity.animal.Animal;
import ru.javarush.golf.Gineika.IslandModel.entity.animal.herbivore.*;
import ru.javarush.golf.Gineika.IslandModel.entity.animal.predator.*;
import ru.javarush.golf.Gineika.IslandModel.entity.plant.Plant;
import ru.javarush.golf.Gineika.IslandModel.island.Island;
import ru.javarush.golf.Gineika.IslandModel.island.Location;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Creator {
    int units;
    Island island;

    public Creator(int units, Island island) {
        this.units = units;
        this.island = island;
    }

    // запускаем в цикле создание объектов
    public void createUnits(){
        while (units >= 0){
            int localThreadRandomX = ThreadLocalRandom.current().nextInt(0, island.getX());
            int localThreadRandomY = ThreadLocalRandom.current().nextInt(0, island.getY());
            Location locationCast = island.getLocationCast(localThreadRandomX, localThreadRandomY);
            int localThreadRandomType = ThreadLocalRandom.current().nextInt(0, 17);

            createObjectByNumber(localThreadRandomType, locationCast, localThreadRandomX, localThreadRandomY);

            units--;
        }
    }

    //
    private void createObjectByNumber(int number, Location location, int x, int y){
        switch (number){
            case 1 -> {
                List<Herbivore> herbivores = location.getHerbivores();
                int count = (int) herbivores.stream().filter(e -> e.getClass() == Boar.class).count();
                if (count >= Boa.MAX_QUANTITY ) return;
                herbivores.add(new Boar(x, y));
            }
            case 2 -> {
                List<Herbivore> herbivores = location.getHerbivores();
                int count = (int) herbivores.stream().filter(e -> e.getClass() == Boar.class).count();
                if (count >= Buffalo.MAX_QUANTITY ) return;
                herbivores.add(new Buffalo(x, y));
            }
            case 3 -> {
                List<Herbivore> herbivores = location.getHerbivores();
                int count = (int) herbivores.stream().filter(e -> e.getClass() == Boar.class).count();
                if (count >= Caterpillar.MAX_QUANTITY ) return;
                herbivores.add(new Caterpillar(x, y));
            }
            case 4 -> {
                List<Herbivore> herbivores = location.getHerbivores();
                int count = (int) herbivores.stream().filter(e -> e.getClass() == Boar.class).count();
                if (count >= Deer.MAX_QUANTITY ) return;
                herbivores.add(new Deer(x, y));
            }
            case 5 -> {
                List<Herbivore> herbivores = location.getHerbivores();
                int count = (int) herbivores.stream().filter(e -> e.getClass() == Boar.class).count();
                if (count >= Duck.MAX_QUANTITY ) return;
                herbivores.add(new Duck(x, y));
            }
            case 6 -> {
                List<Herbivore> herbivores = location.getHerbivores();
                int count = (int) herbivores.stream().filter(e -> e.getClass() == Boar.class).count();
                if (count >= Goat.MAX_QUANTITY ) return;
                herbivores.add(new Goat(x, y));
            }
            case 7 -> {
                List<Herbivore> herbivores = location.getHerbivores();
                int count = (int) herbivores.stream().filter(e -> e.getClass() == Boar.class).count();
                if (count >= Horse.MAX_QUANTITY ) return;
                herbivores.add(new Horse(x, y));
            }
            case 8 -> {
                List<Herbivore> herbivores = location.getHerbivores();
                int count = (int) herbivores.stream().filter(e -> e.getClass() == Boar.class).count();
                if (count >= Mouse.MAX_QUANTITY ) return;
                herbivores.add(new Mouse(x, y));
            }
            case 9 -> {
                List<Herbivore> herbivores = location.getHerbivores();
                int count = (int) herbivores.stream().filter(e -> e.getClass() == Boar.class).count();
                if (count >= Rabbit.MAX_QUANTITY ) return;
                herbivores.add(new Rabbit(x, y));
            }
            case 10 -> {
                List<Herbivore> herbivores = location.getHerbivores();
                int count = (int) herbivores.stream().filter(e -> e.getClass() == Boar.class).count();
                if (count >= Sheep.MAX_QUANTITY ) return;
                herbivores.add(new Sheep(x, y));
            }
            case 11 -> {
                List<Predator> predators = location.getPredators();
                int count = (int) predators.stream().filter(e -> e.getClass() == Bear.class).count();
                if (count >= Bear.MAX_QUANTITY ) return;
                predators.add(new Bear(x, y));
            }
            case 12 -> {
                List<Predator> predators = location.getPredators();
                int count = (int) predators.stream().filter(e -> e.getClass() == Boa.class).count();
                if (count >= Boa.MAX_QUANTITY ) return;
                predators.add(new Boa(x, y));
            }
            case 13 -> {
                List<Predator> predators = location.getPredators();
                int count = (int) predators.stream().filter(e -> e.getClass() == Eagle.class).count();
                if (count >= Eagle.MAX_QUANTITY ) return;
                predators.add(new Eagle(x, y));
            }
            case 14 -> {
                List<Predator> predators = location.getPredators();
                int count = (int) predators.stream().filter(e -> e.getClass() == Fox.class).count();
                if (count >= Fox.MAX_QUANTITY ) return;
                predators.add(new Fox(x, y));
            }
            case 15 -> {
                List<Predator> predators = location.getPredators();
                int count = (int) predators.stream().filter(e -> e.getClass() == Wolf.class).count();
                if (count >= Wolf.MAX_QUANTITY ) return;
                predators.add(new Wolf(x, y));
            }
            default -> {
                List<Plant> plants= location.getPlants();
                int count = (int) plants.stream().filter(e -> e.getClass() == Plant.class).count();
                plants.add(new Plant());
            }

        }
    }

    //
//    private ArrayList<?> getArrOfLocation(int num, Location location){
//        return switch (num){
//            case 1 -> {
//                List<Herbivore> herbivores = location.getHerbivores();
//                yield herbivores;
//            }
//            case 2 -> location.getPredators();
//            default -> location.getPlants();
//        };
//    }

    //
    private Location getLocation(Island island, int x, int y){
        return island.getLocationCast(x, y);
    }

//    private <T> boolean getBool(Class<T> type, int count, Type entity) {
//        T entityT = (T) entity;
//        entityT
//    }
}
