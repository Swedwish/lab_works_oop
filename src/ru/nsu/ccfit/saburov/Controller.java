package ru.nsu.ccfit.saburov;

import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;




public class Controller {
    static void openNearby(MineField mineField, PairInt coordinates) {
        mineField.opened[coordinates.getFirst()][coordinates.getSecond()] = 1;
        for (int i = coordinates.getFirst() - 1; i <= coordinates.getFirst() + 1; i++) {
            for (int j = coordinates.getSecond() - 1; j <= coordinates.getSecond() + 1; j++) {
                try {
                    if (mineField.field[i][j] == '0' && mineField.opened[i][j] == 0) {
                        openNearby(mineField, new PairInt(i, j));
                    }
                    mineField.opened[i][j] = 1;
                } catch (ArrayIndexOutOfBoundsException ignored) {
                }
            }
        }
    }

    static void menu(){
        View.printMenu();
        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine().toLowerCase(Locale.ROOT);
        if (Objects.equals(option, "new game")){
            play();
        }
        else if(Objects.equals(option, "high scores")){
            View.printHighScores();
            scanner.nextLine();
            menu();
        }
        else if(Objects.equals(option, "about")){
            View.about();
            scanner.nextLine();
            menu();
        }
        else if (Objects.equals(option, "exit")){
            View.exit();
            System.exit(0);
        }
        else{
            View.tryAgain();
            menu();
        }
    }

    static void play(){
        View.greetings();
        MineField mineField = new MineField();
        mineField.fillField();
        View.printGameTextField(mineField);
        while (true)
            makeMove(mineField);
    }

    static void makeMove(MineField mineField) {
        System.out.print("Write command:");
        Scanner scanner = new Scanner(System.in);
        String command = scanner.next();
        if (command.equals("help")){
            View.help();
            String a = new Scanner(System.in).nextLine();
        }
        else if (scanner.hasNextInt()){
            if (command.equals("g")) {
                String firstCoordinate = scanner.next();
                if (scanner.hasNextInt()) {
                    String secondCoordinate = scanner.next();
                    Go(mineField, firstCoordinate, secondCoordinate);
                }
                else View.wrong();
            } else if (command.equals("f")) {
                String firstCoordinate = scanner.next();
                if (scanner.hasNextInt()){
                    String secondCoordinate = scanner.next();
                    flag(mineField, firstCoordinate, secondCoordinate);
                }
                else View.wrong();
            }
            else if(Character.isDigit(command.charAt(0))){
                if (scanner.hasNextInt()) {
                    String secondCoordinate = scanner.next();
                    Go(mineField, command, secondCoordinate);
                }
                else View.wrong();
            }
            else{
                View.wrong();
            }
        }
    }

    static void flag(MineField mineField, String firstCoordinate, String secondCoordinate){
        if (mineField.opened[Integer.parseInt(firstCoordinate)-1][Integer.parseInt(secondCoordinate)-1] == 0) {
            mineField.opened[Integer.parseInt(firstCoordinate)-1][Integer.parseInt(secondCoordinate)-1] = 2;
        }
        else if (mineField.opened[Integer.parseInt(firstCoordinate)-1][Integer.parseInt(secondCoordinate)-1] == 1){
            View.wrong();
        }
        else{
            mineField.opened[Integer.parseInt(firstCoordinate)-1][Integer.parseInt(secondCoordinate)-1] = 0;
        }
        View.printGameTextField(mineField);
    }

    static void Go(MineField mineField, String firstCoordinate, String secondCoordinate) {
        Scanner scanner = new Scanner(System.in);
        PairInt coordinate = new PairInt(Integer.parseInt(firstCoordinate)-1, Integer.parseInt(secondCoordinate)-1);
        if (coordinate.getSecond()<0 || coordinate.getSecond()>=mineField.size || coordinate.getFirst()<0 || coordinate.getFirst()>=mineField.size){
            View.wrong();
            return;
        }
        mineField.opened[coordinate.getFirst()][coordinate.getSecond()] = 1;
        if (mineField.field[coordinate.getFirst()][coordinate.getSecond()] == '*') {
            View.loss(mineField);
            System.exit(0);
        } else if (mineField.field[coordinate.getFirst()][coordinate.getSecond()] == '0') {
            openNearby(mineField, coordinate);
        }
        View.printGameTextField(mineField);
        int flag = 0;
        for (int i = 0; i < mineField.getSize(); i++) {
            for (int j = 0; j < mineField.getSize(); j++) {
                if (mineField.opened[i][j] != 1){
                    flag++;
                }
            }
        }
        if (flag == mineField.mineCount){
            View.victory(mineField);
            System.exit(0);
        }
    }
}
