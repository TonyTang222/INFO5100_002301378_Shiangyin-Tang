package org.example_1.final_project;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;  // For ImageView

import java.io.File;
import java.util.List;

public class ImageManagerApp extends Application {

    private Stage primaryStage;
    private VBox root;
    private GridPane gridPane;
    private ImageUploader imageUploader;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Image Management Tool");

        // Initialize the ImageUploader (no arguments constructor)
        imageUploader = new ImageUploader();

        // Set up the main layout
        root = new VBox(10);
        Scene scene = new Scene(root, 800, 600);

        // Set up file chooser for uploading images
        Button uploadButton = new Button("Upload Image");
        uploadButton.setOnAction(e -> uploadImages());

        // Set up image conversion format options
        ComboBox<String> formatComboBox = new ComboBox<>();
        formatComboBox.getItems().addAll("png", "jpeg", "bmp");
        formatComboBox.setValue("jpeg");

        Button convertButton = new Button("Convert Images");
        convertButton.setOnAction(e -> convertImages(formatComboBox.getValue()));

        // Add UI elements
        root.getChildren().addAll(uploadButton, formatComboBox, convertButton);

        // Add labels for displaying image properties
        Label labelWidth = new Label();
        Label labelHeight = new Label();
        Label labelCamera = new Label();
        root.getChildren().addAll(labelWidth, labelHeight, labelCamera);

        gridPane = new GridPane();
        root.getChildren().add(gridPane);

        // Show the main scene
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void uploadImages() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.bmp"));
        List<File> selectedFiles = fileChooser.showOpenMultipleDialog(primaryStage);

        if (selectedFiles != null) {
            // Upload images using the imageUploader
            imageUploader.uploadImages(selectedFiles);

            // Display thumbnails in the gridPane
            for (File file : selectedFiles) {
                Image image = new Image(file.toURI().toString(), 100, 100, true, true);  // Resize to 100x100
                ImageView imageView = new ImageView(image);
                gridPane.add(imageView, 0, 0);  // Add the thumbnail to the grid (can adjust grid positioning)
            }
        }
    }

    private void convertImages(String format) {
        // Convert images using the imageUploader
        imageUploader.convertImages(format, primaryStage);
    }
}