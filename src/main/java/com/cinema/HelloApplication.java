package com.cinema;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;


public class HelloApplication extends Application {
   DatabaseHandler h=new DatabaseHandler();
     
    @Override
    public void start(Stage stage) throws IOException {
        try {
            Parent root= FXMLLoader.load(HelloApplication.class.getResource("hello-view.fxml"));
            Scene scene =new Scene(root);
            stage.setScene(scene);
            stage.getIcons().add((h.download_picture(4)));
            stage.setTitle("OYE");
            stage.setResizable(false);
            stage.centerOnScreen();
            stage.show();
        }
         catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }


}