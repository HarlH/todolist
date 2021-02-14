package ui;

import java.util.Scanner;

public class ConsoleUI {


    //EFFECT: prints a message showing commands to use the to do list
    public void startUp() {
        System.out.println("Hi! This is a to-do list. To start, use one of the command below:");
        System.out.println("Add : Adds a task to the to-do list.");
        System.out.println("Remove : removes a task from the list.");
        System.out.println("Quit : exit the to do list app.");
        System.out.println("What do you want to do? Enter a command here:");
    }


    // gets user task and prints it out
    String getInput() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}
