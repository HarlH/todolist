package model;


import org.json.JSONObject;
import persistence.Writable;

import java.time.LocalDate;

// A task with a title describing what to do, and a due date.
public class Task implements Writable {
    // A title of type string for the task
    private String title;
    // The completion status of boolean type for the task , if true: the task is completed, otherwise false.
    private boolean complete;
    // The priority status of the task, if true: the task is important, otherwise false.
    private boolean important;
    // The due date of the task as yyyy-mm-dd format
    private LocalDate dueDate;

    //REQUIRES: title should not be empty, and should not be the same with an existing title
    //EFFECTS: Constructs task with given description
    public Task(String title, LocalDate dueDate) {

        this.setTitle(title);
        this.complete = false;
        this.important = false;
        this.setDueDate(dueDate);
    }


    // MODIFIES: this
    // EFFECTS: set the title of the task to the given parameter
    public void setTitle(String title) {
        this.title = title.trim();
    }

    // MODIFIES: this
    // EFFECTS: set the completion status of the task to the given parameter
    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    // MODIFIES: this
    // EFFECTS: set the priority status of the task to the given parameter
    public void setImportant(boolean important) {
        this.important = important;
    }

    // MODIFIES: this
    // EFFECTS: set the due date of the task to the given parameter
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    //EFFECTS: returns the title of this
    public String getTitle() {
        return title;
    }

    //EFFECTS: returns the due date of this
    public LocalDate getDueDate() {
        return dueDate;
    }

    //EFFECTS: returns the completion status of this
    public boolean isComplete() {
        return complete;
    }

    //EFFECTS: returns the important status of this
    public boolean isImportant() {
        return important;
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("title", title);
        json.put("complete", complete);
        json.put("important", important);
        json.put("dueDate", dueDate);
        return json;
    }
}
