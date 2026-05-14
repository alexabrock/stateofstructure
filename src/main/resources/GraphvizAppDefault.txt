import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;

import com.hhu.prograDatastructures.generic.BinarySearchTree;
import com.hhu.prograDatastructures.generic.PrograHashSet;
import com.hhu.prograDatastructures.generic.PrograLinkedList;
import com.hhu.util.Visualizer;

public class GraphvizApp {

    public static void main(String[] args) {
        startLinkedListApplication();
    }

    private static void startPrograHashSetApplication() {
        PrograHashSet<Integer> set = new PrograHashSet<>();
        Visualizer.register(set);

        set.insert(1);
        Visualizer.record();

        set.insert(2);
        Visualizer.record();

        set.insert(3);
        Visualizer.record();

        set.insert(4);
        Visualizer.record();

        set.insert(5);
        Visualizer.record();

        set.insert(6);
        Visualizer.record();

        Visualizer.show();
    }

    private static void startPrograLinkedListApplication() {
        PrograLinkedList<Integer> list = new PrograLinkedList<>();
        Visualizer.register(list);

        list.add(1);
        Visualizer.record();

        list.add(2);
        Visualizer.record();

        list.add(3);
        Visualizer.record();

        Visualizer.show();
    }

    static void startBinarySearchTreeApplication() {
        BinarySearchTree<String> treeSet = new BinarySearchTree<>();
        Visualizer.register(treeSet);

        treeSet.insert("Hello");
        Visualizer.record();

        treeSet.insert("Progra");
        Visualizer.record();

        treeSet.insert("2026");
        Visualizer.record();

        Visualizer.show();
    }

    static void startTreeSetApplication() {
        TreeSet<String> treeSet = new TreeSet<>();
        Visualizer.register(treeSet);

        treeSet.add("Hello");
        Visualizer.record();

        treeSet.add("Progra");
        Visualizer.record();

        Visualizer.show();
    }

    static void startStackApplication() {
        Stack<String> stack = new Stack<>();
        Visualizer.register(stack);

        stack.push("Hello");
        Visualizer.record();

        stack.push("Progra");
        Visualizer.record();

        stack.pop();
        Visualizer.record();

        Visualizer.show();
    }

    static void startLinkedHashSetApplication() {
        LinkedHashSet<String> set = new LinkedHashSet<>();
        Visualizer.register(set);

        set.add("Hello");
        Visualizer.record();

        set.add("Progra");
        Visualizer.record();

        set.add("2026");
        Visualizer.record();

        set.add("!");
        Visualizer.record();

        Visualizer.show();
    }

    static void startArrayListApplication() {
        ArrayList<Integer> list = new ArrayList<>();
        Visualizer.register(list);

        list.add(1);
        Visualizer.record();

        list.add(2);
        Visualizer.record();

        Visualizer.show();
    }

    static void startTreeMapApplication() {
        TreeMap<String, Integer> map = new TreeMap<>();
        Visualizer.register(map);

        map.put("hello", 1);
        Visualizer.record();

        map.put("world", 2);
        Visualizer.record();

        Visualizer.show();
    }

    static void startLinkedListApplication() {
        LinkedList<String> list = new LinkedList<>();
        Visualizer.register(list);

        list.add("Lotte");
        Visualizer.record();

        list.add("Dieter");
        Visualizer.record();

        Visualizer.show();
    }
}