package ui.fields;

import ui.ToDoListUI;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

// a text field for user to write the title of the task they want to add
public class AddTitleField extends TextField {

    public AddTitleField(ToDoListUI todoList, JComponent parent, GridBagConstraints gc) {
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
        // EFFECTS: enables the add button when text field is not empty and due date field is not empty and in format
        private void changed() {
            String name = textField.getText();

            String duedate = todoList.getDueDate();
            boolean dateInFormat;

            try {
                LocalDate.parse(duedate);
                dateInFormat = true;

            } catch (DateTimeParseException d) {
                todoList.setEnabledAddButton(false);
                dateInFormat = false;
            }


            if (!name.isEmpty() && dateInFormat) {
                todoList.setEnabledAddButton(true);
            } else {
                todoList.setEnabledAddButton(false);
            }
        }
    }
}
