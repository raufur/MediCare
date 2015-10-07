package com.coders.epsilon.medicare.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.coders.epsilon.medicare.R;
import com.coders.epsilon.medicare.model.DoctorProfile;

import java.util.ArrayList;


/**
 * Created by Prodip on 6/21/2015.
 */
public class DoctorAdapter extends ArrayAdapter<String> {



    Context context;
    ArrayList<DoctorProfile> doctorProfiles;
    public DoctorAdapter(Context context, ArrayList doctorProfiles) {
        super(context, R.layout.custom_view_doctor,  doctorProfiles);
        this.context = context;
        //this.value = value;
        this. doctorProfiles =  doctorProfiles;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.custom_view_doctor,parent,false);
        TextView txtView = (TextView) rowView.findViewById(R.id.textViewDoctor);
        txtView.setText( doctorProfiles.get(position).getDname().toString());
        return rowView;
    }
}
