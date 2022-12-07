package com.cinema;
import java.io.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
    @FXML
    private Label premiername1;

    @FXML
    private Label premiername2;
    @FXML
    private Label premiername3;
    @FXML
    private ScrollPane scroll_pane;
    private Stage stage;
    private Scene scene;
    private Parent root;
    DatabaseHandler reserv = new DatabaseHandler();
    public static ArrayList<Integer> reservetickets_ = new ArrayList<>();
    public static ArrayList<Integer> boughttickets_ = new ArrayList<>();
    DatabaseHandler db_conect = new DatabaseHandler();
    private boolean timeover;
    boolean is_date_true = true;
    private ArrayList<ImageView> imageViews = new ArrayList<>();

    public void SwitchToMenu(ActionEvent event) throws IOException {
        root = FXMLLoader.load(HelloController.class.getResource("postmenu.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();

    }

    public  void date_validity_check(String entered_date) {

            String date_array[] = entered_date.split("\\.");
            boolean isLeap = false;
            boolean isValidDate = true;
            int day = Integer.parseInt(date_array[0]);
            int month = Integer.parseInt(date_array[1]);
            int year = Integer.parseInt(date_array[2]);
            if (year % 4 == 0) {
                isLeap = true;
            }
            if (day < 1 || day > 31) {
                isValidDate = false;
            }
            if (month < 1 || month > 12) {
                isValidDate = false;
            }
            if (month == 2 && day > 29 && isLeap == true) {
                isValidDate = false;
            }
            if (month == 2 && day > 28 && isLeap == false) {
                isValidDate = false;
            }
            if (month == 4 || month == 6 || month == 9 || month == 11) {
                if (day > 30) {
                    isValidDate = false;
                }
            }
        }




    public void controlldate(ActionEvent Text) {

        int flag=0;
        Date now = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date choicedate = null;
        int dayOfWeek = 0;
        try {
            if (DateTime.getText().equals(""))
            {
                Alert alert =new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Ви не обрали дату");
                alert.showAndWait();
            }
            else
            {
                date_validity_check(DateTime.getText());
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Введіть дату правильно");
            alert.setContentText("Введіть дату у форматі дд.мм.гггг");
            alert.showAndWait();
            flag=1;
        }
        catch (NumberFormatException e )
        {
            if (flag==0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Введіть дату правильно");
                alert.setContentText("Дату можна вводити тільки цифрами через крапку");
                alert.showAndWait();
            }
        }
            try {
                String date = simpleDateFormat.format(now);

                choicedate = simpleDateFormat.parse(DateTime.getText());
                timeover = choicedate.before(simpleDateFormat.parse(date));
                Calendar calendar1 = Calendar.getInstance();
                calendar1.setTime(choicedate);
                dayOfWeek = calendar1.get(Calendar.DAY_OF_WEEK);

            } catch (ParseException e) {

            }
            if (timeover) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Спробуйте ще раз");
                alert.setHeaderText("Ви ввели дату, що минула");
                alert.showAndWait();
                DateTime.clear();
            }
            if (dayOfWeek == 1) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Оберіть іншу дату");
                alert.setHeaderText("Неділя - вихідний");
                alert.showAndWait();
                DateTime.clear();
            }
        }




    @FXML
    private void initialize() {

        reservetickets_.clear();
        ImageView[] img={imgpremieremovie1,imgpremieremovie2,imgpremieremovie3};
        Label [] premier_name={premiername1,premiername2,premiername3};

        for(int t=0;t<img.length;t++) {
            final int s = t;
            img[s].setOnMouseClicked(mouseEvent ->
            {
                int flag = 0;
                try {
                    if (DateTime.getText().equals("")) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText("Ви не обрали дату");
                        alert.showAndWait();
                    } else {
                        date_validity_check(DateTime.getText());
                        if (HelloController.getConnection == 1) {
                            reserv.checkage(premier_name[s].getText());
                            if (HelloController.age >= HelloController.movieage) {
                                HelloController.age_limit = true;
                            } else {
                                HelloController.age_limit = false;
                            }
                        }
                        FXMLLoader seans_Loader = new FXMLLoader(PostController.class.getResource("HallA.fxml"));
                        try {
                            seans_Loader.load();
                            try {
                                HallAController hallAController1 = seans_Loader.getController();
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
                                Date justtime = simpleDateFormat.parse(DateTime.getText());
                                Date premiertime = simpleDateFormat.parse("25.12.2022");
                                String url = "SELECT*FROM movies where moviename=?";
                                try (PreparedStatement preparedStatement = db_conect.getToBD().prepareStatement(url)) {
                                    if (s == 0) {
                                        preparedStatement.setString(1, premiername1.getText());
                                    }
                                    if (s == 1) {
                                        preparedStatement.setString(1, premiername2.getText());
                                    }
                                    if (s == 2) {
                                        preparedStatement.setString(1, premiername3.getText());
                                    }
                                    ResultSet resultSet = preparedStatement.executeQuery();
                                    while (resultSet.next()) {
                                        Blob blob = resultSet.getBlob("moviepicture");
                                        InputStream inputStream = blob.getBinaryStream();
                                        Image image = new Image(inputStream);
                                        if (justtime.getTime() - premiertime.getTime() >= 0) {

                                            hallAController1.setPremierLabeles(simpleDateFormat.format(justtime), resultSet.getString("moviename"),
                                                    resultSet.getString(" movietime1"), "F", image,
                                                    resultSet.getString(String.valueOf("movieage")), (simpleDateFormat.format(premiertime) + " " + resultSet.getString(" movietime1")),
                                                    false);
                                            reserv.reserve_tickets(resultSet.getString("moviename"), "F", simpleDateFormat.format(justtime), resultSet.getString(" movietime1"));
                                            reserv.buy_tickets(resultSet.getString("moviename"), "F", simpleDateFormat.format(justtime), resultSet.getString(" movietime1"));

                                        }
                                        if (justtime.equals(premiertime) || justtime.before(premiertime)) {
                                            hallAController1.setPremierLabeles(simpleDateFormat.format(premiertime), resultSet.getString("moviename"),
                                                    resultSet.getString(" movietime1"), "F", image,
                                                    resultSet.getString(String.valueOf("movieage")), (simpleDateFormat.format(premiertime) + " " + resultSet.getString(" movietime1")),
                                                    true);
                                            reserv.reserve_tickets(resultSet.getString("moviename"), "F", simpleDateFormat.format(premiertime), resultSet.getString(" movietime1"));
                                            reserv.buy_tickets(resultSet.getString("moviename"), "F", simpleDateFormat.format(premiertime), resultSet.getString(" movietime1"));

                                        }

                                    }
                                }
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }


                        } catch (ParseException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        root = seans_Loader.getRoot();
                        stage = new Stage();
                        stage.setScene(new Scene(root));
                        stage.setTitle("OYE");
                        stage.setResizable(false);
                        stage.initModality(Modality.WINDOW_MODAL);
                        stage.initOwner(((Node) mouseEvent.getSource()).getScene().getWindow());
                        stage.centerOnScreen();
                        stage.showAndWait();

                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Введіть дату правильно");
                    alert.setContentText("Введіть дату у форматі дд.мм.гггг");
                    alert.showAndWait();
                    flag = 1;
                } catch (NumberFormatException e) {
                    if (flag == 0) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText("Введіть дату правильно");
                        alert.setContentText("Дату можна вводити тільки цифрами через крапку");
                        alert.showAndWait();
                    }
                }
            });
        }
        Button[] btn = {movie1attime1, movie1attime2, movie1attime3, movie2attime1, movie2attime2, movie2attime3, movie3attime1,
                movie3attime2, movie3attime3, movie4attime1, movie4attime2, movie4attime3};
        Date now = new Date();
        for (int g = 0; g < btn.length; g++) {
            final int h = g;
            btn[g].setOnAction(event -> {


                int flag = 0;
                try {
                    if (DateTime.getText().equals("")) {
                        Alert alert =new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText("Ви не обрали дату");
                        alert.showAndWait();
                    } else {
                        date_validity_check(DateTime.getText());
                        FXMLLoader seans_Loader = new FXMLLoader(PostController.class.getResource("HallA.fxml"));
                        String dateofmov = DateTime.getText() + " " + btn[h];

                        if (h < 3) {
                            if (HelloController.getConnection == 1) {
                                reserv.checkage(namemovie1.getText());
                                if (HelloController.age >= HelloController.movieage) {
                                    HelloController.age_limit = true;
                                } else {
                                    HelloController.age_limit = false;
                                }
                            }
                            if (h == 0) {
                                reserv.reserve_tickets(namemovie1.getText(), "A", DateTime.getText(), movie1attime1.getText());
                                reserv.buy_tickets(namemovie1.getText(), "A", DateTime.getText(), movie1attime1.getText());
                            } else if (h == 1) {
                                reserv.reserve_tickets(namemovie1.getText(), "A", DateTime.getText(), movie1attime2.getText());
                                reserv.buy_tickets(namemovie1.getText(), "A", DateTime.getText(), movie1attime2.getText());
                            } else if (h == 2) {
                                reserv.reserve_tickets(namemovie1.getText(), "A", DateTime.getText(), movie1attime3.getText());
                                reserv.buy_tickets(namemovie1.getText(), "A", DateTime.getText(), movie1attime3.getText());
                            }
                            try {
                                seans_Loader.load();
                                HallAController hela = seans_Loader.getController();
                                hela.setlabeles(DateTime.getText(), namemovie1.getText(), btn[h].getText(), "A", imgmovie1.getImage(), age1.getText(), dateofmov);

                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        } else if (h > 2 && h < 6) {
                            if (HelloController.getConnection == 1) {
                                reserv.checkage(namemovie2.getText());
                                if (HelloController.age >= HelloController.movieage) {
                                    HelloController.age_limit = true;
                                } else {
                                    HelloController.age_limit = false;
                                }
                            }
                            if (h == 3) {
                                reserv.reserve_tickets(namemovie2.getText(), "B", DateTime.getText(), movie2attime1.getText());
                                reserv.buy_tickets(namemovie2.getText(), "B", DateTime.getText(), movie2attime1.getText());
                            } else if (h == 4) {
                                reserv.reserve_tickets(namemovie2.getText(), "B", DateTime.getText(), movie2attime2.getText());
                                reserv.buy_tickets(namemovie2.getText(), "B", DateTime.getText(), movie2attime2.getText());
                            } else if (h == 5) {
                                reserv.reserve_tickets(namemovie2.getText(), "B", DateTime.getText(), movie2attime3.getText());
                                reserv.buy_tickets(namemovie2.getText(), "B", DateTime.getText(), movie2attime3.getText());
                            }
                            try {
                                seans_Loader.load();
                                HallAController hela = seans_Loader.getController();
                                hela.setlabeles(DateTime.getText(), namemovie2.getText(), btn[h].getText(), "B", imgmovie2.getImage(), age2.getText(), dateofmov);
                                //
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        } else if (h > 5 && h < 9) {
                            if (HelloController.getConnection == 1) {
                                reserv.checkage(namemovie3.getText());
                                if (HelloController.age >= HelloController.movieage) {
                                    HelloController.age_limit = true;
                                } else {
                                    HelloController.age_limit = false;
                                }
                            }
                            if (h == 6) {
                                reserv.reserve_tickets(namemovie3.getText(), "C", DateTime.getText(), movie3attime1.getText());
                                reserv.buy_tickets(namemovie3.getText(), "C", DateTime.getText(), movie3attime1.getText());
                            } else if (h == 7) {
                                reserv.reserve_tickets(namemovie3.getText(), "C", DateTime.getText(), movie3attime2.getText());
                                reserv.buy_tickets(namemovie3.getText(), "C", DateTime.getText(), movie3attime2.getText());
                            } else if (h == 8) {
                                reserv.reserve_tickets(namemovie3.getText(), "C", DateTime.getText(), movie3attime3.getText());
                                reserv.buy_tickets(namemovie3.getText(), "C", DateTime.getText(), movie3attime3.getText());
                            }
                            try {
                                seans_Loader.load();
                                HallAController hela = seans_Loader.getController();
                                hela.setlabeles(DateTime.getText(), namemovie3.getText(), btn[h].getText(), "C", imgmovie3.getImage(), age3.getText(), dateofmov);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        } else if (h > 8) {
                            if (HelloController.getConnection == 1) {
                                reserv.checkage(namemovie4.getText());
                                if (HelloController.age >= HelloController.movieage) {
                                    HelloController.age_limit = true;
                                } else {
                                    HelloController.age_limit = false;
                                }
                            }
                            if (h == 9) {
                                reserv.reserve_tickets(namemovie4.getText(), "D", DateTime.getText(), movie4attime1.getText());
                                reserv.buy_tickets(namemovie4.getText(), "D", DateTime.getText(), movie4attime1.getText());
                            } else if (h == 10) {
                                reserv.reserve_tickets(namemovie4.getText(), "D", DateTime.getText(), movie4attime2.getText());
                                reserv.buy_tickets(namemovie4.getText(), "D", DateTime.getText(), movie4attime2.getText());
                            } else if (h == 11) {
                                reserv.reserve_tickets(namemovie4.getText(), "D", DateTime.getText(), movie4attime3.getText());
                                reserv.buy_tickets(namemovie4.getText(), "D", DateTime.getText(), movie4attime3.getText());
                            }
                            try {
                                seans_Loader.load();
                                HallAController hela = seans_Loader.getController();
                                hela.setlabeles(DateTime.getText(), namemovie4.getText(), btn[h].getText(), "D", imgmovie4.getImage(), age4.getText(), dateofmov);
                                //
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }


                        root = seans_Loader.getRoot();
                        stage = new Stage();
                        stage.setScene(new Scene(root));
                        stage.setTitle("OYE");
                        stage.setResizable(false);
                        stage.initModality(Modality.WINDOW_MODAL);
                        stage.initOwner(((Node) event.getSource()).getScene().getWindow());
                        stage.centerOnScreen();
                        stage.showAndWait();
                    }
                    } catch(ArrayIndexOutOfBoundsException e){
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText("Введіть дату правильно");
                        alert.setContentText("Введіть дату у форматі дд.мм.гггг");
                        alert.showAndWait();
                        flag = 1;
                    }
                catch(NumberFormatException e )
                    {
                        if (flag == 0) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setHeaderText("Введіть дату правильно");
                            alert.setContentText("Дату можна вводити тільки цифрами через крапку");
                            alert.showAndWait();
                        }
                    }


                });

        }
        DateTime.setStyle("-fx-prompt-text-fill: black");
        DateTime.setStyle("-fx-background-color:  #FF00FF");
        //добавление начинки в афишу
        premierlabel.setText("Прем'єри");
        ImageView[] a = {imgmovie1, imgmovie2, imgmovie3, imgmovie4};
        Label[] b = {namemovie1, namemovie2, namemovie3, namemovie4};
        Label[] c = {descriptionmovie1, descriptionmovie2, descriptionmovie3, descriptionmovie4};
        Label[] d = {age1, age2, age3, age4};
        Button[] e1 = {movie1attime1, movie2attime1, movie3attime1, movie4attime1};
        Button[] e2 = {movie1attime2, movie2attime2, movie3attime2, movie4attime2};
        Button[] e3 = {movie1attime3, movie2attime3, movie3attime3, movie4attime3};
        Label[] d1 = {age_premier_1, age_premier_2, age_premier_3};
        Label[] b1 = {premiername1, premiername2, premiername3};
        ImageView[] a1 = {imgpremieremovie1, imgpremieremovie2, imgpremieremovie3};

        for (int i = 0; i <= 3; i++) {
            int k = i + 1;
            db_conect.getToBD();
            String urlk = "SELECT*FROM movies WHERE idmovies=?";
            try (PreparedStatement post_statementk = db_conect.connection.prepareStatement(urlk)) {
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
                db_conect.getToBD();
                String urlf = "SELECT*FROM movies WHERE idmovies=?";
                try (PreparedStatement preparedStatement_2 = db_conect.connection.prepareStatement(urlf)) {
                    Blob blobf;
                    preparedStatement_2.setInt(1, f);
                    ResultSet resultSetf = preparedStatement_2.executeQuery();
                    if (resultSetf.next()) {
                        blobf = resultSetf.getBlob(2);
                        InputStream inputStreamf = blobf.getBinaryStream();
                        Image image = new Image(inputStreamf);
                        a1[m].setImage(image);
                        String premieragef = resultSetf.getString(8);
                        d1[m].setText(premieragef + "+");
                        b1[m].setText(resultSetf.getString("moviename"));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}


