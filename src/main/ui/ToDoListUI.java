package ui;

import model.Task;
import model.ToDoList;
import ui.buttons.*;
import ui.fields.AddDateField;
import ui.fields.AddImportanceField;
import ui.fields.AddTitleField;
import ui.fields.MarkCompletedField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/*
 * Represents the main window in which the to do list is displayed
 */
public class ToDoListUI extends JFrame {

    private static final int WIDTH = 1200;
    private static final int HEIGHT = 600;

    private ToDoList toDoList;
    private Font textFieldFont = new Font("Helvetica", Font.PLAIN, 16);

    private JTextArea textArea;
    private JPanel textPanel;
    private JPanel toolArea;
    private MarkCompletedField markCompletedField;
    private AddTitleField addTitleField;
    private AddDateField addDateField;
    private JComboBox importance;
    private JLabel name;
    private JLabel dueDate;
    private AddButton add;
    private MarkCompletedButton complete;
    private String important = "Important";
    private String notImportant = "Not Important";
    private String[] types = {notImportant, important};


    // EFFECTS: sets up window in which the to do list will be displayed
    public ToDoListUI() {
        super("To Do List");
        toDoList = new ToDoList();
        initializing();
    }


    // MODIFIES: this
    // EFFECTS: draws the JFrame window and makes the text areas and buttons for the to-do list
    public void initializing() {
        setLayout(new BorderLayout());
        setSize(WIDTH, HEIGHT);
        createTextPanel();
        createButtons();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }


    // MODIFIES: this
    // EFFECTS: creates the area where all tasks will be displayed to the users
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


    // MODIFIES: this
    // EFFECTS: declares and instantiates all the  buttons and add/complete areas on the right side of the window
    private void createButtons() {
        Container toolContainer = getContentPane();
        toolArea = new JPanel();
        toolArea.setLayout(new GridBagLayout());
        toolArea.setPreferredSize(new Dimension(WIDTH / 3, HEIGHT));
        toolContainer.add(toolArea, BorderLayout.EAST);

        GridBagConstraints gc = new GridBagConstraints();

        gc.weightx = 0.5;
        gc.weighty = 0.5;

        gc.gridx = 0;
        gc.gridy = 0;
        JPanel addPanel = new JPanel();
        createAddPanel(addPanel);
        toolArea.add(addPanel, gc);

        gc.gridy = 2;
        JPanel completePanel = new JPanel();
        createCompletePanel(completePanel);
        toolArea.add(completePanel, gc);

        gc.gridy = 3;
        Buttons load = new LoadButton(this, toolArea, gc);


        gc.anchor = GridBagConstraints.BASELINE;
        gc.gridy = 4;
        Buttons save = new SaveButton(this, toolArea, gc);
    }


    // MODIFIES: this
    // EFFECTS: constructs the area where users can create the item to be added
    private void createAddPanel(JPanel addPanel) {
        addPanel.setLayout(new GridBagLayout());
        addPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Add a new Task:"),
                BorderFactory.createEmptyBorder(40, 10, 20, 10)));
        GridBagConstraints addPanelConstraints = new GridBagConstraints();
        addPanelConstraints.weightx = 0.5;
        addPanelConstraints.weighty = 0.5;
        addPanelConstraints.insets = new Insets(0, 0, 10, 0);

        name = new JLabel("Task Title: ");
        addPanelConstraints.anchor = GridBagConstraints.LINE_END;
        addPanelConstraints.gridx = 0;
        addPanelConstraints.gridy = 0;
        addPanel.add(name, addPanelConstraints);

        addPanelConstraints.anchor = GridBagConstraints.LINE_START;
        addPanelConstraints.gridx = 1;
        addTitleField = new AddTitleField(this, addPanel, addPanelConstraints);



        importance = new JComboBox(types);
        importance.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox typeBox = (JComboBox) e.getSource();
                String selected = (String) typeBox.getSelectedItem();
                if (selected.equals(important)) {
                    add.setEnabled(false);
                } else if (selected.equals(notImportant)) {
                    if (addTitleField.getFieldText().isEmpty()) {
                        add.setEnabled(false);
                    } else {
                        add.setEnabled(true);
                    }
                }
            }
        });
        addPanelConstraints.anchor = GridBagConstraints.BASELINE;
        addPanelConstraints.gridy = 1;
        addPanel.add(importance, addPanelConstraints);

        dueDate = new JLabel("Due Date (example: 2020-12-25): ");
        addPanelConstraints.anchor = GridBagConstraints.LINE_END;
        addPanelConstraints.gridx = 0;
        addPanelConstraints.gridy = 2;
        addPanel.add(dueDate, addPanelConstraints);


        addPanelConstraints.anchor = GridBagConstraints.LINE_START;
        addPanelConstraints.gridx = 1;
        addDateField = new AddDateField(this, addPanel, addPanelConstraints);

        addPanelConstraints.gridy = 3;
        add = new AddButton(this, addPanel, addPanelConstraints);
    }


    // MODIFIES: this
    // EFFECTS: constructs the area where the user can input the item they want to be completed
    private void createCompletePanel(JPanel completePanel) {
        completePanel.setLayout(new GridBagLayout());
        completePanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Complete an Item:"),
                BorderFactory.createEmptyBorder(10, 70, 10, 80)));
        GridBagConstraints completePanelConstraints = new GridBagConstraints();
        completePanelConstraints.weightx = 0.5;
        completePanelConstraints.weighty = 0.5;
        completePanelConstraints.insets = new Insets(0, 0, 10, 0);

        name = new JLabel("Task Title : ");
        completePanelConstraints.anchor = GridBagConstraints.LINE_END;
        completePanelConstraints.gridx = 0;
        completePanelConstraints.gridy = 0;
        completePanel.add(name, completePanelConstraints);

        completePanelConstraints.anchor = GridBagConstraints.LINE_START;
        completePanelConstraints.gridx = 1;
        markCompletedField = new MarkCompletedField(this, completePanel, completePanelConstraints);

        completePanelConstraints.gridy = 1;
        complete = new MarkCompletedButton(this, completePanel, completePanelConstraints);

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
                        .append(i.getStatus()).append("\n Completion Status: ")
                        .append(i.isComplete()).append("\n");
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
        toDoList.removeTask(name);
        System.out.println("Marked task as completed");
    }


    public void setEnabledAddButton(Boolean b) {
        add.setEnabled(b);
    }

    public void setEnabledCompletedButton(Boolean b) {
        complete.setEnabled(b);
    }


    // MODIFIES: this
    // EFFECTS: sets the name field in the add area to empty
    public void setAddNameField() {
        addTitleField.setEmpty();
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
    public String getAddTitle() {
        return addTitleField.getFieldText();
    }

    // EFFECTS: gets the item type the combo box is set on
    public String getImportance() {
        return (String) importance.getSelectedItem();
    }

    // EFFECTS: gets the text in the date field in the add area
    public String getDueDate() {
        return addDateField.getFieldText();
    }

    // EFFECTS: gets the font of the to do list
    public Font getTextFieldFont() {
        return textFieldFont;
    }

    public ToDoList getToDoList() {
        return toDoList;
    }


}
