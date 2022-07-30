package q1;
import java.awt.*;

// A Human object, which is a mammals that can program in Java.
public class Human extends Mammals {
    // Parameters
    private boolean isCurrentlyProgramming;

    // Constructor.
    void sociallyInteract() {
        System.out.println(getName() + " is making new human friends!");
    }

    public Human(String givenName, double givenAge, Color givenColor, boolean isCurrentlyProgramming) {
        super(givenName, givenAge, givenColor);
        setCurrentlyProgramming(isCurrentlyProgramming);
    }

    // Override Super methods.
    @Override
    void eat() {
        System.out.println("Human: " + getName() + " is eating.");
    }

    // Override Object methods.
    @Override
    public String toString() {
        String animalType = "Animal type: " + "Human" + ";";
        String humanAge = " age: " + getAge() + ";";
        String humanColor = " color: " + "[r:" + getColor().getRed() + ",g:" + getColor().getGreen() + ",b:" + getColor().getBlue() + "];";
        String isCurrentlyProgramming = " is it Currently programming in Java? " + this.isCurrentlyProgramming() + ";";
        return getName() + " ID: \n" + animalType + humanAge + humanColor + isCurrentlyProgramming + "\n=======================";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Human human = (Human) o;
        return super.equals(human) && this.isCurrentlyProgramming() == human.isCurrentlyProgramming();
    }

    @Override
    protected Object clone() {
        return new Human(getName(), getAge(), getColor(),
                this.isCurrentlyProgramming());
    }

    public boolean isCurrentlyProgramming() {
        return this.isCurrentlyProgramming;
    }

    public void setCurrentlyProgramming(boolean currentlyProgramming) {
        this.isCurrentlyProgramming = currentlyProgramming;
    }
}

