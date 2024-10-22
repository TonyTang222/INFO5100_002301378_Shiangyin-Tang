// Abstract base class Shape
abstract class Shape {
    // Static field to represent the color
    static String color = "Blue";

    // Abstract methods for area and perimeter calculation
    abstract double calculateArea();
    abstract double calculatePerimeter();

    // Static method to demonstrate static fields
    public static String getColor() {
        return color;
    }
}

// Derived class: Triangle
class Triangle extends Shape {
    double base, height, side1, side2;

    // Constructor
    Triangle(double base, double height, double side1, double side2) {
        this.base = base;
        this.height = height;
        this.side1 = side1;
        this.side2 = side2;
    }

    // Overriding calculateArea method
    @Override
    double calculateArea() {
        return base * height * 0.5;
    }

    // Overriding calculatePerimeter method
    @Override
    double calculatePerimeter() {
        return base + side1 + side2;
    }
}

// Derived class: Rectangle
class Rectangle extends Shape {
    double length, width;

    // Constructor
    Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    // Overriding calculateArea method
    @Override
    double calculateArea() {
        return length * width;
    }

    // Overriding calculatePerimeter method
    @Override
    double calculatePerimeter() {
        return (length + width) * 2;
    }
}

// Derived class: Circle
class Circle extends Shape {
    double radius;
    final double PI = 3.14;

    // Constructor
    Circle(double radius) {
        this.radius = radius;
    }

    // Overriding calculateArea method
    @Override
    double calculateArea() {
        return radius * radius * PI;
    }

    // Overriding calculatePerimeter method
    @Override
    double calculatePerimeter() {
        return  radius * 2 * PI;
    }
}

// Derived class: Square
class Square extends Shape {
    double side;

    // Constructor
    Square(double side) {
        this.side = side;
    }

    // Overriding calculateArea method
    @Override
    double calculateArea() {
        return side * side;
    }

    // Overriding calculatePerimeter method
    @Override
    double calculatePerimeter() {
        return side * 4;
    }
}

// Main class to demonstrate the functionality
public class main {
    public static void main(String[] args) {
        Shape shape1 = new Triangle(9, 2, 3, 3);
        Shape shape2 = new Rectangle(8, 7);
        Shape shape3 = new Circle(2);
        Shape shape4 = new Square(2);

        System.out.println("Triangle area: " + shape1.calculateArea());
        System.out.println("Rectangle area: " + shape2.calculateArea());
        System.out.println("Circle area: " + shape3.calculateArea());
        System.out.println("Square area: " + shape4.calculateArea());

        System.out.println("Triangle perimeter: " + shape1.calculatePerimeter());
        System.out.println("Rectangle perimeter: " + shape2.calculatePerimeter());
        System.out.println("Circle perimeter: " + shape3.calculatePerimeter());
        System.out.println("Square perimeter: " + shape4.calculatePerimeter());

        // Demonstrate static field usage
        System.out.println("Shape color: " + Shape.getColor());
    }
}
