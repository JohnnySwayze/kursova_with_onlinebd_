package com.example.cinema;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class PostMenuController {
    private Stage stage ;
    private Scene scene;
    private Parent root;
    public void SwitchToAfisha(ActionEvent event) throws IOException {
        root  = FXMLLoader.load(Objects.requireNonNull(PostController.class.getResource("post.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void SwitchToAuthorization(ActionEvent event) throws IOException {
        root  = FXMLLoader.load(Objects.requireNonNull(PostController.class.getResource("authorization.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
