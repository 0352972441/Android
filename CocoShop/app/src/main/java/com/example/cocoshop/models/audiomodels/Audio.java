package com.example.cocoshop.Models.audiomodels;

public class Audio {
    //private String title;// Tên của bài đọc
    private String urlReader;// Ảnh của người đọc
    private String readerName;// Tên của người đọc
    //private String urlAudio; // Tạm thời chưa xử lý. File audio
    private Sound sound;
    private int imageAudio; // Hình ảnh nền của carditem của bài Audio
    private boolean favorite = false;
    private Category category; // Thể loại audio để lấy ra thể loại
    public Audio(String urlReader, String readerName, Sound sound, int imageAudio, Category category) {
        //this.title = title;
        this.sound = sound;
        this.urlReader = urlReader;
        this.readerName = readerName;
        //.urlAudio = urlAudio;
        this.imageAudio = imageAudio;
        this.category = category;
        this.favorite = false;
    }

    /*public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }*/

    public String getUrlReader() {
        return urlReader;
    }

    public void setUrlReader(String urlReader) {
        this.urlReader = urlReader;
    }

    public String getReaderName() {
        return readerName;
    }

    public void setReaderName(String readerName) {
        this.readerName = readerName;
    }

    public Sound getSound() {
        return sound;
    }

    public void setSound(Sound sound) {
        this.sound = sound;
    }

    public int getImageAudio() {
        return imageAudio;
    }

    public void setImageAudio(int imageAudio) {
        this.imageAudio = imageAudio;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
}
