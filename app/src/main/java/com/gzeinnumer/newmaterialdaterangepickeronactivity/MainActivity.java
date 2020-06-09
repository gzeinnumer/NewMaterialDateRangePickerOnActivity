package com.gzeinnumer.newmaterialdaterangepickeronactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialDatePicker.Builder<Pair<Long, Long>> builder = MaterialDatePicker.Builder.dateRangePicker();

                Calendar f = Calendar.getInstance();
                f.add(Calendar.MONTH, 0);
                Calendar e = Calendar.getInstance();
                e.add(Calendar.MONTH,0);
                CalendarConstraints.Builder calendarConstraints = new CalendarConstraints.Builder();
                calendarConstraints.setStart(f.getTimeInMillis());
                calendarConstraints.setEnd(e.getTimeInMillis());

                builder.setCalendarConstraints(calendarConstraints.build());
                MaterialDatePicker<Pair<Long, Long>> picker = builder.build();
                picker.show(getSupportFragmentManager(),"date_picker_tag");

                picker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Pair<Long, Long>>() {
                    @Override
                    public void onPositiveButtonClick(Pair<Long, Long> selection) {
                        SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                        String awal = formater.format(new Date(selection.first));
                        String akhir = formater.format(new Date(selection.second));
                        Toast.makeText(getApplicationContext(), awal +" - "+akhir, Toast.LENGTH_SHORT).show();
                    }
                });
                picker.addOnNegativeButtonClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "Tidak jadi memilih", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}