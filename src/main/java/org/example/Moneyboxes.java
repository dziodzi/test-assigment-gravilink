package org.example;

public class Moneyboxes {

    static int totalOutcomes = 0;
    static int positiveOutcomes = 0;
    

    public static void checkVariant(int first, int second, int n, int k, int count) {
        if (count <= (2*n - 1)) {
            if (first == n) {
                totalOutcomes++;
                if (second == k) {
                    positiveOutcomes++;
                }
            } else if (second == n) {
                totalOutcomes++;
                if (first == k) {
                    positiveOutcomes++;
                }

            } else {
                checkVariant(first + 1, second, n, k, count);
                checkVariant(first, second + 1, n, k, count);
            }
        }
    }

    public static double count(int n, int k) {
        totalOutcomes = 0;
        positiveOutcomes = 0;
        checkVariant(0, 0, n, k, 0);

        return (double) positiveOutcomes / totalOutcomes;
    }

    public static void main(String[] args) {
        System.out.printf("%.3f%n", count(10, 5));
        System.out.printf("%.3f%n", count(15, 7));
    }
}
