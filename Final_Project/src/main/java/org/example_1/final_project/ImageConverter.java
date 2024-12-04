package org.example_1.final_project;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class ImageConverter extends AbstractImageManager {

    public ImageConverter() {
        super();  // Calling the constructor of AbstractImageManager
    }

    @Override
    public void convertImages(String format, Stage primaryStage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png", "*.bmp"));
        File file = fileChooser.showSaveDialog(primaryStage);

        if (file != null) {
            try {
                BufferedImage bufferedImage = ImageIO.read(file);
                File outputFile = new File(file.getParent(), "converted_image." + format);
                ImageIO.write(bufferedImage, format, outputFile);
                System.out.println("Image converted and saved as " + outputFile.getName());
            } catch (IOException e) {
                System.out.println("Error converting image: " + e.getMessage());
            }
        }
    }

    @Override
    public void uploadImages(List<File> selectedFiles) {
        // Implement the image uploading logic here.
        // For example, you can iterate over the selected files and process them.

        if (selectedFiles != null) {
            for (File file : selectedFiles) {
                try {
                    // You can implement your own logic for handling the uploaded files.
                    System.out.println("Uploading image: " + file.getName());
                    // For example, you can call showImageProperties to display image info
                    showImageProperties(file);
                } catch (Exception e) {
                    System.out.println("Error uploading image: " + e.getMessage());
                }
            }
        }
    }
}