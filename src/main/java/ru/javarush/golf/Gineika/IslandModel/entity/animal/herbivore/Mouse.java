package ru.javarush.golf.Gineika.IslandModel.entity.animal.herbivore;

import ru.javarush.golf.Gineika.IslandModel.entity.DescriptionEntity;

public class Mouse extends Herbivore{
    public static final double WEIGHT = DescriptionEntity.MOUSE_WEIGHT;
    public static final int MAX_QUANTITY = DescriptionEntity.MOUSE_MAX_QUANTITY;
    public static final int STEEP = DescriptionEntity.MOUSE_STEEP;
    public static final double FULL_SATURATE = DescriptionEntity.MOUSE_FULL_SATURATE;

    public Mouse(int x, int y) {
        super(x, y);
        this.currentWeight = WEIGHT;
    }
}
