package com.example.circle;

public class Circle {
    private double radius;

    public Circle(double radius){
        this.radius = radius;
    }

    public double getArea(){
        return Math.PI * radius * radius;
    }

    public static void main(String[] args) {
        Circle circle = new Circle(11.7);
        System.out.println("circle area = "+circle.getArea());
    }
}
