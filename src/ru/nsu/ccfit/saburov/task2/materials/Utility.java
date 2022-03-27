package ru.nsu.ccfit.saburov.task2.materials;

import java.io.InputStreamReader;
import java.util.Map;
import java.util.Stack;
import java.util.Vector;

import static java.lang.Character.isDigit;


public class Utility {
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

    static public String readWord(InputStreamReader i) {
        try {
            int c = i.read();
            while(c == ' ' || c == '\r' || c=='\n'){
                c = i.read();
            }

            if (c == '#') {
                while (c != '\n' && c != -1) {
                    c = i.read();
                }
                return "#";
            }
            StringBuilder s = new StringBuilder();
            while (c != ' ' && c != '\n' && c != '\r' && c != -1 && c != '#') {
                s.append((char) c);
                c = i.read();
            }
            if (c == '#') {
                while (c != '\n' && c != -1) {
                    c = i.read();
                }
            }
            if (c == '\r') {
                //noinspection ResultOfMethodCallIgnored
                i.read();
            }
            return s.toString();

        }
        catch(Exception e){
            System.out.println("IOException in Utility." + e);
            //System.exit(5);
            return "";
        }
    }
    static int Get2Values(Container a, Container b, Stack<Double> S, String operation){
        if (S.empty()){
            System.out.println("Stack is empty when attempting to make an \""+operation +"\" operation");
            //System.exit(5);
            return -1;
        }
        a.myVar = S.pop();
        if (S.empty()){
            System.out.println("Stack has only one value ("+ a.myVar +") when attempting to make an \""+operation +"\" operation");
            S.push(a.myVar);
            //System.exit(5);
            return -1;
        }
        b.myVar = S.pop();
        return 0;
    }
}
