/** Class that prints the Collatz sequence starting from a given number.
 *  @author 24039100046
 */
public class Collatz {

    /**
     * return the next number of int n in collatz array
     */
    public static int nextNumber(int n) {
        if (n % 2 == 0) {
            return n / 2;
        } else {
            return n * 3 + 1;
        }
    }

    public static void main(String[] args) {
        int n = 5;
        System.out.print(n + " ");
        while (n != 1) {
            n = nextNumber(n);
            System.out.print(n + " ");
        }
        System.out.println();
    }
}

