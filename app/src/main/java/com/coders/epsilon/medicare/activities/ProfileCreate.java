package com.coders.epsilon.medicare.activities;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.coders.epsilon.medicare.R;
import com.coders.epsilon.medicare.databases.ProfileDataSource;
import com.coders.epsilon.medicare.model.Profile;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;




public class ProfileCreate extends ActionBarActivity implements
        OnClickListener {
    Button btnSave = null;
    EditText etName = null;
    EditText etAge = null;
    Spinner etBloodGroup = null;
    EditText etWeight = null;
    EditText etHeight = null;
    EditText etDateOfBirth = null;
    EditText etSpecialNotes = null;
    Toast toast = null;

    String mName = "";
    String mAge = "";
    String mBloodGroup = "";
    String mWeight = "";
    String mHeight = "";
    String mDateOfBirth = "";
    String mSpecialNotes = "";


    ProfileDataSource icareDS = null;
    private int year;
    private int month;
    private int day;

    static final int DATE_PICKER_ID = 1111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_create);

        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#22362D"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        // set the text field id to the variable.
    init();

        addItemsOnSpinner2();
        addListenerOnSpinnerItemSelection();

    }

    private void init() {
        etName = (EditText) findViewById(R.id.createUserName);
        etAge = (EditText) findViewById(R.id.createAge);
        etBloodGroup = (Spinner) findViewById(R.id.createBloodGroup);
        etWeight = (EditText) findViewById(R.id.createWeight);
        etHeight = (EditText) findViewById(R.id.createHeight);

        etDateOfBirth = (EditText) findViewById(R.id.createDateOfBirth);
        etDateOfBirth.setOnClickListener(this);

        etSpecialNotes = (EditText) findViewById(R.id.createSpecialComment);
        btnSave = (Button) findViewById(R.id.Save);
        btnSave.setOnClickListener(this);


    }

    public void addItemsOnSpinner2() {


        List<String> list = new ArrayList<String>();
        list.add("A+");
        list.add("B+");
        list.add("O+");
        list.add("AB+");
        list.add("A-");
        list.add("B-");
        list.add("O-");
        list.add("AB-");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        etBloodGroup.setAdapter(dataAdapter);
    }

    public void addListenerOnSpinnerItemSelection() {
        etBloodGroup = (Spinner) findViewById(R.id.createBloodGroup);
       // etBloodGroup.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.icare, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public void onClick(View v) {
switch (v.getId()){
    case R.id.Save:
        save();

        break;
    case R.id.createDateOfBirth:
        final Calendar c = Calendar.getInstance();
        year  = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day   = c.get(Calendar.DAY_OF_MONTH);
        showDialog(DATE_PICKER_ID);
        break;
}



    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_PICKER_ID:


                return new DatePickerDialog(this, pickerListener, year, month,day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener pickerListener = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        @Override
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {

            year  = selectedYear;
            month = selectedMonth;
            day   = selectedDay;

            // Show selected date
            etDateOfBirth.setText(new StringBuilder().append(month + 1)
                    .append("-").append(day).append("-").append(year)
                    .append(" "));

        }
    };


    public void save(){
        mName = etName.getText().toString();
        mAge = etAge.getText().toString();
        mBloodGroup = String.valueOf(etBloodGroup.getSelectedItem());
        mWeight = etWeight.getText().toString();
        mHeight = etHeight.getText().toString();
        mDateOfBirth = etDateOfBirth.getText().toString();
        mSpecialNotes = etSpecialNotes.getText().toString();

        // Assign values in the ICareProfile
        Profile profileDataInsert = new Profile();
        profileDataInsert.setName(mName);
        profileDataInsert.setAge(mAge);
        profileDataInsert.setBloodGroup(mBloodGroup);
        profileDataInsert.setWeight(mWeight);
        profileDataInsert.setHeight(mHeight);
        profileDataInsert.setDateOfBirth(mDateOfBirth);
        profileDataInsert.setSpecialNotes(mSpecialNotes);

		/*
		 * if update is needed then update otherwise submit
		 */
        icareDS = new ProfileDataSource(this);
        if (icareDS.insert(profileDataInsert) == true) {
            toast = Toast.makeText(this, getString(R.string.successfullySaved),
                    Toast.LENGTH_LONG);
            toast.show();
            startActivity(new Intent(ProfileCreate.this,
                    MainActivity.class));
        } else {
            toast = Toast.makeText(this, getString(R.string.notSaved), Toast.LENGTH_LONG);
            toast.show();
        }

    }
}
