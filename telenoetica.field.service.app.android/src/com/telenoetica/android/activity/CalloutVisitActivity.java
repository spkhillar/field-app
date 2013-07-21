package com.telenoetica.android.activity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.telenoetica.android.rest.AppValuesHolder;
import com.telenoetica.jpa.entities.CallOutVisit;

public class CalloutVisitActivity extends AbstractVisitActivity {
  private Button buttonSubmit;
  private Button pickDate1, pickDate2;
  private TextView complaintRecievedDateDisplay, timeReachedDateDisplay, faultResolvedDateDisplay;
  private TextView complaintRecievedTimeDisplay, timeReachedTimeDisplay, faultResolvedTimeDisplay;
  private int complaintRecievedYear, timeReachedYear, faultResolvedYear;
  private int complaintRecievedMonth, timeReachedMonth, faultResolvedMonth;
  private int complaintRecievedDay, timeReachedDay, faultResolvedDay;
  private int complaintRecievedHour, timeReachedHour, faultResolvedHour;
  private int complaintRecievedMinute, timeReachedMinute, faultResolvedMinute;
  private static final int TIME_DIALOG_ID1 = 0;
  private static final int DATE_DIALOG_ID1 = 1;
  private static final int TIME_DIALOG_ID2 = 2;
  private static final int DATE_DIALOG_ID2 = 3;
  private static final int TIME_DIALOG_ID3 = 4;
  private static final int DATE_DIALOG_ID3 = 5;

