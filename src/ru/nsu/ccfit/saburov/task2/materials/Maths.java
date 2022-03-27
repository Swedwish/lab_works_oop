package ru.nsu.ccfit.saburov.task2.materials;

import java.util.Objects;

public class Maths implements Operation {

    public Maths() {
    }

    @Override
    public void makeOperation(Context context) {


        var a = new Container();
        var b = new Container();
        a.myVar = 0.0;
        b.myVar = 0.0;
        if (Objects.equals(context.args.get(0), "+")) {
            if (Utility.Get2Values(a, b, context.Data, "+") != -1)
                context.Data.push(a.myVar + b.myVar);
        } else if (Objects.equals(context.args.get(0), "-")) {
            if (Utility.Get2Values(a, b, context.Data, "-") != -1)
                context.Data.push(b.myVar - a.myVar);
        } else if (Objects.equals(context.args.get(0), "*")) {
            if (Utility.Get2Values(a, b, context.Data, "*") != -1)
                context.Data.push(a.myVar * b.myVar);
        } else if (Objects.equals(context.args.get(0), "/")) {
            if (Utility.Get2Values(a, b, context.Data, "/") != -1)
                context.Data.push(b.myVar / a.myVar);
        }

    }
}
