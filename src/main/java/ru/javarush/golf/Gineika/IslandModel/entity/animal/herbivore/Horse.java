package ru.javarush.golf.Gineika.IslandModel.entity.animal.herbivore;

import ru.javarush.golf.Gineika.IslandModel.entity.DescriptionEntity;

public class Horse extends Herbivore{
    public static final double WEIGHT = DescriptionEntity.HORSE_WEIGHT;
    public static final int MAX_QUANTITY = DescriptionEntity.HORSE_MAX_QUANTITY;
    public static final int STEEP = DescriptionEntity.HORSE_STEEP;
    public static final double FULL_SATURATE = DescriptionEntity.HORSE_FULL_SATURATE;

    public Horse(int x, int y) {
        super(x, y);
        this.currentWeight = WEIGHT;
    }
}
