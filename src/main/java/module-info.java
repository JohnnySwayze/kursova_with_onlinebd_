module com.example.demo1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires mysql.connector.j;
    requires java.sql;
    requires java.desktop;


    opens com.cinema to javafx.fxml;
    exports com.cinema;
}