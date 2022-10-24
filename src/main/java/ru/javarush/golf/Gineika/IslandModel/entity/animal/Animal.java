package ru.javarush.golf.Gineika.IslandModel.entity.animal;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.javarush.golf.Gineika.IslandModel.Direction;
import ru.javarush.golf.Gineika.IslandModel.StaticIslandModel;
import ru.javarush.golf.Gineika.IslandModel.Statistics;
import ru.javarush.golf.Gineika.IslandModel.entity.Gender;
import ru.javarush.golf.Gineika.IslandModel.entity.Property;
import ru.javarush.golf.Gineika.IslandModel.entity.Type;
import ru.javarush.golf.Gineika.IslandModel.entity.animal.herbivore.*;
import ru.javarush.golf.Gineika.IslandModel.entity.animal.predator.*;
import ru.javarush.golf.Gineika.IslandModel.island.Island;
import ru.javarush.golf.Gineika.IslandModel.island.Location;

import java.io.File;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@Setter
public abstract class Animal implements Type {
    private int currentX;
    private int currentY;
    private Gender gender;
    private boolean alive = true;
    public double currentWeight;

    public Animal(int x, int y) {
        this.currentX = x;
        this.currentY = y;
        this.gender = checkGender(ThreadLocalRandom.current().nextInt(Gender.MALE.getGenderNum(), Gender.FEMALE.getGenderNum()+1));
    }

    // replaces current coordinates
    public void setNewCoordinates(int x, int y) {
        this.currentX = x;
        this.currentY = y;
    }

    // получает координаты
    // проверяет их на выход за границы острова
    // удаляет объект из старой локации
    // добавляет объект в новую локацию
    public void move(Island island) {
        int steep = (int) getProperty(this, Property.STEP);
        int flag = 0;

        while (flag <= 4) {
            Map<String, Integer> newCoordinate = getNewCoordinate(this.currentX, this.currentY, steep);

            if (checkNewCoordinate(newCoordinate, island)) {
                flag += 1;
                continue;
            }

            Location fromLocation = island.getLocationCast(this.currentX, this.currentY);
            fromLocation.removeEntity(this);
            this.setNewCoordinates(newCoordinate.get("x"), newCoordinate.get("y"));
            Location toLocation = island.getLocationCast(this.currentX, this.currentY);
            lostPower(10); // теряет сил про перемещении
            toLocation.addEntity(this);
            fromLocation.removeEntity(this);
            break;
        }
    }

    public abstract void reproduce(Island island, Statistics statistics);

    // получает из json пищевую цепочку вызвавшего метод животного
    // получает совпадения по локации
    // удаляет из локации нужное животное
    public void eat(Island island, Statistics statistics) {

        // проверяет животное на истощение
        if (checkWeight()){
            Location locationCast = island.getLocationCast(currentX, currentY);
            locationCast.removeEntity(this);
            return;
        }

        // проверяет голодно ли животное
        // если нет, то выходит из метода
        if (currentWeight == (double) getProperty(this, Property.WEIGHT)){
            return;
        }

        Set<String> allAnimalsOnLocation = getAllAnimalsOnLocation(island);
        Map<String, Integer> foodChainList = new HashMap<>();
        Map<String, Integer> animalsForEat = new HashMap<>();

         final File foodChain = new File("/Users/aleksandrgineyka/Downloads/ru.javarush" +
                ".golf.Gineika.IslandModel/src/main/java/ru/javarush/golf/Gineika/IslandModel/util/foodChain.json");

        // получает мапу из JSON
        try {
            Map<String, Map<String, Integer>> hashMap = new ObjectMapper().readValue(foodChain, HashMap.class);
            Map<String, Integer> mapPreys = hashMap.get(getNameClass(this.getClass().getName()));
                for (Map.Entry<String, Integer> entry : mapPreys.entrySet()) {
                    if (allAnimalsOnLocation.contains(entry.getKey())) foodChainList.put(entry.getKey(), entry.getValue());
                }
            }catch (Exception e) {
                return;
        }

        if (animalsForEat.size() != 0) {
            int num = 0;
            for(Map.Entry<String, Integer> entry: foodChainList.entrySet()){
                for(String entity: allAnimalsOnLocation){
                    if(entry.getKey().equals(entity)){
                            animalsForEat.put(entry.getKey(), entry.getValue());
                    }
                }
            }
        }else {
            this.currentWeight -= (10 * this. currentWeight / 100);
            return;
        }

        for (Map.Entry<String, Integer> entry: getEat(animalsForEat).entrySet()){
            if(!checkProbability(entry.getValue())){
                this.currentWeight -= (10 * this. currentWeight / 100);
                return;
            }
            Location locationCast = island.getLocationCast(this.currentX, this.currentY);
            List<? extends Animal> animalList = StaticIslandModel.getAnimalList(getClassByString(entry.getKey()), locationCast);
            Animal animal = animalList.stream().filter(e -> e.getClass() == getClassByString(entry.getKey())).findAny().get();
            animalList.remove(animal);
            statistics.addDeath();

            addWeight(animal.currentWeight);
        }
    }

