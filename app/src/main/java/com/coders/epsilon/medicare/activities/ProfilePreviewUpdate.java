package com.coders.epsilon.medicare.activities;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.coders.epsilon.medicare.R;
import com.coders.epsilon.medicare.databases.ProfileDataSource;
import com.coders.epsilon.medicare.model.Profile;
import com.coders.epsilon.medicare.utills.AgeCalculator;


public class ProfilePreviewUpdate extends Fragment implements View.OnClickListener {

    Button updateProfile ;
    Button home_btn ;
    EditText et_name = null;
    EditText et_age = null;
    EditText etBloodGroup = null;
    EditText et_weight = null;
    EditText et_height = null;
    EditText et_dateOfBirth = null;
    EditText et_specialNotes = null;
    Toast toast = null;

    String mName = "";
    String mAge = "";
    String mBloodGroup = "";
    String mWeight = "";
    String mHeight = "";
    String mDateOfBirth;
    String mSpecialNotes = "";

    Profile mUpdateProfile = null;
    ProfileDataSource mDataSource = null;

    private int startYear=1980;
    private int startMonth=6;
    private int startDay=15;


    /// add new
    AgeCalculator ageCal2;


    static final int DATE_START_DIALOG_ID = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.profile_preview_update, container, false);
/// add new
        ageCal2=new AgeCalculator();




        init(view);

        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayView();


            }
        });

        return view;

    }

    private void init(View view) {


        // set the textfield id to the variable.
        et_name = (EditText) view.findViewById(R.id.viewName);
        et_age = (EditText) view.findViewById(R.id.viewAge);
        etBloodGroup = (EditText) view.findViewById(R.id.viewBloodGroup);
        et_weight = (EditText) view.findViewById(R.id.viewWeight);
        et_height = (EditText) view.findViewById(R.id.viewHeight);
        et_dateOfBirth = (EditText) view.findViewById(R.id.viewDOB);
        et_specialNotes = (EditText) view.findViewById(R.id.viewSpComment);
        updateProfile = (Button) view.findViewById(R.id.updateProfile);
        updateProfile.setOnClickListener(mCorkyListener);
        home_btn = (Button) view.findViewById(R.id.buttonHome);

		/*
		 * get the profile which include all data from database according
		 * profileId of the clicked item.
		 */
        mDataSource = new ProfileDataSource(getActivity());
        mUpdateProfile = mDataSource.singleProfileData();

        String mName = mUpdateProfile.getName();
        String mAge2 = mUpdateProfile.getAge();
        String mEyeColor = mUpdateProfile.getBlooGroup();
        String mWeight = mUpdateProfile.getWeight();
        String mHeight = mUpdateProfile.getHeight();
        mDateOfBirth = mUpdateProfile.getDateOfBirth();
        String mSpecialNotes = mUpdateProfile.getSpecialNotes();

        // Add the line
        mAge=ageCal2.calAge(mDateOfBirth);

        // set the value of database to the text field.
        et_name.setText(mName);
        et_age.setText(mAge);
        etBloodGroup.setText(mEyeColor);
        et_weight.setText(mWeight);
        et_height.setText(mHeight);
        et_dateOfBirth.setText(mDateOfBirth);
        et_specialNotes.setText(mSpecialNotes);
        et_dateOfBirth.setOnClickListener(this);

    }
    private View.OnClickListener mCorkyListener = new View.OnClickListener() {
        public void onClick(View v) {
            mName = et_name.getText().toString();
            mAge = et_age.getText().toString();
            mBloodGroup = etBloodGroup.getText().toString();
            mWeight = et_weight.getText().toString();
            mHeight = et_height.getText().toString();
            mDateOfBirth = et_dateOfBirth.getText().toString();
            mSpecialNotes = et_specialNotes.getText().toString();

            // Assign values in the ICareProfile
            Profile profileDataInsert = new Profile();
            profileDataInsert.setName(mName);
            profileDataInsert.setAge(mAge);
            profileDataInsert.setBloodGroup(mBloodGroup);
            profileDataInsert.setWeight(mWeight);
            profileDataInsert.setHeight(mHeight);
            profileDataInsert.setDateOfBirth(mDateOfBirth);
            profileDataInsert.setSpecialNotes(mSpecialNotes);

            mDataSource = new ProfileDataSource(getActivity());

            if (mDataSource.updateData(1, profileDataInsert) == true) {
                toast = Toast.makeText(getActivity(),
                        getString(R.string.successfullyUpdated), Toast.LENGTH_LONG);
                toast.show();
                displayView();

            } else {
                toast = Toast.makeText(getActivity(), getString(R.string.notUpdated),
                        Toast.LENGTH_LONG);
                toast.show();
            }
        }
    };

    private void displayView() {
        // update the main content by replacing fragments
        Fragment fragment = null;

//        fragment = new Fragment_Home();
//        if (fragment != null) {
//            FragmentManager fragmentManager = getFragmentManager();
//            fragmentManager.beginTransaction()
//                    .replace(R.id.frame_container, fragment).commit();
//

       // }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.viewDOB:
                getActivity().showDialog(DATE_START_DIALOG_ID);
                break;

            default:
                break;
        }
    }


    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_START_DIALOG_ID:
                return new DatePickerDialog(getActivity(),
                        mDateSetListener,
                        startYear, startMonth, startDay);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener mDateSetListener
            = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            startYear=selectedYear;
            startMonth=selectedMonth;
            startDay=selectedDay;

            et_dateOfBirth.setText(""+selectedDay+":"+(startMonth+1)+":"+startYear);

        }
    };



//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getActivity().getMenuInflater().inflate(R.menu.menu_profile_preview_update, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.deleteProfile:
//                mDataSource.deleteData(1);
//                startActivity(new Intent(getActivity(),
//                        ProfileCreate.class));
//
//                return true;
//            case R.id.home:
//                startActivity(new Intent(getActivity(),
//                        Home.class));
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }
}
