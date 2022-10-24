package ru.javarush.golf.Gineika.IslandModel.entity.animal.herbivore;

import ru.javarush.golf.Gineika.IslandModel.entity.DescriptionEntity;

public class Buffalo extends Herbivore{
    public static final double WEIGHT = DescriptionEntity.BUFFALO_WEIGHT;
    public static final int MAX_QUANTITY = DescriptionEntity.BUFFALO_MAX_QUANTITY;
    public static final int STEEP = DescriptionEntity.BUFFALO_STEEP;
    public static final double FULL_SATURATE = DescriptionEntity.BUFFALO_FULL_SATURATE;

    public Buffalo(int x, int y) {
        super(x, y);
        this.currentWeight = WEIGHT;
    }
}
