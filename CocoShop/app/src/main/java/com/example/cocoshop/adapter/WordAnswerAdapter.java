package com.example.cocoshop.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cocoshop.R;
import com.example.cocoshop.listener.ListenerAnswer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class WordAnswerAdapter extends RecyclerView.Adapter<WordAnswerAdapter.ViewHolder> {
    private String wordList;
    private List<Character> characters;
    private ListenerAnswer onClickWordAnswer;
    private ArrayList<CardView> cardList;

    public WordAnswerAdapter(String wordList) {
        this.wordList = wordList;
        characters = new ArrayList<>();
        for(int i=0; i<wordList.length(); i++){
            characters.add(wordList.charAt(i));
        }
        Collections.shuffle(characters);
        cardList = new ArrayList<>();
    }

    public void setOnClickWordAnswer(ListenerAnswer onClickWordAnswer) {
        this.onClickWordAnswer = onClickWordAnswer;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return  new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.word_answer_adapter,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        if(wordList != null){
            holder.txWordAnswer.setText(characters.get(position).toString());
            holder.wordAnswerCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickWordAnswer.listenerAnswer(position,holder.txWordAnswer.getText().toString());
                }
            });
            cardList.add(holder.wordAnswerCard);
        }
    }

    public CardView getCard(int position){
        return cardList.get(position);
    }

    @Override
    public int getItemCount() {
        return wordList.length();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txWordAnswer;
        private CardView wordAnswerCard;
        public ViewHolder(@NonNull View view) {
            super(view);
            txWordAnswer = view.findViewById(R.id.tx_word_answer);
            wordAnswerCard = view.findViewById(R.id.word_answer_card);
        }
    }
}
