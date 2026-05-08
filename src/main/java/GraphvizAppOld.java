import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;

import com.hhu.datastructures.prograDatastructures.generic.BinarySearchTree;
import com.hhu.datastructures.prograDatastructures.generic.PrograHashSet;
import com.hhu.datastructures.prograDatastructures.generic.PrograLinkedList;
import com.hhu.util.DrawCalls;
import com.hhu.views.Application;

public class GraphvizAppOld{

    public static void main(String[] args) {

        // startStackApplicationWithFunnyDrawCalls();

        // startArrayListApplicationWithFunnyDrawCalls();

        startTreeMapApplicationWithFunnyDrawCalls();

        // startLinkedHashSetApplicationWithFunnyDrawCalls();

        // startTreeSetApplicationWithFunnyDrawCalls();

        // startLinkedListApplicationWithFunnyDrawCalls();

        // startBinarySearchTreeApplicationWithFunnyDrawCalls();

        // startPrograLinkedListApplication();

        // startPrograHashSetApplication();

    }

    public DrawCalls build() {
        DrawCalls drawCalls = new DrawCalls();

        PrograHashSet<Integer> set = new PrograHashSet<>();
        set.insert(1);
        drawCalls.record(set);
        set.insert(2);
        drawCalls.record(set);
        set.insert(3);
        drawCalls.record(set);
        set.insert(4);
        drawCalls.record(set);
        set.insert(5);
        drawCalls.record(set);
        set.insert(6);
        drawCalls.record(set);
        set.insert(1);
        drawCalls.record(set);
        set.insert(2);
        drawCalls.record(set);
        set.insert(3);
        drawCalls.record(set);
        set.insert(4);
        drawCalls.record(set);
        set.insert(5);
        drawCalls.record(set);
        set.insert(6);
        drawCalls.record(set);
        set.insert(1);
        drawCalls.record(set);
        set.insert(2);
        drawCalls.record(set);
        set.insert(3);
        drawCalls.record(set);
        set.insert(4);
        drawCalls.record(set);

        return drawCalls;
    }

    private static void startPrograHashSetApplication() {
        DrawCalls drawCalls = new DrawCalls();

        PrograHashSet<Integer> set = new PrograHashSet<>();
        set.insert(1);
        drawCalls.record(set);
        set.insert(2);
        drawCalls.record(set);
        set.insert(3);
        drawCalls.record(set);
        set.insert(4);
        drawCalls.record(set);
        set.insert(5);
        drawCalls.record(set);
        set.insert(6);
        drawCalls.record(set);
        set.insert(1);
        drawCalls.record(set);
        set.insert(2);
        drawCalls.record(set);
        set.insert(3);
        drawCalls.record(set);
        set.insert(4);
        drawCalls.record(set);
        set.insert(5);
        drawCalls.record(set);
        set.insert(6);
        drawCalls.record(set);
        set.insert(1);
        drawCalls.record(set);
        set.insert(2);
        drawCalls.record(set);
        set.insert(3);
        drawCalls.record(set);
        set.insert(4);
        drawCalls.record(set);

        Application.start(drawCalls);
    }

    private static void startPrograLinkedListApplication() {
        DrawCalls drawCalls = new DrawCalls();

        PrograLinkedList<Integer> list = new PrograLinkedList<>();
        list.add(1);
        drawCalls.record(list);
        list.add(2);
        drawCalls.record(list);
        list.add(3);
        list.add(1);
        drawCalls.record(list);
        list.add(2);
        drawCalls.record(list);
        list.add(3);

        Application.start(drawCalls);
    }

    static void startBinarySearchTreeApplicationWithFunnyDrawCalls() {
        DrawCalls drawCalls = new DrawCalls();

        BinarySearchTree<String> treeSet = new BinarySearchTree<>();
        treeSet.insert("Hello");
        drawCalls.record(treeSet);
        treeSet.insert("Progra");
        drawCalls.record(treeSet);
        treeSet.insert("2026");
        drawCalls.record(treeSet);
        treeSet.insert("!");
        drawCalls.record(treeSet);
        treeSet.insert("Hello");
        drawCalls.record(treeSet);
        treeSet.insert("Progra");
        drawCalls.record(treeSet);
        treeSet.insert("2026");
        drawCalls.record(treeSet);
        treeSet.insert("!");
        drawCalls.record(treeSet);

        Application.start(drawCalls);
    }

    static void startTreeSetApplicationWithFunnyDrawCalls() {
        DrawCalls drawCalls = new DrawCalls();

        TreeSet<String> treeSet = new TreeSet<>();
        treeSet.add("Hello");
        drawCalls.record(treeSet);
        treeSet.add("Progra");
        drawCalls.record(treeSet);
        treeSet.add("2026");
        drawCalls.record(treeSet);
        treeSet.add("!");
        drawCalls.record(treeSet);
        treeSet.add("Hello");
        drawCalls.record(treeSet);
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
        drawCalls.record(linkedHashSet);
        linkedHashSet.add("Progra");
        drawCalls.record(linkedHashSet);
        linkedHashSet.add("2026");
        drawCalls.record(linkedHashSet);
        linkedHashSet.add("!");
        drawCalls.record(linkedHashSet);
        linkedHashSet.add("Hello");
        drawCalls.record(linkedHashSet);
        linkedHashSet.add("Progra");
        drawCalls.record(linkedHashSet);
        linkedHashSet.add("2026");
        drawCalls.record(linkedHashSet);
        linkedHashSet.add("!");
        drawCalls.record(linkedHashSet);

        Application.start(drawCalls);
    }

    static void startArrayListApplicationWithFunnyDrawCalls() {
        DrawCalls drawCalls = new DrawCalls();

        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        drawCalls.record(list);
        list.add(2);
        drawCalls.record(list);
        list.add(3);
        list.add(1);
        drawCalls.record(list);
        list.add(2);
        drawCalls.record(list);
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
        map.put("jaghva", 35);
        drawCalls.record(map);
        map.put("hello", 1);
        drawCalls.record(map);
        map.put("world", 2);
        drawCalls.record(map);
        map.put("java", 3);
        drawCalls.record(map);
        map.put("jaghva", 35);
        drawCalls.record(map);

        Application.start(drawCalls);

    }

    static void startLinkedListApplicationWithFunnyDrawCalls() {

        LinkedList<String> list = new LinkedList<>();
        DrawCalls drawCalls = new DrawCalls();
        list.add("Lotte");
        drawCalls.record(list);
        list.add("Dieter");
        drawCalls.record(list);
        list.add("Alexa");
        drawCalls.record(list);
        list.add("Holland");
        drawCalls.record(list);
        Application.start(drawCalls);
    }

}
