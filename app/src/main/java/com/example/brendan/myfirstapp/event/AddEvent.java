package com.example.brendan.myfirstapp.event;

import android.app.Activity;
import android.app.DatePickerDialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.brendan.myfirstapp.R;
import com.example.brendan.myfirstapp.fragment.TimePickerFragment;

public class AddEvent extends Activity {


    EditText date;
    EditText time;
    DatePickerDialog datePickerDialog;
    TimePickerDialog timePickerDialog;
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
                String current_date = date.getText().toString();
                if(!current_date.matches(""))
                {
                    String[] date1 = current_date.split("/");
                    mYear = Integer.parseInt(date1[2]);
                    mMonth = Integer.parseInt(date1[1]) -1;
                    mDay = Integer.parseInt(date1[0]);
                }

                // create date picker dialog
               datePickerDialog = new DatePickerDialog(AddEvent.this,R.style.MyDatePickerStyle,
                       new DatePickerDialog.OnDateSetListener(){

                           @Override
                           public void onDateSet(DatePicker view, int year,
                                                 int monthOfYear, int dayOfMonth) {
                               // set day of month , month and year value in the edit text
                               String day = String.valueOf(dayOfMonth);
                               if(dayOfMonth < 10)
                               {
                                   day = "0" + dayOfMonth;
                               }

                               String month = String.valueOf(monthOfYear + 1);
                               if(monthOfYear < 10)
                               {
                                   month = "0" + (monthOfYear + 1);
                               }
                               String date_text = day + "/"+ month + "/" + year;
                               date.setText(date_text);

                           }
                       },mYear,mMonth,mDay);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });

        time = (EditText) findViewById(R.id.et_time);
        time.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                final Calendar c = Calendar.getInstance();
                int hour = 10;
                int minute = 0;
                String current_time = time.getText().toString();
                if(!current_time.matches(""))
                {
                    String[] time1 = current_time.split(":");
                    String[] time2 = time1[1].split(" ");
                    hour = Integer.parseInt(time1[0]);
                    minute = Integer.parseInt(time2[0]);
                }
                timePickerDialog = new TimePickerDialog(AddEvent.this, R.style.MyDatePickerStyle,
                    new TimePickerDialog.OnTimeSetListener(){
                        @Override
                        public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute)
                        {
                            String selected_time = selectedHour + ":" + selectedMinute;
                            SimpleDateFormat fmt = new SimpleDateFormat("HH:mm", Locale.CANADA);
                            Date selectied_date = null;
                            try {
                                selectied_date = fmt.parse(selected_time);
                            }
                            catch(ParseException e)
                            {
                                e.printStackTrace();
                            }

                            SimpleDateFormat fmtOut = new SimpleDateFormat("h:mm aa", Locale.CANADA);
                            String formatedTime = fmtOut.format(selectied_date);
                            time.setText(formatedTime);
                        }
                    },hour, minute,false
                );
                timePickerDialog.show();

            }
        });

    }
}
