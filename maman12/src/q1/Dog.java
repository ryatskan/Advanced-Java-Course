package q1;
import java.awt.*;

// A Dog object, which is a mammals that has an owner and can bite.
public class Dog extends Mammals {
    // Parameters
    private boolean isBiting;
    private Owner owner;

    // Constructor.
    public Dog(String givenName, double givenAge, Color givenColor, boolean givenIsBiting, String ownerName, String ownerNumber) {
        super(givenName, givenAge, givenColor);
        setBiting(givenIsBiting);
        setOwner(ownerName, ownerNumber);
    }
    // Override Super methods.
    @Override
    void eat() {
        System.out.println("Dog: " + this.getName() + " is eating.");
    }
    // Override Object methods.
    @Override
    public String toString() {
        String animalType = "Animal type: " + "Dog" + ";";
        String dogAge = " age: " + this.getAge() + ";";
        String dogColor = " color: " + "[r:" + this.getColor().getRed() + ",g:" + this.getColor().getGreen() + ",b:" + this.getColor().getBlue() + "];";
        String doesBite = " does it bite? " + isBiting() + ";";
        return this.getName() + " ID: \n" + animalType + dogAge + dogColor + doesBite + "\n" + this.getOwnerString() + "\n=======================";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dog dog = (Dog) o;
        return super.equals(dog) && this.isBiting() == dog.isBiting() && this.getOwnerName().equals(dog.getOwnerName()) &&
                this.getOwnerPhoneNum().equals(dog.getOwnerPhoneNum());
    }

    @Override
    protected Object clone() {
        return new Dog(this.getName(), this.getAge(), this.getColor(),
                this.isBiting(), this.getOwnerName(), getOwnerPhoneNum());
    }

    // Getters
    public boolean isBiting() {
        return isBiting;
    }

    public String getOwnerName() {
        return owner.getOwnerName();
    }

    public String getOwnerPhoneNum() {
        return owner.getOwnerPhoneNum();
    }

    // Setters
    public void setBiting(boolean biting) {
        isBiting = biting;
    }

    public void setOwnerName(String newOwnerName) {
        owner.setOwnerName(newOwnerName);
    }

    public void setOwnerPhoneNum(String newOwnerPhoneNum) {
        owner.setOwnerPhoneNum(newOwnerPhoneNum);
    }

    public void setOwner(String newOwnerName, String newOwnerPhoneNum) {
        this.owner = new Owner(newOwnerName, newOwnerPhoneNum);
    }
    public String getOwnerString() {
        return this.owner.toString();
    }
}

