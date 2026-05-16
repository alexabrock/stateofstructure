import java.util.Stack;

import com.hhu.util.Visualizer;

public class GraphvizApp {

    public void build() {
        Stack<String> stack = new Stack<>();
        Visualizer.register(stack);
        stack.push("Hello");
        stack.push("Progra");
        stack.push("!");
        stack.pop();
    }
}