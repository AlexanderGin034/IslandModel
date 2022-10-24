package ru.javarush.golf.Gineika.IslandModel.island;

import lombok.Getter;
import ru.javarush.golf.Gineika.IslandModel.entity.Type;
import ru.javarush.golf.Gineika.IslandModel.entity.animal.Animal;
import ru.javarush.golf.Gineika.IslandModel.entity.animal.herbivore.Herbivore;
import ru.javarush.golf.Gineika.IslandModel.entity.animal.predator.Bear;
import ru.javarush.golf.Gineika.IslandModel.entity.animal.predator.Boa;
import ru.javarush.golf.Gineika.IslandModel.entity.animal.predator.Predator;
import ru.javarush.golf.Gineika.IslandModel.entity.animal.predator.Wolf;
import ru.javarush.golf.Gineika.IslandModel.entity.plant.Plant;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Location {

    // location coordinates
    private final int x;
    private final int y;

    // filling
    private final List<Predator> predators = new ArrayList<>();
    private final List<Herbivore> herbivores = new ArrayList<>();
    private final List<Plant> plants = new ArrayList<>();

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // add a new entity
    public void addEntity(Type obj) {
        Class<? extends Type> aClass = obj.getClass();
        Class<?> superclass = aClass.getSuperclass();

        if (superclass == Predator.class) {
            predators.add((Predator) obj);
        }else if (superclass == Herbivore.class){
            herbivores.add((Herbivore) obj);
        }else{
            plants.add((Plant) obj);
        }
    }

    // remove an old entity
    public void removeEntity(Type obj){
        Class<? extends Type> aClass = obj.getClass();
        Class<?> superclass = aClass.getSuperclass();

        if (superclass == Predator.class) {
            predators.remove((Predator) obj);
        }else if (superclass == Herbivore.class){
            herbivores.remove((Herbivore) obj);
        }else{
            plants.remove((Plant) obj);
        }
    }
}
