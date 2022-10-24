package ru.javarush.golf.Gineika.IslandModel.entity.animal.herbivore;

import ru.javarush.golf.Gineika.IslandModel.entity.DescriptionEntity;

public class Deer extends Herbivore{
    public static final double WEIGHT = DescriptionEntity.DEER_WEIGHT;
    public static final int MAX_QUANTITY = DescriptionEntity.DEER_MAX_QUANTITY;
    public static final int STEEP = DescriptionEntity.DEER_STEEP;
    public static final double FULL_SATURATE = DescriptionEntity.DEER_FULL_SATURATE;

    public Deer(int x, int y) {
        super(x, y);
        this.currentWeight = WEIGHT;
    }
}
