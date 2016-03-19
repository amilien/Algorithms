package patterns;

import java.util.ArrayList;
import java.util.List;

/**
 * Разделяет абстракцию и реализацию так, чтобы они могли изменяться независимо.
 * @author Mike
 */
public class Bridge {

    interface IAnimal {
        void say();
    }

    class Cat implements IAnimal {
        public void say() {
            System.out.println("Miaou! I'm a happy cat!");
        }
    }

    class Pig implements IAnimal {
        public void say() {
            System.out.println("Groin-groin! I'm a happy pig!");
        }
    }

    abstract class Animal {

        IAnimal animal;

        public Animal(IAnimal animal) {
            this.animal = animal;
        }

        public void say() {
            animal.say();
        }
    }

    class HappyAnimal extends Animal {

        public HappyAnimal(IAnimal animal) {
            super(animal);
        }
    }

    public static void main(String[] args) {
        Bridge bridge = new Bridge();
        List<Animal> happyAnimals = new ArrayList<Animal>();
        happyAnimals.add(bridge.new HappyAnimal(bridge.new Cat()));
        happyAnimals.add(bridge.new HappyAnimal(bridge.new Pig()));
        for (Animal animal: happyAnimals) {
            animal.say();
        }
    }
}
