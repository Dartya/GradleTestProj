package com.example.Controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import jfxtras.scene.control.CalendarPicker;
import jfxtras.scene.control.ListView;
import java.time.Month;
import java.util.*;


public class SampleController {
    public CalendarPicker calendarPicker;
    public TextArea textArea;
    public Button addButton ;
    public Button deleteButton;
    public ListView noteList;
    private int day, month, year;
    private String s;
    private ArrayList<String> arrayList = new ArrayList<>();

    private HashMap<String, ArrayList<String>> hmMessage = new HashMap<>();
    ObservableList<String> noteObservList = FXCollections.observableList(new ArrayList<>());

    public void initialize(){

        noteList.setItems(noteObservList);
        System.out.println(calendarPicker.getDisplayedCalendar().getTime());
        s = getDate(calendarPicker.getDisplayedCalendar().getTime());
        checkString(s, arrayList);

        calendarPicker.calendarProperty().addListener(new ChangeListener<Calendar>() {
            @Override
            public void changed(ObservableValue<? extends Calendar> observable, Calendar oldValue, Calendar newValue) {
                String s;
                ArrayList<String> arrayList = new ArrayList<>();
                if (newValue != null) {
                    s = getDate(newValue.getTime());
                    System.out.println(s);
                } else{
                    s = getDate(calendarPicker.getDisplayedCalendar().getTime());
                    System.out.println(s);
                }
                //у нас есть дата s, ищем ее в хэшмэп
                checkString(s, arrayList);
            }
        });
    }

    public void addAction(ActionEvent actionEvent) {
        String s = textArea.getText();
        noteObservList.add(s);
        textArea.clear();
    }

    public void deleteAction(ActionEvent actionEvent) {
        if (noteList.getSelectedItem() != null){
            noteList.getItems().remove(noteList.getSelectionModel().getSelectedIndex());
        }
    }

    private void checkString (String s, ArrayList<String> arrayList){
        if (!(hmMessage.get(s) == null)) {                                      //если по дате находится эррейлист
            arrayList = hmMessage.get(s);                                       //то получаем этот эррейлист
            noteObservList = FXCollections.observableList(arrayList);           //передаем его обсерваблЛисту - оболочке найденного эррейлиста
            noteList.setItems(noteObservList);                                  //устанавливаем обсерваблЛист как контейнер вьюхи окна
        } else {                                                                //если у даты отсутствует эррейлист
            hmMessage.put(s, new ArrayList<String>());                          //по данной дате создаем новый эррейлист
            noteObservList = FXCollections.observableList(hmMessage.get(s));    //получем его и передаем обсерваблЛисту
            noteList.setItems(noteObservList);                                  //устанавливаем обсерваблЛист как контейнер вьюхи окна
        }
    }

    private String getDate(Date date){
        String s;
        day = date.getDate();
        month = date.getMonth();
        year = date.getYear();
        s = "" + day + " " + Month.of(month + 1) + " " + (year + 1900);
        return s;
    }
}
