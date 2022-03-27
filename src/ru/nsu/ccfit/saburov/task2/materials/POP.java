package ru.nsu.ccfit.saburov.task2.materials;

public class POP implements Operation {
    public POP(){}

    @Override
    public void makeOperation(Context context) {
        if (!context.Data.empty()){
            context.Data.pop();
        }
        else{
            System.out.println("Stack is empty when attempting to pop.");
        }

    }
}
