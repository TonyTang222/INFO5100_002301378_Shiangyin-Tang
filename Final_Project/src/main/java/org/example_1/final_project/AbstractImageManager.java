package org.example_1.final_project;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import com.drew.metadata.exif.GpsDirectory;

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
            // Read image dimensions
            BufferedImage bufferedImage = ImageIO.read(file);
            int width = bufferedImage.getWidth();
            int height = bufferedImage.getHeight();

            String cameraModel = "Unknown";
            String location = "Unknown";

            // Extract metadata
            Metadata metadata = ImageMetadataReader.readMetadata(file);

            // Extract camera model
            ExifSubIFDDirectory exifDirectory = metadata.getFirstDirectoryOfType(ExifSubIFDDirectory.class);
            if (exifDirectory != null && exifDirectory.containsTag(ExifSubIFDDirectory.TAG_MODEL)) {
                cameraModel = exifDirectory.getString(ExifSubIFDDirectory.TAG_MODEL);
            } else {
                System.out.println("Camera model not found.");
            }

            // Extract GPS location
            GpsDirectory gpsDirectory = metadata.getFirstDirectoryOfType(GpsDirectory.class);
            if (gpsDirectory != null && gpsDirectory.getGeoLocation() != null) {
                location = gpsDirectory.getGeoLocation().toString();
            } else {
                System.out.println("GPS data not found.");
            }

            // Initialize and display image details
            imageDetails = new ImageDetails(width, height, cameraModel, location);
            imageDetails.displayProperties();

        } catch (Exception e) {
            System.out.println("Error reading image properties: " + e.getMessage());
        }
    }

    // Abstract methods for uploading and converting images
    @Override
    public abstract void uploadImages(List<File> selectedFiles);

    @Override
    public abstract void convertImages(String format, Stage primaryStage);
}