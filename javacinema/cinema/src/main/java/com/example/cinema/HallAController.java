package com.example.cinema;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;


public class HallAController {
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

    Button [] a={btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn10,
            btn11,btn12,btn13,btn14,btn15,btn16,btn17,btn18,btn19,btn20,
    btn21,btn22,btn23,btn24,btn25,btn26,btn27,btn28,btn29,btn30};
    public void getBuyBox(ActionEvent event)
    {
        /* for(int i=0;i<30;i++)
        {
            int f=i;
            int k=i+1;
            a.get(a[i]).setOnAction(event1 -> {
                ((Button).getSource()).setStyle("-fx-background-color: blue");
            });
        }*/
        buybox.setVisible(true);


    }
    public void reserve()
    {

        for (int i=0;i<30;i++)
        {
            int k=i+1;

        }
    }



}
