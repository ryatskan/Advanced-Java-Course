package q1;
import java.awt.*;

// Abstract class representing Birds
abstract public class Birds extends Animal {

    public Birds(String name, double age, Color color) {
        super(name, age, color);
    }

    void fly() {
        System.out.println(this.getName() + " is flying ");
    }
}
