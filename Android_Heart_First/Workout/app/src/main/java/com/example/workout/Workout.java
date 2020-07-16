package com.example.workout;

public class Workout {
    private String description;
    private String title;
    static Workout one = new Workout("5 Handstand push-ups\\n10 1-legged squats\\n15 Pull-ups\"),\n" +
            " new Workout","The Limb Loosener");
    static Workout two = new Workout("100 Pull-ups\n100 Push-ups\n100 Sit-ups\n100 Squats","Core Agony");
    static Workout three = new Workout("5 Pull-ups\n10 Push-ups\n15 Squats","The Wimp Special");
    static Workout four = new Workout("500 meter run\\n21 x 1.5 pood kettleball swing\\n21 x pull-ups","Strength and Length");
    public static final Workout[] workout ={one,two,three,four};
    public Workout(String description, String title){
        this.description = description;
        this.title = title;
    }

    public String getDescription(){
        return description;
    }

    public String getTitle(){
        return title;
    }
    public String toString(){
        return title;
    }
}
