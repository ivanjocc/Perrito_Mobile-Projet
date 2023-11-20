package com.example.perrito_projet;

public class Task {
    private String date;
    private String name;
    private boolean isPriority;
    private String category;
    private boolean isCompleted;

    public Task(String date, String name, boolean isPriority, String category) {
        this.date = date;
        this.name = name;
        this.isPriority = isPriority;
        this.category = category;
        this.isCompleted = false;
    }

    public String getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public boolean isPriority() {
        return isPriority;
    }

    public String getCategory() {
        return category;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}
