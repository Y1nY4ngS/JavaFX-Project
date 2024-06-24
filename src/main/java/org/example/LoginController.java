package org.example;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoginController {

    App app = new App();
    @FXML
    private Button ex_create;

    @FXML
    private Hyperlink ex_forgot;

    @FXML
    private AnchorPane ex_loginAnchor;

    @FXML
    private Button ex_loginBtn;

    @FXML
    private PasswordField ex_password;

    @FXML
    private TextField ex_username;

    @FXML
    private TextField re_address;

    @FXML
    private Button re_alreadyHave;

    @FXML
    private TextField re_city;

    @FXML
    private TextField re_email;

    @FXML
    private PasswordField re_password;

    @FXML
    private AnchorPane re_sideForm;

    @FXML
    private Button re_signupBtn;

    @FXML
    private TextField re_username;

    @FXML
    private AnchorPane side_form;

    @FXML
    protected void handleLoginButtonAction() throws IOException {
        String username = ex_username.getText();
        String password = ex_password.getText();

        if (validateLogin(username, password)) {
            showAlert(Alert.AlertType.INFORMATION, "Login Successful!", "Welcome, " + username);

            Parent root = FXMLLoader.load(getClass().getResource("homepage.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);

            stage.setTitle("PC-World");
            stage.setMinWidth(1100);
            stage.setMinHeight(600);

            stage.setScene(scene);
            stage.show();

            ex_loginBtn.getScene().getWindow().hide();
        } else {
            showAlert(Alert.AlertType.ERROR, "Login Failed!", "Invalid username or password.");
        }
    }
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    private boolean validateLogin(String username, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] credentials = line.split(",");
                if (credentials[0].equals(username) && credentials[1].equals(password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void switchForm(ActionEvent event){
        TranslateTransition slider = new TranslateTransition();

        if(event.getSource() == ex_create){
            slider.setNode(side_form);
            slider.setToX(300);
            slider.setDuration(Duration.seconds(0.5));

            slider.setOnFinished((ActionEvent e) -> {
                re_alreadyHave.setVisible(true);
                ex_create.setVisible(false);
            });
            slider.play();
        } else if(event.getSource() == re_alreadyHave){
            slider.setNode(side_form);
            slider.setToX(0);
            slider.setDuration(Duration.seconds(0.5));

            slider.setOnFinished((ActionEvent e) -> {
                re_alreadyHave.setVisible(false);
                ex_create.setVisible(true);
            });
            slider.play();
        }
    }

    private UserController userController = new UserController();

    @FXML
    private void handleSignupButtonAction() {
        String username = re_username.getText();
        String password = re_password.getText();
        String address = re_address.getText();
        String city = re_city.getText();
        String email = re_email.getText();

        if (username.isEmpty() || password.isEmpty() || address.isEmpty() || city.isEmpty() || email.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Sign Up Failed!", "Please fill all the fields.");
            return;
        }
        User newUser = new User(username, password, address, city, email);
        try {
            userController.saveUser(newUser);
            showAlert(Alert.AlertType.CONFIRMATION, "Sign Up Successful!", "Welcome, " + username);
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Sign Up Failed!", "Invalid username or password.");
        }
    }
}