package com.telenoetica.android.activity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.telenoetica.android.rest.AppValuesHolder;
import com.telenoetica.jpa.entities.CallOutVisit;

public class CalloutVisitActivity extends AbstractVisitActivity {
  private Button ButtonSubmit;
  private Button ButtonReset;
  private Button PickDate1, PickDate2, PickDate3;
  private Button PickTime1, PickTime2, PickTime3;
  private TextView DateDisplay1, DateDisplay2, DateDisplay3;
  private TextView TimeDisplay1, TimeDisplay2, TimeDisplay3;
  private int year1, year2, year3;
  private int month1, month2, month3;
  private int day1, day2, day3;
  private int hour1, hour2, hour3;
  private int minute1, minute2, minute3;
  static final int TIME_DIALOG_ID1 = 0;
  static final int DATE_DIALOG_ID1 = 1;
  static final int TIME_DIALOG_ID2 = 2;
  static final int DATE_DIALOG_ID2 = 3;
  static final int TIME_DIALOG_ID3 = 4;
  static final int DATE_DIALOG_ID3 = 5;

  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    setContentView(R.layout.callout_visit);
    DateDisplay1 = (TextView) findViewById(R.id.date1);
    TimeDisplay1 = (TextView) findViewById(R.id.time1);
    DateDisplay2 = (TextView) findViewById(R.id.date2);
    TimeDisplay2 = (TextView) findViewById(R.id.time2);
    DateDisplay3 = (TextView) findViewById(R.id.date3);
    TimeDisplay3 = (TextView) findViewById(R.id.time3);
    addListenerOnButtonSubmit();
    addListenerOnButtonReset();
    OnDateAndTimeChangeListener1();
    OnDateAndTimeChangeListener2();
    OnDateAndTimeChangeListener3();

