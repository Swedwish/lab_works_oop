package ru.nsu.ccfit.saburov;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class View {
    static void printDashLine(int size){
        for(int i = 0; i <= size*4; i++){
            System.out.print("\u2014");
        }
        System.out.println();
    }

     public static void printTextField(MineField mineField){
        int size = mineField.getSize();
        printDashLine(size);
        for (int i = 0; i < size; i++){
            System.out.print("| ");
            for(int j = 0; j< size; j++){
                System.out.print(mineField.getField(new PairInt(i,j)));
                System.out.print(" | ");
            }
            System.out.println();
            printDashLine(size);
        }
    }

    public static void printGameTextField(MineField mineField){
        int size = mineField.getSize();
        printDashLine(size);
        for (int i = 0; i < size; i++){
            System.out.print("| ");
            for(int j = 0; j< size; j++){
                if(mineField.isOpened(new PairInt(i,j))==1){
                    System.out.print(mineField.getField(new PairInt(i,j)));
                }
                else if(mineField.isOpened(new PairInt(i,j))==2){
                    System.out.print("\u1F32");
                }
                else{
                    System.out.print("#");
                }
                System.out.print(" | ");
            }
            System.out.println();
            printDashLine(size);
        }
    }

    static void victory(MineField mineField){
        System.out.println("You win!!!!!!!");
        printTextField(mineField);
    }

    static void loss(MineField mineField){
        System.out.println("Game over (9(((((");
        printTextField(mineField);
    }

    static void greetings(){
        System.out.println("""
                Welcome to the Minesweeper!
                Game starts now!
                (Type "help" if you feel lost)""");
    }

    public static void printMenu() {
        System.out.println("""
                ----!!!MENU!!!----
                New Game
                High Scores
                About
                Exit""");
    }

    public static void about() {
        System.out.println("""
                Made in 24 hours by a 2nd grade student by name V.S. (Visual studio).
                """);
        System.out.println("Type anything to go back to menu.");
    }

    public static void printHighScores() {
        try {
            Scanner scanner = new Scanner(new FileInputStream("src/ru/nsu/ccfit/saburov/resoruses/HighScores.txt"));
            for (int i = 0; i < 11; i++) {
                System.out.println(scanner.nextLine());
            }
            System.out.println("Type anything to go back to menu.");
        }
        catch (FileNotFoundException e){
            System.out.println("Whoops. High Scores file not found. Not a big deal tho, u can still play.");
        }
    }

    public static void exit() {
        System.out.println("Thanks for playing (or not playing if you weren't really playing)!");
    }

    public static void tryAgain() {
        System.out.println("Incorrect input, try again.");
    }

    static void help(){
        System.out.println("""
                If you don't know the rules, go google it, idk.
                g + coords (e.g. g 3 4) or just coords (e.g. 3 4) to open the field.
                f + coords (e.g. f 3 4) to mark the place where you think the mine could be.
                That's it.""");
        System.out.println("Type anything to go back to game.");
    }

    public static void wrong() {
        System.out.println("Wrong, try again!");
    }
}