package ru.javarush.golf.Gineika.IslandModel.entity.animal.predator;

import ru.javarush.golf.Gineika.IslandModel.entity.DescriptionEntity;

public class Wolf extends Predator{
    public static final double WEIGHT = DescriptionEntity.WOLF_WEIGHT;
    public static final int MAX_QUANTITY = DescriptionEntity.WOLF_MAX_QUANTITY;
    public static final int STEEP = DescriptionEntity.WOLF_STEEP;
    public static final double FULL_SATURATE = DescriptionEntity.WOLF_FULL_SATURATE;

    public Wolf(int x, int y) {
        super(x, y);
        this.currentWeight = WEIGHT;
    }
}
