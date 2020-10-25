package com.example.cocoshop.adapter;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.cocoshop.R;
import com.example.cocoshop.animation.Animations;
import com.example.cocoshop.listener.ListenerAnswer;
import com.example.cocoshop.listener.ListenerViewPage;
import com.example.cocoshop.screen.FinishReviewScreen;
import com.example.cocoshop.screen.ReviewActivity;

import java.util.List;
import java.util.Map;

public class ReviewVocabularyAdapter extends RecyclerView.Adapter<ReviewVocabularyAdapter.ViewHolder> {
    private List<Map<String,Object>> topicList;
    private ListenerViewPage onClickNextQuestion;
    private boolean isCorrected = false;
    // Số câu hỏi
    private static int numberOfQuestion = 0;
    private Activity activity;
    public ReviewVocabularyAdapter(List<Map<String,Object>> topicList, Activity activity) {
        this.topicList = topicList;
        numberOfQuestion = topicList.size();
        this.activity = activity;
    }

    public void setOnClickNextQuestion(ListenerViewPage onClickNextQuestion) {
        this.onClickNextQuestion = onClickNextQuestion;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.review_vocabulary_favorite,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        if(topicList != null){
            final String answer = topicList.get(position).get("vocabulary").toString();
            holder.mTxMean.setText(topicList.get(position).get("mean").toString());
            WordAnswerAdapter answerAdapter = new WordAnswerAdapter(topicList.get(position).get("vocabulary").toString());
            final WordLineAdapter lineAdapter = new WordLineAdapter(topicList.get(position).get("vocabulary").toString());

            // Set Adapter cho recyclerView
            holder.mWordRecycler.setAdapter(answerAdapter);
            holder.mLineRecycler.setAdapter(lineAdapter);
            holder.mLineRecycler.setLayoutManager(new GridLayoutManager(holder.mLineRecycler.getContext(),4));
            holder.mWordRecycler.setLayoutManager(new GridLayoutManager(holder.mLineRecycler.getContext(),4));

            // Lắng nghe sự kiện khi click vào một chữ để điền vào ô
            answerAdapter.setOnClickWordAnswer(new ListenerAnswer() {
                @Override
                public void listenerAnswer(int position, String answers) {
                    for(int i=0; i<answer.length(); i++){
                        if(lineAdapter.getViewPosition(i).getText().toString().equalsIgnoreCase("")){
                            lineAdapter.getViewPosition(i).setText(answers);
                            break;
                        }
                    }
                }
            });

            // Lắng nghe sự kiện kiểm tra đáp án của hệ thống và câu trả lời người dùng đã trùng khớp chưa
            holder.mBtnCheck.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MediaPlayer mediaPlayer = null;
                    // Nếu đã trả lời thì chuyên nút check thành nút  chuyển câu hỏi kế tiếp
                    if(isCorrected){
                        // Next câu hỏi
                        onClickNextQuestion.onClickListener();
                        isCorrected = false;// Sau khi chuyển câu hỏi thì set về lại false để nút next thành nút check
                        if(position+1 == topicList.size()){// Nếu là câu cuối cùng thì nút next chuyên sang màn hình tổng kết
                            Intent intent = new Intent(holder.mBtnCheck.getContext(), FinishReviewScreen.class);
                            intent.putExtra(FinishReviewScreen.REVIEW,topicList.size());
                            intent.putExtra(FinishReviewScreen.NUMBEROFQUESTION,numberOfQuestion);
                            holder.img_Correction.getContext().startActivity(intent);
                            activity.finish();
                        }
                    }else {
                        // Nếu đáp án trung khớp thì
                        if (answer.trim().equalsIgnoreCase(lineAdapter.getAnswer())) {
                            mediaPlayer = MediaPlayer.create(holder.mTxMean.getContext(), R.raw.correct_answer);
                            mediaPlayer.start();
                            holder.img_Correction.setVisibility(View.VISIBLE);
                            Toast.makeText(holder.mLineRecycler.getContext(), "Correct", Toast.LENGTH_SHORT).show();
                            isCorrected = true;
                            holder.mBtnCheck.setText("Next");
                            Animations animations = new Animations(holder.mTxMean.getContext());
                            Animation animation = animations.fadeIn(2000);
                            holder.img_Correction.startAnimation(animation);
                            animation.setAnimationListener(new Animation.AnimationListener() {
                                @Override
                                public void onAnimationStart(Animation animation) {

                                }

                                @Override
                                public void onAnimationEnd(Animation animation) {
                                    holder.img_Correction.setVisibility(View.INVISIBLE);
                                }

                                @Override
                                public void onAnimationRepeat(Animation animation) {

                                }
                            });
                        } else {
                            // Nếu đáp án sai thì thông báo âm thanh wrong và trư đi -1 điêm vì đã trả lời sai
                            mediaPlayer = MediaPlayer.create(holder.mTxMean.getContext(), R.raw.wrong_answer_sound);
                            mediaPlayer.start();
                            numberOfQuestion = numberOfQuestion - 1;
                        }
                    }
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        if(topicList == null){
            return 0;
        }
        return topicList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView mTxMean;
        private Button mBtnCheck;
        private RecyclerView mLineRecycler,mWordRecycler;
        private ImageView img_Correction;
        public ViewHolder(@NonNull View view) {
            super(view);
            // Button
            mBtnCheck = view.findViewById(R.id.btn_check_answer);

            // TextView
            mTxMean = view.findViewById(R.id.tx_mean);

            // RecyclerView
            mLineRecycler = view.findViewById(R.id.line_recycler);
            mWordRecycler = view.findViewById(R.id.word_recycler);

            // ImageView
            img_Correction = view.findViewById(R.id.img_correction);
            // Ratingbar
        }
    }
}

