package dsisejkovic.algortihms;

/**
 * Created by dsisejkovic on 11.7.2015..
 */
public class FibonacciSeries {


    // nested excessive recursion
    // O(2^n) -> in each step, f() is called two times, thus 2^n
    public static int fibRecursive(int n) {
        if (n < 2) {
            return n;
        }

        return fibRecursive(n - 2) + fibRecursive(n - 1);
    }

    public static int fibIterative(int n) {
        if (n < 2) {
            return n;
        }

        int fib = 1;
        int fibPrev = 0;
        for (int i = 2; i <= n; i++) {
            int tmp = fib;
            fib += fibPrev;
            fibPrev = tmp;
        }

        return fib;
    }


    public static void main(String[] args) {
        System.out.println(FibonacciSeries.fibIterative(45));
        System.out.println(FibonacciSeries.fibRecursive(45));
    }


}
