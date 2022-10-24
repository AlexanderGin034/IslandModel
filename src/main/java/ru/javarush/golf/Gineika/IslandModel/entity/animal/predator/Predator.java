package ru.javarush.golf.Gineika.IslandModel.entity.animal.predator;

import ru.javarush.golf.Gineika.IslandModel.StaticIslandModel;
import ru.javarush.golf.Gineika.IslandModel.Statistics;
import ru.javarush.golf.Gineika.IslandModel.entity.Gender;
import ru.javarush.golf.Gineika.IslandModel.entity.animal.Animal;
import ru.javarush.golf.Gineika.IslandModel.entity.animal.Hunterable;
import ru.javarush.golf.Gineika.IslandModel.island.Island;
import ru.javarush.golf.Gineika.IslandModel.island.Location;

import java.util.List;
import java.util.NoSuchElementException;

public abstract class Predator extends Animal {
    public Predator(int x, int y) {
        super(x, y);
    }

    @Override
    public void reproduce(Island island, Statistics statistics) {
        Gender gender = this.getGender();

        Location currentLocation = island.getLocationCast(this.getCurrentX(), this.getCurrentY());

        List<Predator> predators = currentLocation.getPredators();

        // выходит из метода, если нет животно того же вида и противополоного пола
        try {
            predators.stream()
                    .filter(e -> e.getClass() == this.getClass()).filter(e -> e.getGender() != gender)
                    .findAny().get();
        }catch (NoSuchElementException e) {
            return;
        }

        try {
            Predator predator = newObject(this.getClass(), this, this.getCurrentX(), this.getCurrentY());
            predators.add(predator);
            lostPower(20);
            statistics.addBirth();
        }catch (NullPointerException e){
            return;
        }
    }
}
