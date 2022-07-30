package q1;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/*
 * code for question q1.q1b
 */
public class q1b {
    public static void main(String[] args) {
        int i, givenInt;
        ArrayList<Set<Integer>> setList = new ArrayList<>();
        setList.add(new Set<>(getRanArray(10)));
        setList.get(0).setSetName("set 1");
        setList.add(new Set<>(getRanArray(10)));
        setList.get(1).setSetName("set 2");
        setList.add(new Set<>(getRanArray(10)));
        setList.get(2).setSetName("set 3");
        System.out.println(setList.get(0));
        System.out.println(setList.get(1));
        System.out.println(setList.get(2));

        setList.get(0).union(setList.get(1));
        System.out.println(setList.get(0));

        setList.get(0).intersect(setList.get(2));
        System.out.println(setList.get(0));

        Integer[] intArr = new Integer[2];
        Scanner sc = new Scanner(System.in);
        intArr[0] = sc.nextInt();
        intArr[1] = sc.nextInt();
        Set<Integer> set4 = new Set<>(intArr);
        for (i = 0; i < 3; i++) {
            if (set4.isSubset(setList.get(i))) {
                System.out.println("The given two numbers are a subset of set " + (i + 1) + "!");
            } else {
                System.out.println("The given two numbers are NOT a subset of set " + (i + 1) + "!");
            }
        }

        givenInt = sc.nextInt();
        if (setList.get(0).isMember(givenInt)) {
            System.out.println(givenInt + " is inside set " + 1);
        } else {
            System.out.println(givenInt + " is NOT inside set " + 1);
        }
        setList.get(1).insert(givenInt);
        System.out.println(setList.get(1).toString());
        setList.get(2).delete(givenInt);
        System.out.println(setList.get(2).toString());


    }

    /*
     * Returns an Integer array of given size containing only random elements
     * between 0 to 100 (inclusive).
     */
    public static Integer[] getRanArray(int size) {
        int i;
        Random ran = new Random();
        Integer[] ranArr = new Integer[size];
        for (i = 0; i < size; i++) {
            ranArr[i] = randInt(0, 100);
        }
        return ranArr;
    }

    private static int randInt(int min, int max) {

        return new Random().nextInt((max - min) + 1) + min;
    }
}
