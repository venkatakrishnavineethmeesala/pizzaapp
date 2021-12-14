package com.example.insertcalendarevent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity2 extends AppCompatActivity {


    int day_sel,month_sel,year_sel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button addEvent = (Button) findViewById(R.id.Addevent);
        CalendarView calender = (CalendarView) findViewById(R.id.calendarView);
        TextView text = (TextView) findViewById(R.id.displayText);


        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dateselected  = sdf.format(new Date(calender.getDate()));
        text.setText("date:"+dateselected);

        calender.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayofMonth) {
                text.setText("date:"+dayofMonth+"/"+ month+"/"+year);

                day_sel = dayofMonth;
                month_sel = month;
                year_sel = year;

            }
        });




        addEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                create_event();
            }
        });

    }

    public void create_event(){

        Intent intent = new Intent(Intent.ACTION_INSERT,
                CalendarContract.Events.CONTENT_URI);
        // Add the calendar event details
        intent.putExtra(CalendarContract.Events.TITLE, "Event name!");
        intent.putExtra(CalendarContract.Events.DESCRIPTION,
                "Learn how to create an event");
        intent.putExtra(CalendarContract.Events.EVENT_LOCATION,
                "UMKC.com");

        // set date as per the selected by the user
        Calendar startTime = Calendar.getInstance();
        startTime.set(year_sel,month_sel,day_sel);

        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,
                startTime.getTimeInMillis());
        intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);

        //add email id
        intent.putExtra(Intent.EXTRA_EMAIL, "kmnpx@umsystem.edu");
        // Use the Calendar app to add the new event.
        startActivity(intent);

    }


}