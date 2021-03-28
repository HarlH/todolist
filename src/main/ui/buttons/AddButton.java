package ui.buttons;

import model.Task;
import ui.ToDoListUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class AddButton extends Buttons {
    private Task task;

    public AddButton(ToDoListUI todoList, JComponent parent, GridBagConstraints gc) {
        super(todoList, parent, gc);
    }

    // MODIFIES: this
    // EFFECTS: constructs a new button and disables it initially
    @Override
    public void createButton() {
        button = new JButton("Add item");
        button.setEnabled(false);
    }

    // MODIFIES: this
    // EFFECTS: constructs a new listener object and adds it to the button
    @Override
    protected void addListener() {
        button.addActionListener(new AddToolClickHandler());
    }

    private class AddToolClickHandler implements ActionListener {

        // MODIFIES: todoList
        // EFFECTS: when button is pressed, get data from the fields in the panel and construct
        //          an item based on the data, and reset text field and combo box
        @Override
        public void actionPerformed(ActionEvent e) {
            Toolkit.getDefaultToolkit().beep();
            String name = todoList.getAddName();
            String importance = todoList.getImportance();
            LocalDate dueDate = LocalDate.parse(todoList.getDueDate());

            if (importance.equals(todoList.important)) {
                task = new Task(name, dueDate);
                task.setImportant(true);
                todoList.addTask(task);
            } else if (importance.equals(todoList.notImportant)) {
                task = new Task(name, dueDate);
                task.setImportant(false);
                todoList.addTask(task);
            }
            todoList.setAddNameField();
            todoList.setImportance();
            todoList.setDateField();
            todoList.viewList(todoList.getToDoList());
        }
    }
}
