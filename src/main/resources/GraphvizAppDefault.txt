import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;

import com.hhu.datastructures.prograDatastructures.generic.BinarySearchTree;
import com.hhu.datastructures.prograDatastructures.generic.PrograHashSet;
import com.hhu.datastructures.prograDatastructures.generic.PrograLinkedList;
import com.hhu.ui.application.Application;
import com.hhu.util.DrawCalls;

public class GraphvizApp {

    public static void main(String[] args) {
        startLinkedHashSetApplicationWithFunnyDrawCalls();
    }
     public DrawCalls build() {

        Stack<String> stack = new Stack<>();
        DrawCalls drawCalls = new DrawCalls(stack);

        stack.push("Hello");
        drawCalls.record();

        stack.push("Progra");
        drawCalls.record();

        stack.push("middleElement");
        drawCalls.record();

        stack.push("2026");
        drawCalls.record();

        stack.pop();
        drawCalls.record();

        stack.search("Hello");
        drawCalls.record();

        return drawCalls;
     }

    private static void startPrograHashSetApplication() {

        PrograHashSet<Integer> set = new PrograHashSet<>();
        DrawCalls drawCalls = new DrawCalls(set);

        set.insert(1);
        drawCalls.record();

        set.insert(2);
        drawCalls.record();

        set.insert(3);
        drawCalls.record();

        set.insert(4);
        drawCalls.record();

        set.insert(5);
        drawCalls.record();

        set.insert(6);
        drawCalls.record();

        set.insert(1);
        drawCalls.record();

        set.insert(2);
        drawCalls.record();

        set.insert(3);
        drawCalls.record();

        set.insert(4);
        drawCalls.record();

        set.insert(5);
        drawCalls.record();

        set.insert(6);
        drawCalls.record();

        set.insert(1);
        drawCalls.record();

        set.insert(2);
        drawCalls.record();

        set.insert(3);
        drawCalls.record();

        set.insert(4);
        drawCalls.record();

        Application.start(drawCalls);
    }

    private static void startPrograLinkedListApplication() {

        PrograLinkedList<Integer> list = new PrograLinkedList<>();
        DrawCalls drawCalls = new DrawCalls(list);

        list.add(1);
        drawCalls.record();

        list.add(2);
        drawCalls.record();

        list.add(3);
        drawCalls.record();

        list.add(1);
        drawCalls.record();

        list.add(2);
        drawCalls.record();

        list.add(3);
        drawCalls.record();

        Application.start(drawCalls);
    }

    static void startBinarySearchTreeApplicationWithFunnyDrawCalls() {

        BinarySearchTree<String> treeSet = new BinarySearchTree<>();
        DrawCalls drawCalls = new DrawCalls(treeSet);

        treeSet.insert("Hello");
        drawCalls.record();

        treeSet.insert("Progra");
        drawCalls.record();

        treeSet.insert("2026");
        drawCalls.record();

        treeSet.insert("!");
        drawCalls.record();

        treeSet.insert("Hello");
        drawCalls.record();

        treeSet.insert("Progra");
        drawCalls.record();

        treeSet.insert("2026");
        drawCalls.record();

        treeSet.insert("!");
        drawCalls.record();

        Application.start(drawCalls);
    }

    static void startTreeSetApplicationWithFunnyDrawCalls() {

        TreeSet<String> treeSet = new TreeSet<>();
        DrawCalls drawCalls = new DrawCalls(treeSet);

        treeSet.add("Hello");
        drawCalls.record();

        treeSet.add("Progra");
        drawCalls.record();

        treeSet.add("2026");
        drawCalls.record();

        treeSet.add("!");
        drawCalls.record();

        treeSet.add("Hello");
        drawCalls.record();

        treeSet.add("Progra");
        drawCalls.record();

        treeSet.add("2026");
        drawCalls.record();

        treeSet.add("!");
        drawCalls.record();

        Application.start(drawCalls);
    }

    static void startStackApplicationWithFunnyDrawCalls() {

        Stack<String> stack = new Stack<>();
        DrawCalls drawCalls = new DrawCalls(stack);

        stack.push("Hello");
        drawCalls.record();

        stack.push("Progra");
        drawCalls.record();

        stack.push("middleElement");
        drawCalls.record();

        stack.push("2026");
        drawCalls.record();

        stack.pop();
        drawCalls.record();

        stack.search("Hello");
        drawCalls.record();

        Application.start(drawCalls);
    }

    static void startLinkedHashSetApplicationWithFunnyDrawCalls() {

        LinkedHashSet<String> set = new LinkedHashSet<>();
        DrawCalls drawCalls = new DrawCalls(set);

        set.add("Hello");
        drawCalls.record();

        set.add("Progra");
        drawCalls.record();

        set.add("2026");
        drawCalls.record();

        set.add("!");
        drawCalls.record();

        set.add("Hello");
        drawCalls.record();

        set.add("Progra");
        drawCalls.record();

        set.add("2026");
        drawCalls.record();

        set.add("!");
        drawCalls.record();

        Application.start(drawCalls);
    }

    static void startArrayListApplicationWithFunnyDrawCalls() {

        ArrayList<Integer> list = new ArrayList<>();
        DrawCalls drawCalls = new DrawCalls(list);

        list.add(1);
        drawCalls.record();

        list.add(2);
        drawCalls.record();

        list.add(3);
        drawCalls.record();

        list.add(1);
        drawCalls.record();

        list.add(2);
        drawCalls.record();

        list.add(3);
        drawCalls.record();

        Application.start(drawCalls);
    }

    static void startTreeMapApplicationWithFunnyDrawCalls() {

        TreeMap<String, Integer> map = new TreeMap<>();
        DrawCalls drawCalls = new DrawCalls(map);

        map.put("hello", 1);
        drawCalls.record();

        map.put("world", 2);
        drawCalls.record();

        map.put("java", 3);
        drawCalls.record();

        map.put("jaghva", 35);
        drawCalls.record();

        map.put("hello", 1);
        drawCalls.record();

        map.put("world", 2);
        drawCalls.record();

        map.put("java", 3);
        drawCalls.record();

        map.put("jaghva", 35);
        drawCalls.record();

        Application.start(drawCalls);
    }

    static void startLinkedListApplicationWithFunnyDrawCalls() {

        LinkedList<String> list = new LinkedList<>();
        DrawCalls drawCalls = new DrawCalls(list);

        list.add("Lotte");
        drawCalls.record();

        list.add("Dieter");
        drawCalls.record();

        list.add("Alexa");
        drawCalls.record();

        list.add("Holland");
        drawCalls.record();

        Application.start(drawCalls);
    }
}