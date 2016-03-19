package patterns;

import java.util.ArrayList;
import java.util.List;

/**
 * ¬²¬Ñ¬Ù¬Õ¬Ö¬Ý¬ñ¬Ö¬ä ¬Ñ¬Ò¬ã¬ä¬â¬Ñ¬Ü¬è¬Ú¬ð ¬Ú ¬â¬Ö¬Ñ¬Ý¬Ú¬Ù¬Ñ¬è¬Ú¬ð ¬ä¬Ñ¬Ü, ¬é¬ä¬à¬Ò¬í ¬à¬ß¬Ú ¬Þ¬à¬Ô¬Ý¬Ú ¬Ú¬Ù¬Þ¬Ö¬ß¬ñ¬ä¬î¬ã¬ñ ¬ß¬Ö¬Ù¬Ñ¬Ó¬Ú¬ã¬Ú¬Þ¬à.
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
