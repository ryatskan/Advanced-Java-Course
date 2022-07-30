package q1;

import java.util.ArrayList;
import java.util.Iterator;

/*
 * A q1.Set object represent a mathematical set - a collection that has no duplicate elements.
 */
class Set<T> {
    private final ArrayList<T> list = new ArrayList<>();
    private String setName = "";

    /*
     * Constructor.
     */
    public Set(T[] elements) {
        for (T element : elements) {
            insert(element);
        }
    }

    /*
     * Empty Constructor - redundant but added for the question demands.
     */
    public Set() {

    }

    /*
     * Receives a set, and insert all of its elements into this set.
     * (union operation)
     */
    public void union(Set<T> givenSet) {
        Iterator<T> it = givenSet.iterator();
        T curr;
        while (it.hasNext()) {
            curr = it.next();
            if (!isMember(curr)) insert(curr);
        }
    }

    /*
     Receives a set, and eliminate all elements in this set that are not
     both in this and the given Set. (intersect operation)
     */
    public void intersect(Set<T> givenSet) {
        Iterator<T> it = givenSet.iterator();
        Iterator<T> thisIt = iterator();
        T curr;
        while (it.hasNext()) {
            curr = it.next();
            if (!isMember(curr)) delete(curr);
        }
        while (thisIt.hasNext()) {
            curr = thisIt.next();
            if (!givenSet.isMember(curr)) thisIt.remove();
        }
    }

    /*
     * Receives a set and check if it is a subset of this set.
     */
    public boolean isSubset(Set<T> givenSet) {
        for (T element : this.list) {
            if (!givenSet.isMember(element)) {
                return false;
            }
        }
        return true;
    }

    /*
     * Receives an element and check if the given element is inside this set.
     */
    public boolean isMember(T element) {
        return list.contains(element);
    }

    /*
     * Receives an element. If the given element is inside the set, remove it.
     * If not inside, do nothing.
     */
    public void delete(T element) {
        list.remove(element);
    }

    /*
     * Insert a new element to the set.
     * If already inside the set, do nothing.
     */
    public void insert(T element) {
        if (!list.contains(element)) {
            list.add(element);
        }
    }

    // Getter
    public Iterator<T> iterator() {
        return list.iterator();
    }

    // Setter
    public void setSetName(String name) {
        setName = name;
    }

    /*
     * Overrides the toString method.
     * Return a String that contains the (possible) set name
     * and all the elements in the set.
     *
     */
    @Override
    public String toString() {
        String output;
        if (!setName.equals("")) {
            output = setName + ": ";
        } else {
            output = "";
        }
        for (int i = 0; i < list.size(); i++) {
            output = output + list.get(i).toString() + " ";
        }
        return output;
    }
}

