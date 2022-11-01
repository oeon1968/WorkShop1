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
                   // addNewTask();
                    break;
                case "REMOVE":
                   // removeTask();
                    break;
                case "LIST":
                     showListOfTask(listOfTask);
                case "EXIT":
                    break;
                default:
                    System.out.println("Choose correct option, please.");
            }
        }

    }
    private static String[][] getTasks(File fileName){
        // I check numbers of lines and numbers of field in file
        //I return array of lines and fields

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
                        System.out.print("myListOfTask["+i+"]["+j+"] =");
                        System.out.println(myLine[j]);
                    }
                }
            }
            return myListOfTasks;
        } catch (FileNotFoundException exFNF){
            System.out.println("File "+fileName+" not found!");
            exFNF.getStackTrace();
            return null;
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
    private static void showListOfTask(String[][] vListOfTask){
        System.out.println("Your tasks:");
        for (String[] strings : vListOfTask) {
            for (String string : strings) {
                System.out.print(string + ", ");
            }
            System.out.println();
        }
    }
}

