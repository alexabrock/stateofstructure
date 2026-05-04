import java.util.Stack;
import com.hhu.views.Application;

public class GraphvizApp {

    public static void main(String[] args) {

        //startStackApplicationWithFunnyDrawCalls();

    }

    static void startStackApplicationWithFunnyDrawCalls() {

        Stack<String> stack = new Stack<>();
        stack.push("Hello");
        stack.push("Progra");
        stack.push("middleElement");
        stack.push("2026");
        stack.pop();
        stack.search("Hello");
        Application.start(stack);

    }
}
