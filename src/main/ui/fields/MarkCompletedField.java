package ui.fields;

import ui.ToDoListUI;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class MarkCompletedField extends TextField {
    public MarkCompletedField(ToDoListUI todoList, JComponent parent, GridBagConstraints gc) {
        super(todoList, parent, gc);
    }

    // MODIFIES: this
    // EFFECTS: constructs a new listener and adds it to the text field
    @Override
    protected void addListener() {
        textField.getDocument().addDocumentListener(new CompleteItemNameHandler());
    }

    private class CompleteItemNameHandler implements DocumentListener {
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
        // EFFECTS: enables the button if the text field is not empty
        public void changed() {
            String name = todoList.getCompleteName();
            if (!name.isEmpty()) {
                todoList.complete.setEnabled(true);
            } else {
                todoList.complete.setEnabled(false);
            }
        }
    }
}
