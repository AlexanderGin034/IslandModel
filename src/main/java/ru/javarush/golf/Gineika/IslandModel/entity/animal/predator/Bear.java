package ru.javarush.golf.Gineika.IslandModel.entity.animal.predator;

import ru.javarush.golf.Gineika.IslandModel.entity.DescriptionEntity;

public class Bear extends Predator{
    public static final double WEIGHT = DescriptionEntity.BEAR_WEIGHT;
    public static final int MAX_QUANTITY = DescriptionEntity.BEAR_MAX_QUANTITY;
    public static final int STEEP = DescriptionEntity.BEAR_STEEP;
    public static final double FULL_SATURATE = DescriptionEntity.BEAR_FULL_SATURATE;

    public Bear(int x, int y) {
        super(x, y);
        this.currentWeight = WEIGHT;
    }
}
