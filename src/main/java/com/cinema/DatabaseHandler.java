package com.cinema;
import com.mysql.cj.jdbc.Driver;

import java.io.*;
import java.sql.*;




public class DatabaseHandler extends Configs {

    public static int  i;
    ResultSet resultSet;
    Connection connection;

    public Connection getToBD()
    {

        try
        {
            Driver driver=new Driver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(URL,dbUser,dbPass);
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return connection;

    }
    public void change_movie(int action ,String url1 ,int id,String name , String picture_path,
                             String description,String time1seans,String time2seans,String time3seans ,String age_limit)
    {

        String url="UPDATE movies SET "+url1+" WHERE idmovies="+id;
        System.out.println(url);
        try(PreparedStatement preparedStatement=getToBD().prepareStatement(url))
        {
            /*InputStream is = new FileInputStream(new File(picture_path));
            preparedStatement.setBlob(1,is);
            preparedStatement.setString(2,name);
            preparedStatement.setString(3,description);
            preparedStatement.setString(4,time1seans);
            preparedStatement.setString(5,time2seans);
            preparedStatement.setString(6,time3seans);
            preparedStatement.setString(7,age_limit);*/
            if (action == 1)
            {
                preparedStatement.setString(1,time1seans);
            }
            if(action == 2)
            {
                preparedStatement.setString(1,time2seans);
            }
            if(action == 3)
            {
                preparedStatement.setString(1,time3seans);
            }
            if(action == 11)
            {
                FileInputStream fileInputStream=new FileInputStream(new File(picture_path));
                preparedStatement.setBinaryStream(1,fileInputStream);
                preparedStatement.setString(2,name);
                preparedStatement.setString(3,description);
                preparedStatement.setString(4,time1seans);
                preparedStatement.setInt(5,Integer.parseInt(age_limit));
            }
            if(action == 12)
            {
                FileInputStream fileInputStream=new FileInputStream(new File(picture_path));
                preparedStatement.setBinaryStream(1,fileInputStream);
                preparedStatement.setString(2,name);
                preparedStatement.setString(3,description);
                preparedStatement.setString(4,time2seans);
                preparedStatement.setInt(5,Integer.parseInt(age_limit));
            }
            if(action == 13)
            {
                FileInputStream fileInputStream=new FileInputStream(new File(picture_path));
                preparedStatement.setBinaryStream(1,fileInputStream);
                preparedStatement.setString(2,name);
                preparedStatement.setString(3,description);
                preparedStatement.setString(4,time3seans);
                preparedStatement.setInt(5,Integer.parseInt(age_limit));
            }
            if (action == 4)
            {
                FileInputStream fileInputStream=new FileInputStream(new File(picture_path));
                preparedStatement.setBinaryStream(1,fileInputStream);
                preparedStatement.setString(2,name);
                preparedStatement.setString(3,description);
                preparedStatement.setInt(4,Integer.parseInt(age_limit));
            }
            if(action == 8)
            {
                preparedStatement.setString(1,time1seans);
            }
            if (action == 9)
            {
                System.out.println(picture_path);
                FileInputStream fileInputStream=new FileInputStream(new File(picture_path));
                preparedStatement.setBinaryStream(1,fileInputStream);
                preparedStatement.setString(2,name);
                preparedStatement.setString(3,description);
                preparedStatement.setString(4,time1seans);
                preparedStatement.setInt(5,Integer.parseInt(age_limit));
            }
            preparedStatement.executeUpdate();

        }
        catch(SQLException  | FileNotFoundException e )
        {
            e.printStackTrace();
        }
    }
    public String movie_title(int num)
    {
        String title=null;
        String reserve="SELECT*FROM movies WHERE idmovies = ?";
        try(PreparedStatement preparedStatement=getToBD().prepareStatement(reserve))
        {
            preparedStatement.setInt(1,num);
            ResultSet resultSet1=preparedStatement.executeQuery();
            while(resultSet1.next())
            {
                title=resultSet1.getString("moviename");
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return title;
    }
    public void signUpUser(String firstname ,String lastname,Integer age,String email ,String password) {
        String insert = "INSERT INTO users(firstname,lastname,age,email,password) VALUES (?,?,?,?,?);";

        try (PreparedStatement statement = getToBD().prepareStatement(insert)) {
            statement.setString(1,firstname);
            statement.setString(2,lastname);
            statement.setInt(3,age);
            statement.setString(4,email);
            statement.setString(5,password);
            statement.executeUpdate();
            i=0;
        }
        catch (SQLIntegrityConstraintViolationException e)
        {
            i=1;
        }
        catch (SQLException e)
        {

            e.printStackTrace();
        }

    }

    public void reserve_tickets(String namemovie,String nameofhall,String datetime,String movietime)
    {

        String urlreserve = "SELECT*FROM tickets WHERE nameofmovie=? and hallname=? and dateofmovie=? and seanstime=? and conditionofplace=?;";
        try (PreparedStatement prst_hall = getToBD().prepareStatement(urlreserve)) {
            prst_hall.setString(1, namemovie);
            prst_hall.setString(2, nameofhall);
            prst_hall.setString(3, datetime);
            prst_hall.setString(4, movietime);
            prst_hall.setInt(5, 1);
            ResultSet resultSet = prst_hall.executeQuery();
            while (resultSet.next()) {
                int con = resultSet.getInt(7);
                PostController.reservetickets_.add(con);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void buy_tickets(String namemovie1,String nameofhall1,String datetime1,String movietime1)
    {

        String urlreserve1 = "SELECT*FROM tickets WHERE nameofmovie=? and hallname=? and dateofmovie=? and seanstime=? and conditionofplace=?;";
        try (PreparedStatement prst_hall = getToBD().prepareStatement(urlreserve1)) {
            prst_hall.setString(1, namemovie1);
            prst_hall.setString(2, nameofhall1);
            prst_hall.setString(3, datetime1);
            prst_hall.setString(4, movietime1);
            prst_hall.setInt(5, 2);
            ResultSet resultSet = prst_hall.executeQuery();
            while (resultSet.next()) {
                int con = resultSet.getInt(7);
                PostController.boughttickets_.add(con);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
   public void checkage(String moviename)
    {

        String url="SELECT*FROM movies WHERE moviename=?;";
        try(PreparedStatement preparedStatement= getToBD().prepareStatement(url))
        {
            preparedStatement.setString(1,moviename);
            ResultSet resultSet=preparedStatement.executeQuery();
            while(resultSet.next())
            {
                int  age_of_movie=resultSet.getInt("movieage");
                HelloController.movieage=age_of_movie;
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
}
