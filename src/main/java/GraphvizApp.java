import com.hhu.datastructures.prograDatastructures.generic.BinarySearchTree;
import com.hhu.datastructures.prograDatastructures.generic.PrograHashSet;
import com.hhu.util.DrawCalls;
import com.hhu.ui.application.Application;

public class GraphvizApp {

    public static void main(String[] args) {
        startBinarySearchTreeApplicationWithFunnyDrawCalls();
    }

    public DrawCalls build() {

        PrograHashSet<Integer> set = new PrograHashSet<>();
        DrawCalls drawCalls = new DrawCalls(set);

        set.insert(1);
        drawCalls.record();

        set.insert(4);
        drawCalls.record();

        return drawCalls;
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

        Application.start(drawCalls);
    }
}