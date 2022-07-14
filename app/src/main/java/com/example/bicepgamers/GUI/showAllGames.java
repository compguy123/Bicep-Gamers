package com.example.bicepgamers.GUI;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bicepgamers.Logic.GameManager;
import com.example.bicepgamers.R;
import com.example.bicepgamers.objects.Game;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

/*
This class is the gui that shows the user all the games.
Is uses the recycler view class to show games
 */
public class ShowAllGames extends AppCompatActivity{

    //array list of games from database
    private ArrayList<Game> gameList;
    //logic layer
    private GameManager gameManager;
    //recycler viewer
    private RecyclerView recyclerView;
    private TextView noGameText;
    private RecyclerAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_all_games);
        navigationSetup();
        // get all games from database
        gameManager = new GameManager();
        gameList = new ArrayList<>();
        gameList.addAll(gameManager.getAllGames());

        makeAdapter();

        //create a add game buttom to add new games to databse
        Button addGameButton = findViewById(R.id.addGameButton);
        //take to different page if the user wants to add a game
        addGameButton.setOnClickListener(view -> openAddNewGame());

        //show text if no games are in the database
        noGameText = findViewById(R.id.emptyGames);

        if ( gameList.size() != 0 ) {
            noGameText.setVisibility(View.INVISIBLE);
        }
    }

    private void makeAdapter(){
        //create the reccyler adapter
        recyclerView = findViewById(R.id.recyclerViewGames);
        adapter = new RecyclerAdapter(gameList);
        layoutManager = new LinearLayoutManager(getApplicationContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        adapter.setOnGameListener(new RecyclerAdapter.OnGameListener() {
            @Override
            public void onGameClick(int position) {
                itemClick(position);
            }

            @Override
            public void onDeleteClick(int position) {
                deleteClick(position);
            }
        });
    }

    private void openAddNewGame() {
        //open add new gmae page whent he user clicks add new game button
        Intent intent = new Intent(this, AddNewGame.class);
        startActivity(intent);
    }

    public void itemClick(int position) {
        Intent intent = new Intent(this, SessionTimer.class);
        String[] gameVar = new String[5];

        gameVar[0] = gameList.get(position).getGameName();
        gameVar[1] = gameList.get(position).getGenre();
        gameVar[2] = gameList.get(position).getDevice();
        if(gameList.get(position).getType()) {
            gameVar[3] = "Online";
        }
        else {
            gameVar[3] = "Offline";
        }
        gameVar[4] = Integer.toString(gameList.get(position).getID());

        intent.putExtra("gameVar", gameVar);
        startActivity(intent);
    }

    public void deleteClick(int position) {
        gameManager.deleteGame(gameList.get(position));
        gameList.remove(position);
        adapter.notifyItemRemoved(position);

        if ( gameList.size() == 0 ) {
            noGameText.setVisibility(View.VISIBLE);
        }
    }

    private void navigationSetup() {
        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.games);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), HomePage.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.sessions:
                        startActivity(new Intent(getApplicationContext(), ShowAllSessions.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.games:
                        return true;
                }

                return false;
            }
        });
    }
}
