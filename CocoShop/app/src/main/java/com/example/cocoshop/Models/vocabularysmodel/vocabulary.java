package com.example.cocoshop.Models.vocabularysmodel;

public class vocabulary {
    private String mean;
    private String vocabulary;
    private String read;
    private String spelling;
    private String vocabularyCode;

    public vocabulary(String mean, String vocabulary, String read, String spelling, String vocabularyCode) {
        this.mean = mean;
        this.vocabulary = vocabulary;
        this.read = read;
        this.spelling = spelling;
        this.vocabularyCode = vocabularyCode;
    }

    public String getMean() {
        return mean;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }

    public String getVocabulary() {
        return vocabulary;
    }

    public void setVocabulary(String vocabulary) {
        this.vocabulary = vocabulary;
    }

    public String getRead() {
        return read;
    }

    public void setRead(String read) {
        this.read = read;
    }

    public String getSpelling() {
        return spelling;
    }

    public void setSpelling(String spelling) {
        this.spelling = spelling;
    }

    public String getVocabularyCode() {
        return vocabularyCode;
    }

    public void setVocabularyCode(String vocabularyCode) {
        this.vocabularyCode = vocabularyCode;
    }
}
