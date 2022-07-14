package com.example.bicepgamers.GUI;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
/*
This is the spinner class used in addAGame class
Implemented in iteration 1
 */
public class Spinner implements AdapterView.OnItemSelectedListener {
    private Context context;

    public Spinner(Context context) {
        this.context = context;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String deviceName = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(getApplicationContext(), deviceName, Toast.LENGTH_LONG).show();
    }

    private Context getApplicationContext() {
        return this.context;
    }

    //on defaults selects an option, method never used
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
