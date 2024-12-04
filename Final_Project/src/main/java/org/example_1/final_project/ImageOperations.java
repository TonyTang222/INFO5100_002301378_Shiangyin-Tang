package org.example_1.final_project;

import javafx.stage.Stage;

import java.io.File;
import java.util.List;

public interface ImageOperations {
    void uploadImages(List<File> selectedFiles);  // Method for uploading images

    void convertImages(String format, Stage primaryStage);  // Method for converting images

    void showImageProperties(File file);  // Method to show image properties
}