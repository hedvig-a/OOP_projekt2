package com.example.oopprojekt.src.model;

public class DateIdeaGenerator extends Idea {

    public DateIdeaGenerator(String activity, String category, String description){
        super(activity, category, description);
    }

    @Override
    public String toString() {
        return "[Date idea] \n" + super.toString();
    }


}
