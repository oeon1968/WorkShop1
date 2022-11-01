package pl.coderslab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import org.apache.commons.lang3.ArrayUtils;


public class TaskManager {
    public static void main(String[] args) {
        File myFile = new File("tasks.csv");
        String[] listOfAction = {"Add","Remove", "List", "Exit"};
        String action="OTHER";
        String[][] listOfTask  = getTasks(myFile);
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
    private static String[][] getTasks(File fileName){

        try {
            int numberOfLine = 0;
            int numberOfField = 0;
            Scanner scFile = new Scanner(fileName);
            String[][] myListOfTasks;
            while(scFile.hasNextLine()){
                numberOfLine++;
                numberOfField= scFile.nextLine().split(",").length;
            }
            scFile.close();
            scFile = new Scanner(fileName);
            myListOfTasks = new String[numberOfLine][numberOfField];
            while (scFile.hasNextLine()){
                for(int i=0; i < numberOfLine; i++){
                    String[] myLine;
                    myLine = scFile.nextLine().split(",");
                    for (int j=0; j< myLine.length; j++){
                        myListOfTasks[i][j] = myLine[j];
                    }

                }


            }

        } catch (FileNotFoundException exFNF){
            System.out.println("File "+fileName+" not found!");
            exFNF.getStackTrace();
            return null;
        }
        return myListOfTask;
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

