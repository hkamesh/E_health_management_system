package hm;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class hm extends Application {
    // Database connection details
    private static final String URL = "jdbc:mysql://localhost:3306/e_healthcare";
    private static final String USER = "root";
    private static final String PASSWORD = "1qaz2wsx";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("E-Healthcare Management System");

        // UI Elements
        Label usernameLabel = new Label("Username:");                                                 
        TextField usernameField = new TextField();
        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        Button loginButton = new Button("Login");

        // Layout Setup
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.add(usernameLabel, 0, 0);
        gridPane.add(usernameField, 1, 0);
        gridPane.add(passwordLabel, 0, 1);
        gridPane.add(passwordField, 1, 1);
        gridPane.add(loginButton, 1, 2);

        // Login button action
        loginButton.setOnAction(event -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            login(username, password, primaryStage);
        });

        Scene scene = new Scene(gridPane, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Database connection method
    private static Connection getConnection() {
        try {                                                                                                                                 
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Database connection failed.");
            return null;
        }
    }

    // Login logic method
    private void login(String username, String password, Stage stage) {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";

        try (Connection connection = getConnection()) {
            if (connection == null) return;

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String role = resultSet.getString("role");
                showAlert("Login Successful", "Welcome, " + role + "!");
                
                // Open the appropriate dashboard based on role
                if ("doctor".equals(role)) {
                    showDoctorDashboard(stage);
                } else if ("patient".equals(role)) {
                    showPatientDashboard(stage);
                }
            } else {   :                                                                                                                   
                showAlert("Login Failed", "Invalid username or password.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "An error occurred during login.");
        }
    }

    // Show the Doctor Dashboard
    private void showDoctorDashboard(Stage stage) {
        stage.setTitle("Doctor Dashboard");

        // UI Elements for Doctor Dashboard
        Label doctorLabel = new Label("Doctor Dashboard");
        Button viewPatientsButton = new Button("View Patients");

        // Button action
        viewPatientsButton.setOnAction(event -> showPatientsList());

        VBox doctorLayout = new VBox(20);
        doctorLayout.getChildren().addAll(doctorLabel, viewPatientsButton);

        Scene doctorScene = new Scene(doctorLayout, 300, 200);
        stage.setScene(doctorScene);
    }

    // Show the Patient Dashboard
    private void showPatientDashboard(Stage stage) {
        stage.setTitle("Patient Dashboard");

                                                                                                                                                  
        Label patientLabel = new Label("Patient Dashboard");
        Button viewDetailsButton = new Button("View Patient Details");

        // Button action (Placeholder)
        viewDetailsButton.setOnAction(event -> showAlert("Patient Details", "Displaying patient details..."));

        VBox patientLayout = new VBox(20);
        patientLayout.getChildren().addAll(patientLabel, viewDetailsButton);

        Scene patientScene = new Scene(patientLayout, 300, 200);
        stage.setScene(patientScene);
    }

    // Show all patients (for Doctor to view)
    private void showPatientsList() {
        // Placeholder: Show a list of patients
        // In a real app, this data would come from the database
        showAlert("Patients List", "1. Ravi\n2. Anjali\n3. Suresh\n4. Priya\n5. Vikram\n6. Neha");
    }

    // Alert dialog for messages
    private static void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}


