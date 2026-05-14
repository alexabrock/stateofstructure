import java.util.LinkedList;
import java.util.Stack;

import com.hhu.util.Visualizer;

public class GraphvizApp {

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
}