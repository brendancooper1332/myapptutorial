package com.example.brendan.myfirstapp.event;

import android.app.Activity;
import android.app.DatePickerDialog;
import java.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import com.example.brendan.myfirstapp.R;

public class AddEvent extends Activity {


    EditText date;
    DatePickerDialog datePickerDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_event);

        //initiate the date picker and a button
        date = (EditText) findViewById(R.id.et_date);
        // perform click event on edit text
        date.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                //get calendar class's instance and get current date, month and year from calendar
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // create date picker dialog
               datePickerDialog = new DatePickerDialog(AddEvent.this,
                       new DatePickerDialog.OnDateSetListener(){

                           @Override
                           public void onDateSet(DatePicker view, int year,
                                                 int monthOfYear, int dayOfMonth) {
                               // set day of month , month and year value in the edit text
                               date.setText(dayOfMonth + "/"
                                       + (monthOfYear + 1) + "/" + year);

                           }
                       },mYear,mMonth,mDay);
            }
        });


    }
}
