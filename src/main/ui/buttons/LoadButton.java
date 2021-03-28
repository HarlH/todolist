package ui.buttons;

import model.Task;
import model.ToDoList;
import persistence.JsonReader;
import ui.ToDoListUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

// a button that allows the user to load tasks from file
public class LoadButton extends Buttons {
    private JsonReader jsonReader = new JsonReader("./data/todolist.json");

    public LoadButton(ToDoListUI todoList, JComponent parent, GridBagConstraints gc) {
        super(todoList, parent, gc);
    }

    // MODIFIES: this
    // EFFECTS: constructs a new listener object and adds it to the button
    @Override
    protected void addListener() {
        button.addActionListener(new SaveButtonClickHandler());
    }


    // MODIFIES: this
    // EFFECTS: constructs a new button 
    @Override
    public void createButton() {
        button = new JButton("Load Tasks");
        button.setEnabled(true);
    }

    // EFFECTS: returns the string "Load file Completed"

    protected String getLabel() {
        return "Load file Completed";
    }

    private class SaveButtonClickHandler implements ActionListener {

        //EFFECTS: when button pressed, loads the tasks from the file and adds them to the todoList
        @Override
        public void actionPerformed(ActionEvent e) {
            Toolkit.getDefaultToolkit().beep();

            ToDoList newlist = todoList.getToDoList();

            try {
                ToDoList savedlist = jsonReader.read();
                System.out.println("Loaded to-do list");
                for (Task t : savedlist.getTaskList()) {
                    Task newtask = new Task(t.getTitle(),t.getDueDate());
                    newtask.setImportant(t.isImportant());
                    newlist.addTask(newtask);
                }
            } catch (IOException ex) {
                System.out.println("Unable to read from file ");
            }
            todoList.viewList(todoList.getToDoList());
            button.setEnabled(false);
        }
    }

}
