import java.util.Scanner;
package q2;
public class Main {
    // Receive two (legal) Rational objects from user. Do computations and comparisons on them.
    public static void main(String[] args) {
        Rational[] x;
        x = receiveNumbers();
        printComputation(x);
        printComparisons(x);
    }
    /*
     Asks for 4 integers, where the second and fourth one are bigger than 0.
     Loops until it receives desired input.
     */
    static Rational[] receiveNumbers() {
        Rational[] newArr = new Rational[2];
        int firstNum1, firstNum2, secondNum1, secondNum2;
        boolean succeeded = false;
        while(!succeeded) {
            System.out.println("Please insert 4 Numbers");

            Scanner stream = new Scanner(System.in);
            try {
                firstNum1 = stream.nextInt();
                firstNum2 = stream.nextInt();
                secondNum1 = stream.nextInt();
                secondNum2 = stream.nextInt();
                newArr[0] = new Rational(firstNum1, firstNum2);
                newArr[1] = new Rational(secondNum1, secondNum2);
                succeeded = true;
            } catch (Exception e) {
                System.out.println(e);
                succeeded= false;
            }
        }
        return newArr;
    }

    static void printComputation(Rational[] arr) {
        Rational rat1 = arr[0], rat2 = arr[1];

        Rational plusRes = rat1.plus(rat2);
        Rational minusRes = rat1.minus(rat2);
        Rational multiplyRes = rat1.multiply(rat2);
        printResult(rat1,rat2, plusRes, '+');
        printResult(rat1,rat2, minusRes, '-');
        printResult(rat1,rat2, multiplyRes, '*');
        // Handles division, which can throw exception
        try{
            Rational divideRes = rat1.divide(rat2);
            printResult(rat1,rat2, divideRes, '÷');
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }
    static void printComparisons(Rational[] arr) {
        Rational rat1 = arr[0], rat2 = arr[1];

        boolean areEqual = rat1.equals(rat2);
        boolean isGreaterThan = rat1.greaterThan(rat2);
        System.out.println(rat1 + "=" + rat2 + " is " + areEqual);
        System.out.println(rat1 + ">" + rat2 + " is " + isGreaterThan);

    }

    static void printResult(Rational rat1, Rational rat2, Rational rat3, Character op) {
        System.out.println(rat1.toString() + op + rat2.toString() + " = " + rat3.reduce().toString());
    }
}

