package com.example.resip;

import java.io.Serializable;
import java.util.List;

public class Recipe implements Serializable {
    private int id;
    private String title;
    private String description;
    private String price;
    private int imageResource;
    private String time;
    private String portion;
    private String calories;
    private List<Step> steps;

    public Recipe(int id, String title, String description, String price, int imageResource, String time, String portion, String calories, List<Step> steps) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.imageResource = imageResource;
        this.time = time;
        this.portion = portion;
        this.calories = calories;
        this.steps = steps;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getTime() {
        return time;
    }

    public String getPortion() {
        return portion;
    }

    public String getCalories() {
        return calories;
    }

    public List<Step> getSteps() {
        return steps;
    }
}