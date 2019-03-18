package com.example;

import static org.testfx.api.FxAssert.verifyThat;
import static org.junit.Assert.*;
import static org.testfx.matcher.control.LabeledMatchers.hasText;
import static org.testfx.matcher.control.ListViewMatchers.hasItems;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import jfxtras.scene.control.CalendarPicker;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.loadui.testfx.GuiTest;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

import java.util.Date;

/*
Полезные ссылки с примерами тестов:
https://github.com/TestFX/TestFX/wiki
https://github.com/TestFX/TestFX/wiki/Getting-Started
https://github.com/TestFX/TestFX/issues/566
https://github.com/lestard/todomvcFX/blob/master/tests/src/main/java/todomvcfx/AbstractTest.java
*/

public class MainTest extends ApplicationTest {

    Parent mainNode;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        mainNode = FXMLLoader.load(Main.class.getResource("/Sample.fxml"));
        primaryStage.setScene(new Scene(mainNode));
        primaryStage.show();
        primaryStage.toFront();
    }

    //Unit tests
    @Test
    public void shouldContain_addButton() {
        // expect:
        verifyThat("#addButton", hasText("Добавить"));
    }

    @Test
    public void shouldContain_deleteButton() {
        // expect:
        verifyThat("#deleteButton", hasText("Удалить"));
    }

    @Test
    public void listViewDontHaveElements(){
        verifyThat("#noteList", hasItems(0));
    }

    //Integration Tests
    @Test
    public void testInputNote () {
        //подготовка данных
        String s1 = "Make integration tests";
        String s2 = "Make unit tests";
        String result1, result2;
        ListView<String> listView = (ListView) GuiTest.find("#noteList");

        //действия
        clickOn("#textArea");
        write(s1);
        clickOn("#addButton");
        result1 = listView.getItems().get(0);

        clickOn("#textArea");
        write(s2);
        clickOn("#addButton");
        result2 = listView.getItems().get(1);

        //сравнение результатов
        verifyThat("#noteList", hasItems(2));
        assertEquals(s1, result1);
        assertEquals(s2, result2);
    }

    @Test
    public void testDeleteNote1 () {
        //подготовка данных
        String s1 = "Make integration tests";
        ListView<String> listView = (ListView) GuiTest.find("#noteList");
        String result;

        //действия
        clickOn("#textArea");
        write(s1);
        clickOn("#addButton");
        clickOn(listView);
        clickOn((Node) lookup(hasText(s1)).query());
        clickOn("#deleteButton");

        //сравнение результатов
        verifyThat("#noteList", hasItems(0));
    }

    @Test
    public void testDeleteNote2 () {
        //подготовка данных
        String s1 = "Make integration tests";
        String s2 = "Make unit tests";
        ListView<String> listView = (ListView) GuiTest.find("#noteList");
        String result;

        //действия
        clickOn("#textArea");
        write(s1);
        clickOn("#addButton");

        clickOn("#textArea");
        write(s2);
        clickOn("#addButton");

        clickOn((Node) lookup(hasText(s1)).query());               //выбираем элемент с текстом s1
        clickOn("#deleteButton");                           //нажимаем на кнопку "Удалить"

        //сравнение результатов
        verifyThat("#noteList", hasItems(1));   //удостоверяемся, что в листе один элемент
        result = listView.getItems().get(listView.getSelectionModel().getSelectedIndex()); //получаем текст выбранного элемента
        assertEquals(s2, result);                                  //сравниваем вторую строку с текстом выбранного элемента
        System.out.println(result);                                //пробуем выводить результат теста в консоль
    }

    @Test
    public void testCalendarNotes(){
        //подготовка данных
        String s1 = "Make integration tests";
        String s2 = "make unit tests";
        ListView<String> listView = (ListView) GuiTest.find("#noteList");
        CalendarPicker calendarPicker = (CalendarPicker) GuiTest.find("#calendarPicker");
        Date date = calendarPicker.getDisplayedCalendar().getTime();
        int day = date.getDate();
        String nextDay = ""+(day+1);

        //действия
        clickOn("#textArea");
        write(s1);
        clickOn("#addButton");

        clickOn((Node) lookup(nextDay).query());
        clickOn("#textArea");
        write(s2);
        clickOn("#addButton");

        clickOn((Node) lookup(nextDay).query());
        clickOn("#textArea");
        write("Now delete 1st element");
        clickOn("#addButton");

        clickOn((Node) lookup(hasText(s1)).query());               //выбираем элемент с текстом s1
        clickOn("#deleteButton");                           //нажимаем на кнопку "Удалить"

        //сравнение результатов
        verifyThat("#noteList", hasItems(1));   //удостоверяемся, что в листе один элемент
    }
}