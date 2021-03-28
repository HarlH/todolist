package ui.fields;

import ui.ToDoListUI;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class AddDateField extends TextField {

    public AddDateField(ToDoListUI todoList, JComponent parent, GridBagConstraints gc) {
        super(todoList, parent, gc);
    }

    // MODIFIES: this
    // EFFECTS: constructs a new listener object and adds it to the text field
    @Override
    protected void addListener() {
        textField.getDocument().addDocumentListener(new AddDateHandler());
    }

    private class AddDateHandler implements DocumentListener {
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
        // EFFECTS: enables the add button if the text field is not empty and in the correct format
        public void changed() {
            String duedate = textField.getText();
            if (!duedate.isEmpty()) {
                todoList.add.setEnabled(true);
            } else {
                todoList.add.setEnabled(false);
            }

            try {
                LocalDate.parse(duedate);
            } catch (DateTimeParseException d) {
                todoList.add.setEnabled(false);
            }

        }
    }
}
