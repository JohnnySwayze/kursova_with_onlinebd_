package com.example.cinema;

import com.mysql.cj.jdbc.Driver;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class AuthorizationController {
    @FXML
    private TextField login_field;

    @FXML
    private PasswordField password_field;
    private Stage stage;
    private Scene scene;
    private Parent root;

    private int c;
    private String btnText="Повернутися до авторизації";
    public void SwitchToRegistration(ActionEvent event) throws IOException {
        root = FXMLLoader.load(PostMenuController.class.getResource("registration.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void SwitchToAfisha(ActionEvent event) throws IOException {
        root = FXMLLoader.load(PostMenuController.class.getResource("post.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void AuthorizationBtn (ActionEvent event) throws IOException {
        String emailText = login_field.getText().trim();
        String loginPassword = password_field.getText().trim();
        if (!emailText.equals("") && !loginPassword.equals("")) {
            loginUser(emailText, loginPassword);
            if (c == 1)
            {
                root = FXMLLoader.load(PostMenuController.class.getResource("person.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            }
            else
            {
                FXMLLoader error_Loader = new FXMLLoader(PostController.class.getResource("errorwindow.fxml"));
                try {
                    error_Loader.load();
                    ErrorWindowController errorWindowController=error_Loader.getController();
                    errorWindowController.errorRegistration("Користувача з такими данними не існує",btnText);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                root  = error_Loader.getRoot();
                stage= new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Error");
                stage.setResizable(false);
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(((Node)event.getSource()).getScene().getWindow());
                stage.showAndWait();
            }
        }
        else
        {
            FXMLLoader error_Loader = new FXMLLoader(PostController.class.getResource("errorwindow.fxml"));
            try {
                error_Loader.load();
                ErrorWindowController errorWindowController=error_Loader.getController();
                errorWindowController.errorRegistration("Поле 'Email' та 'Пароль' порожні",btnText);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            root  = error_Loader.getRoot();
            stage= new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Error");
            stage.setResizable(false);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node)event.getSource()).getScene().getWindow());
            stage.showAndWait();
        }
    }
    Connection connection;
    private void loginUser(String emailText,String loginPassword) {
        try
        {
            Driver driver=new Driver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Cinema","root","12345");
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        String url1="SELECT * FROM users WHERE email=? and password = ? ;";
        try(PreparedStatement preparedStatement=connection.prepareStatement(url1))
        {
            preparedStatement.setString(1,emailText);
            preparedStatement.setString(2,loginPassword);
            ResultSet resultSet=preparedStatement.executeQuery();
            int count=0;
            while(resultSet.next())
            {
                count++;
                if (count>=1)
                {
                    c=1;
                    System.out.println("Success");
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }




    }





}
