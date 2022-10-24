package ru.javarush.golf.Gineika.IslandModel.entity.animal.predator;

import ru.javarush.golf.Gineika.IslandModel.entity.DescriptionEntity;

public class Eagle extends Predator{
    public static final double WEIGHT = DescriptionEntity.EAGLE_WEIGHT;
    public static final int MAX_QUANTITY = DescriptionEntity.EAGLE_MAX_QUANTITY;
    public static final int STEEP = DescriptionEntity.EAGLE_STEEP;
    public static final double FULL_SATURATE = DescriptionEntity.EAGLE_FULL_SATURATE;

    public Eagle(int x, int y) {
        super(x, y);
        this.currentWeight = WEIGHT;
    }
}
