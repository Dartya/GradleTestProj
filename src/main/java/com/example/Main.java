package com.example;

import com.example.Controllers.SampleController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

public class Main extends Application {
    private static final String FXML_MAIN = "/Sample.fxml";
    private Stage primaryStage;             //сцена главного окна
    private FXMLLoader fxmlLoader;          //загрузчик файлов FXML
    private AnchorPane currentRoot;         //коренной Node (Parent)

    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        createGUI();
    }

    private void createGUI() {
        currentRoot = loadFXML(); //возвращает сконфигурированный Node (Parent)
        Scene scene = new Scene(currentRoot, 550, 500);
        primaryStage.setScene(scene);
        primaryStage.setMinHeight(500);
        primaryStage.setMinWidth(550);
        primaryStage.show();
    }

    private AnchorPane loadFXML() {   //возвращает сконфигурированный Node (Parent)

        fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource(FXML_MAIN));
        AnchorPane node = null;

        try {
            node = (AnchorPane) fxmlLoader.load();
            primaryStage.setTitle("JCalendarFX v0.1");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return node;
    }

    public static void main(String[] args) {

        try{
            launch(args);
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.println(e.toString());
        }
    }
}
