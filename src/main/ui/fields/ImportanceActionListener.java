package ui.fields;

import ui.ToDoListUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//action listener for the importance box
public class ImportanceActionListener implements ActionListener {
    private String important = "Important";
    private String notImportant = "Not Important";
    private ToDoListUI toDoListUI;


    //EFFECTS: construct a new importance action listener
    public ImportanceActionListener(ToDoListUI toDoList) {
        this.toDoListUI = toDoList;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JComboBox typeBox = (JComboBox) e.getSource();
        String selected = (String) typeBox.getSelectedItem();
        if (selected.equals(important)) {
            toDoListUI.setEnabledAddButton(false);
        } else if (selected.equals(notImportant)) {
            if (toDoListUI.getAddTitle().isEmpty()) {
                toDoListUI.setEnabledAddButton(false);
            } else {
                toDoListUI.setEnabledAddButton(true);
            }
        }
    }

}
