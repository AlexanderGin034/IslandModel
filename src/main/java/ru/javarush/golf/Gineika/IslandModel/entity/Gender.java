package ru.javarush.golf.Gineika.IslandModel.entity;

import lombok.Getter;

@Getter
public enum Gender {
    MALE(1), FEMALE(2);

    private int genderNum;

    private Gender(int genderNum) {
        this.genderNum = genderNum;
    }
}
