package ru.javarush.golf.Gineika.IslandModel.entity.animal.herbivore;

import lombok.Getter;
import lombok.Setter;
import ru.javarush.golf.Gineika.IslandModel.entity.DescriptionEntity;

@Getter
@Setter
public class Boar extends Herbivore{
    public static final double WEIGHT = DescriptionEntity.BOAR_WEIGHT;
    public static final int MAX_QUANTITY = DescriptionEntity.BEAR_MAX_QUANTITY;
    public static final int STEEP = DescriptionEntity.BEAR_STEEP;
    public static final double FULL_SATURATE = DescriptionEntity.BEAR_FULL_SATURATE;

    public Boar(int x, int y) {
        super(x, y);
        this.currentWeight = WEIGHT;
    }
}