    // получить новые координаты
    private Map<String, Integer> getNewCoordinate(int x, int y, int steep) {
        int direction = ThreadLocalRandom.current().nextInt(Direction.RANGE_FROM, Direction.RANGE_TO);

        Map<String, Integer> coordinateMap = new HashMap<>() {{
            put("x", x);
            put("y", y);
        }};

         switch (direction) {
            case 1  -> coordinateMap.put("y", y+steep); // UP
            case 2  -> coordinateMap.put("y", y-steep); // DOWN
            case 3  -> coordinateMap.put("x", x-steep); // LEFT
            default -> coordinateMap.put("x", x+steep); // RIGHT
        };

         return coordinateMap;
    }

    // проверяем новые координаты на выход за границы острова
    private boolean checkNewCoordinate(Map<String, Integer> newCoordinateMap, Island island) {
        int newX = newCoordinateMap.get("x");
        int newY = newCoordinateMap.get("y");
        int endX = island.getBordersIsland().get("x");
        int endY = island.getBordersIsland().get("y");

        if (newX < 0 || newY < 0 || newX > endX || newY > endY || checkCountAnimalsOnLocation(newX, newY, island)) {
            return true;
        }
        return false;
    }

    // check gender (return male or female)
    private Gender checkGender(int genderNum){
        return switch (genderNum) {
            case 1 -> Gender.MALE;
            default -> Gender.FEMALE;
        };
    }

    // возвращает каст класса
    private <T extends Animal> T getClassAnimal(Class<T>  clazz, Animal anima) {
        T animalT = (T) anima;
        return animalT;
    }

    // проверяет количество определенного типа на локации
    // если количество превышает лимит, то возвращает false
    private boolean checkCountAnimalsOnLocation(int x, int y, Island island) {
        Location locationCast = island.getLocationCast(x, y);

        List<? extends Animal> animalList = StaticIslandModel.getAnimalList(this.getClass(), locationCast);
        int count = (int) animalList.stream().filter(e -> e.getClass() == this.getClass()).count();

        return count >= getMaxQuantity(this)? true: false;
    }

    // возвращает максимальное количество животных
    // одного типа на локации
    private int getMaxQuantity(Animal obj) {
        if (obj instanceof Boar){
            return Boar.MAX_QUANTITY;
        }else if (obj instanceof Buffalo){
            return Buffalo.MAX_QUANTITY;
        }else if (obj instanceof Caterpillar){
            return Caterpillar.MAX_QUANTITY;
        }else if (obj instanceof Deer){
            return Deer.MAX_QUANTITY;
        }else if (obj instanceof Duck){
            return Duck.MAX_QUANTITY;
        }else if (obj instanceof Goat){
            return Goat.MAX_QUANTITY;
        }else if (obj instanceof Horse){
            return Horse.MAX_QUANTITY;
        }else if (obj instanceof Mouse){
            return Mouse.MAX_QUANTITY;
        }else if (obj instanceof Rabbit){
            return Rabbit.MAX_QUANTITY;
        }else if (obj instanceof Sheep){
            return Sheep.MAX_QUANTITY;
        }else if (obj instanceof Bear){
            return Bear.MAX_QUANTITY;
        }else if (obj instanceof Boa){
            return Boa.MAX_QUANTITY;
        }else if (obj instanceof Eagle){
            return Eagle.MAX_QUANTITY;
        }else if (obj instanceof Fox){
            return Fox.MAX_QUANTITY;
        }else if (obj instanceof Wolf){
            return Wolf.MAX_QUANTITY;
        }else {
            return 0;
        }

    }

