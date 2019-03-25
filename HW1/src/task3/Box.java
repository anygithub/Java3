package task3;

import java.util.ArrayList;

public class Box<T extends Fruit> {
    private ArrayList<T> fruits;
    private float weight;

    public Box() {
        fruits = new ArrayList<>();
    }

    public void add(T fruit) {
        fruits.add(fruit);
        this.setWeight(this.weight+fruit.weight);
    }

    public void addFruits(ArrayList<T> fruits) {
        this.fruits.addAll(fruits);
    }

    public ArrayList<T> getFruits() {
        return fruits;
    }

    public float getWeight() {
        /*float totalWeight = 0.0f;
        for (T fruit : fruits)
            totalWeight += fruit.getWeight();
        return (float)Math.ceil(totalWeight * 100)/100;*/
        return this.weight;

    }

    public void setWeight(float weight) {
        this.weight = weight;
    }


    public boolean compareByWeight(Box<?> box) {
        return Float.compare(getWeight(), box.getWeight()) == 0;
    }

    public boolean compareByType(Box<?> b2) {
        if(this.getClass() == b2.getClass()) return true;
        return false;
    }

    public void moveAllFruitsToOtherBox(Box<T> box) {
        box.addFruits(getFruits());
        fruits.clear();

    }
}
