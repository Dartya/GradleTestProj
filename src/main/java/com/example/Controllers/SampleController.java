package com.example.Controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import jfxtras.scene.control.CalendarPicker;
import jfxtras.scene.control.CalendarTextField;
import jfxtras.scene.control.ListView;
import jfxtras.scene.control.LocalTimePicker;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;


public class SampleController {
    public CalendarPicker calendarPicker;
    public LocalTimePicker timePicker;
    public TextArea textArea;
    public Button addButton ;
    public Button deleteButton;
    public ListView noteList;
    private Date date;
    private LocalDate localdate;
    private int day, month, year;

    private HashMap<String, ArrayList<String>> hmMessage = new HashMap<>();
    ObservableList<String> noteObservList = FXCollections.observableList(new ArrayList<>());

    public void initialize(){
        String s;
        ArrayList<String> arrayList = new ArrayList<>();

        noteList.setItems(noteObservList);
        System.out.println(calendarPicker.getDisplayedCalendar().getTime());
        date = calendarPicker.getDisplayedCalendar().getTime();
        day = date.getDate();
        month = date.getMonth();
        year = date.getYear();
        s = "" + day + " " + Month.of(month + 1) + " " + (year + 1900);
        checkString(s, arrayList);

        calendarPicker.calendarProperty().addListener(new ChangeListener<Calendar>() {
            @Override
            public void changed(ObservableValue<? extends Calendar> observable, Calendar oldValue, Calendar newValue) {
                String s;
                ArrayList<String> arrayList = new ArrayList<>();
                if (newValue != null) {
                    System.out.println(newValue.getTime());
                    date = newValue.getTime();
                    day = date.getDate();
                    month = date.getMonth();
                    year = date.getYear();
                    s = "" + day + " " + Month.of(month + 1) + " " + (year + 1900);
                    System.out.println(s);
                    System.out.println(localdate = LocalDate.of(year + 1900, Month.of(month + 1), day));
                } else{
                    System.out.println(calendarPicker.getDisplayedCalendar().getTime());
                    date = calendarPicker.getDisplayedCalendar().getTime();
                    day = date.getDate();
                    month = date.getMonth();
                    year = date.getYear();
                    s = "" + day + " " + Month.of(month + 1) + " " + (year + 1900);
                    System.out.println(s);
                    System.out.println(localdate = LocalDate.of(year + 1900, Month.of(month + 1), day));
                }
                //у нас есть дата s
                //ищем ее в хэшмэп
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

        if (!(hmMessage.get(s) == null)) {   //если по дате находится эррейлист
            arrayList = hmMessage.get(s);   //то поллучаем этот эррейлист
            noteObservList = FXCollections.observableList(arrayList);   //передаем его обсерваблЛисту - оболочке найденного эррейлиста
            noteList.setItems(noteObservList);                          //устанавливаем обсерваблЛист как контейнер вьюхи окна
        } else {                                                                //если у даты отсутствует эррейлист
            hmMessage.put(s, new ArrayList<String>());                          //по данной дате создаем новый эррейлист
            noteObservList = FXCollections.observableList(hmMessage.get(s));    //получем его и передаем обсерваблЛисту
            noteList.setItems(noteObservList);                                  //устанавливаем обсерваблЛист как контейнер вьюхи окна
        }
    }
}