    private Number getProperty(Animal obj, Property property) {
        if (obj instanceof Boar){
            if (property == Property.MAX_QUANTITY){
                return Boar.MAX_QUANTITY;
            }else if (property == Property.STEP){
                return Boar.STEEP;
            }else if (property == Property.WEIGHT){
                return Boar.WEIGHT;
            }else {
                return Boar.FULL_SATURATE;
            }
        }else if (obj instanceof Buffalo){
            if (property == Property.MAX_QUANTITY){
                return Buffalo.MAX_QUANTITY;
            }else if (property == Property.STEP){
                return Buffalo.STEEP;
            }else if (property == Property.WEIGHT){
                return Buffalo.WEIGHT;
            }else {
                return Buffalo.FULL_SATURATE;
            }
        }else if (obj instanceof Caterpillar){
            if (property == Property.MAX_QUANTITY){
                return Caterpillar.MAX_QUANTITY;
            }else if (property == Property.STEP){
                return Caterpillar.STEEP;
            }else if (property == Property.WEIGHT){
                return Caterpillar.WEIGHT;
            }else {
                return Caterpillar.FULL_SATURATE;
            }
        }else if (obj instanceof Deer){
            if (property == Property.MAX_QUANTITY){
                return Deer.MAX_QUANTITY;
            }else if (property == Property.STEP){
                return Deer.STEEP;
            }else if (property == Property.WEIGHT){
                return Deer.WEIGHT;
            }else {
                return Deer.FULL_SATURATE;
            }
        }else if (obj instanceof Duck){
            if (property == Property.MAX_QUANTITY){
                return Duck.MAX_QUANTITY;
            }else if (property == Property.STEP){
                return Duck.STEEP;
            }else if (property == Property.WEIGHT){
                return Duck.WEIGHT;
            }else {
                return Duck.FULL_SATURATE;
            }
        }else if (obj instanceof Goat){
            if (property == Property.MAX_QUANTITY){
                return Goat.MAX_QUANTITY;
            }else if (property == Property.STEP){
                return Goat.STEEP;
            }else if (property == Property.WEIGHT){
                return Goat.WEIGHT;
            }else {
                return Goat.FULL_SATURATE;
            }
        }else if (obj instanceof Horse){
            if (property == Property.MAX_QUANTITY){
                return Horse.MAX_QUANTITY;
            }else if (property == Property.STEP){
                return Horse.STEEP;
            }else if (property == Property.WEIGHT){
                return Horse.WEIGHT;
            }else {
                return Horse.FULL_SATURATE;
            }
        }else if (obj instanceof Mouse){
            if (property == Property.MAX_QUANTITY){
                return Mouse.MAX_QUANTITY;
            }else if (property == Property.STEP){
                return Mouse.STEEP;
            }else if (property == Property.WEIGHT){
                return Mouse.WEIGHT;
            }else {
                return Mouse.FULL_SATURATE;
            }
        }else if (obj instanceof Rabbit){
            if (property == Property.MAX_QUANTITY){
                return Rabbit.MAX_QUANTITY;
            }else if (property == Property.STEP){
                return Rabbit.STEEP;
            }else if (property == Property.WEIGHT){
                return Rabbit.WEIGHT;
            }else {
                return Rabbit.FULL_SATURATE;
            }
        }else if (obj instanceof Sheep){
            if (property == Property.MAX_QUANTITY){
                return Sheep.MAX_QUANTITY;
            }else if (property == Property.STEP){
                return Sheep.STEEP;
            }else if (property == Property.WEIGHT){
                return Sheep.WEIGHT;
            }else {
                return Sheep.FULL_SATURATE;
            }
        }else if (obj instanceof Bear){
            if (property == Property.MAX_QUANTITY){
                return Bear.MAX_QUANTITY;
            }else if (property == Property.STEP){
                return Bear.STEEP;
            }else if (property == Property.WEIGHT){
                return Bear.WEIGHT;
            }else {
                return Bear.FULL_SATURATE;
            }
        }else if (obj instanceof Boa){
            if (property == Property.MAX_QUANTITY){
                return Boa.MAX_QUANTITY;
            }else if (property == Property.STEP){
                return Boa.STEEP;
            }else if (property == Property.WEIGHT){
                return Boa.WEIGHT;
            }else {
                return Boa.FULL_SATURATE;
            }
        }else if (obj instanceof Eagle){
            if (property == Property.MAX_QUANTITY){
                return Eagle.MAX_QUANTITY;
            }else if (property == Property.STEP){
                return Eagle.STEEP;
            }else if (property == Property.WEIGHT){
                return Eagle.WEIGHT;
            }else {
                return Eagle.FULL_SATURATE;
            }
        }else if (obj instanceof Fox){
            if (property == Property.MAX_QUANTITY){
                return Fox.MAX_QUANTITY;
            }else if (property == Property.STEP){
                return Fox.STEEP;
            }else if (property == Property.WEIGHT){
                return Fox.WEIGHT;
            }else {
                return Fox.FULL_SATURATE;
            }
        }else if (obj instanceof Wolf){
            if (property == Property.MAX_QUANTITY){
                return Wolf.MAX_QUANTITY;
            }else if (property == Property.STEP){
                return Wolf.STEEP;
            }else if (property == Property.WEIGHT){
                return Wolf.WEIGHT;
            }else {
                return Wolf.FULL_SATURATE;
            }
        }else {
            return 0;
        }
    }

