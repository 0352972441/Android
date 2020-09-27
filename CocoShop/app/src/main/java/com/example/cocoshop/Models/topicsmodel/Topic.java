package com.example.cocoshop.Models.topicsmodel;
enum Level {BEGINER,HIGHTBEGINER,LOWINTERMADIATE,INTERMADIATE,HIGHINTERMADIATE,LOWADVANCED,HIGHADVANCED}
public class Topic {
    private String name;
    private String description;
    private int topicCode;
    private Level level;


    public Topic(String name, String description, int topicCode, Level level) {
        this.name = name;
        this.description = description;
        this.topicCode = topicCode;
        this.level = level;
    }

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

    public int getTopicCode() {
        return topicCode;
    }

    public void setTopicCode(int topicCode) {
        this.topicCode = topicCode;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }
}
