package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class ToDoList {
    private ArrayList<Task> list = new ArrayList<Task>();


    public void addTask(String title, LocalDate dueDate) {
        list.add(new Task(title, dueDate));
    }

    //REQUIRES: at least one task in tasks
    public void removeTask() {

    }

    //REQUIRES: at least one task in tasks
    //EFFECTS: returns task object at given index from tasks
    public Task getTask(int index) {
        return list.get(index);
    }

    //EFFECTS: returns tasks
    public ArrayList<Task> getList() {
        return list;
    }



    //EFFECTS: returns size of the list
    public int getSize() {
        return list.size();
    }


}
