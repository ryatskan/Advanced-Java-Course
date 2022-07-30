package q1;

import java.awt.*;

// A Snake object, which is a Reptile that can be poisonous and can hiss.
public class Snake extends Reptiles {
    // Parameters
    private boolean isPoisonous;
    // Constructor
    public Snake(String givenName, double givenAge, Color givenColor, boolean givenIsPoisonous) {
        super(givenName, givenAge, givenColor);
        setPoisonous(givenIsPoisonous);
    }

    void hiss() {
        System.out.println(this.getName() + " hissses at you!");
    }
    // Override Super methods.
    @Override
    void eat() {
        System.out.println("Snake: " + getName() + " is eating.");
    }
    // Override Object methods.
    @Override
    public String toString() {
        String animalType = "Animal type: " + "Snake" + ";";
        String snakeAge = " age: " + getAge() + ";";
        String snakeColor = " color: " + "[r:" + getColor().getRed() + ",g:" + getColor().getGreen() + ",b:" + getColor().getBlue() + "];";
        String isPoisonous = " is poisonous?: " + this.isPoisonous();

        return getName() + " ID: \n" + animalType + snakeAge + snakeColor + isPoisonous + "\n=======================";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Snake snake = (Snake) o;
        return super.equals(snake);
    }

    @Override
    protected Object clone() {
        return new Snake(getName(), getAge(), getColor(), this.isPoisonous());
    }

    public boolean isPoisonous() {
        return this.isPoisonous;
    }

    public void setPoisonous(boolean poisonous) {
        this.isPoisonous = poisonous;
    }
}

