package ru.javarush.golf.Gineika.IslandModel.entity.animal.herbivore;

import ru.javarush.golf.Gineika.IslandModel.entity.DescriptionEntity;
import ru.javarush.golf.Gineika.IslandModel.entity.animal.Hunterable;

public class Duck extends Herbivore implements Hunterable {
    public static final double WEIGHT = DescriptionEntity.DUCK_WEIGHT;
    public static final int MAX_QUANTITY = DescriptionEntity.DUCK_MAX_QUANTITY;
    public static final int STEEP = DescriptionEntity.DUCK_STEEP;
    public static final double FULL_SATURATE = DescriptionEntity.DUCK_FULL_SATURATE;

    public Duck(int x, int y) {
        super(x, y);
        this.currentWeight = WEIGHT;
    }
}
