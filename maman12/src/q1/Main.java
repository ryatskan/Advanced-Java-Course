package q1;
import java.awt.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        System.out.println("q1b:");
        q1b();
        System.out.println("q1c:");
        q1c();
    }

    /*
     * Create an animal pension and prints result depending on its type.
     */
    public static void q1b() {
        ArrayList<Animal> animalPension = createPensionDatabase();
        iterateOverPensionDatabase(animalPension);

    }

    private static void iterateOverPensionDatabase(ArrayList<Animal> animalPension) {
        for (Animal x : animalPension) {
            System.out.println(x.toString());
            x.eat();
            x.sleep();
            if (x instanceof Reptiles) ((Reptiles) x).shedSkin();
            if (x instanceof Snake) ((Snake) x).hiss();
            if (x instanceof Cat) ((Cat) x).purr();
            if (x instanceof Birds) ((Birds) x).fly();
            if (x instanceof Human) ((Human) x).sociallyInteract();

            System.out.println();

        }

    }

    static ArrayList<Animal> createPensionDatabase() {
        ArrayList<Animal> pension = new ArrayList<>();
        pension.add(new Dog("Pupper", 2, Color.BLACK, false, "Dan", "052694040"));
        pension.add(new Owl("Duke The Owl", 22, Color.gray));
        pension.add(new Human("Roy", 20.8, Color.white, true));
        pension.add(new Snake("DangerNoodle", 2, Color.yellow, false));
        pension.add(new Cat("Michi", 2, Color.BLUE, true));
        return pension;

    }

    /*
     * Create a new Dog and clone it into a new dog. change the new dog properties and print both dogs,
     * to make sure that the original dog is left unchanged.
     */
    public static void q1c() {
        Dog dog = new Dog("Biscut First Dog", 5, Color.lightGray, false, "Harry", "123456789");
        Dog newDog = (Dog) dog.clone();
        // Change new dog prop
        newDog.setOwnerName("Angelika");
        newDog.setOwnerPhoneNum("987654321");
        // Print both dogs
        System.out.println(dog);
        System.out.println(newDog);

    }
}
