package ru.javarush.golf.Gineika.IslandModel;

public enum Direction {
    UP(1),
    DOWN(2),
    LEFT(3),
    RIGHT(4);

    private int numDirection;
    public static final int RANGE_FROM = 1;
    public static final int RANGE_TO = 5; // реальное значение 4

    private Direction(int numDirection){
        this.numDirection = numDirection;
    }
}
