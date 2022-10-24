package ru.javarush.golf.Gineika.IslandModel;

import lombok.Getter;
import ru.javarush.golf.Gineika.IslandModel.entity.animal.Animal;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Statistics {
    List<? extends Animal> animalsBirth = new ArrayList<>();
    List<? extends Animal> animalsDeath = new ArrayList<>();
    int death = 0;
    int birth = 0;

    public void addDeath(){
        death += 1;
    }

    public void addBirth(){
        birth += 1;
    }

    public void resetStatistics(){
        death = 0;
        birth = 0;
    }

    @Override
    public String toString() {
        return "Statistics{" +
                "death=" + death +
                ", birth=" + birth +
                '}';
    }
}
