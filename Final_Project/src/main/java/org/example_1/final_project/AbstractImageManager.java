package org.example_1.final_project;

import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.List;

public abstract class AbstractImageManager implements ImageOperations {
    protected ImageDetails imageDetails;  // Encapsulate image details in this class

    // Common method to display image properties
    @Override
    public void showImageProperties(File file) {
        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            int width = bufferedImage.getWidth();
            int height = bufferedImage.getHeight();
            imageDetails = new ImageDetails(width, height, "Unknown");  // Placeholder for camera info

            imageDetails.displayProperties();  // Displaying image properties
        } catch (IOException e) {
            System.out.println("Error reading image properties: " + e.getMessage());
        }
    }

    // Abstract methods for uploading and converting images
    @Override
    public abstract void uploadImages(List<File> selectedFiles);

    @Override
    public abstract void convertImages(String format, Stage primaryStage);
}