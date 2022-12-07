package com.example.cinema;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
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

        if (u_email.getText().contains("@") && isOnlyDigits) {

            dbHandler.signUpUser(u_firstname.getText(),u_secondname.getText(),Integer.parseInt(u_age.getText()),u_email.getText(),u_password.getText());
            if (DatabaseHandler.i == 0 ) {
                root = FXMLLoader.load(AuthorizationController.class.getResource("person.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
            else
            {
                FXMLLoader error_Loader = new FXMLLoader(PostController.class.getResource("errorwindow.fxml"));
                try {
                    error_Loader.load();
                    ErrorWindowController errorWindowController=error_Loader.getController();
                    errorWindowController.errorRegistration("Користувач з такою поштовою адресою вже зареэстрован",text1);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                root  = error_Loader.getRoot();
                stage= new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Error");
                stage.setResizable(false);
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(((Node)event.getSource()).getScene().getWindow());
                stage.showAndWait();
            }

        }
        else if (!isOnlyDigits && !u_email.getText().contains("@"))
        {
            FXMLLoader error_Loader = new FXMLLoader(PostController.class.getResource("errorwindow.fxml"));
            try {
                error_Loader.load();
                ErrorWindowController errorWindowController=error_Loader.getController();
                errorWindowController.errorRegistration("Ви ввели невірні значення . Перевірте себе , email має містити '@',та спробуйте ще раз",text1);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            root  = error_Loader.getRoot();
            stage= new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Error");
            stage.setResizable(false);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node)event.getSource()).getScene().getWindow());
            stage.showAndWait();

        }
        else if (!u_email.getText().contains("@")&& isOnlyDigits)
        {
            FXMLLoader error_Loader = new FXMLLoader(PostController.class.getResource("errorwindow.fxml"));
            try {
                error_Loader.load();
                ErrorWindowController errorWindowController=error_Loader.getController();
                errorWindowController.errorRegistration("Ви ввели невірні значення . Перевірте себе , email має містити '@' та поле вік має містити тільки цифри та буду цілим числом,та спробуйте ще раз",text1);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            root  = error_Loader.getRoot();
            stage= new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Error");
            stage.setResizable(false);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node)event.getSource()).getScene().getWindow());
            stage.showAndWait();

        }
        else
        {
            FXMLLoader error_Loader = new FXMLLoader(PostController.class.getResource("errorwindow.fxml"));
            try {
                error_Loader.load();
                ErrorWindowController errorWindowController=error_Loader.getController();
                errorWindowController.errorRegistration("Ви ввели невірні значення . Перевірте себе ,поле вік має містити тільки цифри та буду цілим числом ,та спробуйте ще раз",text1);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            root  = error_Loader.getRoot();
            stage= new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Error");
            stage.setResizable(false);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node)event.getSource()).getScene().getWindow());
            stage.showAndWait();
        }
    }
    public void SwitchToAfisha(ActionEvent event) throws IOException {
        root  = FXMLLoader.load(AuthorizationController.class.getResource("post.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }




}
