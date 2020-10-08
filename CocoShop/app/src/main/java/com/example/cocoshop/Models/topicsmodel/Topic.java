package com.example.cocoshop.Models.topicsmodel;

import com.example.cocoshop.Models.vocabularysmodel.Vocabulary;

import java.util.ArrayList;
import java.util.Map;

public class Topic {
    private String urlImage;
    private String name;
    private String description;
    private long topicCode;
    private Levels level;
    private ArrayList<Vocabulary> vocabularies;


    public Topic(String name, String description, long topicCode, Levels level, ArrayList<Vocabulary> vocabularies, String urlImage) {
        this.name = name;
        this.description = description;
        this.topicCode = topicCode;
        this.level = level;
        this.vocabularies = vocabularies;
        this.urlImage = urlImage;
    }

   /* public Topic() {

    }*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getTopicCode() {
        return topicCode;
    }

    public void setTopicCode(int topicCode) {
        this.topicCode = topicCode;
    }

    public Levels getLevel() {
        return level;
    }

    public void setLevel(Levels level) {
        this.level = level;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public ArrayList<Vocabulary> getVocabularies() {
        return vocabularies;
    }

    public void setVocabularies(ArrayList<Vocabulary> vocabularies) {
        this.vocabularies = vocabularies;
    }
}
