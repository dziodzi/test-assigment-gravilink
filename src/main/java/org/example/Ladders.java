package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Ladders {

    public static void main(String[] args) {
        solve("/4_1.txt");
        solve("/4_2.txt");
    }

    static int calculate(int[] nums, int A) {
        int answer = 0;
        int n = nums.length;
        boolean isARow = false;


        for (int i = 0; i < n; i++) {

            if (i != 0) {
                {
                    if (nums[i] != nums[i - 1] + 1) {
                        isARow = false;
                    }
                }
            }

            if (isARow && nums[i] == A) {
                answer++;
                isARow = false;
            } else if (nums[i] == 1)
                isARow = true;
        }
        return answer;
    }

    public static void solve(String path) {
        try {
            String absolutePath = new File("src/main/java/org/example").getAbsolutePath();

            File file = new File(absolutePath + path);

            Scanner scanner = new Scanner(file);

            int n = Integer.parseInt(scanner.nextLine());

            int A = Integer.parseInt(scanner.nextLine());

            String[] elements = scanner.nextLine().split(" ");

            int[] array = new int[n];

            for (int i = 0; i < n; i++) {
                array[i] = Integer.parseInt(elements[i]);
            }


            System.out.println(calculate(array, A));

            scanner.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}