package com.example.bicepgamers.GUI;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
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


import com.example.bicepgamers.Logic.SessionManager;
import com.example.bicepgamers.R;
import com.example.bicepgamers.objects.Session;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

//THis class shows all the sessions that a user has done
public class ShowAllSessions extends AppCompatActivity {
    private ArrayList<Session> sessionList;
    private SessionManager sessionManager;

    private RecyclerView recyclerView;
    private SessionRecyclerAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private TextView noHistoryText;

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_all_sessions);
        navigationSetup();

        noHistoryText = findViewById(R.id.emptyHistory);

        sessionManager = new SessionManager();
        sessionList = new ArrayList<>();
        sessionList.addAll(sessionManager.getAllSessions());
        makeAdapter();

        if ( sessionList.size() != 0 ) {
            noHistoryText.setVisibility(View.INVISIBLE);
        }

    }

    private void makeAdapter(){
        recyclerView = findViewById(R.id.recyclerViewSessions);
        adapter = new SessionRecyclerAdapter(sessionList);
        layoutManager = new LinearLayoutManager(getApplicationContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        adapter.setOnSessionListener(new SessionRecyclerAdapter.OnSessionListener() {
            @Override
            public void onDeleteClick(int position) {
                deleteClick(position);
            }
        });
    }

    public void deleteClick(int position) {
        sessionManager.deleteSession(sessionList.get(position));
        sessionList.remove(position);
        adapter.notifyItemRemoved(position);

        if ( sessionList.size() == 0 ) {
            noHistoryText.setVisibility(View.VISIBLE);
        }

    }

    private void navigationSetup() {
        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.sessions);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), HomePage.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.games:
                        startActivity(new Intent(getApplicationContext(), ShowAllGames.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.sessions:
                        return true;
                }

                return false;
            }
        });
    }
}
