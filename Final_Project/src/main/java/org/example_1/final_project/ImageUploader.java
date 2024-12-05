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
        FileChooser openFileChooser = new FileChooser();
        openFileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.jpeg", "*.png", "*.bmp"));
        File inputFile = openFileChooser.showOpenDialog(primaryStage);

        if (inputFile != null) {
            try {
                BufferedImage bufferedImage = ImageIO.read(inputFile);

                if (bufferedImage == null) {
                    System.out.println("Invalid or unsupported image file.");
                    return;
                }

                // Normalize the format for consistency
                if ("jpeg".equalsIgnoreCase(format)) {
                    format = "jpg";
                }

                FileChooser saveFileChooser = new FileChooser();
                saveFileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*." + format));
                File outputFile = saveFileChooser.showSaveDialog(primaryStage);

                if (outputFile != null) {
                    boolean result = ImageIO.write(bufferedImage, format, outputFile);
                    if (result) {
                        System.out.println("Image converted and saved as: " + outputFile.getAbsolutePath());
                    } else {
                        System.out.println("Failed to save image. Ensure the format is supported.");
                    }
                } else {
                    System.out.println("Save operation cancelled.");
                }
            } catch (IOException e) {
                System.out.println("Error converting image: " + e.getMessage());
            }
        } else {
            System.out.println("No input file selected.");
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