import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;

import com.hhu.legacyDatastructures.VArrayList;
import com.hhu.legacyDatastructures.VLinkedList;
import com.hhu.legacyDatastructures.VStack;
import com.hhu.legacyDatastructures.VTreeMap;
import com.hhu.prograDatastructures.specific.IntList;
import com.hhu.util.DrawCalls;
import com.hhu.views.application.Application;

public class GraphvizApp {
    public static void main(String[] args) {
        //startVListApplication();

        //startVStackApplication();

        //startVTreeMapApplication();

        //startVPrograListApplication();

        //startVArrayListApplication();

        //startStackApplicationWithFunnyDrawCalls();

        //startArrayListApplicationWithFunnyDrawCalls();

        //startPrograListApplicationWithFunnyDrawCalls();

        startTreeMapApplicationWithFunnyDrawCalls();

        //startLinkedHashSetApplicationWithFunnyDrawCalls();

        //startTreeSetApplicationWithFunnyDrawCalls();

    }

    
    static void startTreeSetApplicationWithFunnyDrawCalls() {
        DrawCalls drawCalls = new DrawCalls();

        TreeSet<String> treeSet = new TreeSet<>();
        treeSet.add("Hello");
        drawCalls.record(treeSet, "push()");
        treeSet.add("Progra");
        drawCalls.record(treeSet, "push()");
        treeSet.add("2026");
        drawCalls.record(treeSet, "push()");
        treeSet.add("!");
        drawCalls.record(treeSet, "push()");

        Application.startApplication(drawCalls);
    }

    static void startStackApplicationWithFunnyDrawCalls() {
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

    static void startLinkedHashSetApplicationWithFunnyDrawCalls() {
        DrawCalls drawCalls = new DrawCalls();

        LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.add("Hello");
        drawCalls.record(linkedHashSet, "push()");
        linkedHashSet.add("Progra");
        drawCalls.record(linkedHashSet, "push()");
        linkedHashSet.add("2026");
        drawCalls.record(linkedHashSet, "push()");
        linkedHashSet.add("!");
        drawCalls.record(linkedHashSet, "push()");

        Application.startApplication(drawCalls);
    }
    
    static void startArrayListApplicationWithFunnyDrawCalls() {
        DrawCalls drawCalls = new DrawCalls();

        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        drawCalls.record(list, "push()");
        list.add(2);
        drawCalls.record(list, "push()");
        list.add(3);

        Application.startApplication(drawCalls);
    }

    static void startPrograListApplicationWithFunnyDrawCalls() {
        DrawCalls drawCalls = new DrawCalls();

        IntList list = new IntList();
        list.add(1);
        drawCalls.record(list, "push()");
        list.add(2);
        drawCalls.record(list, "push()");
        list.add(3);
        drawCalls.record(list, "push()");
        Application.startApplication(drawCalls);
    }

    static void startTreeMapApplicationWithFunnyDrawCalls() {
        DrawCalls drawCalls = new DrawCalls();

        TreeMap<String, Integer> map = new TreeMap<>();
        map.put("hello", 1);
        drawCalls.record(map, "push()");
        map.put("world", 2);
        drawCalls.record(map, "push()");
        map.put("java", 3);
        drawCalls.record(map, "push()");

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
        IntList list = new IntList();
        list.add(1);
        list.add(2);
        list.add(3);

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
