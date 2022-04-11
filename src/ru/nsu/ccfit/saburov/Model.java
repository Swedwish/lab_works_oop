package ru.nsu.ccfit.saburov;

import java.lang.*;
import java.util.Vector;

class PairInt {
    int first;
    int second;

    PairInt(int first, int second) {
        this.first = first;
        this.second = second;
    }

    Boolean isEqual(PairInt Pair2) {
        return this.first == Pair2.first && this.second == Pair2.second;
    }

    void setFirst(int value) {
        first = value;
    }

    void setSecond(int value) {
        second = value;
    }

    int getFirst() {
        return first;
    }

    int getSecond() {
        return second;
    }
}

class MineField {
    int size;
    int mineCount;
    int [][] opened;
    char[][] field;
    Vector<PairInt> mineLocationsList = new Vector<>();

    MineField() {
        this.size = 9;
        this.mineCount = 10;
        field = new char[size][size];
        opened = new int[size][size];
    }
    MineField(int size, int mineCount) {
        this.size = size;
        this.mineCount = mineCount;
        field = new char[size][size];
        opened = new int[size][size];
    }

    void fillCell(PairInt coordinates) {
        if (field[coordinates.getFirst()][coordinates.getSecond()] == '*') return;
        field[coordinates.getFirst()][coordinates.getSecond()] = '0';
        for (int i = coordinates.getFirst() - 1; i <= coordinates.getFirst() + 1; i++) {
            for (int j = coordinates.getSecond() - 1; j <= coordinates.getSecond() + 1; j++) {
                try {
                    if (field[i][j] == '*') {
                        field[coordinates.getFirst()][coordinates.getSecond()]++;
                    }
                } catch (ArrayIndexOutOfBoundsException ignored) {

                }
            }
        }
    }

    void fillField() {
        int count = 0;
        PairInt mineCoordinates;
        int flag;
        if (size * size < mineCount) {
            System.out.println("Too many mines.");
            System.exit(5);
        }
        while (count < mineCount) {
            flag = 0;
            mineCoordinates = new PairInt((int) (Math.random() * size), (int) (Math.random() * size));
            for (PairInt pairInt : mineLocationsList) {
                if (mineCoordinates.isEqual(pairInt)) {
                    flag = 1;
                }
            }
            if (flag == 1) {
                continue;
            }
            mineLocationsList.add(mineCoordinates);
            count++;
        }
        for (int i = 0; i < size; i++) {
            field[mineLocationsList.get(i).getFirst()][mineLocationsList.get(i).getSecond()] = '*';
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                fillCell(new PairInt(i,j));
            }
        }
    }

    public int getSize() {
        return size;
    }

    public char getField(PairInt coordinates) {
        return field[coordinates.getFirst()][coordinates.getSecond()];
    }

    public int isOpened(PairInt coordinates){
        return opened[coordinates.getFirst()][coordinates.getSecond()];
    }
}

