import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    public static void main(String[] args) {
        System.out.println("Part A: Using Separate Threads\n");

        // Part A - Separate Threads
        Thread primeThread = new Thread(new PrimeCalculator(25), "PrimeCalculator");
        Thread fibonacciThread = new Thread(new FibonacciCalculator(50), "FibonacciCalculator");
        Thread factorialThread = new Thread(new FactorialCalculator(50), "FactorialCalculator");

        primeThread.start();
        fibonacciThread.start();
        factorialThread.start();

        // Wait for Part A threads to finish
        try {
            primeThread.join();
            fibonacciThread.join();
            factorialThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("\nPart B: Using Thread Pool\n");

        // Part B - Thread Pool
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        // Submit individual tasks to calculate primes
        for (int i = 1; i <= 25; i++) {
            final int count = i;
            executorService.submit(() -> {
                int num = nthPrime(count);
                printMessage("Prime", count, num);
                delay();
            });
        }

        // Submit individual tasks to calculate Fibonacci numbers
        for (int i = 1; i <= 50; i++) {
            final int count = i;
            executorService.submit(() -> {
                int fibNum = nthFibonacci(count);
                printMessage("Fibonacci", count, fibNum);
                delay();
            });
        }

        // Submit individual tasks to calculate factorials
        for (int i = 1; i <= 50; i++) {
            final int count = i;
            executorService.submit(() -> {
                long factorial = calculateFactorial(count);
                printMessage("Factorial", count, factorial);
                delay();
            });
        }

        executorService.shutdown();
    }

    // Prime number calculation for Part A
    static class PrimeCalculator implements Runnable {
        private final int countLimit;

        PrimeCalculator(int countLimit) {
            this.countLimit = countLimit;
        }

        @Override
        public void run() {
            int count = 0;
            int num = 2;
            while (count < countLimit) {
                if (isPrime(num)) {
                    printMessage("Prime", count + 1, num);
                    count++;
                    delay();
                }
                num++;
            }
        }
    }

    // Fibonacci calculation for Part A
    static class FibonacciCalculator implements Runnable {
        private final int countLimit;

        FibonacciCalculator(int countLimit) {
            this.countLimit = countLimit;
        }

        @Override
        public void run() {
            int n1 = 0, n2 = 1;
            for (int i = 1; i <= countLimit; i++) {
                printMessage("Fibonacci", i, n1);
                int next = n1 + n2;
                n1 = n2;
                n2 = next;
                delay();
            }
        }
    }

    // Factorial calculation for Part A
    static class FactorialCalculator implements Runnable {
        private final int countLimit;

        FactorialCalculator(int countLimit) {
            this.countLimit = countLimit;
        }

        @Override
        public void run() {
            for (int i = 1; i <= countLimit; i++) {
                long factorial = calculateFactorial(i);
                printMessage("Factorial", i, factorial);
                delay();
            }
        }
    }

    // Utility methods used in both Part A and Part B
    private static int nthPrime(int n) {
        int count = 0;
        int num = 2;
        while (true) {
            if (isPrime(num)) {
                count++;
                if (count == n) return num;
            }
            num++;
        }
    }

    private static int nthFibonacci(int n) {
        int n1 = 0, n2 = 1;
        for (int i = 1; i < n; i++) {
            int next = n1 + n2;
            n1 = n2;
            n2 = next;
        }
        return n1;
    }

    private static long calculateFactorial(int n) {
        long result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    private static boolean isPrime(int n) {
        if (n <= 1) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    private static void printMessage(String type, int count, long result) {
        System.out.printf("[%d] [%s] %s %d: %d%n", System.currentTimeMillis(),
                Thread.currentThread().getName(), type, count, result);
    }

    private static void delay() {
        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(100, 501));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}