package org.example_1.final_project;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class ImageUploader extends AbstractImageManager {

    // Constructor with no arguments
    public ImageUploader() {
        super();  // Call the constructor of the abstract class
    }

    @Override
    public void convertImages(String format, Stage primaryStage) {
        // Implement image conversion logic
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png", "*.jpeg", "*.bmp"));
        File file = fileChooser.showOpenDialog(primaryStage);

        if (file != null) {
            System.out.println("Selected file: " + file.getAbsolutePath());
            try {
                BufferedImage bufferedImage = ImageIO.read(file);
                if (bufferedImage == null) {
                    System.out.println("Image reading failed.");
                } else {
                    System.out.println("Image read successfully.");
                }
            } catch (IOException e) {
                System.out.println("Error reading image: " + e.getMessage());
            }
        } else {
            System.out.println("No file selected.");
        }
    }

    @Override
    public void uploadImages(List<File> selectedFiles) {
        // Implement image uploading logic
        if (selectedFiles != null) {
            for (File file : selectedFiles) {
                try {
                    // Implement your logic for handling the uploaded files here
                    System.out.println("Uploading image: " + file.getName());
                    // You could also display the image properties or show thumbnails here
                    showImageProperties(file);
                } catch (Exception e) {
                    System.out.println("Error uploading image: " + e.getMessage());
                }
            }
        }
    }
}