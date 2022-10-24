package ru.javarush.golf.Gineika.IslandModel.entity.animal.herbivore;

import ru.javarush.golf.Gineika.IslandModel.entity.DescriptionEntity;

public class Caterpillar extends Herbivore{
    public static final double WEIGHT = DescriptionEntity.CATERPILLAR_WEIGHT;
    public static final int MAX_QUANTITY = DescriptionEntity.CATERPILLAR_MAX_QUANTITY;
    public static final int STEEP = DescriptionEntity.CATERPILLAR_STEEP;
    public static final double FULL_SATURATE = DescriptionEntity.CATERPILLAR_FULL_SATURATE;

    public Caterpillar(int x, int y) {
        super(x, y);
        this.currentWeight = WEIGHT;
    }
}
