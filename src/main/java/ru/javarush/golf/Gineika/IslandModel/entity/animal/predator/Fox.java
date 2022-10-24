package ru.javarush.golf.Gineika.IslandModel.entity.animal.predator;

import ru.javarush.golf.Gineika.IslandModel.entity.DescriptionEntity;
import ru.javarush.golf.Gineika.IslandModel.entity.animal.Hunterable;

public class Fox extends Predator implements Hunterable {
    public static final double WEIGHT = DescriptionEntity.FOX_WEIGHT;
    public static final int MAX_QUANTITY = DescriptionEntity.FOX_MAX_QUANTITY;
    public static final int STEEP = DescriptionEntity.FOX_STEEP;
    public static final double FULL_SATURATE = DescriptionEntity.FOX_FULL_SATURATE;

    public Fox(int x, int y) {
        super(x, y);
        this.currentWeight = WEIGHT;
    }
}
