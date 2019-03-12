package com.example;

import com.example.Controllers.SampleController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class Main extends Application {
    private static final String FXML_MAIN = "/Sample.fxml";
    private Stage primaryStage;             //сцена главного окна
    private SampleController mainController;//контроллер сцены главного окна
    private FXMLLoader fxmlLoader;          //загрузчик файлов FXML
    private AnchorPane currentRoot;           //коренной Node (Parent)

    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        createGUI();
    }

    private void createGUI() {
        currentRoot = loadFXML(); //возвращает сконфигурированный Node (Parent)
        Scene scene = new Scene(currentRoot, 850, 700);
        primaryStage.setScene(scene);
        primaryStage.setMinHeight(750);
        primaryStage.setMinWidth(760);
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
