import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;

import com.hhu.legacyDatastructures.VArrayList;
import com.hhu.legacyDatastructures.VLinkedList;
import com.hhu.legacyDatastructures.VStack;
import com.hhu.legacyDatastructures.VTreeMap;
import com.hhu.util.DrawCalls;
import com.hhu.views.application.Application;

public class GraphvizApp {
    
    public static void main(String[] args) {

        //startStackApplicationWithFunnyDrawCalls();

        //startArrayListApplicationWithFunnyDrawCalls();

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

}
