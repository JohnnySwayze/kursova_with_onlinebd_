package com.cinema;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class RegistrationController  {
    @FXML
    private TextField u_firstname;

    @FXML
    private TextField u_secondname;

    @FXML
    private TextField u_age;

    @FXML
    private TextField u_email;

    @FXML
    private PasswordField u_password;
    private Stage stage ;
    private Scene scene;
    private Parent root;




    DatabaseHandler dbHandler=new DatabaseHandler();
    public void adduser(ActionEvent event) throws IOException  {
        String text1="повернутися до реєстрації";
        boolean isOnlyDigits = true;
        for(int i = 0; i < u_age.getText().length(); i++) {
            if(!Character.isDigit(u_age.getText().charAt(i))) {
                isOnlyDigits = false;
            }
        }
        if (u_firstname.getText().equals(" ") || u_secondname.getText().equals("")||u_age.getText().equals("")||
                u_email.getText().equals("")||u_password.getText().equals(""))
        {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Ви заповнили не всі поля");
            alert.setContentText("Повернутися до реєстрації");
            alert.showAndWait();
        }
        else {
            if (u_email.getText().contains("@") && isOnlyDigits && !(u_firstname.equals("")) && !u_secondname.equals("")
                    && !u_age.equals("") && !u_password.equals("")) {

                dbHandler.signUpUser(u_firstname.getText(), u_secondname.getText(), Integer.parseInt(u_age.getText()), u_email.getText(), u_password.getText());
                HelloController.email_user=u_email.getText();
                if (DatabaseHandler.i == 0) {
                    HelloController.getConnection = 1;
                    root = FXMLLoader.load(AuthorizationController.class.getResource("person.fxml"));
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.centerOnScreen();
                    stage.show();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Користувач з такою поштовою адресою вже зареэстрован");
                    alert.setContentText("Повернутися до реєстрації");
                    alert.showAndWait();
                }

            } else if (!isOnlyDigits && !u_email.getText().contains("@")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Ви ввели невірні значення . Перевірте себе , email має містити '@',та спробуйте ще раз");
                alert.setContentText("Повернутися до реєстрації");
                alert.showAndWait();

            } else if (!u_email.getText().contains("@") && isOnlyDigits) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Ви ввели невірні значення . Перевірте себе , email має містити '@' та поле вік має містити тільки цифри та буду цілим числом,та спробуйте ще раз");
                alert.setContentText("Повернутися до реєстрації");
                alert.showAndWait();
            } else if (u_email.getText().contains("@") && !isOnlyDigits) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Ви ввели невірні значення . Перевірте себе ,поле вік має містити тільки цифри та буду цілим числом ,та спробуйте ще раз");
                alert.setContentText("Повернутися до реєстрації");
                alert.showAndWait();
            }
        }

    }
    public void SwitchToAfisha(ActionEvent event) throws IOException {
        root  = FXMLLoader.load(AuthorizationController.class.getResource("post.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }




}
