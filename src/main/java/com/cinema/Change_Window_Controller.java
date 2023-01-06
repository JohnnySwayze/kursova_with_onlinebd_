package com.cinema;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.File;


public class Change_Window_Controller {
    @FXML
    private TextField movie_title_field;
    @FXML
    private Label title;
    @FXML
    private TextField picture_field;
    @FXML
    private TextField moviedescription_field;

    @FXML
    private TextField timeofthefirstsession_field;
    @FXML
    private TextField timeofthesecondsession_field;
    @FXML
    private TextField timeofthethirdsession_field;
    @FXML
    private Button change_btn;
    @FXML
    private Button choicepath_btn;
    @FXML
    private Label timeofthesecondseans_label;
    @FXML
    private Label timeofthethirdseans_label;
    @FXML
    private TextField age_limit_field;
    @FXML
    private AnchorPane anch;
    public static int movie_id;
    DatabaseHandler handler=new DatabaseHandler();
    private Stage stage1;
    private Scene scene1;
    private Parent root1;
    @FXML
    void choise_path(MouseEvent event) {
        char c ='\"';
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File Dialog");
        Stage stage = (Stage) anch.getScene().getWindow();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            picture_field.setText(file.getAbsolutePath().replaceAll("\\\\", "/"));
        }
    }
    @FXML
    void changes_btn(MouseEvent mouseEvent) throws Exception
    {
        String movie_name = movie_title_field.getText();
        String picture_path = picture_field.getText();
        String movie_description = moviedescription_field.getText();
        String timeoffirstseans = timeofthefirstsession_field.getText();
        String timeofsecondseans = timeofthesecondsession_field.getText();
        String timeofthirdseans = timeofthethirdsession_field.getText();
        String agelimit=age_limit_field.getText();

        if (timeofthesecondsession_field.isVisible())
        {

            if (!timeoffirstseans.equals("") || !timeofsecondseans.equals("") || !timeofthirdseans.equals(""))
            {

                if(!movie_name.equals("") && !picture_path.equals("") && !movie_description.equals("") && !agelimit.equals(""))
                {
                    if (!timeoffirstseans.equals(""))
                    {
                        String url ="moviepicture=? , moviename=? , moviedescription=? , movietime1=? , movieage=? ";
                        handler.change_movie(11,url,movie_id,movie_name,picture_path,movie_description,timeoffirstseans,timeofsecondseans
                                ,timeofthirdseans,agelimit);
                    }
                    if (!timeofsecondseans.equals(""))
                    {
                        String url ="moviepicture=? , moviename=? , moviedescription=? , movietime2=? , movieage=? ";
                        handler.change_movie(12,url,movie_id,movie_name,picture_path,movie_description,timeoffirstseans,timeofsecondseans
                                ,timeofthirdseans,agelimit);
                    }
                    if (!timeofthirdseans.equals(""))
                    {
                        String url ="moviepicture=? , moviename=? , moviedescription=? , movietime3=? , movieage=? ";
                        handler.change_movie(13,url,movie_id,movie_name,picture_path,movie_description,timeoffirstseans,timeofsecondseans
                                ,timeofthirdseans,agelimit);
                    }
                }
                if (!timeoffirstseans.equals(""))
                {
                    String url ="movietime1=?";
                    handler.change_movie(1,url,movie_id,movie_name,picture_path,movie_description,timeoffirstseans,timeofsecondseans
                            ,timeofthirdseans,agelimit);
                }
                if (!timeofsecondseans.equals(""))
                {
                    String url ="movietime2=?";
                    handler.change_movie(2,url,movie_id,movie_name,picture_path,movie_description,timeoffirstseans,timeofsecondseans
                            ,timeofthirdseans,agelimit);
                }
                if (!timeofthirdseans.equals(""))
                {
                    String url ="movietime3=?";
                    handler.change_movie(3,url,movie_id,movie_name,picture_path,movie_description,timeoffirstseans,timeofsecondseans
                            ,timeofthirdseans,agelimit);
                }
            }
            if (!movie_name.equals("") && !picture_path.equals("") && !movie_description.equals("") && !agelimit.equals(""))
            {
                String url="moviepicture=? , moviename=? , moviedescription =? ,movieage=?";
                handler.change_movie(4,url,movie_id,movie_name,picture_path,movie_description,timeoffirstseans,timeofsecondseans
                        ,timeofthirdseans,agelimit);
            }
        }
        if (!timeofthethirdsession_field.isVisible())
        {
            if (movie_name.equals("") && picture_path.equals("") && movie_description.equals("") && !timeoffirstseans.equals("")
                     && agelimit.equals("") )
            {

                String url="movietime1= ?";
                handler.change_movie(8,url,movie_id,movie_name,picture_path,movie_description,timeoffirstseans,timeofsecondseans
                        ,timeofthirdseans,agelimit);
            }
            else if (!movie_name.equals("") && !picture_path.equals("") && !movie_description.equals("") && !timeoffirstseans.equals("")
                    && !agelimit.equals(""))
            {

               String url="moviepicture=? , moviename=? , moviedescription=? , movietime1=? , movieage=? ";
               handler.change_movie(9,url,movie_id,movie_name,picture_path,movie_description,timeoffirstseans,timeofsecondseans
                        ,timeofthirdseans,agelimit);
            }
        }
        root1 = FXMLLoader.load(Change_Window_Controller.class.getResource("post.fxml"));
        stage1 = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        scene1 = new Scene(root1);
        stage1.setScene(scene1);
        stage1.centerOnScreen();
        stage1.show();
    }
    @FXML
    void initialize() {
        System.out.println(handler.movie_title(movie_id));
        title.setText("Фільм: '"+handler.movie_title(movie_id)+"'");
        if (movie_id>4)
        {
            timeofthesecondsession_field.setVisible(false);
            timeofthethirdsession_field.setVisible(false);
            timeofthesecondseans_label.setVisible(false);
            timeofthethirdseans_label.setVisible(false);
        }

    }
}
