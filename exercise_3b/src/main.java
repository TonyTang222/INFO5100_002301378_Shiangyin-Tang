import java.io.*;

// Abstract base class Shape
abstract class Shape implements Serializable {
    static String color = "Blue";

    abstract double calculateArea();
    abstract double calculatePerimeter();

    public static String getColor() {
        return color;
    }
}

// Derived class: Triangle
class Triangle extends Shape {
    double base, height, side1, side2;

    Triangle(double base, double height, double side1, double side2) {
        this.base = base;
        this.height = height;
        this.side1 = side1;
        this.side2 = side2;
    }

    @Override
    double calculateArea() {
        return base * height * 0.5;
    }

    @Override
    double calculatePerimeter() {
        return base + side1 + side2;
    }
}

// Derived class: Rectangle
class Rectangle extends Shape {
    double length, width;

    Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    @Override
    double calculateArea() {
        return length * width;
    }

    @Override
    double calculatePerimeter() {
        return (length + width) * 2;
    }
}

// Derived class: Circle
class Circle extends Shape {
    double radius;
    final double PI = 3.14;

    Circle(double radius) {
        this.radius = radius;
    }

    @Override
    double calculateArea() {
        return radius * radius * PI;
    }

    @Override
    double calculatePerimeter() {
        return radius * 2 * PI;
    }
}

// Derived class: Square
class Square extends Shape {
    double side;

    Square(double side) {
        this.side = side;
    }

    @Override
    double calculateArea() {
        return side * side;
    }

    @Override
    double calculatePerimeter() {
        return side * 4;
    }
}

// Main class to demonstrate serialization and deserialization
public class main {
    public static void main(String[] args) {
        Shape shape1 = new Triangle(9, 2, 3, 3);
        Shape shape2 = new Rectangle(8, 7);
        Shape shape3 = new Circle(2);
        Shape shape4 = new Square(2);

        // Serialization
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("shapes.ser"))) {
            out.writeObject(shape1);
            out.writeObject(shape2);
            out.writeObject(shape3);
            out.writeObject(shape4);
            System.out.println("Shapes have been serialized.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Deserialization
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("shapes.ser"))) {
            Triangle deserializedTriangle = (Triangle) in.readObject();
            Rectangle deserializedRectangle = (Rectangle) in.readObject();
            Circle deserializedCircle = (Circle) in.readObject();
            Square deserializedSquare = (Square) in.readObject();

            System.out.println("Deserialized Triangle area: " + deserializedTriangle.calculateArea());
            System.out.println("Deserialized Rectangle area: " + deserializedRectangle.calculateArea());
            System.out.println("Deserialized Circle area: " + deserializedCircle.calculateArea());
            System.out.println("Deserialized Square area: " + deserializedSquare.calculateArea());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}