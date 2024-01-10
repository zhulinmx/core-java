package com.basic.ch02;

public class Point {
    private double x, y;

    public Point() {
    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }


    public double getDistance(Point p) {
        return Math.sqrt(
                (this.x - p.getX()) * (this.x - p.getX()) +
                        (this.x - p.getX()) * (this.x - p.getX())
        );
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
