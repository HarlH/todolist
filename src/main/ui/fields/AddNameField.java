package ui.fields;

import ui.ToDoListUI;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class AddNameField extends TextField {

    public AddNameField(ToDoListUI todoList, JComponent parent, GridBagConstraints gc) {
        super(todoList, parent, gc);
    }

    // MODIFIES: this
    // EFFECTS: constructs a new listener object and adds it to the text field
    @Override
    protected void addListener() {
        textField.getDocument().addDocumentListener(new AddNameHandler());
    }

    private class AddNameHandler implements DocumentListener {
        @Override
        public void insertUpdate(DocumentEvent e) {
            changed();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            changed();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            changed();
        }

        // MODIFIES: todoList
        // EFFECTS: enables the add button when text field is not empty and other conditions
        private void changed() {
            String name = textField.getText();

            if (!name.isEmpty() && !todoList.getDueDate().isEmpty()) {
                todoList.add.setEnabled(true);
            } else {
                todoList.add.setEnabled(false);
            }
        }
    }
}
