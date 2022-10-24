package ru.javarush.golf.Gineika.IslandModel.entity.animal.herbivore;

import ru.javarush.golf.Gineika.IslandModel.entity.DescriptionEntity;

public class Rabbit extends Herbivore{
    public static final double WEIGHT = DescriptionEntity.RABBIT_WEIGHT;
    public static final int MAX_QUANTITY = DescriptionEntity.RABBIT_MAX_QUANTITY;
    public static final int STEEP = DescriptionEntity.RABBIT_STEEP;
    public static final double FULL_SATURATE = DescriptionEntity.RABBIT_FULL_SATURATE;

    public Rabbit(int x, int y) {
        super(x, y);
        this.currentWeight = WEIGHT;
    }
}
