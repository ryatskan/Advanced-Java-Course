package q1;

import java.awt.*;
// Abstract class representing Reptiles
abstract public class Reptiles extends Animal {
    void shedSkin() {
        System.out.println(this.getName() + " sheds skin");
    }
    public Reptiles(String givenName, double givenAge, Color givenColor) {
        super(givenName, givenAge, givenColor);
    }

}
