package pl.coderslab;

import java.io.File;
import java.util.Scanner;

public class TaskManager {
    public static void main(String[] args) {
        File myFile = new File("tasks.csv");
        String[] listOfAction = {"Add","Remove", "List", "Exit"};
        String action="OTHER";
        while (!"EXIT".equals(action.toUpperCase())){
            action =showActions(listOfAction);
            switch (action.toUpperCase()){
                case "ADD":
                    addNewTask();
                    break;
                case "REMOVE":
                    removeTask();
                    break;
                case "LIST":
                    showListOfTask();
                case "EXIT":
                    break;
            }
        }

    }
    private static String showActions(String[] listOfAction){
        Scanner chooseAction = new Scanner(System.in);
        System.out.println(ConsoleColors.BLUE+"Please select an option:"+ConsoleColors.RESET);
        for (String action:listOfAction){
            System.out.println(action);
        }
        return chooseAction.next();
    }
}

