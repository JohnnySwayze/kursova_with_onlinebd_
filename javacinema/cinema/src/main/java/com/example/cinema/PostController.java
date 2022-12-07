package com.example.cinema;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;

import com.mysql.cj.jdbc.Driver;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PostController {

    @FXML
    private TextField DateTime;

    @FXML
    private Label namemovie1;

    @FXML
    private Label namemovie2;

    @FXML
    private Label namemovie3;

    @FXML
    private Label namemovie4;

    @FXML
    private Label descriptionmovie1;

    @FXML
    private Label descriptionmovie2;

    @FXML
    private Label descriptionmovie3;

    @FXML
    private Label descriptionmovie4;

    @FXML
    private ImageView imgmovie1;

    @FXML
    private ImageView imgmovie2;

    @FXML
    private ImageView imgmovie3;

    @FXML
    private ImageView imgpremieremovie1;

    @FXML
    private ImageView imgpremieremovie2;

    @FXML
    private ImageView imgpremieremovie3;

    @FXML
    private ImageView imgmovie4;
    @FXML
    private Button movie1attime1;

    @FXML
    private Button movie1attime2;

    @FXML
    private Button movie1attime3;

    @FXML
    private Button movie2attime1;

    @FXML
    private Button movie2attime2;

    @FXML
    private Button movie2attime3;

    @FXML
    private Button movie3attime1;

    @FXML
    private Button movie3attime2;

    @FXML
    private Button movie3attime3;

    @FXML
    private Button movie4attime1;

    @FXML
    private Button movie4attime2;

    @FXML
    private Button movie4attime3;
    @FXML
    private Label age1;

    @FXML
    private Label age2;

    @FXML
    private Label age3;

    @FXML
    private Label age4;

    @FXML
    private Label age_premier_1;

    @FXML
    private Label age_premier_2;

    @FXML
    private Label age_premier_3;
    @FXML
    private Label premierlabel;

    private Stage stage;
    private Scene scene;
    private Parent root;
    Connection connection;

  /*  ImageView[] imageViews1 = {imgmovie1, imgmovie2, imgmovie3, imgmovie4};
    Label[] namemovie = {namemovie1, namemovie2, namemovie3, namemovie4};
    Label[] descmovie = {descriptionmovie1, descriptionmovie2, descriptionmovie3, descriptionmovie4};
    Label[] age = {age1, age2, age3, age4};
    Button[] movtime1 = {movie1attime1, movie2attime1, movie3attime1, movie4attime1};
    Button[] movtime2 = {movie1attime2, movie2attime2, movie3attime2, movie4attime2};
    Button[] movtime3 = {movie1attime3, movie2attime3, movie3attime3, movie4attime3};
    Label[] ageprem = {age_premier_1, age_premier_2, age_premier_3};
    ImageView[] imgpremiermov = {imgpremieremovie1, imgpremieremovie2, imgpremieremovie3};
    public void UpdateMovies(ArrayList<Image> a, ArrayList<String> b ,ArrayList<String> c ,
                             ArrayList<String> e1, ArrayList<String> e2, ArrayList<String> e3,
                             ArrayList<Integer> d,ArrayList<Image> a1,ArrayList<Integer> d2){

        for (int i = 0; i <= 3; i++) {
            imageViews1[i].setImage(a.get(i));
            namemovie[i].setText(b.get(i));
            descmovie[i].setText(c.get(i));
            movtime1[i].setText(e1.get(i));
            movtime2[i].setText(e2.get(i));
            movtime3[i].setText(e3.get(i));
            age[i].setText(String.valueOf(d.get(i)) + "+");
        }
        for(int k =0 ; k<3;k++)
        {
            ageprem[k].setText(String.valueOf(d2.get(k)));
            imgpremiermov[k].setImage(a1.get(k));
        }
    }*/
    public void updateMovies() {
        premierlabel.setText("Прем'єри");
        ImageView[] a = {imgmovie1, imgmovie2, imgmovie3, imgmovie4};
        Label[] b = {namemovie1, namemovie2, namemovie3, namemovie4};
        Label[] c = {descriptionmovie1, descriptionmovie2, descriptionmovie3, descriptionmovie4};
        Label[] d = {age1, age2, age3, age4};
        Button[] e1 = {movie1attime1, movie2attime1, movie3attime1, movie4attime1};
        Button[] e2 = {movie1attime2, movie2attime2, movie3attime2, movie4attime2};
        Button[] e3 = {movie1attime3, movie2attime3, movie3attime3, movie4attime3};
        Label[] d1 = {age_premier_1, age_premier_2, age_premier_3};
        ImageView[] a1 = {imgpremieremovie1, imgpremieremovie2, imgpremieremovie3};

        for (int i = 0; i <= 3; i++) {
            int k = i + 1;


            try {
                Driver driver = new Driver();
                DriverManager.registerDriver(driver);
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Cinema", "root", "12345");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            String urlk = "SELECT*FROM movies WHERE idmovies=?";

            try (PreparedStatement post_statementk = connection.prepareStatement(urlk)) {

                Blob blobk;
                post_statementk.setInt(1, k);
                ResultSet resultSetk = post_statementk.executeQuery();
                if (resultSetk.next()) {
                    blobk = resultSetk.getBlob(2);
                    InputStream inputStream = blobk.getBinaryStream();
                    Image image = new Image(inputStream);
                    a[i].setImage(image);
                    String namemovie_k = resultSetk.getString(3);
                    b[i].setText(namemovie_k);
                    String moviediscription_k = resultSetk.getString(4);
                    c[i].setText(moviediscription_k);
                    String movie_timek = resultSetk.getString(5);
                    e1[i].setText(movie_timek);
                    String movie_time2 = resultSetk.getString(6);
                    e2[i].setText(movie_time2);
                    String movie_time3 = resultSetk.getString(7);
                    e3[i].setText(movie_time3);
                    Integer movie_agek = resultSetk.getInt(8);
                    d[i].setText(String.valueOf(movie_agek) + "+");
                }

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            for (int m = 0; m < 3; m++) {
                int f = 5 + m;
                try {
                    Driver driver = new Driver();
                    DriverManager.registerDriver(driver);
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Cinema", "root", "12345");
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                String urlf = "SELECT*FROM movies WHERE idmovies=?";
                try (PreparedStatement preparedStatement_2 = connection.prepareStatement(urlf)) {
                    Blob blobf;
                    preparedStatement_2.setInt(1, f);
                    ResultSet resultSetf = preparedStatement_2.executeQuery();
                    if (resultSetf.next()) {
                        blobf = resultSetf.getBlob(2);
                        InputStream inputStreamf = blobf.getBinaryStream();
                        Image image = new Image(inputStreamf);
                        a1[m].setImage(image);
                        String premieragef = resultSetf.getString(8);
                        d1[m].setText(premieragef);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
    }





    public void OpenSeans(ActionEvent event)throws IOException
    {
        FXMLLoader seans_Loader = new FXMLLoader(PostController.class.getResource("HallA.fxml"));
        try {
            seans_Loader.load();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        root  = seans_Loader.getRoot();
        stage= new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Error");
        stage.setResizable(false);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)event.getSource()).getScene().getWindow());
        stage.showAndWait();
    }
    public void SwitchToMenu(ActionEvent event) throws IOException {
        root = FXMLLoader.load(HelloController.class.getResource("postmenu.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}

