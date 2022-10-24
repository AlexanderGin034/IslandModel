package ru.javarush.golf.Gineika.IslandModel.entity.animal.herbivore;

import ru.javarush.golf.Gineika.IslandModel.entity.DescriptionEntity;

public class Sheep extends Herbivore{
    public static final double WEIGHT = DescriptionEntity.SHEEP_WEIGHT;
    public static final int MAX_QUANTITY = DescriptionEntity.SHEEP_MAX_QUANTITY;
    public static final int STEEP = DescriptionEntity.SHEEP_STEEP;
    public static final double FULL_SATURATE = DescriptionEntity.SHEEP_FULL_SATURATE;

    public Sheep(int x, int y) {
        super(x, y);
        this.currentWeight = WEIGHT;
    }
}
