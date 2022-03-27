package Testing;

import org.junit.jupiter.api.*;
import ru.nsu.ccfit.saburov.task2.materials.*;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Stack;
import java.util.Vector;

class TestClass {

    void pop(Context context) {
        (new POP()).makeOperation(context);
    }

    void push(Context context) {
        (new PUSH()).makeOperation(context);
    }

    void define(Context context) {
        (new DEFINE()).makeOperation(context);
    }

    void sqrt(Context context) {
        (new SQRT()).makeOperation(context);
    }

    void math(Context context) {
        (new Maths()).makeOperation(context);
    }

    @Test
    void PopAndPushTest() {
        Context context = new Context(new Stack<>(),new HashMap<>(),new Vector<>());

        context.argsAdd("3.0");
        push(context);
        assertEquals(context.DataPeek(), 3.0);
        context.argsClear();

        context.argsAdd("5");
        push(context);
        assertEquals(context.DataPeek(), 5.0);
        context.argsClear();

        pop(context);
        assertEquals(context.DataPeek(), 3.0);

        pop(context);
        assertTrue(context.DataEmpty());
    }

    @Test
    void DefineTest() {
        Context context = new Context(new Stack<>(),new HashMap<>(),new Vector<>());

        context.argsAdd("a");
        context.argsAdd("4");
        define(context);
        context.argsRemove(1);
        push(context);
        assertEquals(context.DataPeek(), 4.0);
        context.argsClear();
        context.DataClear();

        context.argsAdd("5.0");
        context.argsAdd("4");
        define(context);
        context.argsRemove(1);
        push(context);
        assertEquals(context.DataPeek(), 4.0);
        context.argsClear();
        context.DataClear();

        context.argsAdd("+5");
        push(context);
        assertEquals(context.DataPeek(), 5.0);
        context.argsClear();
        context.DataClear();

        context.argsAdd("-5");
        push(context);
        assertEquals(context.DataPeek(), -5.0);
        context.argsClear();
        context.DataClear();
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
        assertEquals(context.DataPeek(),30);
        context.argsClear();

        context.argsAdd("25");
        push(context);
        context.argsSet(0,"5.0");
        push(context);
        context.argsSet(0,"-");
        math(context);
        assertEquals(context.DataPeek(),20);
        context.argsClear();
        
        context.argsAdd("25");
        push(context);
        context.argsSet(0,"5.0");
        push(context);
        context.argsSet(0,"/");
        math(context);
        assertEquals(context.DataPeek(),5);
        context.argsClear();

        context.argsAdd("25");
        push(context);
        context.argsSet(0,"5.0");
        push(context);
        context.argsSet(0,"*");
        math(context);
        assertEquals(context.DataPeek(),125);
        context.argsClear();

        context.argsAdd("25");
        push(context);
        context.argsClear();
        sqrt(context);
        assertEquals(context.DataPeek(),5);
    }
}