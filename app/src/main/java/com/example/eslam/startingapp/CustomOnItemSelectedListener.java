package com.example.eslam.startingapp;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

/**
 * Created by islam on 24/01/17.
 */

public class CustomOnItemSelectedListener implements android.widget.AdapterView.OnItemSelectedListener {
    private String piriority;
    private Spinner spinner;
    public CustomOnItemSelectedListener(String piriority,Spinner spinner){
        this.piriority=piriority;
        this.spinner=spinner;
    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        piriority=String.valueOf(spinner.getSelectedItem());
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public String getPiriority() {
        return piriority;
    }

    public void setPiriority(String piriority) {
        this.piriority = piriority;
    }
}
