


import java.nio.file.Path;
import java.nio.file.Paths;

import com.hhu.datastructures.VArrayList;
import com.hhu.datastructures.VLinkedList;
import com.hhu.datastructures.VStack;
import com.hhu.datastructures.VTreeMap;
import com.hhu.datastructures.VPrograList;
import com.hhu.views.application.Application;

public class GraphvizApp {
    public static void main(String[] args) {
        //startVListApplication();

        startVStackApplication();

        //startVTreeMapApplication();

        //startVPrograListApplication();

        //startVArrayListApplication();

    }

    private static void startVArrayListApplication() {
        VArrayList list = new VArrayList();
        list.add(1);
        list.add(2);
        list.add(3);

        Path path = Paths.get("src/main/java/GraphvizApp.java");
        Application.startApplication(list);
    }

    static void startVPrograListApplication() {
        VPrograList list = new VPrograList();
        list.insert(1);
        list.insert(2);
        list.insert(3);

        Path path = Paths.get("src/main/java/GraphvizApp.java");
        Application.startApplication( list);

    }

    static void startVListApplication() {

        VLinkedList<String> list = new VLinkedList<>();
        list.add("Hello");
        list.add("Progra");
        list.add("2026");

        Path path = Paths.get("src/main/java/GraphvizApp.java");
        Application.startApplication( list);
    }

    static void startVStackApplication() {


        VStack<String> stack = new VStack<>();
        stack.push("Hello");
        stack.push("Progra");
        stack.push("middleElement");
        stack.push("2026");
        stack.pop();
        stack.search("Hello");

        Path path = Paths.get("src/main/java/GraphvizApp.java");
        Application.startApplication( stack);
    }

    static void startVTreeMapApplication() {


        VTreeMap<String, Integer> map = new VTreeMap<>();
        map.put("hello", 1);
        map.put("world", 2);
        map.put("java", 3);

        Path path = Paths.get("src/main/java/GraphvizApp.java");
        Application.startApplication( map);
    }
}
