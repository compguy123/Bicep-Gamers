package com.example.bicepgamers.GUI;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bicepgamers.R;
import com.example.bicepgamers.objects.VideoObject;

import java.util.ArrayList;

public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutAdapter.ViewHolder> {

    private ArrayList<VideoObject> videoList;
    private OnVideoListener mOnVideoListener;

    //constructor takes the list of videos to put into recyclerview
    public WorkoutAdapter(ArrayList<VideoObject> videoList){ this.videoList = videoList;}

    //method to record which video the user clicks on
    public void setOnVideoListener(WorkoutAdapter.OnVideoListener onVideoListener) {
        this.mOnVideoListener = onVideoListener;
    }

    //sets the UI for the recyclerview
    @NonNull
    @Override
    public WorkoutAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_cell, parent, false);
        return new ViewHolder(view, mOnVideoListener);
    }

    //updates the viewholder for each item with the required content to display to user.
    @Override
    public void onBindViewHolder(@NonNull WorkoutAdapter.ViewHolder holder, int position) {
        int resource = videoList.get(position).getImageView();
        String name = videoList.get(position).getTitle();

        holder.setData(resource, name);

    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }

    public interface OnVideoListener {
        void onPlayClick(int position);
    }

    //assists the recyclerview to display items on screen
    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        private TextView textView;
        private ImageView playBtn;

        public ViewHolder(@NonNull View itemView, WorkoutAdapter.OnVideoListener onVideoListener) {
            super(itemView);

            imageView = itemView.findViewById(R.id.workoutImage);
            textView = itemView.findViewById(R.id.videoText);

            playBtn = itemView.findViewById(R.id.videobtn);
            //records which item is clicked on
            playBtn.setOnClickListener(view -> {
                if(onVideoListener != null) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION) {
                        onVideoListener.onPlayClick(position);
                    }
                }
            });


        }

        public void setData(int resource, String title){
            imageView.setImageResource(resource);
            textView.setText(title);
        }
    }
}
