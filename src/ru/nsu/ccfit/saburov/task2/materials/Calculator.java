package ru.nsu.ccfit.saburov.task2.materials;

import org.apache.log4j.Logger;

import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Properties;
import java.util.Stack;
import java.util.Vector;

public class Calculator {
    private final Context context = new Context(new Stack<>(),new HashMap<>(),new Vector<>());
    private static final Logger logger = Logger.getLogger(Calculator.class);
    private final InputStreamReader reader;
    private final String config;

    public Calculator(InputStreamReader reader, String config) {
        this.config = config;
        this.reader = reader;
    }


    public void Calculate() {
        try {
            Properties pps = new Properties();
            pps.load(Factory.class.getResourceAsStream(config));
            String word;
            Operation worker;
            while (!(word = Utility.readWord(reader)).equals("")) {
                context.args.clear();
                worker = Factory.make_class(word,pps,context,reader);
                if (worker == null){
                    continue;
                }
                logger.info("Executing "+ word+"...");
                worker.makeOperation(context);
            }
        } catch (Exception e) {
            System.out.println("Exception in Factory." + e);
        }
    }
}
