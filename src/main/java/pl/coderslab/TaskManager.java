package pl.coderslab;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;
import org.apache.commons.lang3.ArrayUtils;


public class TaskManager {
    private static int numberOfLine = 0;
    private static int numberOfField = 0;
    public static String fileName = "tasks.csv";
    private static File  myFile = new File(fileName);
    final static String[] listOfAction = {"Add","Remove", "List", "Exit"};
    private static String[][] listOfTasks  = getTasks(myFile);
  //  private static String[][] myListOfTasks;
    public static void main(String[] args) {
        String action="OTHER";
        while (!"EXIT".equals(action.toUpperCase())){
            action =showActions();
            switch (action.toUpperCase()){
                case "ADD":
                    addNewTask();
                    break;
                case "REMOVE":
                    removeTask();
                    break;
                case "LIST":
                     showListOfTask();
                     break;
                case "EXIT":
                    try {
                        exit();
                        break;
                    } catch (IOException IOex) {
                        IOex.getStackTrace();
                }
                default:
                    System.out.println("Choose correct option, please.");
            }
        }

    }
    private static String[][] getTasks(File vFile){
        // I check numbers of lines and numbers of field in file
        //I return array of lines and fields


        try {
            Scanner scFile = new Scanner(vFile);
            while(scFile.hasNextLine()){
                numberOfLine++;
                numberOfField= scFile.nextLine().split(",").length;
            }
            scFile.close();
            scFile = new Scanner(vFile);
            listOfTasks = new String[numberOfLine][numberOfField];
            while (scFile.hasNextLine()){
                for(int i=0; i < numberOfLine; i++){
                    String[] myLine;
                    myLine = scFile.nextLine().split(",");
                    for (int j=0; j< myLine.length; j++){
                        listOfTasks[i][j] = myLine[j];
                    }
                }
            }
            scFile.close();
            return listOfTasks;
        } catch (FileNotFoundException exFNF){
            System.out.println("File "+vFile+" not found!");
            exFNF.getStackTrace();
            return null;
        }
    }
    private static String showActions(){
        Scanner chooseAction = new Scanner(System.in);
        System.out.println(ConsoleColors.BLUE+"Please select an option:"+ConsoleColors.RESET);
        for (String action:listOfAction){
            System.out.println(action);
        }
        return chooseAction.next();
    }
    private static void showListOfTask(){
        int counter = 0;
        System.out.println("Your tasks:");
        for (String[] strings : listOfTasks) {
            System.out.print(++counter+": ");
            for (String string : strings) {
                System.out.print(string + ", ");
            }
            System.out.println();
        }
    }
    private static void addNewTask() {

        Scanner scFile = new Scanner(System.in);
        String[] newTask = new String[numberOfField];
        System.out.println(ConsoleColors.BLUE+"New task");
        System.out.println(ConsoleColors.YELLOW+"Input task's description:"+ConsoleColors.RESET);
        newTask[0] = scFile.nextLine();
        System.out.println(ConsoleColors.YELLOW+"Input date of expiration (YYYY-MM-DD):"+ConsoleColors.RESET);
        newTask[1] = scFile.nextLine();
        System.out.println(ConsoleColors.YELLOW+"Is the task important (true/false):"+ConsoleColors.RESET);
        newTask[2] = scFile.nextLine();
        listOfTasks = Arrays.copyOf(listOfTasks, ++numberOfLine);
        listOfTasks[numberOfLine-1]= newTask;
        System.out.println(ConsoleColors.BLUE+"Task was added"+ConsoleColors.RESET);
    }
    private static void removeTask(){
        Scanner sc = new Scanner(System.in);
        int removedRecord=-1;
        while ((removedRecord>= listOfTasks.length) || (removedRecord == -1)) {
            System.out.println(ConsoleColors.RED+"Choose number of task to remove:"+ConsoleColors.RESET);
            showListOfTask();
            try {
                removedRecord = Integer.parseInt(sc.next())-1;
            } catch (NumberFormatException ex){
                System.out.println("Input value is incorrect. Try again:");
                removedRecord = -1;
            }
        };
        listOfTasks = ArrayUtils.remove(listOfTasks, removedRecord);
        System.out.println("Task was removed.");
    };

    private static void exit() throws IOException {
        if (myFile.canWrite()) {
            FileWriter vFile = new FileWriter(myFile);
            for (int i = 0; i< listOfTasks.length; i++){
                vFile.write(String.join(",",listOfTasks[i])+"\n");
            }
        }
        System.out.println(ConsoleColors.RED+"bye, bye, bye :)"+ConsoleColors.RESET);
    };
}

