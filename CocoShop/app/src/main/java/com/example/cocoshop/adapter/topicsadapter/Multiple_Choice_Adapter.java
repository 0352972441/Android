package com.example.cocoshop.Adapter.topicsadapter;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.cocoshop.Models.vocabularysmodel.Vocabulary;
import com.example.cocoshop.R;
import com.example.cocoshop.listener.OnClickListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class Multiple_Choice_Adapter extends RecyclerView.Adapter<Multiple_Choice_Adapter.ViewHolder> {
    private ArrayList<Vocabulary> vocabularies = new ArrayList<>();
    private OnClickListener onClickNextAnswerListener;
    private  int score=0;
    private  int correctAnswer = 0;
    private boolean isAnswered = false;
    private MediaPlayer answerSound;
    private ArrayList<Integer> number = new ArrayList<>();
    //private static int index = 0;

    public Multiple_Choice_Adapter(ArrayList<Vocabulary> vocabularies) {
        this.vocabularies = vocabularies;
        Collections.shuffle(vocabularies);
        number.add(0);
        number.add(1);
        number.add(2);
        number.add(3);
    }

    public void setOnClickNextAnswerListener(OnClickListener onClickNextAnswerListener) {
        this.onClickNextAnswerListener = onClickNextAnswerListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.multiple_choice_vocabulary,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        if(vocabularies != null){
            final TextView[] textViews = {holder.txAnswer1,holder.txAnswer2,holder.txAnswer3,holder.txAnswer4};
            final CardView[] cardAnswer = {holder.cardAnswer1,holder.cardAnswer2,holder.cardAnswer3,holder.cardAnswer4};
            final MediaPlayer mediaPlayer = new MediaPlayer();
            Map<String,Object> item = (Map<String, Object>) vocabularies.get(position);

            Log.d("Data",""+item);
            holder.tx_Mean.setText(item.get("mean").toString());
            final ArrayList<String> answer = new ArrayList<>();
            final String vocabuary = item.get("vocabulary").toString();
            Log.d("Vocabulary:",vocabuary);
            answer.add(vocabuary);
            answer.add(String.valueOf(vocabuary.charAt(vocabuary.length()-1))+vocabuary);
            Collections.shuffle(number);
            if(vocabuary.length() > number.get(0)) {
                answer.add(vocabuary.substring(0, number.get(1))+String.valueOf(vocabuary.charAt(vocabuary.length()-number.get(0)))+vocabuary.substring(number.get(0) + 1, vocabuary.length()));
                answer.add(vocabuary.substring(0, number.get(0)) + vocabuary.substring(number.get(0) + 1, vocabuary.length()));
            }

            for(int i=0; i<number.size();i++){
                final int[] index = new int[1];
                index[0] = i;
                textViews[index[0]].setText(answer.get(number.get(i)));
                cardAnswer[index[0]].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(textViews[index[0]].getText().toString().equalsIgnoreCase(vocabuary)){
                            if(!isAnswered){
                                answerSound = MediaPlayer.create(holder.bottom_sheet.getContext(),R.raw.correct_answer);
                                answerSound.start();
                                holder.bottom_sheet.setVisibility(View.VISIBLE); //Show infomation
                                cardAnswer[index[0]].setBackgroundColor(Color.GREEN);
                                score+=10;
                                correctAnswer +=1;
                                //new Multiple_Choice_Dao(cardAnswer[index[0]],holder.tx_score,holder.bottom_sheet,Color.GREEN,R.raw.correct_answer).execute();
                                isAnswered = true;
                            }
                        }else{
                            if(!isAnswered){
                                answerSound = MediaPlayer.create(holder.bottom_sheet.getContext(),R.raw.wrong_answer_sound);
                                answerSound.start();
                                cardAnswer[index[0]].setBackgroundColor(Color.RED);
                                holder.bottom_sheet.setVisibility(View.VISIBLE);
                                //new Multiple_Choice_Dao(cardAnswer[index[0]],holder.tx_score,holder.bottom_sheet,Color.RED,R.raw.wrong_answer_sound).execute();
                                isAnswered = true;
                            }
                        }
                    }
                });
            }

            try {
                mediaPlayer.setDataSource(item.get("read").toString());
                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        //TODO
                    }
                });
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }

            holder.img_speaker.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mediaPlayer.start();
                }
            });

            if(position == vocabularies.size()-1){
                holder.img_next_question.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        holder.relativeMultipChoice.setVisibility(View.INVISIBLE);
                        holder.relative_Question.setVisibility(View.INVISIBLE);
                        holder.bottom_sheet.setVisibility(View.INVISIBLE);
                        holder.tx_Answer_Correct.setText(String.valueOf(correctAnswer)+" / "+ vocabularies.size());
                        holder.tx_score.setText(""+(score));
                        holder.relative_CompleteTopic.setVisibility(View.VISIBLE);
                        if(score>= (vocabularies.size() *10) -20 ){
                            finishTopic(holder.linearLayoutStar,3);
                        }else if(score >= (vocabularies.size() *10)/2 -20){
                            finishTopic(holder.linearLayoutStar,2);
                        }else{
                            finishTopic(holder.linearLayoutStar,1);
                        }
                    }
                });
            }else{
                holder.img_next_question.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onClickNextAnswerListener.onClickListener();
                        isAnswered = false;
                        for(int i=0; i< number.size(); i++){
                            cardAnswer[i].setBackgroundColor(Color.WHITE);
                        }
                        holder.bottom_sheet.setVisibility(View.INVISIBLE);
                    }
                });
            }

        }
    }

    private void finishTopic(LinearLayout linearLayout, int n){
        ImageView[] star = new ImageView[3];
        for (int i=0; i< 3; i++){
            ImageView img_Star = new ImageView(linearLayout.getContext());
            img_Star.setImageResource(R.drawable.ic_star_border_none_unfinish_24dp);
            star[i] = img_Star;
        }
        for(int i=0; i< n; i++){
            ImageView achieved = star[i];
            achieved.setImageResource(R.drawable.ic_star_yellow_finish_24dp);
            /*achieved.setMinimumWidth(R.dimen.icon_size_48dp);
            achieved.setMinimumHeight(R.dimen.icon_size_48dp);*/
            linearLayout.addView(star[i]);
        }
    }

    @Override
    public int getItemCount() {
        return vocabularies.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txAnswer1,txAnswer2,txAnswer3,txAnswer4,tx_Mean,tx_score,tx_Answer_Correct;
        private CardView cardAnswer1,cardAnswer2,cardAnswer3,cardAnswer4;
        private FrameLayout bottom_sheet;
        private ImageView img_speaker,img_next_question;
        private LinearLayout linearLayoutStar;
        private RelativeLayout relativeMultipChoice,relative_Question,relative_CompleteTopic;
        public ViewHolder(@NonNull View view) {
            super(view);
            txAnswer1 = (TextView)view.findViewById(R.id.tx_answer_1);
            txAnswer2 = (TextView)view.findViewById(R.id.tx_answer_2);
            txAnswer3 = (TextView)view.findViewById(R.id.tx_answer_3);
            txAnswer4 = (TextView)view.findViewById(R.id.tx_answer_4);
            tx_Mean = (TextView)view.findViewById(R.id.tx_mean);
            tx_score = (TextView)view.findViewById(R.id.tx_point);
            tx_Answer_Correct = (TextView)view.findViewById(R.id.tx_answer_correct);

            cardAnswer1 = (CardView) view.findViewById(R.id.card_answer_1);
            cardAnswer2 = (CardView) view.findViewById(R.id.card_answer_2);
            cardAnswer3 = (CardView) view.findViewById(R.id.card_answer_3);
            cardAnswer4 = (CardView) view.findViewById(R.id.card_answer_4);

            bottom_sheet = (FrameLayout)view.findViewById(R.id.bottom_sheet_answer);
            linearLayoutStar = (LinearLayout)view.findViewById(R.id.finish_star);
            relative_Question = (RelativeLayout)view.findViewById(R.id.relative_question);
            relativeMultipChoice = (RelativeLayout)view.findViewById(R.id.muiltip_choice);
            relative_CompleteTopic = (RelativeLayout)view.findViewById(R.id.complete_topic);

            img_speaker = (ImageView)view.findViewById(R.id.img_speaker);
            img_next_question = (ImageView)view.findViewById(R.id.img_next_question);
        }
    }
}
