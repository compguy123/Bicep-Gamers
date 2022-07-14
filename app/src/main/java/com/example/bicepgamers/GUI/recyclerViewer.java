package com.example.bicepgamers.GUI;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.bicepgamers.R;
import com.example.bicepgamers.objects.Game;
import java.util.List;

/*
THis class is used in the show al games gui class
ITs is a recycler view that is used to show all games
implemented in iteration 2
 */
public class recyclerViewer extends RecyclerView.Adapter<recyclerViewer.MyViewHolder>{
    //This is the gameList that will be show in the show all game class
    private List<Game> gameList;

    public recyclerViewer (List <Game> gameList){
        this.gameList=gameList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView nameTxt;

        public MyViewHolder(final View view){
            super(view);
            nameTxt =view.findViewById(R.id.gameNameTxt);
        }
    }
    @NonNull
    @Override
    public recyclerViewer.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_all_games,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String gameName = gameList.get(position).getGameName();
        holder.nameTxt.setText(gameName);
    }


    @Override
    public int getItemCount() {
        return gameList.size();
    }


}