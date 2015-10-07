package com.coders.epsilon.medicare.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.coders.epsilon.medicare.R;
import com.coders.epsilon.medicare.databases.FamilyProfileDataSource;
import com.coders.epsilon.medicare.model.FamilyProfileClass;

import java.util.ArrayList;
import java.util.List;



public class DispalyALLFamilyProfile extends ActionBarActivity {


    ListView lstView;
    FamilyProfileDataSource database;
    List<FamilyProfileClass> familyProfileClassList;
    ArrayList<String> allProfiles;


    FamilyProfileClass familyProfileClass;
    TextView displayFamilyListNotification;

    ArrayAdapter<FamilyProfileClass> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispaly_allfamily_profile);


        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#22362D"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        init();
    }


    private void init() {

        database = new FamilyProfileDataSource(this);

        displayFamilyListNotification = (TextView) findViewById(R.id.tvNotification);


        allProfiles = new ArrayList<String>();

        // arrayListEmployee = new ArrayList<Employee>();
        familyProfileClassList = database.getAllFamilyProfile();

        if (!familyProfileClassList.isEmpty()) {
            //Toast.makeText(getApplicationContext(), String.valueOf(arrayListDoctor), Toast.LENGTH_LONG).show();
            lstView = (ListView) findViewById(R.id.FamilyLV);

            familyProfileClassList = database.getAllFamilyProfile();

            // use the SimpleCursorAdapter to show the
            // elements in a ListView
             adapter = new ArrayAdapter<FamilyProfileClass>(this,
                    android.R.layout.simple_list_item_1, familyProfileClassList);
            lstView.setAdapter(adapter);
            registerForContextMenu(lstView);
            adapter.notifyDataSetChanged();
        } else {
            displayFamilyListNotification.setText("There are no Family Profile; Please add Family Profile");
        }
    }




    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        if (v.getId()==R.id.FamilyLV){
            AdapterView.AdapterContextMenuInfo info= (AdapterView.AdapterContextMenuInfo)menuInfo;
            familyProfileClass=adapter.getItem(info.position);
            menu.setHeaderTitle(familyProfileClass.getFname());
            menu.add(0, v.getId(), 0, "Call");
            menu.add(0, v.getId(), 0, "Send SMS");
            menu.add(0, v.getId(), 0, "View Details");
            menu.add(0, v.getId(), 0, "Create Diet Chart");



           /* String[] menuItems = getResources().getStringArray(R.array.menu);
            for (int i = 0; i<menuItems.length; i++) {
                menu.add(Menu.NONE, i, i, menuItems[i]);
            }*/

        }

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();

        try {
            familyProfileClass=adapter.getItem(info.position);

            if (item.getTitle()=="Send SMS"){
                Toast.makeText(this, "send sms", Toast.LENGTH_LONG).show();
                Intent smsIntent = new Intent(Intent.ACTION_VIEW);
                smsIntent.setType("vnd.android-dir/mms-sms");
                smsIntent.putExtra("address", familyProfileClass.getFNumber());
                startActivity(smsIntent);
            }else if (item.getTitle()=="Call"){

                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + familyProfileClass.getFNumber()));
                startActivity(callIntent);
                Toast.makeText(this, "Call", Toast.LENGTH_LONG).show();
            }

            else if (item.getTitle()=="Delete") {


                database.deleteFamilyData(familyProfileClass.getFid());
                adapter = new ArrayAdapter<FamilyProfileClass>(this,
                        android.R.layout.simple_list_item_1, familyProfileClassList);
                lstView.setAdapter(adapter);
                registerForContextMenu(lstView);
                adapter.notifyDataSetChanged();

            }
            else if (item.getTitle()=="View Details") {


                String FName = adapter.getItem(info.position).getFname();
                String FNumber =adapter.getItem(info.position).getFNumber();
                String FRelationship = adapter.getItem(info.position).getFrelationship();
                String Fage =adapter.getItem(info.position).getFage();
                String FDateOfBirth = adapter.getItem(info.position).getFdateOfBirth();
                String FbloodGroup = adapter.getItem(info.position).getFbloodGroup();
                String FHeight = adapter.getItem(info.position).getFheight();
                String FWeight = adapter.getItem(info.position).getFweight();
                String FspecialNotes = adapter.getItem(info.position).getFspecialNotes();
                Intent i=new Intent(this,DisplayFamilyDetails.class);
                i.putExtra("FName", FName);
                i.putExtra("FNumber", FNumber);
                i.putExtra("FRelationship", FRelationship);
                i.putExtra("Fage", Fage);
                i.putExtra("FDateOfBirth", FDateOfBirth);
                i.putExtra("FbloodGroup", FbloodGroup);
                i.putExtra("FHeight", FHeight);
                i.putExtra("FWeight", FWeight);
                i.putExtra("FspecialNotes", FspecialNotes);
                startActivity(i);


            }
            else if (item.getTitle()=="Create Diet Chart") {
//                String FName = adapter.getItem(info.position).getFname();
//                Intent i=new Intent(this, FamilyDeitChartListTodayAndUpcoming.class);
//             //   i.putExtra("FName", FName);
//                startActivity(i);
            }
            else
            {
                return false;}
        }catch (Exception e){
            e.getMessage();
            return true;
        }


        return true;
    }



//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_dispaly_allfamily_profile, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.AddNewProfile) {
//
//            Intent intent= new Intent(this, FamilyProfile.class);
//            startActivity(intent);
//            return true;
//        }if(id==R.id.Home){
//            Intent intent= new Intent(this, MainActivity.class);
//            startActivity(intent);
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
