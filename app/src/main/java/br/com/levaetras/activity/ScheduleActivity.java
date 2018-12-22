package br.com.levaetras.activity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.badoualy.datepicker.DatePickerTimeline;
import com.github.badoualy.datepicker.MonthView;

import java.util.Calendar;
import java.util.GregorianCalendar;

import br.com.levaetras.R;
import br.com.levaetras.adapter.AdapterEmployee;
import br.com.levaetras.adapter.AdapterSchedule;
import br.com.levaetras.service.EmployeeService;
import br.com.levaetras.service.ScheduleService;

public class ScheduleActivity extends AppCompatActivity implements View.OnClickListener {

    private DatePickerTimeline mTimeline;
    private Dialog myDialog;
    private LinearLayout mBack;
    private TextView mName;
    private RecyclerView mRecycler;
    private LinearLayout mToday;
    private  AdapterSchedule mAdaper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        initView();
        myDialog = new Dialog(this);


        mTimeline.setOnDateSelectedListener(new DatePickerTimeline.OnDateSelectedListener() {
            @Override
            public void onDateSelected(int year, int month, int day, int index) {
                showPopup(day + " / " + month + " / " + year);
                Toast.makeText(ScheduleActivity.this, day + " / " + month + " / " + year, Toast.LENGTH_SHORT).show();
            }
        });
        mTimeline.setDateLabelAdapter(new MonthView.DateLabelAdapter() {
            @Override
            public CharSequence getLabel(Calendar calendar, int index) {
                return Integer.toString(calendar.get(Calendar.MONTH) + 1) + "/" + (calendar.get(Calendar.YEAR) % 2000);
            }
        });

        initAdapter();

    }

    private void initAdapter() {
        mAdaper = new AdapterSchedule(ScheduleService.getSchedule(), this);
        RecyclerView.LayoutManager layout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecycler.setLayoutManager(layout);
        mRecycler.setAdapter(mAdaper);
    }

    private void initView() {
        mBack = (LinearLayout) findViewById(R.id.back);
        mBack.setOnClickListener(this);
        mName = (TextView) findViewById(R.id.name);
        mTimeline = (DatePickerTimeline) findViewById(R.id.timeline);
        mRecycler = (RecyclerView) findViewById(R.id.recycler);
        mToday = (LinearLayout) findViewById(R.id.today);
        mToday.setOnClickListener(this);
    }

    public void showPopup(String day) {

        //var
        Button create, cancel;
        TextView close;
        EditText mDay = null;


        //layout
        myDialog.setContentView(R.layout.pop_up_schedule);

        //binds
        close = myDialog.findViewById(R.id.close);
        mDay = myDialog.findViewById(R.id.day);
        create = myDialog.findViewById(R.id.create);
        cancel = myDialog.findViewById(R.id.cancel);

        //set
        close.setText("X");
        mDay.setText(day);

        //clicks
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myDialog.dismiss();

            }
        });

        //transparent
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //show
        myDialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.today:
                GregorianCalendar calendar = new GregorianCalendar();
                int day = calendar.get(GregorianCalendar.DAY_OF_MONTH);
                int month = calendar.get(GregorianCalendar.MONTH);
                int year = calendar.get(GregorianCalendar.YEAR);
                showPopup(day+"/"+month+"/"+year);
                break;
        }
    }
}
