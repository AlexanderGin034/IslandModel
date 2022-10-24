package ru.javarush.golf.Gineika.IslandModel.entity.animal.herbivore;

import ru.javarush.golf.Gineika.IslandModel.Statistics;
import ru.javarush.golf.Gineika.IslandModel.entity.Gender;
import ru.javarush.golf.Gineika.IslandModel.entity.animal.Animal;
import ru.javarush.golf.Gineika.IslandModel.entity.animal.predator.Predator;
import ru.javarush.golf.Gineika.IslandModel.island.Island;
import ru.javarush.golf.Gineika.IslandModel.island.Location;

import java.util.List;
import java.util.NoSuchElementException;

public abstract class Herbivore extends Animal {
    public Herbivore(int x, int y) {
        super(x, y);
    }

    @Override
    public void reproduce(Island island, Statistics statistics) {

        Location currentLocation = island.getLocationCast(this.getCurrentX(), this.getCurrentY());

        List<Herbivore> herbivores = currentLocation.getHerbivores();

        // выходит из метода, если нет животно того же вида и противоположного пола
        try {
            herbivores.stream()
                    .filter(e -> e.getClass() == this.getClass() && e.getGender() != this.getGender())//.filter(e -> e.getGender() != this.getGender())
                    .findAny().get();
        }catch (NoSuchElementException e) {
            return;
        }

        try {
            Herbivore herbivore = newObject(this.getClass(), this, this.getCurrentX(), this.getCurrentY());
            herbivores.add(herbivore);
            lostPower(20);
            statistics.addBirth();
        }catch (NullPointerException e){
            return;
        }
    }
}
