package com.example.cocoshop.Adapter.topicsadapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cocoshop.Models.vocabularysmodel.Vocabulary;
import com.example.cocoshop.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Random;

public class Multiple_Choice_Adapter extends RecyclerView.Adapter<Multiple_Choice_Adapter.ViewHolder> {
    private ArrayList<Vocabulary> vocabularies;
    private ArrayList<Integer> number = new ArrayList<>();
    public Multiple_Choice_Adapter(ArrayList<Vocabulary> vocabularies) {
        this.vocabularies = vocabularies;
        number.add(0);
        number.add(1);
        number.add(2);
        number.add(3);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.multiple_choice_vocabulary,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(vocabularies != null){
            Map<String,Object> item = (Map<String, Object>) vocabularies.get(position);
            ArrayList<String> answer = new ArrayList<>();
            String vocabuary = item.get("vocabulary").toString();
            answer.add(vocabuary);
            answer.add(String.valueOf(vocabuary.charAt(vocabuary.length()-1))+vocabuary);
            answer.add(vocabuary+String.valueOf(vocabuary.charAt(vocabuary.length()-1)));
            final TextView[] textViews = {holder.txAnswer1,holder.txAnswer2,holder.txAnswer3,holder.txAnswer4};
            Collections.shuffle(number);
            answer.add(String.valueOf(vocabuary.charAt(number.get(0)))+vocabuary);
            for(int i=0; i<number.size();i++){
                textViews[i].setText(answer.get(number.get(i)));
            }
        }
    }

    @Override
    public int getItemCount() {
        return vocabularies.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txAnswer1,txAnswer2,txAnswer3,txAnswer4;
        private CardView cardAnswer1,cardAnswer2,cardAnswer3,cardAnswer4;
        public ViewHolder(@NonNull View view) {
            super(view);
            txAnswer1 = (TextView)view.findViewById(R.id.tx_answer_1);
            txAnswer2 = (TextView)view.findViewById(R.id.tx_answer_2);
            txAnswer3 = (TextView)view.findViewById(R.id.tx_answer_3);
            txAnswer4 = (TextView)view.findViewById(R.id.tx_answer_4);
            cardAnswer1 = (CardView) view.findViewById(R.id.card_answer_1);
            cardAnswer2 = (CardView) view.findViewById(R.id.card_answer_2);
            cardAnswer3 = (CardView) view.findViewById(R.id.card_answer_3);
            cardAnswer4 = (CardView) view.findViewById(R.id.card_answer_4);
        }
    }
}
