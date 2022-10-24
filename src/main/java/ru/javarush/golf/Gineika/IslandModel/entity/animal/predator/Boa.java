package ru.javarush.golf.Gineika.IslandModel.entity.animal.predator;

import ru.javarush.golf.Gineika.IslandModel.entity.DescriptionEntity;

public class Boa extends Predator{
    public static final double WEIGHT = DescriptionEntity.BOAR_WEIGHT;
    public static final int MAX_QUANTITY = DescriptionEntity.BOA_MAX_QUANTITY;
    public static final int STEEP = DescriptionEntity.BOA_STEEP;
    public static final double FULL_SATURATE = DescriptionEntity.BOA_FULL_SATURATE;

    public Boa(int x, int y) {
        super(x, y);
        this.currentWeight = WEIGHT;
    }
}
