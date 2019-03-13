package com.example.Controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import jfxtras.scene.control.CalendarPicker;
import jfxtras.scene.control.CalendarTextField;
import jfxtras.scene.control.ListView;
import jfxtras.scene.control.LocalTimePicker;

import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;
import java.util.Date;
import java.util.Observable;


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

    public void initialize(){

        calendarPicker.calendarProperty().addListener(new ChangeListener<Calendar>() {
            @Override
            public void changed(ObservableValue<? extends Calendar> observable, Calendar oldValue, Calendar newValue) {
                System.out.println(newValue.getTime());
                date = newValue.getTime();
                day = date.getDate();
                month = date.getMonth();
                year = date.getYear();
                System.out.println(day+" "+Month.of(month+1)+" "+(year+1900));
                System.out.println(localdate = LocalDate.of(year+1900, Month.of(month+1), day));
            }
        });
    }

    public void addAction(ActionEvent actionEvent) {

    }

    public void deleteAction(ActionEvent actionEvent) {

    }
}
