package ru.nsu.ccfit.saburov.task2.materials;

public class PUSH implements Operation {
    public PUSH(){}
    @Override
    public void makeOperation(Context context) {
        double a = Utility.getValue(context.args,context.definitions,0);
        if (!Double.isNaN(a))
            context.Data.push(a);
    }
}
