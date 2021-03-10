package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;


// A to-do list containing tasks and functions to add, remove, and marked completed
public class ToDoList implements Writable {
    private ArrayList<Task> taskList = new ArrayList<>();


    //REQUIRES: title is not empty, and is not the same with an existing title
    //EFFECTS: add a new task into the to-do list
    public void addTask(Task task) {
        taskList.add(task);
    }

    //REQUIRES: at least one task in the list
    //EFFECTS: removes task object with the same title as the parameter
    public void removeTask(String title)  {
        for (Task t : taskList) {
            if (t.getTitle().equals(title)) {
                taskList.remove(t);

            }
        }
    }

    //REQUIRES: at least one task in the list
    //EFFECTS: mark the task object with the same title as the parameter as completed
    public void markTaskCompleted(String title) {
        for (Task t : taskList) {
            if (t.getTitle().equals(title)) {
                t.setComplete(true);
            }
        }
    }


    //EFFECTS: returns the to-do list
    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    //REQUIRES: at least one task in tasks
    //EFFECTS: returns task object at given index from tasks
    public Task getTask(int index) {
        return taskList.get(index);
    }


    //EFFECTS: returns size of the list
    public int getSize() {
        return taskList.size();
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("todolist", toDoListToJson());
        return json;
    }

    // EFFECTS: returns tasks in the to-do list as a JSON array
    private JSONArray toDoListToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Task t : taskList) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;
    }


}
