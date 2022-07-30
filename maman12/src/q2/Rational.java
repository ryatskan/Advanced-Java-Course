// A Rational Object that represents a rational number as a fraction of two integers
package q2;
public class Rational {
    // Parameters
    private int num;
    private int denum;

    // I assumed the parameters are integers
    public Rational(int givenNumerator, int givenDenominator) throws IllegalArgumentException {
        if (givenDenominator <= 0) throw new IllegalArgumentException("denominator is not a positive number");
        setNum(givenNumerator);
        setDenum(givenDenominator);
    }

    /*
     * Constructor that allows negative denominator, but makes the denum positive by negating both num and denum.
     */
    static public Rational standardizedRational(int givenNumerator, int givenDenominator) throws IllegalArgumentException {
        if (givenDenominator == 0) throw new IllegalArgumentException("denominator is zero");
        if (givenDenominator < 0) {
            givenDenominator = Math.abs(givenDenominator);
            givenNumerator = givenNumerator * -1;
        }
        return new Rational(givenNumerator, givenDenominator);
    }

    public boolean greaterThan(Rational rat) {
        return (rat.getNum() * this.getDenum() < rat.getDenum() * this.getNum()); // ad > bc
    }

    public boolean equals(Rational rat) {
        return (rat.getNum() * this.getDenum() == rat.getDenum() * this.getNum()); // ad = bc
    }

    /* Adds the Rational with a given Rational into a new one.
     outputs the resulting Rational.
    */
    public Rational plus(Rational rat) {

        int outputDenum = rat.getDenum() * this.getDenum();
        int outputNum = rat.getNum() * this.getDenum() + rat.getDenum() * this.getNum();
        return standardizedRational(outputNum, outputDenum);
    }

    /* Subtracting the Rational with a given Rational into a new one.
    outputs the resulting Rational.
    */
    public Rational minus(Rational rat) {

        int outputDenum = rat.getDenum() * this.getDenum();
        int outputNum = rat.getDenum() * this.getNum() - rat.getNum() * this.getDenum();
        return standardizedRational(outputNum, outputDenum);
    }

    /* Multiplies the Rational with a given Rational into a new one.
    outputs the resulting Rational.
    */
    public Rational multiply(Rational rat) {
        int outputDenum = rat.getDenum() * this.getDenum();
        int outputNum = rat.getNum() * this.getNum();
        return standardizedRational(outputNum, outputDenum);
    }

    public Rational divide(Rational rat) throws ArithmeticException {
        // if (rat.num == 0) throw new ArithmeticException;
        if (rat.num == 0) throw new ArithmeticException("Division by zero");
        Rational tempRat = standardizedRational(rat.getDenum(), rat.getNum());
        return this.multiply(tempRat);
    }

    // Getters
    public int getNumerator() {
        return this.num;
    }

    public int getDenominator() {
        return this.denum;
    }

    /*
     Was told to implement getNumerator and getDenominator, but I prefer using these 2
     For less bloat.
     */
    public int getNum() {
        return getNumerator();
    }

    public int getDenum() {
        return getDenominator();
    }
    // Setters

    public void setNum(int num) {
        this.num = num;
    }

    public void setDenum(int denum) {
        this.denum = denum;
    }

    @Override
    public String toString() {
        if (this.getNum() == this.getDenum() && this.getNum() == 1) return "1";
        if (this.getNum() == 0) return "0";
        if (this.getDenum() == 1) return Integer.toString(getNum());
        else return getNum() + "/" + getDenum();
    }

    /*
     * Returns a new Rational that is a minimized fraction of this Rational.
     */
    public Rational reduce() {
        int newDenum = this.getDenum();
        int newNum = this.getNum();
        int gcd = gcd(newNum, newDenum);
        newDenum = newDenum / gcd;
        newNum = newNum / gcd;
        return standardizedRational(newNum, newDenum);
    }

    public static int gcd(int p, int q) {
        if (q == 0) {
            return p;
        }
        return gcd(q, p % q);
    }
}