package ui.buttons;

import ui.ToDoListUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


//a button that allows user to marked a task as completed and remove the task from the to-do list
public class MarkCompletedButton extends Buttons {

    public MarkCompletedButton(ToDoListUI todoList, JComponent parent, GridBagConstraints gc) {
        super(todoList, parent, gc);
    }

    // MODIFIES: this
    // EFFECTS: constructs a new button and disables it initially
    @Override
    public void createButton() {
        button = new JButton("Complete Item");
        button.setEnabled(false);
    }

    // MODIFIES: this
    // EFFECTS: constructs a new listener object and adds it to the button
    @Override
    protected void addListener() {
        button.addActionListener(new CompleteToolCLickHandler());
    }

    private class CompleteToolCLickHandler implements ActionListener {

        // MODIFIES: todoList
        // EFFECTS: when button is pressed, complete the task with the given title and clear text field
        @Override
        public void actionPerformed(ActionEvent e) {
            Toolkit.getDefaultToolkit().beep();
            String name = todoList.getCompleteName();
            todoList.completeTask(name);

            todoList.setCompletedNameField();
            todoList.viewList(todoList.getToDoList());
        }
    }
}