    addItemsOnSpinner(R.id.spinner_main_category_of_fault, AppValuesHolder.getFaults());
    addItemsOnSpinner(R.id.spinner_equipment_causing_fault, AppValuesHolder.getSpares());
    addItemsOnSpinner(R.id.spinner_customer1, AppValuesHolder.getClients());
    addItemsOnSpinner(R.id.spinner_customer2, AppValuesHolder.getClients());
    addItemsOnSpinner(R.id.spinner_customer3, AppValuesHolder.getClients());
    addItemsOnSpinner(R.id.spinner_customer4, AppValuesHolder.getClients());
    addItemsOnSpinner(R.id.spinner_equip_comp_repaired, AppValuesHolder.getSpares());
    addItemsOnSpinner(R.id.spinner_equip_comp_replaced, AppValuesHolder.getSpares());
  }

  /*
   * public void showDateTime() { Context context; // Dialog dialog = new
   * Dialog(context); dialog.setContentView(R.layout.callout_visit);
   * dialog.setTitle("Choose Time & Date"); TimePicker tp = (TimePicker)
   * dialog.findViewById(R.id.timePicker1); //
   * tp.setOnTimeChangedListener(TimePicker.OnTimeChangedListener()));
   * 
   * }
   */

  public void addListenerOnButtonSubmit() {

    final Context context = this;
    ButtonSubmit = (Button) findViewById(R.id.btn_cv_submit);
    final Map<String, Object> valueMap = new LinkedHashMap<String, Object>();
    ButtonSubmit.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(final View arg0) {

        // Intent intent = new Intent(context, MainMenu.class);
        // startActivity(intent);
        ViewGroup group = (ViewGroup) findViewById(R.id.ll4_cv);
        List<String> errorList = new ArrayList<String>();
        getTargetObject(group, valueMap,errorList);
        CallOutVisit callOutVisit = new CallOutVisit();
        callOutVisit.setUserId(AppValuesHolder.getCurrentUser());
        saveVisit(callOutVisit, valueMap);

      }

    });

  }

  private void addListenerOnButtonReset() {
    final Context context = this;
    ButtonReset = (Button) findViewById(R.id.btn_cv_reset);
    ButtonReset.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(final View arg0) {
        Reset r = new Reset();
        ViewGroup group = (ViewGroup) findViewById(R.id.ll4_cv);
        r.clearForm(group);
        TextView date1, date2, date3, time1, time2, time3;
        date1 = (TextView) findViewById(R.id.date1);
        date1.setText("");
        date2 = (TextView) findViewById(R.id.date2);
        date2.setText("");
        date3 = (TextView) findViewById(R.id.date3);
        date3.setText("");
        time1 = (TextView) findViewById(R.id.time1);
        time1.setText("");
        time2 = (TextView) findViewById(R.id.time2);
        time2.setText("");
        time3 = (TextView) findViewById(R.id.time3);
        time3.setText("");
      }

    });
  }

  private void updateDate(final TextView DateDisplay, final int day, final int month, final int year) {
    // Month is 0 based so add 1
    DateDisplay.setText(new StringBuilder().append(day).append("/").append(month + 1).append("/").append(year)
      .append(" "));

  }

  public void updatetime(final TextView TimeDisplay, final int hour, final int minute) {
    TimeDisplay.setText(new StringBuilder().append(pad(hour)).append(":").append(pad(minute)));
  }

  public void OnDateAndTimeChangeListener1() {
    final Context context = this;
    PickTime1 = (Button) findViewById(R.id.timePicker1);
    PickTime1.setOnClickListener(new OnClickListener() {

      @Override
      @SuppressWarnings("deprecation")
      public void onClick(final View v) {
        // TODO Auto-generated method stub
        showDialog(TIME_DIALOG_ID1);
      }

    });

    PickDate1 = (Button) findViewById(R.id.datePicker1);
    PickDate1.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(final View v) {
        // TODO Auto-generated method stub
        showDialog(DATE_DIALOG_ID1);
      }
    });
    final Calendar c = Calendar.getInstance();
    year1 = c.get(Calendar.YEAR);
    month1 = c.get(Calendar.MONTH);
    day1 = c.get(Calendar.DAY_OF_MONTH);
    hour1 = c.get(Calendar.HOUR_OF_DAY);
    minute1 = c.get(Calendar.MINUTE);
  }

  private static String pad(final int c) {
    if (c >= 10) {
      return String.valueOf(c);
    } else {
      return "0" + String.valueOf(c);
    }
  }

  private DatePickerDialog.OnDateSetListener DateSetListener1 = new DatePickerDialog.OnDateSetListener() {

    @Override
    public void onDateSet(final DatePicker view, final int year, final int monthOfYear, final int dayOfMonth) {
      year1 = year;
      month1 = monthOfYear;
      day1 = dayOfMonth;
      updateDate(DateDisplay1, day1, month1, year1);
    }
  };

  // Timepicker dialog generation
  private TimePickerDialog.OnTimeSetListener TimeSetListener1 = new TimePickerDialog.OnTimeSetListener() {
    @Override
    public void onTimeSet(final TimePicker view, final int hourOfDay, final int minute) {
      hour1 = hourOfDay;
      minute1 = minute;
      updatetime(TimeDisplay1, hour1, minute1);
    }

  };

  @Override
  protected Dialog onCreateDialog(final int id) {
    switch (id) {
    case DATE_DIALOG_ID1:
      return new DatePickerDialog(this, DateSetListener1, year1, month1, day1);

    case TIME_DIALOG_ID1:
      return new TimePickerDialog(this, TimeSetListener1, hour1, minute1, false);

    case DATE_DIALOG_ID2:
      return new DatePickerDialog(this, DateSetListener2, year2, month2, day2);

    case TIME_DIALOG_ID2:
      return new TimePickerDialog(this, TimeSetListener2, hour2, minute2, false);

    case DATE_DIALOG_ID3:
      return new DatePickerDialog(this, DateSetListener3, year3, month3, day3);

    case TIME_DIALOG_ID3:
      return new TimePickerDialog(this, TimeSetListener3, hour3, minute3, false);
    }
    return null;

  }

  public void OnDateAndTimeChangeListener2() {
    final Context context = this;
    PickTime2 = (Button) findViewById(R.id.timePicker2);
    PickTime2.setOnClickListener(new OnClickListener() {

      @Override
      @SuppressWarnings("deprecation")
      public void onClick(final View v) {
        // TODO Auto-generated method stub
        showDialog(TIME_DIALOG_ID2);
      }

    });

    PickDate2 = (Button) findViewById(R.id.datePicker2);
    PickDate2.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(final View v) {
        // TODO Auto-generated method stub
        showDialog(DATE_DIALOG_ID2);
      }
    });
    final Calendar c = Calendar.getInstance();
    year2 = c.get(Calendar.YEAR);
    month2 = c.get(Calendar.MONTH);
    day2 = c.get(Calendar.DAY_OF_MONTH);
    hour2 = c.get(Calendar.HOUR_OF_DAY);
    minute2 = c.get(Calendar.MINUTE);

  }

  private static String pad2(final int c) {
    if (c >= 10) {
      return String.valueOf(c);
    } else {
      return "0" + String.valueOf(c);
    }
  }

  private DatePickerDialog.OnDateSetListener DateSetListener2 = new DatePickerDialog.OnDateSetListener() {

    @Override
    public void onDateSet(final DatePicker view, final int year, final int monthOfYear, final int dayOfMonth) {
      year2 = year;
      month2 = monthOfYear;
      day2 = dayOfMonth;
      updateDate(DateDisplay2, day2, month2, year2);
    }
  };

  private TimePickerDialog.OnTimeSetListener TimeSetListener2 = new TimePickerDialog.OnTimeSetListener() {
    @Override
    public void onTimeSet(final TimePicker view, final int hourOfDay, final int minute) {
      hour2 = hourOfDay;
      minute2 = minute;
      updatetime(TimeDisplay2, hour2, minute2);
    }

  };

  public void OnDateAndTimeChangeListener3() {
    final Context context = this;
    PickTime3 = (Button) findViewById(R.id.timePicker3);
    PickTime3.setOnClickListener(new OnClickListener() {

      @Override
      @SuppressWarnings("deprecation")
      public void onClick(final View v) {
        // TODO Auto-generated method stub
        showDialog(TIME_DIALOG_ID3);
      }

    });

    PickDate2 = (Button) findViewById(R.id.datePicker3);
    PickDate2.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(final View v) {
        // TODO Auto-generated method stub
        showDialog(DATE_DIALOG_ID3);
      }
    });
    final Calendar c = Calendar.getInstance();
    year3 = c.get(Calendar.YEAR);
    month3 = c.get(Calendar.MONTH);
    day3 = c.get(Calendar.DAY_OF_MONTH);
    hour3 = c.get(Calendar.HOUR_OF_DAY);
    minute3 = c.get(Calendar.MINUTE);

  }

  private static String pad3(final int c) {
    if (c >= 10) {
      return String.valueOf(c);
    } else {
      return "0" + String.valueOf(c);
    }
  }

  private DatePickerDialog.OnDateSetListener DateSetListener3 = new DatePickerDialog.OnDateSetListener() {

    @Override
    public void onDateSet(final DatePicker view, final int year, final int monthOfYear, final int dayOfMonth) {
      year3 = year;
      month3 = monthOfYear;
      day3 = dayOfMonth;
      updateDate(DateDisplay3, day3, month3, year3);
    }
  };

  private TimePickerDialog.OnTimeSetListener TimeSetListener3 = new TimePickerDialog.OnTimeSetListener() {
    @Override
    public void onTimeSet(final TimePicker view, final int hourOfDay, final int minute) {
      hour3 = hourOfDay;
      minute3 = minute;
      updatetime(TimeDisplay3, hour3, minute3);
    }

  };

}
