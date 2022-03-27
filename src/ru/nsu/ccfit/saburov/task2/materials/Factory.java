package ru.nsu.ccfit.saburov.task2.materials;

import org.apache.log4j.Logger;

import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class Factory {

    public static Operation make_class(String word, Properties pps, Context context, InputStreamReader reader) {
        try {
            if (word.equals("DEFINE")){
                context.args.add(Utility.readWord(reader));
                context.args.add(Utility.readWord(reader));
            }
            else if(word.equals("PUSH")){
                context.args.add(Utility.readWord(reader));
            }
            else if(word.equals("+") || word.equals("-") || word.equals("*") || word.equals("/")){
                context.args.add(word);
            }
            else if(word.equals("EXIT") || word.equals("QUIT")){
                System.exit(5);
            }
            if(pps.containsKey(word)) {
                return (Operation) Class.forName(pps.getProperty(word)).getDeclaredConstructor().newInstance();
            }
            else{
                System.out.println("Can't recognise word \"" + word + "\".\n");
                return null;
            }
        } catch (Exception e) {
            System.out.println("Exception in Factory." + e);
        }
        return null;
    }
}
