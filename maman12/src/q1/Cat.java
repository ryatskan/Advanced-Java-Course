package q1;
import java.awt.*;

// A Cat object, which is a mammal and can purr and have fur.
public class Cat extends Mammals {
    // Parameters
    private boolean hasFur;
    // Constructor
    public Cat(String givenName, double givenAge, Color givenColor, boolean hasFur) {
        super(givenName, givenAge, givenColor);
        setHasFur(hasFur);
    }
    void purr() {
        System.out.println("purrr");
    }
    // Override Super methods.
    @Override
    void eat() {
        System.out.println("Cat: " + getName() + " is eating.");
    }
    // Override Object methods.
    @Override
    public String toString() {
        String animalType = "Animal type: " + "Cat" + ";";
        String catAge = " age: " + getAge() + ";";
        String catColor = " color: " + "[r:" + getColor().getRed() + ",g:" + getColor().getGreen() + ",b:" + getColor().getBlue() + "];";
        String hasFurr = " has Furr?: " + this.hasFurr();

        return getName() + " ID: \n" + animalType + catAge + catColor + hasFurr + "\n=======================";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cat cat = (Cat) o;
        return super.equals(cat);
    }

    @Override
    protected Object clone() {
        return new Cat(getName(), getAge(), getColor(), this.hasFurr());
    }

    public boolean hasFurr() {
        return hasFur;
    }

    public void setHasFur(boolean hasFur) {
        this.hasFur = hasFur;
    }
}

