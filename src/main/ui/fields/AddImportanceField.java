package ui.fields;

import ui.ToDoListUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// a box for user to choose the importance status of the task they want to add
public class AddImportanceField extends JComboBox {
    private JComboBox importance;
    private ToDoListUI toDoList;
    private String important = "Important";
    private String notImportant = "Not Important";
    private String[] types = {notImportant, important};

    public AddImportanceField(ToDoListUI toDoList) {
        this.toDoList = toDoList;
        importance = new JComboBox(types);
        importance.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox typeBox = (JComboBox) e.getSource();
                String selected = (String) typeBox.getSelectedItem();
                if (selected.equals(important)) {
                    toDoList.setEnabledAddButton(false);
                } else if (selected.equals(notImportant)) {
                    if (toDoList.getAddTitle().isEmpty()) {
                        toDoList.setEnabledAddButton(false);
                    } else {
                        toDoList.setEnabledAddButton(true);
                    }
                }
            }
        });

    }


}
