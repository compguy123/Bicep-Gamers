package com.example.bicepgamers.GUI;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bicepgamers.R;


/*
    This class is the GUI for adding a new game to the database
 */

public class addANewGame extends AppCompatActivity {
    //gui addons
    private Button createGame;
    private EditText getGameName;
    private Switch onlines;
    private android.widget.Spinner deviceSpinner;
    private android.widget.Spinner genreSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_game);

        //create a spinner that lets user choose what device their game uses
        deviceSpinner = findViewById(R.id.deviceSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.devices, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        deviceSpinner.setAdapter(adapter);
        deviceSpinner.setOnItemSelectedListener(new Spinner(this));
        //get device choosen. This will be used in iteration 2 to create a new game and add to database
        String deviceName = deviceSpinner.getSelectedItem().toString();


        //get the genre of the game, used in iteration 2
        String genreName = genreSpinner.getSelectedItem().toString();

        //reference to buttons and spinners
        createGame = findViewById(R.id.createGameButton);
        getGameName = findViewById(R.id.gameNamePlainText);
        onlines = findViewById(R.id.onlineSwitch);

        //click listens for buttons, for iteration 2
        createGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}