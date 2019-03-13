package com.example;

import com.example.Controllers.SampleController;
import com.sun.javafx.scene.control.skin.DatePickerSkin;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

public class Main extends Application {
    private static final String FXML_MAIN = "/Sample.fxml";
    private Stage primaryStage;             //сцена главного окна
    private SampleController mainController;//контроллер сцены главного окна
    private FXMLLoader fxmlLoader;          //загрузчик файлов FXML
    private AnchorPane currentRoot;           //коренной Node (Parent)

    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        createGUI();
        /*DatePicker datePicker = new DatePicker(LocalDate.now());
        try {
            BorderPane root = new BorderPane();
            Scene scene = new Scene(root, 400, 400);

            DatePickerSkin datePickerSkin = new DatePickerSkin(datePicker);
            Node popupContent = datePickerSkin.getPopupContent();

            root.setCenter(popupContent);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        datePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("New Value: " + newValue);
        });*/
    }

    private void createGUI() {
        currentRoot = loadFXML(); //возвращает сконфигурированный Node (Parent)
        Scene scene = new Scene(currentRoot, 350, 350);
        primaryStage.setScene(scene);
        primaryStage.setMinHeight(350);
        primaryStage.setMinWidth(350);
        primaryStage.show();
    }

    private AnchorPane loadFXML() {   //возвращает сконфигурированный Node (Parent)
        fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource(FXML_MAIN));
        AnchorPane node = null;

        try {
            node = (AnchorPane) fxmlLoader.load();
            mainController = fxmlLoader.getController();
            primaryStage.setTitle("JCalendarFX v0.1");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return node;
    }

    public static void main(String[] args) {
        //launch(args);
        try{
            launch(args);
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.println(e.toString());
            /*try{
                PrintWriter pw = new PrintWriter(new File("<somefilename.txt>"));
                e.printStackTrace(pw);
                pw.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }*/
        }
    }
}
