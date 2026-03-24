package com.hhu;


import java.nio.file.Path;
import java.nio.file.Paths;

import com.hhu.datastructures.VLinkedList;
import com.hhu.datastructures.VStack;
import com.hhu.datastructures.VTreeMap;
import com.hhu.views.application.Application;

public class GraphvizApp {
    public static void main(String[] args) {
        //startVListApplication();

        startVStackApplication();

        //startVTreeMapApplication();

    }

    static void startVListApplication() {

        VLinkedList<String> list = new VLinkedList<>();
        list.add("Hello");
        list.add("Progra");
        list.add("2026");

        Path path = Paths.get("src/main/java/com/hhu/GraphvizApp.java");
        Application.startApplication(path, list);
    }

    static void startVStackApplication() {


        VStack<String> stack = new VStack<>();
        stack.push("Hello");
        stack.push("Progra");
        stack.push("middleElement");
        stack.push("2026");

        Path path = Paths.get("src/main/java/com/hhu/GraphvizApp.java");
        Application.startApplication(path, stack);
    }

    static void startVTreeMapApplication() {


        VTreeMap<String, Integer> map = new VTreeMap<>();
        map.put("hello", 1);
        map.put("world", 2);
        map.put("java", 3);

        Path path = Paths.get("src/main/java/com/hhu/GraphvizApp.java");
        Application.startApplication(path, map);
    }
}
