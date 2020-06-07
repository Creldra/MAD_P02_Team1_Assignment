package com.example.mad_team1_assignment;

public class StorySQLite {
    private String storyText;
    private int textID;
    public StorySQLite(){}
    public StorySQLite(String StoryText, int TextID){
        this.storyText = StoryText;
        this.textID = TextID;
    }

    public String getStoryText() {
        return storyText;
    }

    public void setStoryText(String storyText) {
        this.storyText = storyText;
    }

    public int getTextID() {
        return textID;
    }

    public void setTextID(int textID) {
        this.textID = textID;
    }
}

