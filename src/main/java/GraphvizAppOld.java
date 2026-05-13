

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;

import com.hhu.datastructures.prograDatastructures.generic.BinarySearchTree;
import com.hhu.datastructures.prograDatastructures.generic.PrograHashSet;
import com.hhu.datastructures.prograDatastructures.generic.PrograLinkedList;
import com.hhu.util.Visualizer;

public class GraphvizAppOld {

    public static void main(String[] args) {
        startTreeMapApplicationWithFunnyDrawCalls();
    }

    private static void startPrograHashSetApplication() {
        PrograHashSet<Integer> set = new PrograHashSet<>();
        Visualizer.register(set);

        for (int i = 1; i <= 6; i++) {
            set.insert(i);
            Visualizer.record();
        }

        // Repeating insertions to show set behavior
        for (int i = 1; i <= 6; i++) {
            set.insert(i);
            Visualizer.record();
        }

        Visualizer.show();
    }

    private static void startPrograLinkedListApplication() {
        PrograLinkedList<Integer> list = new PrograLinkedList<>();
        Visualizer.register(list);

        for (int i = 1; i <= 3; i++) {
            list.add(i);
            Visualizer.record();
        }

        Visualizer.show();
    }

    static void startBinarySearchTreeApplicationWithFunnyDrawCalls() {
        BinarySearchTree<String> treeSet = new BinarySearchTree<>();
        Visualizer.register(treeSet);

        treeSet.insert("Hello");
        Visualizer.record();
        treeSet.insert("Progra");
        Visualizer.record();
        treeSet.insert("2026");
        Visualizer.record();
        treeSet.insert("!");
        Visualizer.record();

        Visualizer.show();
    }

    static void startTreeSetApplicationWithFunnyDrawCalls() {
        TreeSet<String> treeSet = new TreeSet<>();
        Visualizer.register(treeSet);

        treeSet.add("Hello");
        Visualizer.record();
        treeSet.add("Progra");
        Visualizer.record();
        treeSet.add("2026");
        Visualizer.record();
        treeSet.add("!");
        Visualizer.record();

        Visualizer.show();
    }

    static void startStackApplicationWithFunnyDrawCalls() {
        Stack<String> stack = new Stack<>();
        Visualizer.register(stack);

        stack.push("Hello");
        Visualizer.record();
        stack.push("Progra");
        Visualizer.record();
        stack.push("middleElement");
        Visualizer.record();
        stack.push("2026");
        Visualizer.record();

        stack.pop();
        Visualizer.record();

        stack.search("Hello");
        Visualizer.record();

        Visualizer.show();
    }

    static void startLinkedHashSetApplicationWithFunnyDrawCalls() {
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

    static void startArrayListApplicationWithFunnyDrawCalls() {
        ArrayList<Integer> list = new ArrayList<>();
        Visualizer.register(list);

        list.add(1);
        Visualizer.record();
        list.add(2);
        Visualizer.record();
        list.add(3);
        Visualizer.record();

        Visualizer.show();
    }

    static void startTreeMapApplicationWithFunnyDrawCalls() {
        TreeMap<String, Integer> map = new TreeMap<>();
        Visualizer.register(map);

        map.put("hello", 1);
        Visualizer.record();
        map.put("world", 2);
        Visualizer.record();
        map.put("java", 3);
        Visualizer.record();
        map.put("jaghva", 35);
        Visualizer.record();

        Visualizer.show();
    }

    static void startLinkedListApplicationWithFunnyDrawCalls() {
        LinkedList<String> list = new LinkedList<>();
        Visualizer.register(list);

        list.add("Lotte");
        Visualizer.record();
        list.add("Dieter");
        Visualizer.record();
        list.add("Alexa");
        Visualizer.record();
        list.add("Holland");
        Visualizer.record();

        Visualizer.show();
    }
}