  public void addListenerOnButtonSubmit() {
    buttonSubmit = (Button) findViewById(R.id.btn_cv_submit);
    buttonSubmit.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(final View arg0) {
        // Intent intent = new Intent(context, MainMenu.class);
        // startActivity(intent);
        renderConfirmationDialog();
      }
    });
  }

  private Date getDateTime(final int textViewId) {
    TextView dateTimeTextView = (TextView) findViewById(textViewId);
    String finalDate = dateTimeTextView.getText().toString();
    return getDateInFormat(finalDate);
  }

  private Date getDateInFormat(final String date) {
    if (StringUtils.isBlank(date)) {
      return null;
    }
    String format = "dd/MM/yyyy HH:mm:ss";
    String[] dateTimeArray = StringUtils.split(date, "|");
    String finalDate = dateTimeArray[0];
    if (dateTimeArray.length == 2) {
      finalDate = finalDate + " " + dateTimeArray[1] + ":00";
    } else {
      finalDate = finalDate + " 00:00:00";
    }
    Date returnDate = null;
    SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
    try {
      returnDate = sdf.parse(finalDate);
    } catch (ParseException e) {}
    return returnDate;

  }

  private String updateDate(final TextView dateDisplay, final int day, final int month, final int year) {
    // Month is 0 based so add 1
    StringBuilder sb = new StringBuilder().append(day).append("/").append(month + 1).append("/").append(year);
    dateDisplay.setText(sb);
    return sb.toString();
  }

  private String updateTime(final TextView timeDisplay, final int hour, final int minute) {
    StringBuilder sb = new StringBuilder().append(pad(hour)).append(":").append(pad(minute));
    timeDisplay.setText(sb);
    return sb.toString();
  }

  public void OnDateAndTimeChangeListener1() {
    final Context context = this;
    pickDate1 = (Button) findViewById(R.id.datePicker1);
    pickDate1.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(final View v) {
        // TODO Auto-generated method stub
        showDialog(DATE_DIALOG_ID1);
      }
    });
    final Calendar c = Calendar.getInstance();
    complaintRecievedYear = c.get(Calendar.YEAR);
    complaintRecievedMonth = c.get(Calendar.MONTH);
    complaintRecievedDay = c.get(Calendar.DAY_OF_MONTH);
    complaintRecievedHour = c.get(Calendar.HOUR_OF_DAY);
    complaintRecievedMinute = c.get(Calendar.MINUTE);
  }

  private static String pad(final int c) {
    if (c >= 10) {
      return String.valueOf(c);
    } else {
      return "0" + String.valueOf(c);
    }
  }

  private DatePickerDialog.OnDateSetListener complaintRecievedDateListener = new DatePickerDialog.OnDateSetListener() {
    @SuppressWarnings("deprecation")
    @Override
    public void onDateSet(final DatePicker view, final int year, final int monthOfYear, final int dayOfMonth) {
      complaintRecievedYear = year;
      complaintRecievedMonth = monthOfYear;
      complaintRecievedDay = dayOfMonth;
      String date =
          updateDate(complaintRecievedDateDisplay, complaintRecievedDay, complaintRecievedMonth, complaintRecievedYear);
      setDateAndTime(R.id.complaintRecievedDateTime, date, true);
      showDialog(TIME_DIALOG_ID1);
    }
  };
  // Timepicker dialog generation
  private TimePickerDialog.OnTimeSetListener complaintRecievedTimeListener = new TimePickerDialog.OnTimeSetListener() {
    @Override
    public void onTimeSet(final TimePicker view, final int hourOfDay, final int minute) {
      complaintRecievedHour = hourOfDay;
      complaintRecievedMinute = minute;
      String time = updateTime(complaintRecievedTimeDisplay, complaintRecievedHour, complaintRecievedMinute);
      setDateAndTime(R.id.complaintRecievedDateTime, time, false);
    }
  };

  private void setDateAndTime(final int textViewId, final String dateTime, final boolean date) {
    TextView dateTimeTextView = (TextView) findViewById(textViewId);
    String finalDate = dateTimeTextView.getText().toString();
    if (StringUtils.isBlank(finalDate)) {
      finalDate = dateTime + "|";
    } else if (date) {
      String[] dateTimeArray = StringUtils.split(finalDate, "|");
      if (dateTimeArray.length == 2) {
        finalDate = dateTime + "|" + dateTimeArray[1];
      } else {
        finalDate = dateTime + "|";
      }
    } else {
      String[] dateTimeArray = StringUtils.split(finalDate, "|");
      if (dateTimeArray.length == 2) {
        finalDate = dateTimeArray[0] + "|" + dateTime;
      } else {
        finalDate = finalDate + dateTime;
      }
    }
    LOGGER.debug("....setDateAndTime..." + finalDate);
    dateTimeTextView.setText(finalDate);
  }

  @Override
  protected Dialog onCreateDialog(final int id) {
    switch (id) {
    case DATE_DIALOG_ID1:
      return new DatePickerDialog(this, complaintRecievedDateListener, complaintRecievedYear, complaintRecievedMonth,
        complaintRecievedDay);
    case TIME_DIALOG_ID1:
      return new TimePickerDialog(this, complaintRecievedTimeListener, complaintRecievedHour, complaintRecievedMinute,
        false);
    case DATE_DIALOG_ID2:
      return new DatePickerDialog(this, timeReachedDateListener, timeReachedYear, timeReachedMonth, timeReachedDay);
    case TIME_DIALOG_ID2:
      return new TimePickerDialog(this, timeReachedTimeListener, timeReachedHour, timeReachedMinute, false);
    case DATE_DIALOG_ID3:
      return new DatePickerDialog(this, faultResolvedDateListener, faultResolvedYear, faultResolvedMonth,
        faultResolvedDay);
    case TIME_DIALOG_ID3:
      return new TimePickerDialog(this, faultResolvedTimeListener, faultResolvedHour, faultResolvedMinute, false);
    }
    return null;
  }

  public void OnDateAndTimeChangeListener2() {
    final Context context = this;
    pickDate2 = (Button) findViewById(R.id.datePicker2);
    pickDate2.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(final View v) {
        // TODO Auto-generated method stub
        showDialog(DATE_DIALOG_ID2);
      }
    });
    final Calendar c = Calendar.getInstance();
    timeReachedYear = c.get(Calendar.YEAR);
    timeReachedMonth = c.get(Calendar.MONTH);
    timeReachedDay = c.get(Calendar.DAY_OF_MONTH);
    timeReachedHour = c.get(Calendar.HOUR_OF_DAY);
    timeReachedMinute = c.get(Calendar.MINUTE);
  }

  private static String pad2(final int c) {
    if (c >= 10) {
      return String.valueOf(c);
    } else {
      return "0" + String.valueOf(c);
    }
  }

  private DatePickerDialog.OnDateSetListener timeReachedDateListener = new DatePickerDialog.OnDateSetListener() {
    @Override
    public void onDateSet(final DatePicker view, final int year, final int monthOfYear, final int dayOfMonth) {
      timeReachedYear = year;
      timeReachedMonth = monthOfYear;
      timeReachedDay = dayOfMonth;
      String date = updateDate(timeReachedDateDisplay, timeReachedDay, timeReachedMonth, timeReachedYear);
      setDateAndTime(R.id.timeReachedDateTime, date, true);
      showDialog(TIME_DIALOG_ID2);
    }
  };
  private TimePickerDialog.OnTimeSetListener timeReachedTimeListener = new TimePickerDialog.OnTimeSetListener() {
    @Override
    public void onTimeSet(final TimePicker view, final int hourOfDay, final int minute) {
      timeReachedHour = hourOfDay;
      timeReachedMinute = minute;
      String time = updateTime(timeReachedTimeDisplay, timeReachedHour, timeReachedMinute);
      setDateAndTime(R.id.timeReachedDateTime, time, false);
    }
  };

  public void OnDateAndTimeChangeListener3() {
    pickDate2 = (Button) findViewById(R.id.datePicker3);
    pickDate2.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(final View v) {
        // TODO Auto-generated method stub
        showDialog(DATE_DIALOG_ID3);
      }
    });
    final Calendar c = Calendar.getInstance();
    faultResolvedYear = c.get(Calendar.YEAR);
    faultResolvedMonth = c.get(Calendar.MONTH);
    faultResolvedDay = c.get(Calendar.DAY_OF_MONTH);
    faultResolvedHour = c.get(Calendar.HOUR_OF_DAY);
    faultResolvedMinute = c.get(Calendar.MINUTE);
  }

  private static String pad3(final int c) {
    if (c >= 10) {
      return String.valueOf(c);
    } else {
      return "0" + String.valueOf(c);
    }
  }

  private DatePickerDialog.OnDateSetListener faultResolvedDateListener = new DatePickerDialog.OnDateSetListener() {
    @Override
    public void onDateSet(final DatePicker view, final int year, final int monthOfYear, final int dayOfMonth) {
      faultResolvedYear = year;
      faultResolvedMonth = monthOfYear;
      faultResolvedDay = dayOfMonth;
      String date = updateDate(faultResolvedDateDisplay, faultResolvedDay, faultResolvedMonth, faultResolvedYear);
      setDateAndTime(R.id.faultResolvedDateTime, date, true);
      showDialog(TIME_DIALOG_ID3);
    }
  };
  private TimePickerDialog.OnTimeSetListener faultResolvedTimeListener = new TimePickerDialog.OnTimeSetListener() {
    @Override
    public void onTimeSet(final TimePicker view, final int hourOfDay, final int minute) {
      faultResolvedHour = hourOfDay;
      faultResolvedMinute = minute;
      String time = updateTime(faultResolvedTimeDisplay, faultResolvedHour, faultResolvedMinute);
      setDateAndTime(R.id.faultResolvedDateTime, time, false);
    }
  };

  @Override
  protected void initializeActivity(final Bundle savedInstanceState) {
    // checkForUserIdandPassword();
    setContentView(R.layout.callout_visit);
    complaintRecievedDateDisplay = (TextView) findViewById(R.id.complaintRecievedDate);
    complaintRecievedTimeDisplay = (TextView) findViewById(R.id.complaintRecievedTime);
    timeReachedDateDisplay = (TextView) findViewById(R.id.timeReachedDate);
    timeReachedTimeDisplay = (TextView) findViewById(R.id.timeReachedTime);
    faultResolvedDateDisplay = (TextView) findViewById(R.id.faultResolvedDate);
    faultResolvedTimeDisplay = (TextView) findViewById(R.id.faultResolvedTime);
    addListenerOnButtonSubmit();
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

  @Override
  public void saveCurrentActivity() {
    // TODO Auto-generated method stub
    final Map<String, Object> valueMap = new LinkedHashMap<String, Object>();
    ViewGroup group = (ViewGroup) findViewById(R.id.ll4_cv);
    List<String> errorList = new ArrayList<String>();
    getTargetObject(group, valueMap, errorList);
    if (CollectionUtils.isEmpty(errorList)) {
      CallOutVisit callOutVisit = new CallOutVisit();
      callOutVisit.setUserId(AppValuesHolder.getCurrentUser());
      Date date = getDateTime(R.id.faultResolvedDateTime);
      callOutVisit.settimeFaultResolved(date);
      date = getDateTime(R.id.complaintRecievedDateTime);
      callOutVisit.setTimeComplainReceived(date);
      date = getDateTime(R.id.timeReachedDateTime);
      callOutVisit.setTimeReachedToSite(date);

      saveVisit(callOutVisit, valueMap);
    } else {
      LOGGER.error("Validation failed");
    }
  }

}
