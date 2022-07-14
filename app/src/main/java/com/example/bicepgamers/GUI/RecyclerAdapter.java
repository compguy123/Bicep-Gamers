package com.example.bicepgamers.GUI;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.example.bicepgamers.R;
import com.example.bicepgamers.objects.Game;

import java.util.ArrayList;

/*
THis class is used in the show all games in the gui class
ITs is a recycler view that is used to show all games
This class is used by the class showallgames which contains all the games
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>{
    //This is the gameList that will be show in the show all game class
    private ArrayList<Game> gameList;
    private ArrayList<Integer> iconList;
    private OnGameListener mOnGameListener;

    public RecyclerAdapter(ArrayList <Game> gameList){
        this.gameList=gameList;
        iconList= new ArrayList<Integer>();

        //icons of the 3 games we have implemented
        iconList.add(R.drawable.lol_icon);
        iconList.add(R.drawable.mario_icon);
        iconList.add(R.drawable.call_of_duty);
    }

    public void setOnGameListener(OnGameListener onGameListener) {
        this.mOnGameListener = onGameListener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        //the variables of each item inside the recycler viewer
        private TextView gameTxt;
        private TextView genreTxt;
        private TextView deviceTxt;
        private TextView typeTxt;
        private ImageView deleteGame;
        private ImageView gameIcons;

        public MyViewHolder(@NonNull View itemView, OnGameListener onGameListener){
            super(itemView);
            //set parameters
            gameTxt = itemView.findViewById(R.id.gameName);
            genreTxt = itemView.findViewById(R.id.genre);
            deviceTxt = itemView.findViewById(R.id.device);
            typeTxt = itemView.findViewById(R.id.type);
            deleteGame = itemView.findViewById(R.id.deleteGame);
            gameIcons = itemView.findViewById(R.id.imageView);

            //????????????
            itemView.setOnClickListener(view -> {
                if(onGameListener != null) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION) {
                        onGameListener.onGameClick(position);
                    }
                }
            });

            deleteGame.setOnClickListener(view -> {
                if(onGameListener != null) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION) {
                        onGameListener.onDeleteClick(position);
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.game_cell,parent,false);
        return new MyViewHolder(itemView, mOnGameListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // get the game data from the array list and put each game in the recycler viewer
        String gameName = gameList.get(position).getGameName();
        holder.gameTxt.setText(gameName);

        String genre = gameList.get(position).getGenre();
        holder.genreTxt.setText(genre);

        String device = gameList.get(position).getDevice();
        holder.deviceTxt.setText(device);

        String type;
        if(gameList.get(position).getType()) {
            type = "Online";
        }
        else {
            type = "Offline";
        }
        holder.typeTxt.setText(type);

        if (position < iconList.size() && gameList.get(position).getID() == position) {
        Integer icon = iconList.get(position);

            holder.gameIcons.setImageResource(icon);
        }
    }

    @Override
    public int getItemCount() {
        return gameList.size();
    }

    public interface OnGameListener {
        void onGameClick(int position);
        void onDeleteClick(int position);
    }

}
