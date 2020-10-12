package com.example.cocoshop.Adapter.topicsadapter;

import android.media.MediaPlayer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.cocoshop.Models.vocabularysmodel.Vocabulary;
import com.example.cocoshop.R;
import com.example.cocoshop.dao.audiodao.FavoriteVocabularyDao;
import com.example.cocoshop.dao.audiodao.VocabularyLikedDao;
import com.example.cocoshop.listener.Listener;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewPagerVocabulary extends RecyclerView.Adapter<ViewPagerVocabulary.ViewHolder> {
    private ArrayList<Vocabulary> vocabularies;
    private Listener onClickNextVocabulary;
    private Listener onClickMultipleChoice;
    private FavoriteVocabularyDao favoriteVocabularyDao;
    private boolean isReaded = false;
    private boolean isFavorite = false;
    public ViewPagerVocabulary(ArrayList<Vocabulary> vocabularies) {
        this.vocabularies = vocabularies;
    }

    public ViewPagerVocabulary() {
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public void setOnClickNextVocabulary(Listener onClickNextVocabulary) {
        this.onClickNextVocabulary = onClickNextVocabulary;
    }

    public void setOnClickMultipleChoice(Listener onClickMultipleChoice) {
        this.onClickMultipleChoice = onClickMultipleChoice;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.vocabulary,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        if(vocabularies!= null){
            final Map<String,Object> item = (Map<String, Object>) vocabularies.get(position);
            holder.txSpelling.setText(item.get("spelling").toString());
            holder.txVocabulary.setText(item.get("vocabulary").toString());
            holder.txMeans.setText(item.get("mean").toString());
            new VocabularyLikedDao(holder.imgFavoriteVocabulary).execute(item.get("vocabulary").toString());
            holder.imgFavoriteVocabulary.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(isFavorite) {
                        favoriteVocabularyDao= new FavoriteVocabularyDao(true);
                        holder.imgFavoriteVocabulary.setImageResource(R.drawable.ic_un_favorite_audio_24dp);
                        favoriteVocabularyDao.execute(item);
                        isFavorite = false;
                    }else{
                        favoriteVocabularyDao = new FavoriteVocabularyDao(false);
                        favoriteVocabularyDao.execute(item);
                        holder.imgFavoriteVocabulary.setImageResource(R.drawable.ic_on_favorite_audio_24dp);
                        isFavorite = true;
                    }
                }
            });
            // Click để hiện thị bảng từ vựng chi tiết
            holder.txDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.bottomSheet.setVisibility(View.VISIBLE);
                    /*holder.imgNextVocabulary.setVisibility(View.INVISIBLE);
                    holder.imgFavoriteVocabulary.setVisibility(View.INVISIBLE);*/
                    holder.layoutNextvocabulary.setVisibility(View.INVISIBLE);
                    holder.txDetail.setVisibility(View.INVISIBLE);
                }
            });
            // Click ẩn băng chi tiết từ vựng
            holder.imgHideBottomSheet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.bottomSheet.setVisibility(View.INVISIBLE);
                    if(isReaded){
                       /* holder.imgNextVocabulary.setVisibility(View.VISIBLE);
                        holder.imgFavoriteVocabulary.setVisibility(View.VISIBLE);*/
                        holder.layoutNextvocabulary.setVisibility(View.VISIBLE);
                    }
                    holder.txDetail.setVisibility(View.VISIBLE);
                }
            });

            // OnClick Chuyên sang từ vựng kế tiếp
            holder.imgNextVocabulary.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickNextVocabulary.listener(position);
                    isReaded = false;
                    holder.layoutNextvocabulary.setVisibility(View.INVISIBLE);
                }
            });
            // Khởi tao mediaPlay
            final MediaPlayer mediaPlayer = new MediaPlayer();
            try {
                // Set đường dẫn file âm thanh
                mediaPlayer.setDataSource(item.get("read").toString());
                // Lăng nghe chuẩn bị
                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        // Bắt đầu(Chạy file Phát âm thanh)
                        //mp.start();
                    }
                });
                mediaPlayer.prepare();
                // CLick vào loa để phát file âm thanh
                holder.imgSpeaker.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mediaPlayer.start();
                        isReaded = true;
                        // Sau khi phát xong hiển thị nút click từ vựng kế tiếp và thích từ vựng
                        if(isReaded){
                            holder.layoutNextvocabulary.setVisibility(View.VISIBLE);
                            if(position == vocabularies.size()-1){
                                //holder.imgNextVocabulary.setVisibility(View.INVISIBLE);
                                holder.imgNextVocabulary.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        onClickMultipleChoice.listener(position);
                                    }
                                });
                            }
                        }
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public int getItemCount() {
        return vocabularies.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgSpeaker,imgDescription,imgHideBottomSheet,imgNextVocabulary,imgFavoriteVocabulary;
        private TextView txVocabulary,txSpelling,txDetail,txMeans;
        private RelativeLayout bottomSheet,layoutNextvocabulary;
        public ViewHolder(@NonNull View view) {
            super(view);
            txSpelling = (TextView)view.findViewById(R.id.spelling);
            txVocabulary = (TextView)view.findViewById(R.id.vocabulary);
            imgDescription = (ImageView)view.findViewById(R.id.img_description);
            imgSpeaker = (ImageView)view.findViewById(R.id.speaker);
            txDetail = (TextView)view.findViewById(R.id.tx_detail);
            bottomSheet = (RelativeLayout)view.findViewById(R.id.bottom_sheet);
            txMeans = (TextView)view.findViewById(R.id.means);
            imgHideBottomSheet = (ImageView)view.findViewById(R.id.img_hide_bottom);
            layoutNextvocabulary = (RelativeLayout)view.findViewById(R.id.layout_next_vocabulary);
            imgFavoriteVocabulary = (ImageView)view.findViewById(R.id.img_favorite_vocabulary);
            imgNextVocabulary = (ImageView)view.findViewById(R.id.img_next_vocabulary);
        }
    }
}
