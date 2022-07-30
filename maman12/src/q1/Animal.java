package q1;
import java.awt.*;
// Abstract class representing Animals
abstract public class Animal {
    // Parameters
    private String name;
    private double age;
    private Color color;

    // Abstract Methods
    abstract void eat();

    void sleep() {
        System.out.println(name + " is sleeping.");
    }

    ;

    // Constructor
    public Animal(String name, double age, Color color) {
        setAge(age);
        setColor(color);
        setName(name);
    }

    public String getName() {
        return name;
    }

    public double getAge() {
        return age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return this.getName().equals(animal.getName()) && this.getAge() == animal.getAge()
                && this.getColor().equals(animal.getColor());
    }

    public Color getColor() {
        return color;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(double age) {
        this.age = age;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
