import java.util.ArrayList;
import java.util.Stack;

import com.hhu.legacyDatastructures.VArrayList;
import com.hhu.legacyDatastructures.VLinkedList;
import com.hhu.legacyDatastructures.VStack;
import com.hhu.legacyDatastructures.VTreeMap;
import com.hhu.prograDatastructures.PrograList;
import com.hhu.util.DrawCalls;
import com.hhu.views.application.Application;

public class GraphvizApp {
    public static void main(String[] args) {
        //startVListApplication();

        //startVStackApplication();

        //startVTreeMapApplication();

        //startVPrograListApplication();

        //startVArrayListApplication();

        startVStackApplicationWithFunnyDrawCalls();

        //startVArrayListApplicationWithFunnyDrawCalls();

    }

    static void startVStackApplicationWithFunnyDrawCalls() {
        DrawCalls drawCalls = new DrawCalls();

        Stack<String> stack = new Stack<>();
        stack.push("Hello");
        drawCalls.record(stack, "push()");
        stack.push("Progra");
        drawCalls.record(stack, "push()");
        stack.push("middleElement");
        stack.push("2026");
        stack.pop();
        stack.search("Hello");

        Application.startApplication(drawCalls);
    }
    
    static void startVArrayListApplicationWithFunnyDrawCalls() {
        DrawCalls drawCalls = new DrawCalls();

        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        drawCalls.record(list, "push()");
        list.add(2);
        drawCalls.record(list, "push()");
        list.add(3);

        Application.startApplication(drawCalls);
    }

    private static void startVArrayListApplication() {
        VArrayList<Integer> list = new VArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

       // Application.startApplication(list);
    }

    static void startVPrograListApplication() {
        PrograList list = new PrograList();
        list.insert(1);
        list.insert(2);
        list.insert(3);

        //Application.startApplication( list);

    }

    static void startVListApplication() {

        VLinkedList<String> list = new VLinkedList<>();
        list.add("Hello");
        list.add("Progra");
        list.add("2026");

        //Application.startApplication( list);
    }

    static void startVStackApplication() {


        VStack<String> stack = new VStack<>();
        stack.push("Hello");
        stack.push("Progra");
        stack.push("middleElement");
        stack.push("2026");
        stack.pop();
        stack.search("Hello");

        //Application.startApplication( stack);
    }

    static void startVTreeMapApplication() {


        VTreeMap<String, Integer> map = new VTreeMap<>();
        map.put("hello", 1);
        map.put("world", 2);
        map.put("java", 3);

       // Application.startApplication( map);
    }
}
