package ru.nsu.ccfit.saburov.task2.materials;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class PRINT implements Operation{

    public PRINT() {
    }

    @Override
    public void makeOperation(Context context) {
        if (!context.Data.empty()) {
            System.out.println(context.Data.peek());
            try {
                OutputStreamWriter s = new OutputStreamWriter(new FileOutputStream("src/Testing/output-log.txt", true));
                s.write(context.Data.peek()+"\n");
                s.close();
            }
            catch (Exception e){
                System.out.println(e);
            }
        }
        else{
            System.out.println("Stack is empty when attempting to print.");
        }
    }
}
