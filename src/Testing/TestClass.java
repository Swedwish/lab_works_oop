package Testing;

import org.junit.jupiter.api.*;
import ru.nsu.ccfit.saburov.task2.materials.*;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Stack;
import java.util.Vector;

class TestClass {

    void pop(Context context) {
        (new Pop()).makeOperation(context);
    }

    void push(Context context) {
        (new Push()).makeOperation(context);
    }

    void define(Context context) {
        (new Define()).makeOperation(context);
    }

    void sqrt(Context context) {
        (new Sqrt()).makeOperation(context);
    }

    void math(Context context) {
        (new Maths()).makeOperation(context);
    }

    @Test
    void PopAndPushTest() {
        Context context = new Context(new Stack<>(),new HashMap<>(),new Vector<>());

        context.argsAdd("3.0");
        push(context);
        assertEquals(context.dataPeek(), 3.0);
        context.argsClear();

        context.argsAdd("5");
        push(context);
        assertEquals(context.dataPeek(), 5.0);
        context.argsClear();

        pop(context);
        assertEquals(context.dataPeek(), 3.0);

        pop(context);
        assertTrue(context.dataEmpty());
    }

    @Test
    void DefineTest() {
        Context context = new Context(new Stack<>(),new HashMap<>(),new Vector<>());

        context.argsAdd("a");
        context.argsAdd("4");
        define(context);
        context.argsRemove(1);
        push(context);
        assertEquals(context.dataPeek(), 4.0);
        context.argsClear();
        context.dataClear();

        context.argsAdd("5.0");
        context.argsAdd("4");
        define(context);
        context.argsRemove(1);
        push(context);
        assertEquals(context.dataPeek(), 4.0);
        context.argsClear();
        context.dataClear();

        context.argsAdd("+5");
        push(context);
        assertEquals(context.dataPeek(), 5.0);
        context.argsClear();
        context.dataClear();

        context.argsAdd("-5");
        push(context);
        assertEquals(context.dataPeek(), -5.0);
        context.argsClear();
        context.dataClear();
    }

    @Test
    void MathTest(){
        Context context = new Context(new Stack<>(),new HashMap<>(),new Vector<>());

        context.argsAdd("25");
        push(context);
        context.argsSet(0,"5.0");
        push(context);
        context.argsSet(0,"+");
        math(context);
        assertEquals(context.dataPeek(),30);
        context.argsClear();

        context.argsAdd("25");
        push(context);
        context.argsSet(0,"5.0");
        push(context);
        context.argsSet(0,"-");
        math(context);
        assertEquals(context.dataPeek(),20);
        context.argsClear();
        
        context.argsAdd("25");
        push(context);
        context.argsSet(0,"5.0");
        push(context);
        context.argsSet(0,"/");
        math(context);
        assertEquals(context.dataPeek(),5);
        context.argsClear();

        context.argsAdd("25");
        push(context);
        context.argsSet(0,"5.0");
        push(context);
        context.argsSet(0,"*");
        math(context);
        assertEquals(context.dataPeek(),125);
        context.argsClear();

        context.argsAdd("25");
        push(context);
        context.argsClear();
        sqrt(context);
        assertEquals(context.dataPeek(),5);
    }
}