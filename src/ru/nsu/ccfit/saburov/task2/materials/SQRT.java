package ru.nsu.ccfit.saburov.task2.materials;

public class SQRT implements Operation {


    @Override
    public void makeOperation(Context context) {
        if (context.Data.empty()){
            System.out.println("Stack is empty when attempting to make an \"SQRT\" operation");
            //System.exit(5);
            return;
        }
        double a = context.Data.pop();
        if (a < 0){
            System.out.println("Can't take a square root of a negative number. ୧((#Φ益Φ#))୨");
            //System.exit(5);
        }
        else {
            context.Data.push(Math.sqrt(a));
        }
    }
}