    // возвращает новый объект заданного типа
    public  <T extends Animal> T newObject (Class<T> clazz, Animal obj, int x, int y) throws NullPointerException{
        if ( obj instanceof Boar){
            return (T) new Boar(x, y);
        }else if (obj instanceof Buffalo){
            return (T) new Buffalo(x, y);
        }else if (obj instanceof Caterpillar){
            return (T) new Caterpillar(x, y);
        }else if (obj instanceof Deer){
            return (T) new Deer(x, y);
        }else if (obj instanceof Duck){
            return (T) new Duck(x, y);
        }else if (obj instanceof Goat){
            return (T) new Goat(x, y);
        }else if (obj instanceof Horse){
            return (T) new Horse(x, y);
        }else if (obj instanceof Mouse){
            return (T) new Mouse(x, y);
        }else if (obj instanceof Rabbit){
            return (T) new Rabbit(x, y);
        }else if (obj instanceof Sheep){
            return (T) new Sheep(x, y);
        }else if (obj instanceof Bear){
            return (T) new Bear(x, y);
        }else if (obj instanceof Boa){
            return (T) new Boa(x, y);
        }else if (obj instanceof Eagle){
            return (T) new Boa(x, y);
        }else if (obj instanceof Fox){
            return (T) new Boa(x, y);
        }else if (obj instanceof Wolf){
            return (T) new Boa(x, y);
        }else {
            throw new NullPointerException();
        }
    }

    // возвращает множество животных на нужной локации
    private Set<String> getAllAnimalsOnLocation(Island island){
        Location locationCast = island.getLocationCast(this.currentX, this.getCurrentY());
        List<Predator> predators = locationCast.getPredators();
        List<Herbivore> herbivores = locationCast.getHerbivores();

        return Stream.of(predators, herbivores)
                .flatMap(e -> e.stream())
                .map(e -> e.getClass().getName())
                .collect(Collectors.toSet());
    }

    // возвращает добычу
    private Map<String, Integer> getEat(Map<String, Integer> eatMap) {
        List<Integer> probability = new ArrayList<>();
        Map<String, Integer> food = new HashMap<>();

        for (Map.Entry<String, Integer> entry: eatMap.entrySet()) {
            probability.add(entry.getValue());
        }

        Integer maxInt = probability.stream().max((e1, e2) -> e1 - e2).get();

        for (Map.Entry<String, Integer> entry: eatMap.entrySet()) {
            if(entry.getValue() == maxInt) {
                food.put(entry.getKey(), entry.getValue());
                break;
            }
        }
        return food;
    }

    // проверяет вероятность на съедение
    private boolean checkProbability(int percent) {
        int localThreadRandom = ThreadLocalRandom.current().nextInt(Direction.RANGE_FROM, Direction.RANGE_TO);
         return localThreadRandom <= percent? true: false;
    }

    // возвращает имя класса в String
    private String getNameClass(String fullName) {
        String[] split = fullName.split("\\.");
        return split[split.length-1];
    }

    // возвращает тип Class типа Animal
    private Class<? extends Animal> getClassByString(String name){
        return switch (name) {
            case "Boar" -> Boar.class;
            case "Buffalo" -> Buffalo.class;
            case "Caterpillar" -> Caterpillar.class;
            case "Deer" -> Deer.class;
            case "Duck" -> Duck.class;
            case "Goat" -> Goat.class;
            case "Horse" -> Horse.class;
            case "Mouse" -> Mouse.class;
            case "Rabbit" -> Rabbit.class;
            case "Sheep" -> Sheep.class;
            case "Bear" -> Bear.class;
            case "Boa" -> Boa.class;
            case "Fox" -> Fox.class;
            default ->  Wolf.class;
        };
    }

    public boolean checkWeight(){
        double weightNormal = (double) getProperty(this, Property.WEIGHT);
        double deadLine = 40 * weightNormal / 100;
        return this.getCurrentWeight() < deadLine? true: false;
    }

    private void addWeight(double weight){
        double property = (double) getProperty(this, Property.WEIGHT);
        if (currentWeight + 30 * weight/100 > property){
            currentWeight = property;
        }else{
            currentWeight += 30 * weight/100;
        }
    }

    public void lostPower(int percent){
        this.currentWeight -= (percent * this. currentWeight / 100);
    }
}
