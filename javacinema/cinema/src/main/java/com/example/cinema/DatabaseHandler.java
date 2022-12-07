package com.example.cinema;
import com.mysql.cj.jdbc.Driver;

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
    /*public void setMovie()
    {
        String insertmovie="INSERT INTO movies(movipicture,moviename,moviedescription,movietime,moviage) VALUES(?,?,?,?,?);";
        try(PreparedStatement movie_statement= getToBD().prepareStatement(insertmovie))
        {

            movie_statement.setBlob();
        }
    }
    /*public  ResultSet getUser(User user)
    {

        String select="SELECT * FROM users WHERE email=? AND password =?;";
        try(PreparedStatement prepared_Statement= getToBD().prepareStatement(select))
        {
            prepared_Statement.setString(1,user.getEmail());
            prepared_Statement.setString(2,user.getPassword());
            resultSet=prepared_Statement.executeQuery();
            int i = resultSet;
            System.out.println(resultSet);



        }
        catch(SQLException e )
        {
            throw new RuntimeException();
        }
        return resultSet;
    }*/
}
