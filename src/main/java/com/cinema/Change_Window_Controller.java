package com.cinema;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

import java.awt.*;


public class Change_Window_Controller {
    @FXML
    private Text abc;

    DatabaseHandler handler=new DatabaseHandler();

    public static int movie_id;




    @FXML
    void initialize() {
        System.out.println(handler.movie_title(movie_id));
        //title_label.setText();
    }
}
