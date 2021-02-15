package ui;

import model.Task;
import model.ToDoList;

import java.time.LocalDate;
import java.util.Scanner;

// a console user interface
public class ConsoleUI {
    private ToDoList toDoList;


    public ConsoleUI() {
        toDoList = new ToDoList();
        startUp();
        readCommand();

    }


    //EFFECT: prints a message showing a list of commands to use the to-do list
    public void startUp() {
        System.out.println("Hi! This is a to-do list. To start, use one of the command below:");
        System.out.println("Add : Adds a task to the to-do list.");
        System.out.println("Remove : removes a task from the list.");
        System.out.println("View : view the todo list.");
        System.out.println("Mark Completed : mark a task in the todo list as completed.");
        System.out.println("Quit : exit the to do list app.");

    }


    //MODIFIES: this
    //EFFECTS: read the command of the user
    public void readCommand() {
        System.out.println("What do you want to do? Enter a command here:");
        String command = getInput();
        command = command.toLowerCase();

        while (!command.equals("quit")) {
            if (command.equals("add")) {
                addCommand();
            } else if (command.equals("remove")) {
                removeCommand();
            } else if (command.equals("view")) {
                viewCommand();
            } else if (command.equals("mark completed")) {
                markCompletedCommand();
            } else {
                System.out.println("Command Not Recognized!");
            }

            System.out.println("What do you want to do? Enter a command here:");
            command = getInput();
            command = command.toLowerCase();
        }

    }

    //MODIFIES: this
    //EFFECTS: remove a task from the list
    private void removeCommand() {
        if (toDoList.getSize() == 0) {
            System.out.println("There is no task in the list to be removed.");
        } else {
            System.out.println("What is the name of the task you want to remove?");
            String name = getInput();
            toDoList.removeTask(name);
            System.out.println("Task removed");
        }
    }

    //MODIFIES: this
    //EFFECTS: mark a task in the list as completed
    private void markCompletedCommand() {
        if (toDoList.getSize() == 0) {
            System.out.println("There is no task in the list to be marked.");
        } else {
            System.out.println("What is the name of the task you want to mark completed?");
            String name = getInput();
            toDoList.markTaskCompleted(name);
            System.out.println("Marked task as completed");
        }
    }


    //MODIFIES: this
    //EFFECTS: add a new task to the list
    private void addCommand() {
        System.out.println(" What is the name of the new task?");
        String title = getInput();

        if (checkExistingName(title)) {
            System.out.println(" What is the due date of the new task? [example: 2021-02-15] ");
            LocalDate dueDate = LocalDate.parse(getInput());
            Task newTask = new Task(title, dueDate);
            toDoList.addTask(newTask);
            System.out.println(" Is the new task important?(Yes/No)");
            String priority = getInput();
            priority = priority.toLowerCase();
            if (priority.equals("yes")) {
                newTask.setImportant(true);
            } else if (priority.equals("no")) {
                newTask.setImportant(false);
            } else {
                System.out.println("Input not valid, set priority to Not Important");
            }
            System.out.println("New task added.");
        }
    }

    //EFFECTS: return false: the name already exists, otherwise return true
    private boolean checkExistingName(String name) {
        boolean existed = false;
        for (Task t : toDoList.getList()) {
            if (t.getTitle().equals(name) || name.equals("")) {
                existed = true;
            } else {
                existed = false;
            }
        }
        if (existed) {
            System.out.println("Name is empty or already exists, please try again");
            return false;
        } else {
            return true;
        }
    }

    //EFFECTS: print information of every task in the list
    private void viewCommand() {
        for (Task i : toDoList.getList()) {
            System.out.println("Task Title: " + i.getTitle());
            System.out.println("Task Due Date: " + i.getDueDate());
            System.out.println("Task Priority: " + displayTaskPriority(i));
            System.out.println("Task Completion Status: " + displayTaskCompletion(i));
        }
    }


    //EFFECTS: return the priority status of the task in type string
    private String displayTaskPriority(Task t) {
        if (t.isImportant()) {
            return "Important";
        } else {
            return "Not Important";
        }
    }

    //EFFECTS: return the completion status of the task in type string
    private String displayTaskCompletion(Task t) {
        if (t.isComplete()) {
            return "Completed";
        } else {
            return "In Progress";
        }
    }

    // EFFECTS: get user input
    String getInput() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }


}
