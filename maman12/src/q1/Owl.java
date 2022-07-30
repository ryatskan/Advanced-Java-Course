package q1;
import java.awt.*;

// An Owl object, which is a Bird.
public class Owl extends Birds {
    // Constructor.
    public Owl(String givenName, double givenAge, Color givenColor ) {
        super(givenName, givenAge, givenColor);
    }

    // Override Super methods.
    @Override
    void eat() {
        System.out.println("Owl: " + getName() + " is eating.");
    }
    // Override Object methods.
    @Override
    public String toString() {
        String animalType = "Animal type: " + "Owl" + ";";
        String owlAge = " age: " + getAge() + ";";
        String owlColor = " color: " + "[r:" + getColor().getRed() + ",g:" + getColor().getGreen() + ",b:" + getColor().getBlue() + "];";
        return getName() + " ID: \n" + animalType + owlAge + owlColor + "\n=======================";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Owl owl = (Owl) o;
        return super.equals(owl);
    }

    @Override
    protected Object clone() {
        return new Owl(getName(), getAge(), getColor());
    }
}

