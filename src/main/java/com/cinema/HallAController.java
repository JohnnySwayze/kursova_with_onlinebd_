package com.cinema;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class HallAController {
    DatabaseHandler getdb=new DatabaseHandler();
    @FXML
    private Button btn1;

    @FXML
    private Button btn2;

    @FXML
    private Button btn3;

    @FXML
    private Button btn4;

    @FXML
    private Button btn5;

    @FXML
    private Button btn6;

    @FXML
    private Button btn7;

    @FXML
    private Button btn8;

    @FXML
    private Button btn9;

    @FXML
    private Button btn10;

    @FXML
    private Button btn11;

    @FXML
    private Button btn12;

    @FXML
    private Button btn13;

    @FXML
    private Button btn14;

    @FXML
    private Button btn15;

    @FXML
    private Button btn16;

    @FXML
    private Button btn17;

    @FXML
    private Button btn18;

    @FXML
    private Button btn19;

    @FXML
    private Button btn20;

    @FXML
    private Button btn21;

    @FXML
    private Button btn22;

    @FXML
    private Button btn23;

    @FXML
    private Button btn24;

    @FXML
    private Button btn25;

    @FXML
    private Button btn26;

    @FXML
    private Button btn27;

    @FXML
    private Button btn28;

    @FXML
    private Button btn29;

    @FXML
    private Button btn30;
    @FXML
    private HBox buybox;
    @FXML
    private AnchorPane seanspane;
    @FXML
    private Label datamov;

    @FXML
    private Label nameofmovie;

    @FXML
    private Label timeofmovie;
    @FXML
    private ImageView imgmovie;
    @FXML
    private Label hallname;
    @FXML
    private ImageView screen;
    @FXML
    private Label movie_age;
    @FXML
    private Button buybtn;
    @FXML
    private Button reservebtn;
    @FXML
    private Label amountticketslabel;
    @FXML
    private Label ticketcost;
    private String date_time;
    private  int  ticketprice=0;

    private final int c=HelloController.id_user;
    private  ArrayList<Integer> reservetickets = PostController.reservetickets_;
    private ArrayList<Integer> boughttickets= PostController.boughttickets_;
    private final ArrayList<Button> list=new ArrayList<Button>();
    private final ArrayList<Integer> bluebtn=new ArrayList<>();
    DatabaseHandler handler =new DatabaseHandler();
    private  boolean is_regular_session=false;
    private boolean is_premier_session=false;
    int amount=0;
     public void choicebtn() {
        for (int f=0;f<30;f++)
        {

            final int j=f;
            list.get(j).setOnMouseClicked(e-> {
                if (is_premier_session == true )
                {
                    ticketprice=300;


                }
                if(list.get(j).getStyle()=="-fx-background-color: green") {
                    list.get(j).setStyle("-fx-background-color: blue");
                    amount=amount+1;

                    if (amount==0)
                    {
                        amountticketslabel.setVisible(false);
                    }
                    else if (amount==10)
                    {
                        amountticketslabel.setVisible(true);
                        amountticketslabel.setText("Квитків обрано: "+amount+";"+"    До сплати:  "+(amount*ticketprice*0.9)+"грн;");
                        Alert alert=new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText("Ви обрали "+amount+" тому ви отримуєте знижку у вигляді 10%");
                        alert.showAndWait();
                    }
                    else if (amount >=10)
                    {
                        amountticketslabel.setVisible(true);
                        amountticketslabel.setText("Квитків обрано: "+amount+";"+"    До сплати:  "+(amount*ticketprice*0.9)+"грн;");
                    }
                    else
                    {
                        amountticketslabel.setVisible(true);
                        amountticketslabel.setText("Квитків обрано: "+amount+";"+"    До сплати:  "+(amount*ticketprice)+"грн;");
                    }
                }
                else if (list.get(j).getStyle()=="-fx-background-color: blue")
                {
                    list.get(j).setStyle("-fx-background-color: green");
                    amount=amount-1;

                    if (amount==0)
                    {
                        amountticketslabel.setVisible(false);
                    }
                    else if (amount==10)
                    {
                        amountticketslabel.setVisible(true);
                        amountticketslabel.setText("Квитків обрано: "+amount+";"+"    До сплати:  "+(amount*ticketprice*0.9)+"грн;");
                        Alert alert=new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText("Ви обрали "+amount+" тому ви отримуєте знижку у вигляді 10%");
                        alert.showAndWait();

                    }
                    else if (amount >=10)
                    {
                        amountticketslabel.setVisible(true);
                        amountticketslabel.setText("Квитків обрано: "+amount+";"+"    До сплати:  "+(amount*ticketprice*0.9)+"грн;");
                    }
                    else {//if (amount <=10){
                        amountticketslabel.setVisible(true);
                        amountticketslabel.setText("Квитків обрано: "+amount+";"+"    До сплати:  "+(amount*ticketprice)+"грн;");

                    }

                }
            });

        }
     }
     public void setPremierLabeles(String date_of_movie,String name_of_movie,String time_of_movie,String hall_name,
                                   Image image_movie,String movie_age_limit,String today_date_time,Boolean is_premier)
     {
         is_premier_session=is_premier;
         String text=null;
         if(is_premier_session) {
             ticketprice = 300;
             text="День прем'єри , тому квиток коштує: ";
         }
         if(!is_premier_session)
         {
             text="Квиток коштує: ";
             ticketprice=150;
         }
         ticketcost.setText(text+ticketprice+"грн");
         datamov.setText(date_of_movie);
         nameofmovie.setText(name_of_movie);
         timeofmovie.setText(time_of_movie);
         hallname.setText(hall_name);
         imgmovie.setImage(image_movie);
         movie_age.setText(movie_age_limit+"+");
         date_time=today_date_time;
         SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd.MM.yyyy HH:mm");
         Date now = new Date();
         if (HelloController.getConnection == 1 && HelloController.age_limit )
         {
             buybox.setVisible(true);
             try {

                 if (now.getTime()-simpleDateFormat.parse(date_time).getTime()>=-1800000)
                 {
                     reservebtn.setVisible(false);
                     int i=0 ;
                     if (now.getTime()-simpleDateFormat.parse(date_time).getTime()>=1800000)
                     {
                         reservebtn.setVisible(false);
                         buybtn.setVisible(false);
                         i=1;
                     }
                     if (i==1)
                     {
                         Alert alert=new Alert(Alert.AlertType.WARNING);
                         alert.setContentText("Повернутися до перегляду");
                         alert.setHeaderText("Ви не зможете купити,або забронювати квиток , бо прошло більше 30 хв ");
                         alert.showAndWait();
                     }
                     if (i==0)
                     {
                         Alert alert=new Alert(Alert.AlertType.WARNING);
                         alert.setContentText("Повернутися до перегляду");
                         alert.setHeaderText(" Ви не зможете забронювати квитки");
                         alert.showAndWait();}
                 }
             }
             catch (ParseException e)
             {
                 e.printStackTrace();
             }
         }
         else if(HelloController.getConnection ==0)
         {
             Alert alert = new Alert(Alert.AlertType.WARNING);
             alert.setTitle("Error");
             alert.setHeaderText("Щоб купити квиток треба авторизуватися або зареєструватися");
             alert.setContentText("Повернутися до перегляду");
             alert.showAndWait();
         }
         else if(!HelloController.age_limit)
         {
             Alert alert = new Alert(Alert.AlertType.WARNING);
             alert.setTitle("Error");
             alert.setHeaderText("Ви не зможете купити квитки на цей фільм,та як ваш вік менше ніж вікове обмеження");
             alert.setContentText("Повернутися до перегляду");
             alert.showAndWait();
         }

     }

     public void setlabeles(String data, String namemov, String time, String halla, Image image,String mov_age,String date)
     {
         is_regular_session=true;
         ticketprice=150;
         ticketcost.setText("Квиток коштує: "+ticketprice+"грн");
         datamov.setText(data);
         nameofmovie.setText(namemov);
         timeofmovie.setText(time);
         hallname.setText(halla);
         imgmovie.setImage(image);
         movie_age.setText(mov_age);
         date_time=date.substring(0,10)+" "+date.substring(55,60);//время с кнопки и текстового поля даты;то есть время сеанса;
         SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd.MM.yyyy HH:mm");
         Date now = new Date();
         if (HelloController.getConnection == 1 && HelloController.age_limit )
         {
             buybox.setVisible(true);
             try {

                if (now.getTime()-simpleDateFormat.parse(date_time).getTime()>=-1800000)
                {
                    reservebtn.setVisible(false);
                    int i=0 ;
                    if (now.getTime()-simpleDateFormat.parse(date_time).getTime()>=1800000)
                    {
                        reservebtn.setVisible(false);
                        buybtn.setVisible(false);
                        i=1;
                    }
                    if (i==1)
                    {
                        Alert alert=new Alert(Alert.AlertType.WARNING);
                        alert.setContentText("Повернутися до перегляду");
                        alert.setHeaderText("Ви не зможете купити,або забронювати квиток , бо прошло більше 30 хв з початку сеансу ");
                        alert.showAndWait();
                    }
                    if (i==0)
                    {
                        Alert alert=new Alert(Alert.AlertType.WARNING);
                        alert.setContentText("Повернутися до перегляду");
                        alert.setHeaderText("Ви не зможете забронювати квитки");
                        alert.showAndWait();}
                    }
                }
             catch (ParseException e)
             {
                 e.printStackTrace();
             }
         }
         else if(HelloController.getConnection ==0)
         {
             Alert alert = new Alert(Alert.AlertType.WARNING);
             alert.setTitle("Error");
             alert.setHeaderText("Щоб купити квиток треба авторизуватися або зареєструватися");
             alert.setContentText("Повернутися до перегляду");
             alert.showAndWait();
         }
         else if(!HelloController.age_limit)
         {
             Alert alert = new Alert(Alert.AlertType.WARNING);
             alert.setTitle("Error");
             alert.setHeaderText("Ви не зможете купити квитки на цей фільм,та як ваш вік менше ніж вікове обмеження");
             alert.setContentText("Повернутися до перегляду");
             alert.showAndWait();
         }

     }
    public void reserve(ActionEvent event)
     {
         amount=0;
         for (int i =0;i<30;i++)
         {
             final  int k=i;
             if (list.get(k).getStyle() == "-fx-background-color: blue")
             {
                 bluebtn.add(k);
             }
         }
         //запись в бд забронированных билетов
         for (int m =0 ;m<bluebtn.size();m++)
         {
             int f= bluebtn.get(m);
             getdb.getToBD();
             String url1="INSERT INTO tickets (iduser,nameofmovie,hallname,dateofmovie,seanstime,numberofplace,conditionofplace) VALUES(?,?,?,?,?,?,?);";
             try(PreparedStatement pr_st=getdb.connection.prepareStatement(url1))
             {
                 pr_st.setInt(1,c);
                 pr_st.setString(2,nameofmovie.getText());
                 pr_st.setString(3,hallname.getText());
                 pr_st.setString(4,datamov.getText());
                 pr_st.setString(5,timeofmovie.getText());
                 pr_st.setInt(6,f);
                 pr_st.setInt(7,1);
                 pr_st.executeUpdate();
             }
             catch(SQLException e)
             {
                 e.printStackTrace();
             }

             list.get(f).setStyle("-fx-background-color: grey");
         }
         bluebtn.clear();
     }
    public void buyticket(ActionEvent event)
    {
        amount=0;
        for (int i =0;i<30;i++)
        {
        final  int k=i;

            if (list.get(k).getStyle() =="-fx-background-color: blue")
            {
                bluebtn.add(k);
            }
            for (int m =0 ;m<bluebtn.size();m++)
            {
                int f= bluebtn.get(m);
                getdb.getToBD();
                String url1="INSERT INTO tickets (iduser,nameofmovie,hallname,dateofmovie,seanstime,numberofplace,conditionofplace) VALUES(?,?,?,?,?,?,?);";
                try(PreparedStatement pr_st=getdb.connection.prepareStatement(url1))
                {
                    pr_st.setInt(1,c);
                    pr_st.setString(2,nameofmovie.getText());
                    pr_st.setString(3,hallname.getText());
                    pr_st.setString(4,datamov.getText());
                    pr_st.setString(5,timeofmovie.getText());
                    pr_st.setInt(6,f);
                    pr_st.setInt(7,2);
                    pr_st.executeUpdate();
                }
                catch(SQLException e)
                {
                    e.printStackTrace();
                }
                list.get(f).setStyle("-fx-background-color: red");
            }
            bluebtn.clear();
        }

    }
    @FXML
    void initialize() {
         try
         {
             Image image =new Image(new FileInputStream("C:/Users/USER/IdeaProjects/kursova/src/main/resources/assets/img_1.png"));
             screen.setImage(image);
         }
         catch (FileNotFoundException e)
         {
             throw new RuntimeException(e);
         }
        Button b[] = {btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10,
                btn11, btn12, btn13, btn14, btn15, btn16, btn17, btn18, btn19, btn20,
                btn21, btn22, btn23, btn24, btn25, btn26, btn27, btn28, btn29, btn30};
        for (int i = 0; i < 30; i++)
        {
            list.add(b[i]);
            final int f = i;
            list.get(f).setStyle("-fx-background-color: green");
        }
        if (boughttickets.size()!=0)
        {
            for(int h=0;h<boughttickets.size();h++)
            {
                int s=boughttickets.get(h);
                list.get(s).setStyle("-fx-background-color: red");
            }
            boughttickets.clear();
            PostController.boughttickets_.clear();
        }
        if (reservetickets.size() != 0) {
            for (int y = 0; y < reservetickets.size(); y++)
            {
                int e = reservetickets.get(y);
                list.get(e).setStyle("-fx-background-color: grey");
            }
            reservetickets.clear();
            PostController.reservetickets_.clear();
        }
    }
}





