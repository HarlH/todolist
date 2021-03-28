package ui.buttons;

import model.Task;
import model.ToDoList;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.ToDoListUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

// a button that allows the user to save their to-do list to the file
public class SaveButton extends Buttons {
    private JsonWriter jsonWriter = new JsonWriter("./data/todolist.json");


    public SaveButton(ToDoListUI todoList, JComponent parent, GridBagConstraints gc) {
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
        button = new JButton("Save Tasks");
        button.setEnabled(true);
    }

    // EFFECTS: returns the string "Save Completed"

    protected String getLabel() {
        return "Save Completed";
    }

    private class SaveButtonClickHandler implements ActionListener {

        //EFFECTS: when button pressed, Save the current to-do list to file
        @Override
        public void actionPerformed(ActionEvent e) {
            Toolkit.getDefaultToolkit().beep();

            ToDoList newlist = todoList.getToDoList();
            try {
                jsonWriter.open();
                jsonWriter.write(newlist);
                jsonWriter.close();
                System.out.println("Saved to-do list ");
            } catch (FileNotFoundException exception) {
                System.out.println("Unable to write to file");
            }
            todoList.viewList(todoList.getToDoList());
        }
    }

}
