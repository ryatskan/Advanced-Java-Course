package q1;

import java.util.Iterator;
import java.util.Objects;

/*
 * code for q1.q1c
 */
public class q1c {
    /*
     * Receives a Set whose elements are Comparable,
     * return the minimal item in the Set.
     */
    public static class findMinimum {
        public static <T extends Comparable<T>> T findMinimal(Set<T> givenSet) {
            T temp;
            T min = givenSet.iterator().next();
            Iterator<T> itr = givenSet.iterator();
            while (itr.hasNext()) {
                temp = itr.next();
                if (min.compareTo(temp) > 0) {
                    min = temp;
                }
            }
            return min;
        }
    }

    public static void main(String[] args) {
        Person[] test3 = new Person[5];
        test3[0] = new Person(6, "Ido", "Yatskan", 1996);
        test3[1] = new Person(4, "Liat", "Yatskan", 1993);
        test3[2] = new Person(55, "Roy", "Yatskan", 2001);
        test3[3] = new Person(5, "Nir", "Yatskan", 2001);
        test3[4] = new Person(4, "Eyal", "Yatskan", 2007);
        Set<Person> set3 = new Set<>(test3);
        set3.setSetName("set3");
        System.out.println(findMinimum.findMinimal(set3));
    }

    /*
     * Person represent a real life person - with id, full name and birthday.
     */
    public static class Person implements Comparable<Person> {
        // A Person's constants
        final private int id;
        final private String lastName;
        final private String firstName;
        final private int birthYear;
        /*
         * Constructor.
         */
        public Person(int id, String givenFirstName, String givenLastName, int givenBirthYear) {
            this.id = id;
            lastName = givenLastName;
            firstName = givenFirstName;
            birthYear = givenBirthYear;
        }

        @Override
        public int compareTo(Person o) {
            return Integer.toString(this.id).compareTo(Integer.toString(o.id));
        }

        @Override
        public int hashCode() {
            return 7 + Objects.hash(id);
        }

        @Override
        public boolean equals(Object o) {
            // self check
            if (this == o)
                return true;
            // null check
            if (o == null)
                return false;
            // type check and cast
            if (getClass() != o.getClass())
                return false;
            return (this.id == ((Person) o).id);
        }

        public String toString() {
            return "" + this.id + ":" + this.firstName + " " + this.lastName + " " + this.birthYear + "\n";
        }
    }
}


