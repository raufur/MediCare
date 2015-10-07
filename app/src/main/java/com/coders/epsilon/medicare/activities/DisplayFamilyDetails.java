package com.coders.epsilon.medicare.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.coders.epsilon.medicare.R;


public class DisplayFamilyDetails extends ActionBarActivity {

    EditText etName,etFNumber,etFRelationship,etFage,etFDateOfBirth,etFbloodGroup,etFHeight,etFWeight,etFspecialNotes;

    String FName,FNumber,FRelationship, Fage, FDateOfBirth,FbloodGroup,FHeight,FWeight,FspecialNotes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_family_details);


        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#22362D"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);

         FName = getIntent().getStringExtra("FName");
         FNumber = getIntent().getStringExtra("FNumber");
         FRelationship = getIntent().getStringExtra("FRelationship");
         Fage = getIntent().getStringExtra("Fage");
         FDateOfBirth = getIntent().getStringExtra("FDateOfBirth");
         FbloodGroup = getIntent().getStringExtra("FbloodGroup");
         FHeight = getIntent().getStringExtra("FHeight");
         FWeight = getIntent().getStringExtra("FWeight");
         FspecialNotes = getIntent().getStringExtra("FspecialNotes");

        init();


    }

    private void init() {
        etName=(EditText)findViewById(R.id.etFName);
        etFNumber=(EditText)findViewById(R.id.etFNumber);
        etFage=(EditText)findViewById(R.id.etFAge);
        etFRelationship=(EditText)findViewById(R.id.etFRelationship);
        etFDateOfBirth=(EditText)findViewById(R.id.etFDateOfBirth);
        etFbloodGroup=(EditText)findViewById(R.id.etFBloodGroup);
        etFHeight=(EditText)findViewById(R.id.etFHeight);
        etFWeight=(EditText)findViewById(R.id.etFWeight);
        etFspecialNotes=(EditText)findViewById(R.id.etFspecialNotes);

        etName.setText(FName);
        etFNumber.setText(FNumber);
        etFRelationship.setText(FRelationship);
        etFage.setText(Fage);
        etFDateOfBirth.setText(FDateOfBirth);
        etFbloodGroup.setText(FbloodGroup);
        etFHeight.setText(FHeight);
        etFWeight.setText(FWeight);
        etFspecialNotes.setText(FspecialNotes);
      }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.Home) {
//
//            Intent intent= new Intent(this, MainActivity.class);
//            startActivity(intent);
//
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }
}
