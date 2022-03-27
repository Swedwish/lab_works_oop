package ru.nsu.ccfit.saburov.task2.materials;

import java.util.Map;
import java.util.Vector;

import static java.lang.Character.isDigit;

public class PUSH implements Operation {
    public PUSH(){}

    static double getValue(Vector<String> args, Map<String, Double> definitions, int pos){
        int dotCounter = 0;
        if(definitions.containsKey(args.get(pos)))
            return definitions.get(args.get(pos));
        for (int i = 0; i<args.get(pos).length();i++){
            if (args.get(pos).toCharArray()[i] == '.'){
                dotCounter++;
            }
            if(!isDigit(args.get(pos).toCharArray()[i]) && !(i==0 && (args.get(pos).toCharArray()[i] == '-' || args.get(pos).toCharArray()[i] == '+')) && !(args.get(pos).toCharArray()[i] == '.') || dotCounter>1){
                System.out.println("Failed to recognise param of the function. Expected defined variable or number. Got \""+args.get(pos)+ "\" instead.");
                //System.exit(5);
                return Double.NaN;
            }
        }
        return Double.parseDouble(args.get(pos));
    }
    @Override
    public void makeOperation(Context context) {
        double a = getValue(context.args,context.definitions,0);
        if (!Double.isNaN(a))
            context.Data.push(a);
    }
}
