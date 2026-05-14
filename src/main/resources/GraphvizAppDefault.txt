import java.util.LinkedList;
import java.util.Stack;

import com.hhu.util.Visualizer;

public class GraphvizApp {

    public static void main(String[] args) {
        startExample();
    }

    public void build() {
        Stack<String> stack = new Stack<>();
        Visualizer.register(stack);

        stack.push("Hello");
        Visualizer.record();

        stack.push("Progra");
        Visualizer.record();

        stack.push("!");
        Visualizer.record();

        stack.pop();
        Visualizer.record();
    }

    static void startExample() {
        LinkedList<String> list = new LinkedList<>();
        Visualizer.register(list);

        list.add("Lotte");
        Visualizer.record();

        list.add("Dieter");
        Visualizer.record();

        list.add("Alexa");
        Visualizer.record();

        Visualizer.show();
    }
}