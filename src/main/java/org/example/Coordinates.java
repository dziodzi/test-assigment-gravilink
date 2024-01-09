package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Coordinates {
    public static void main(String[] args) throws FileNotFoundException {
        solve("/5_1.txt");
        solve("/5_2.txt");
    }

    static void solve(String path) throws FileNotFoundException {
        boolean result = calculate(readFile(path));
        System.out.println(result);
    }

    static boolean calculate(List<Point> pointList) {

        List<Point> points = pointList.stream().sorted(Comparator.comparingDouble(Point::getX)).toList();

        double midX = (points.get(0).getX() + points.get(points.size() - 1).getX()) / 2;

        for (int i = 2; i < points.size(); i++) {

            double x = points.get(i).getX();
            double y = points.get(i).getY();

            double x_prev = points.get(i - 1).getX();
            double y_prev = points.get(i - 1).getY();

            double x_prev2 = points.get(i - 2).getX();
            double y_prev2 = points.get(i - 2).getY();

            double k1 = (y - y_prev) / (x - x_prev);
            double k2 = (y - y_prev2) / (x - x_prev2);

            double b1 = (x * y_prev - x_prev * y) / (x - x_prev);
            double b2 = (x * y_prev2 - x_prev2 * y) / (x - x_prev2);

            if (k1 == k2 && b1 == b2) {
                points.get(i - 1).setCollinear();
            }
        }

        List<Point> res = points.stream()
                .sorted(Comparator.comparingDouble(Point::getX))
                .filter(o -> !o.isCollinear)
                .map(o -> new Point(o.getX() - midX, o.getY()))
                .toList();

        for (int i = 0; i < (res.size() / 2) + 1; i++) {
            if (res.get(i).getX() != 0) {
                if ((res.get(i).getX() != (-1 * res.get(res.size() - i - 1).getX())) ||
                        (res.get(i).getY() != res.get(res.size() - i - 1).getY())) {
                    return false;
                }
            }
        }
        return true;
    }

    static List<Point> readFile(String link) throws FileNotFoundException {

        String path = new File("src/main/java/org/example").getAbsolutePath();

        File file = new File(path + link);

        List<Point> pointList = new ArrayList<>();

        Scanner scanner = new Scanner(file);

        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < n; i++) {
            String[] elements = scanner.nextLine().split(" ");
            pointList.add(new Point(Double.parseDouble(elements[0]), Double.parseDouble(elements[1])));
        }

       return pointList;
    }

    static class Point {
        private double x, y;
        private boolean isCollinear;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
            this.isCollinear = false;
        }

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }

        public void setCollinear() {
            this.isCollinear = true;
        }
    }
}