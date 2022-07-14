package com.example.bicepgamers.GUI;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bicepgamers.Logic.GameManager;
import com.example.bicepgamers.R;
import com.example.bicepgamers.objects.DeviceEnum;
import com.example.bicepgamers.objects.Game;
import com.example.bicepgamers.objects.GenreEnum;


/*
    This class is the GUI for adding a new game to the database
 */

public class AddNewGame extends AppCompatActivity {
    //gui addons
    private Button createGame;
    private EditText getGameName;
    private Switch onlines;
    private android.widget.Spinner deviceSpinner;
    private android.widget.Spinner genreSpinner;
    String genreName, deviceName;
    private GameManager gameManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_game);

        gameManager = new GameManager();

        //create a spinner that lets user choose what device their game uses
        deviceSpinner = findViewById(R.id.deviceSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.devices, android.R.layout.simple_spinner_item);
        //use device enum to show user
        deviceSpinner.setAdapter(new ArrayAdapter<DeviceEnum>(this, android.R.layout.simple_spinner_item, DeviceEnum.values()));
        deviceSpinner.setOnItemSelectedListener(new Spinner(this));
        //get device choosen. This will be used in iteration 2 to create a new game and add to database

        //get the genre of the game, used in iteration 2
        genreSpinner = findViewById(R.id.genreSpinner);
        ArrayAdapter<CharSequence> adapterGenre = ArrayAdapter.createFromResource(this, R.array.genre, android.R.layout.simple_spinner_dropdown_item);
        //use the genre enum to show user
        genreSpinner.setAdapter(new ArrayAdapter<GenreEnum>(this, android.R.layout.simple_spinner_item, GenreEnum.values()));
        genreSpinner.setOnItemSelectedListener(new Spinner(this));

        //reference to buttons and spinners
        createGame = findViewById(R.id.createGameButton);
        getGameName = findViewById(R.id.gameNameTxt);
        onlines = findViewById(R.id.onlineSwitch);

        //back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //click listens for buttons, for iteration 2
        createGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get the game created
                createGameClick();
            }
        });
    }

    //back button
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    private void createGameClick() {
        deviceName = deviceSpinner.getSelectedItem().toString();
        genreName = genreSpinner.getSelectedItem().toString();
       // if game name is null, tell user that they need to insert a game
        if(getGameName.getText().toString().matches("")){
           AlertDialog.Builder dialog = new AlertDialog.Builder(this);
           dialog.setTitle("Input A Game Name");
           dialog.setCancelable(false);
           dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface dialog, int which) {
               }
           });
           dialog.show();
        }

       // if game name is not null , create a new game.
       else {
           Game game = new Game(getGameName.getText().toString(), genreName, deviceName, onlines.isChecked());
           Toast.makeText(AddNewGame.this, "Your game : " + game.toString() + " has been added", Toast.LENGTH_SHORT).show();

           //add game to database
           gameManager.insertGame(game);

           Intent intent = new Intent(this, ShowAllGames.class);
           startActivity(intent);
       }
    }
}
