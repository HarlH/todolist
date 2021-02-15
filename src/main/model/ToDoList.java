package model;

import java.time.LocalDate;
import java.util.ArrayList;


// A to-do list containing tasks and functions to add, remove, and marked completed
public class ToDoList {
    private ArrayList<Task> list = new ArrayList<>();


    //REQUIRES: title is not empty, and is not the same with an existing title
    //EFFECTS: add a new task into the to-do list
    public void addTask(Task task) {
        list.add(task);
    }

    //REQUIRES: at least one task in the list
    //EFFECTS: removes task object with the same title as the parameter
    public void removeTask(String title) {
        for (Task t : list) {
            if (t.getTitle().equals(title)) {
                list.remove(t);
                return;
            }
        }
    }

    //REQUIRES: at least one task in the list
    //EFFECTS: mark the task object with the same title as the parameter as completed
    public void markTaskCompleted(String title) {
        for (Task t : list) {
            if (t.getTitle().equals(title)) {
                t.setComplete(true);
            }
        }
    }


    //EFFECTS: returns the to-do list
    public ArrayList<Task> getList() {
        return list;
    }

    //REQUIRES: at least one task in tasks
    //EFFECTS: returns task object at given index from tasks
    public Task getTask(int index) {
        return list.get(index);
    }


    //EFFECTS: returns size of the list
    public int getSize() {
        return list.size();
    }


}
