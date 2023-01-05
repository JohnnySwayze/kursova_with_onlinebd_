package com.cinema;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class HelloController {
    private Stage stage ;
    private Scene scene;
    private Parent root;


    public static int getConnection=0;
    public static int id_user;
    public static String email_user;
    public static int age;
    public static int movieage;
    public static boolean age_limit;

    public static int admin ;
    DatabaseHandler databaseHandler=new DatabaseHandler();
    public void SwitchToPost(ActionEvent event) {
        try {
            root = FXMLLoader.load(PersonController.class.getResource("post.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        }
        catch (IOException e)
        {e.printStackTrace();}
    }
    @FXML
    public void initialize() {
        ArrayList<String> tickettime1 = new ArrayList<>();
        ArrayList<String> tickettime2 = new ArrayList<>();

        Date now = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        String nowdatewithhour = simpleDateFormat1.format(now);
        String nowdateatmidnight = simpleDateFormat.format(now);
        String url = "SELECT*FROM tickets";
        try (PreparedStatement preparedStatement = databaseHandler.getToBD().prepareStatement(url)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String ticketdate = resultSet.getString("dateofmovie");
                String tickethour = resultSet.getString("seanstime");
                tickettime1.add(ticketdate);
                tickettime2.add(ticketdate + " " + tickethour);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        for (int i = 0; i < tickettime1.size(); i++) {
            try {
                Date dateatmidnight = simpleDateFormat.parse(nowdateatmidnight);
                Date ticketdateatmidnight = simpleDateFormat.parse(tickettime1.get(i));
                if (ticketdateatmidnight.before(dateatmidnight)) {
                    String deleteurl = "DELETE FROM tickets WHERE dateofmovie=?";
                    try (PreparedStatement preparedStatement = databaseHandler.getToBD().prepareStatement(deleteurl)) {

                        preparedStatement.setString(1, tickettime1.get(i));
                        preparedStatement.execute();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    tickettime1.remove(i);
                    tickettime2.remove(i);

                }
            }
            catch (ParseException e)
            {e.printStackTrace();}
        }


            String url2 = "SELECT*FROM tickets WHERE dateofmovie=?";
            try (PreparedStatement preparedStatement = databaseHandler.getToBD().prepareStatement(url2)) {
                preparedStatement.setString(1, nowdateatmidnight);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    int conf = resultSet.getInt("conditionofplace");
                    String seanshour=resultSet.getString("seanstime");

                    try {
                        Date ticketsdate = simpleDateFormat1.parse(nowdateatmidnight+" "+seanshour);
                        Date todaydatewithhour=simpleDateFormat1.parse(nowdatewithhour);
                        if (conf == 1) {
                            if (todaydatewithhour.getTime()-ticketsdate.getTime()>=-1800000)
                            {

                                System.out.println("Бронь снята , ибо осталось меньше получаса до начала сеанса или фильм уже прошёл");
                                String deleteurl2="DELETE FROM tickets WHERE dateofmovie=? and seanstime=? and conditionofplace=?;";
                                try(PreparedStatement pr_st=databaseHandler.getToBD().prepareStatement(deleteurl2))
                                {
                                    pr_st.setString(1,nowdateatmidnight);
                                    pr_st.setString(2,seanshour);
                                    pr_st.setInt(3,conf);
                                    pr_st.execute();
                                }
                                catch(SQLException e)
                                {
                                    e.printStackTrace();
                                }
                            }
                        }
                        else {
                            if (todaydatewithhour.getTime()-ticketsdate.getTime()>=4*1800000) {
                                System.out.println("Купленый билет удалён , потому что прошло два часа после начала сеанса");
                                String deleteurl2="DELETE FROM tickets WHERE dateofmovie=? and seanstime=? and conditionofplace=?;";
                                try(PreparedStatement pr_st=databaseHandler.getToBD().prepareStatement(deleteurl2))
                                {
                                    pr_st.setString(1,nowdateatmidnight);
                                    pr_st.setString(2,seanshour);
                                    pr_st.setInt(3,conf);
                                    pr_st.execute();
                                }
                                catch(SQLException e)
                                {
                                    e.printStackTrace();
                                }
                            }
                        }
                    } catch (ParseException event) {
                        event.printStackTrace();
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }



                }


    }
