package com.cinema;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class PostMenuController {
    @FXML
    private Button Switchbtn;
    private Stage stage ;
    private Scene scene;
    private Parent root;
    public void SwitchToAfisha(ActionEvent event) throws IOException {
        root  = FXMLLoader.load(Objects.requireNonNull(PostController.class.getResource("post.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public void SwitchToAuthorization_Person(ActionEvent event) throws IOException {
        if(HelloController.getConnection==0)
        {
            root  = FXMLLoader.load(Objects.requireNonNull(PostController.class.getResource("authorization.fxml")));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        }
        else
        {
            root  = FXMLLoader.load(Objects.requireNonNull(PostController.class.getResource("person.fxml")));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        }
    }
    @FXML
    void initialize()
    {
        if(HelloController.getConnection==0) Switchbtn.setText("Авторизуватися");

        else Switchbtn.setText("До особистого кабінету");
    }
}
