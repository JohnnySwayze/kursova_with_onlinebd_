package com.example.cinema;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ErrorWindowController {
    @FXML
    private Text errormessage;
    @FXML
    private Button switchToRegistration;
    public  void errorRegistration(String text,String btnText)
    {

        errormessage.setText(text);
        switchToRegistration.setText(btnText);
    }

    public  void initialize()
    {
        switchToRegistration.setOnAction( event-> {
        switchToRegistration.getScene().getWindow().hide();
    });
    }

}
