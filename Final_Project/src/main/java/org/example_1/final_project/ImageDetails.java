package org.example_1.final_project;

// ImageDetails.java
public class ImageDetails {
    private int width;
    private int height;
    private String camera;

    // Constructor to initialize image properties
    public ImageDetails(int width, int height, String camera) {
        this.width = width;
        this.height = height;
        this.camera = camera;
    }

    // Getters and setters for encapsulation
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getCamera() {
        return camera;
    }

    public void setCamera(String camera) {
        this.camera = camera;
    }

    // Method to display image properties
    public void displayProperties() {
        System.out.println("Width: " + width + ", Height: " + height + ", Camera: " + camera);
    }
}
