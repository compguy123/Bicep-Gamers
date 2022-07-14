package com.example.bicepgamers.GUI;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.bicepgamers.R;
import com.example.bicepgamers.objects.Game;
import java.util.ArrayList;
import java.util.List;

/*
This class is the gui that shows the user all the games.
Is uses the recycler view class to show games
implemented in iteration 2
 */
public class showAllGames extends AppCompatActivity {
    private List<Game> gameList= new ArrayList<>();
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_all_games);
        recyclerView=findViewById(R.id.recyclerViewGames);
        addfakeData();
        makeAdapter();
    }

    private void makeAdapter(){
        recyclerViewer adapter= new recyclerViewer(gameList);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    //fake data base but could not implement for iteration 1
    private void addfakeData(){
        Game a =new Game (1,"fake1","fake1","fake1",1);
        Game b =new Game (1,"fake2","fake2","fake2",1);
        Game c =new Game (1,"fake3","fake3","fake4",1);
        gameList.add(a);
        gameList.add(b);
        gameList.add(c);

    }

}
