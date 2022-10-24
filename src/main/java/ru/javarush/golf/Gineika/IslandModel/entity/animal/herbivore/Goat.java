package ru.javarush.golf.Gineika.IslandModel.entity.animal.herbivore;

import ru.javarush.golf.Gineika.IslandModel.entity.DescriptionEntity;

public class Goat extends Herbivore{
    public static final double WEIGHT = DescriptionEntity.GOAT_WEIGHT;
    public static final int MAX_QUANTITY = DescriptionEntity.GOAT_MAX_QUANTITY;
    public static final int STEEP = DescriptionEntity.GOAT_STEEP;
    public static final double FULL_SATURATE = DescriptionEntity.GOAT_FULL_SATURATE;

    public Goat(int x, int y) {
        super(x, y);
        this.currentWeight = WEIGHT;
    }
}
