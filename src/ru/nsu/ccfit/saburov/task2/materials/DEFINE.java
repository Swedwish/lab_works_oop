package ru.nsu.ccfit.saburov.task2.materials;

public class DEFINE implements Operation{

    public DEFINE() {
    }

    @Override
    public void makeOperation(Context context) {
        double a = Utility.getValue(context.args,context.definitions,1);
        if (!Double.isNaN(a))
            context.definitions.put(context.args.get(0),a);
    }
}
