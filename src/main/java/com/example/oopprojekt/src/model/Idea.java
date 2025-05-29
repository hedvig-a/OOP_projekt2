package com.example.oopprojekt.src.model;

public class Idea {
    protected String activity;
    protected String category;
    protected String description;
    protected boolean favorite;

    public Idea(String activity, String category, String description) {
        this.activity = activity;
        this.category = category;
        this.description = description;
        this.favorite = false; //By default, the idea is not a favourite.
    }

    public static Idea create(String activity, String category, String description) {
        return "date".equalsIgnoreCase(category)
                ? new DateIdeaGenerator(activity, category, description)
                : new HangoutIdeaGenerator(activity, category, description);
    }

    public static Idea fromLine(String line, boolean isSaved) {
        String[] parts = line.split(";;");
        if (parts.length < 3) return null;

        String activity = parts[0];
        String category = parts[1];
        String description = parts[2];
        boolean favorite = isSaved && parts.length == 4 && Boolean.parseBoolean(parts[3]);

        Idea idea = create(activity, category, description);
        idea.setFavorite(favorite);
        return idea;
    }

    public String toLine() {
        return activity + ";;" + category + ";;" + description + ";;" + favorite;
    }

    public String getActivity() {
        return activity;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public String toString() {
        return "Activity: " + activity +
                "\nDescription\n" + description;
    }
}
