package com.coders.epsilon.medicare.activities;

/**
 * Created by nilima on 10/6/2015.
 */
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.coders.epsilon.medicare.R;

/**
 * Created by Edwin on 15/02/2015.
 */
public class Home extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.activity_home,container,false);
        return v;
    }
}