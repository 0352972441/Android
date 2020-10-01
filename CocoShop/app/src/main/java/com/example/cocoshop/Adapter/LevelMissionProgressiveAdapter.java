package com.example.cocoshop.Adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cocoshop.Models.ProgressiveModels.LevelMap;
import com.example.cocoshop.Models.ProgressiveModels.Star;
import com.example.cocoshop.R;

import java.util.ArrayList;

public class LevelMissionProgressiveAdapter extends RecyclerView.Adapter<LevelMissionProgressiveAdapter.ViewHolder> {
    private ArrayList<LevelMap> progressive;
    public LevelMissionProgressiveAdapter(ArrayList<LevelMap> progressive) {
        this.progressive = progressive;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.level_map_progressive,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(progressive != null){
            holder.txLessonName.setText(progressive.get(position).getLesson());
            Star star = progressive.get(position).getStar();
            switch (star){
                case NONE:
                    holder.imgStarBad.setImageResource(R.drawable.ic_star_border_none_unfinish_24dp);
                    holder.imgStarGood.setImageResource(R.drawable.ic_star_border_none_unfinish_24dp);
                    holder.imgStarExellent.setImageResource(R.drawable.ic_star_border_none_unfinish_24dp);
                    break;
                case BAD:
                    holder.imgStarBad.setImageResource(R.drawable.ic_star_border_none_unfinish_24dp);
                    holder.imgStarGood.setImageResource(R.drawable.ic_star_yellow_finish_24dp);
                    holder.imgStarExellent.setImageResource(R.drawable.ic_star_border_none_unfinish_24dp);
                    break;
                case GOOD:
                    holder.imgStarGood.setImageResource(R.drawable.ic_star_border_none_unfinish_24dp);
                    holder.imgStarBad.setImageResource(R.drawable.ic_star_yellow_finish_24dp);
                    holder.imgStarExellent.setImageResource(R.drawable.ic_star_yellow_finish_24dp);
                    break;
                case EXCELLENT:
                    holder.imgStarExellent.setImageResource(R.drawable.ic_star_yellow_finish_24dp);
                    holder.imgStarGood.setImageResource(R.drawable.ic_star_yellow_finish_24dp);
                    holder.imgStarBad.setImageResource(R.drawable.ic_star_yellow_finish_24dp);
                    break;
            }
        }
    }

    /*private ArrayList<ImageView> completeStar(Star countStar, Context context){
        listStar = new ArrayList<>();
        for(int i=0; i< 3; i++){
            ImageView star = new ImageView(context);
            star.setImageResource(R.drawable.ic_star_yellow_finish_24dp);
            if(countStar == Star.BAD){
                if(i%2 != 0){
                    star.setImageResource(R.drawable.ic_star_yellow_finish_24dp);
                }else{
                    star.setImageResource(R.drawable.ic_star_border_none_unfinish_24dp);
                }
            }
            if(countStar == Star.GOOD){
                if(i%2 != 0){
                    star.setImageResource(R.drawable.ic_star_border_none_unfinish_24dp);
                }else{
                    star.setImageResource(R.drawable.ic_star_yellow_finish_24dp);
                }
            }
            star.setMinimumWidth(24);
            star.setMinimumHeight(24);
            listStar.add(star);
        }
        return listStar;
    }
*/
    @Override
    public int getItemCount() {
        return progressive.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txLessonName;
        private LinearLayout lgStar;
        private ImageView imgBackgroundMission,imgStarBad, imgStarGood,imgStarExellent;
        private View view;
        public ViewHolder(@NonNull View view) {
            super(view);
            this.view = view;
            txLessonName = (TextView)view.findViewById(R.id.txLessonName);
            lgStar = (LinearLayout)view.findViewById(R.id.star);
            imgBackgroundMission = (ImageView)view.findViewById(R.id.background_mission);
            imgStarBad = (ImageView)view.findViewById(R.id.imgStarBad);
            imgStarGood = (ImageView)view.findViewById(R.id.imgStarGood);
            imgStarExellent = (ImageView)view.findViewById(R.id.imgStarExellent);
        }
    }
}
