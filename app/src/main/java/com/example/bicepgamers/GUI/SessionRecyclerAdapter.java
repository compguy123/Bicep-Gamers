package com.example.bicepgamers.GUI;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bicepgamers.R;
import com.example.bicepgamers.objects.Session;

import java.util.ArrayList;
import java.util.Locale;

//This the recycler adapter that is used to show all the sessions.
// This is used by the showallsessions class, which contains an array list of all the sessions
public class SessionRecyclerAdapter extends RecyclerView.Adapter<SessionRecyclerAdapter.MyViewHolder>{
    private ArrayList<Session> sessionList;
    private OnSessionListener mOnSessionListener;

    public SessionRecyclerAdapter(ArrayList <Session> sessionList){
        this.sessionList=sessionList;
    }

    public void setOnSessionListener(OnSessionListener onSessionListener) {
        this.mOnSessionListener = onSessionListener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        //parameter of a single display inside the recycler viewer
        private TextView gameTxt;
        private TextView genreTxt;
        private TextView deviceTxt;
        private TextView typeTxt;
        private TextView sessionLengthTxt;
        private TextView gameLengthTxt;
        private TextView breakLengthTxt;

        private ImageView deleteSession;

        public MyViewHolder(@NonNull View itemView, OnSessionListener onSessionListener){
            super(itemView);
            //set parameters
            gameTxt = itemView.findViewById(R.id.sessionCellGameName);
            genreTxt = itemView.findViewById(R.id.sessionCellGenre);
            deviceTxt = itemView.findViewById(R.id.sessionCellDevice);
            typeTxt = itemView.findViewById(R.id.sessionCellType);
            sessionLengthTxt = itemView.findViewById(R.id.sessionCellLength);
            gameLengthTxt = itemView.findViewById(R.id.sessionCellGameLength);
            breakLengthTxt = itemView.findViewById(R.id.sessionCellBreakLength);

            deleteSession = itemView.findViewById(R.id.deleteSession);

            deleteSession.setOnClickListener(view -> {
                if(onSessionListener != null) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION) {
                        onSessionListener.onDeleteClick(position);
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public SessionRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.session_cell,parent,false);
        return new MyViewHolder(itemView, mOnSessionListener);
    }

    @Override
    // get all data from arralylist from showallsession class
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String gameName = sessionList.get(position).getGameName();
        holder.gameTxt.setText(gameName);

        String genre = sessionList.get(position).getGameGenre();
        holder.genreTxt.setText(genre);

        String device = sessionList.get(position).getGameDevice();
        holder.deviceTxt.setText(device);

        String type= sessionList.get(position).getGameType();
        holder.typeTxt.setText(type);

        String sessionLength = getTimerText(sessionList.get(position).getSessionLength());
        holder.sessionLengthTxt.setText(sessionLength);

        String gameLength = getTimerText(sessionList.get(position).getGameLength());
        holder.gameLengthTxt.setText(gameLength);

        String breakLength = getTimerText(sessionList.get(position).getBreakLength());
        holder.breakLengthTxt.setText(breakLength);

    }

    @Override
    public int getItemCount() {
        return sessionList.size();
    }

    public interface OnSessionListener {
        void onDeleteClick(int position);
    }

    // turn a double into time
    private String getTimerText(double time) {
        int round = (int) Math.round(time);
        int seconds = ((round % 86400) % 3600) % 60;
        int minutes = ((round % 86400) % 3600) / 60;
        int hours = ((round % 86400) / 3600);

        return formatTime(seconds, minutes, hours);
    }

    private String formatTime(int seconds, int minutes, int hours) {
        return String.format(Locale.getDefault(), "%02d", hours) + ":" +
                String.format(Locale.getDefault(),"%02d", minutes) + ":" +
                String.format(Locale.getDefault(),"%02d", seconds);
    }
}
