package ru.javarush.golf.Gineika.IslandModel;

import ru.javarush.golf.Gineika.IslandModel.entity.animal.Animal;
import ru.javarush.golf.Gineika.IslandModel.entity.animal.predator.Predator;
import ru.javarush.golf.Gineika.IslandModel.island.Island;
import ru.javarush.golf.Gineika.IslandModel.island.Location;

import java.util.List;

public class StaticIslandModel {
    public static int getSteepAnimal(Animal animalObj) {
        return 0;
    }

    public static List<? extends Animal> getAnimalList(Class<? extends Animal> animal, Location location) {
        Class<?> superclass = animal.getSuperclass();
        if (superclass == Predator.class) {
            return location.getPredators();
        }else {
            return location.getHerbivores();
        }
    }

    public static <T extends Animal> T getClassAnimal(Class<T>  clazz, Animal anima) {
        T animalT = (T) anima;
        return animalT;
    }

    public static void startLiving(List<? extends Animal> list, int size, Island island, Statistics statistics){

        // цик для еды
        for (int i = 0; i < size; i++) {

            if (i < 0) return;

            if (list.size() < size) {
                size -= 1;
                --i;
            }

            if (list.size() > size) {
                size += 1;
            }

            list.get(i).eat(island, statistics);
            list.get(i).reproduce(island, statistics);
            list.get(i).move(island);
        }
        System.out.println(statistics.toString());

        // цикл для размножения

        // цикл для движения


    }
}