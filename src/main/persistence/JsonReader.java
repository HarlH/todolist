package persistence;

import model.Task;
import model.ToDoList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.stream.Stream;


// Code taken and modified from JsonWriter.java package in JsonSerializationDemo
// URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// Represents a reader that reads the to-do list from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads the to-do list from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ToDoList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseToDoList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses to-do list from JSON object and returns it
    private ToDoList parseToDoList(JSONObject jsonObject) {
        ToDoList list = new ToDoList();
        addTasks(list, jsonObject);
        return list;
    }

    // MODIFIES: toDoList
    // EFFECTS: parses tasks from JSON object and adds them to to-do list
    private void addTasks(ToDoList toDoList, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("todolist");
        for (Object json : jsonArray) {
            JSONObject nextTask = (JSONObject) json;
            addTask(toDoList, nextTask);
        }
    }

    // MODIFIES: toDoList
    // EFFECTS: parses a task from JSON object and adds it to to-do list
    private void addTask(ToDoList toDoList, JSONObject jsonObject) {
        String name = jsonObject.getString("title");
        LocalDate dueDate = LocalDate.parse(jsonObject.getString("dueDate"));
        boolean complete = jsonObject.getBoolean("complete");
        boolean important = jsonObject.getBoolean("important");
        Task newTask = new Task(name, dueDate);
        newTask.setComplete(complete);
        newTask.setComplete(important);

        toDoList.addTask(newTask);
    }
}