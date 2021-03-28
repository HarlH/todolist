package ui;

import model.Task;
import model.ToDoList;
import ui.buttons.AddButton;
import ui.buttons.MarkCompletedButton;
import ui.fields.AddDateField;
import ui.fields.AddNameField;
import ui.fields.MarkCompletedField;

import javax.swing.*;
import java.awt.*;

/*
 * Represents the main window in which the to do list is displayed
 */
public class ToDoListUI extends JFrame {

    private static final int WIDTH = 1200;
    private static final int HEIGHT = 800;
    private ToDoList toDoList;
    public Font textFieldFont = new Font("Helvetica", Font.PLAIN, 16);

    private JTextArea textArea;
    private JPanel textPanel;
    private MarkCompletedField markCompletedField;
    private AddNameField addNameField;
    private AddDateField addDateField;
    private JComboBox importance;

    public AddButton add;
    public MarkCompletedButton complete;

    public String important = "Important";
    public String NotImportant = "Not Important";
    private String[] types = {important, NotImportant};


    // EFFECTS: sets up window in which the to do list will be displayed
    public ToDoListUI() {
        super("To Do List");
        toDoList = new ToDoList();
        initializing();
    }


    // MODIFIES: this
    // EFFECTS: draws the JFrame window for the to do list, and makes the tools that will work on the to do list
    public void initializing() {
        setLayout(new BorderLayout());
        setSize(WIDTH, HEIGHT);
        createTextPanel();
        //createButtons();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

    public void createTextPanel() {
        textPanel = new JPanel();
        textArea = new JTextArea();
        textArea.setFont(textFieldFont);
        textPanel.setPreferredSize(new Dimension(WIDTH * 2 / 3, HEIGHT));
        Color c = new Color(93, 177, 219);
        textPanel.setBackground(c);

        JScrollPane scroll = new JScrollPane(textArea);
        scroll.setPreferredSize(new Dimension(WIDTH * 2 / 3 - 10, HEIGHT - 60));

        textArea.setEditable(false);
        textArea.setBackground(Color.WHITE);
        viewList(toDoList);

        textPanel.add(scroll);

        add(textPanel, BorderLayout.WEST);
    }

    public void addTask(Task t) {
        toDoList.addTask(t);
    }

    public void viewList(ToDoList toDoList) {
        StringBuilder sb = new StringBuilder();
        if (toDoList.getSize() >= 1) {
            for (Task i : toDoList.getTaskList()) {
                sb.append("\nTask Title: ").append(i.getTitle()).append("\nDue Date: ")
                        .append(i.getDueDate()).append("\nTask Priority: ")
                        .append(i.isImportant()).append("\n Completion Status: ")
                        .append(i.isComplete());
            }
        } else {
            sb.append("\nNo items to do");
        }
        String currentItems = "Current items to do: " + sb.toString();
        setText(currentItems);

    }

    // MODIFIES: this
    // EFFECTS: sets the text in the text area to the given text
    public void setText(String txt) {
        textArea.setText(txt);
    }

    public void completeTask(String name) {
        toDoList.markTaskCompleted(name);
        System.out.println("Marked task as completed");
    }

    // MODIFIES: this
    // EFFECTS: sets the name field in the add area to empty
    public void setAddNameField() {
        addNameField.setEmpty();
    }

    // MODIFIES: this
    // EFFECTS: sets the name field in the complete area to empty
    public void setCompletedNameField() {
        markCompletedField.setEmpty();
    }

    // MODIFIES: this
    // EFFECTS: resets the combo box value
    public void setImportance() {
        importance.setSelectedIndex(0);
    }

    // MODIFIES: this
    // EFFECTS: sets the date field in the add area to empty
    public void setDateField() {
        addDateField.setEmpty();
    }

    // EFFECTS: gets the text in the name field in the complete area
    public String getCompleteName() {
        return markCompletedField.getFieldText();
    }

    // MODIFIES: this
    // EFFECTS: sets the name field in the complete area to empty
    public void setCompleteNameField() {
        markCompletedField.setEmpty();
    }

    // EFFECTS: gets the text in the name field in the add area
    public String getAddName() {
        return addNameField.getFieldText();
    }

    // EFFECTS: gets the item type the combo box is set on
    public String getImportance() {
        return (String)importance.getSelectedItem();
    }

    // EFFECTS: gets the text in the date field in the add area
    public String getDueDate() {
        return addDateField.getFieldText();
    }





    public static void main(final String[] args) {
        //new ToDoListUI();
        new ToDoListUI();
    }

}
