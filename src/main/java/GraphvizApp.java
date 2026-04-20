import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;

import com.hhu.util.DrawCalls;
import com.hhu.views.Application;

public class GraphvizApp {

    public static void main(String[] args) {

        startStackApplicationWithFunnyDrawCalls();

        // startArrayListApplicationWithFunnyDrawCalls();

        //startTreeMapApplicationWithFunnyDrawCalls();

        //startLinkedHashSetApplicationWithFunnyDrawCalls();

        // startTreeSetApplicationWithFunnyDrawCalls();

        //startLinkedListApplicationWithFunnyDrawCalls();

    }

    static void startTreeSetApplicationWithFunnyDrawCalls() {
        DrawCalls drawCalls = new DrawCalls();

        TreeSet<String> treeSet = new TreeSet<>();
        treeSet.add("Hello");
        drawCalls.record(treeSet );
        treeSet.add("Progra");
        drawCalls.record(treeSet);
        treeSet.add("2026");
        drawCalls.record(treeSet);
        treeSet.add("!");
        drawCalls.record(treeSet);

        Application.start(drawCalls);
    }

    static void startStackApplicationWithFunnyDrawCalls() {
        DrawCalls drawCalls = new DrawCalls();

        Stack<String> stack = new Stack<>();
        stack.push("Hello");
        drawCalls.record(stack);
        stack.push("Progra");
        drawCalls.record(stack);
        stack.push("middleElement");
        drawCalls.record(stack);
        stack.push("2026");
        drawCalls.record(stack);
        stack.pop();
        drawCalls.record(stack);
        stack.search("Hello");
        drawCalls.record(stack);

        Application.start(drawCalls);
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

        Application.start(drawCalls);
    }

    static void startArrayListApplicationWithFunnyDrawCalls() {
        DrawCalls drawCalls = new DrawCalls();

        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        drawCalls.record(list, "push()");
        list.add(2);
        drawCalls.record(list, "push()");
        list.add(3);

        Application.start(drawCalls);
    }

    static void startTreeMapApplicationWithFunnyDrawCalls() {
        DrawCalls drawCalls = new DrawCalls();

        TreeMap<String, Integer> map = new TreeMap<>();
        map.put("hello", 1);
        drawCalls.record(map);
        map.put("world", 2);
        drawCalls.record(map);
        map.put("java", 3);
        drawCalls.record(map);
        map.put("javasda", 3);
        drawCalls.record(map);

        Application.start(drawCalls);

    }

    static void startLinkedListApplicationWithFunnyDrawCalls() {

        LinkedList<String> list = new LinkedList<>();
        DrawCalls drawCalls = new DrawCalls();
        list.add("Lotte");
        list.add("Dieter");
        list.add("Alexa");
        list.add("Holland");
        drawCalls.record(list, "push()");
        Application.start(drawCalls);
    }

}
