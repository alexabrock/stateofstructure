import com.hhu.datastructures.prograDatastructures.generic.BinarySearchTree;
import com.hhu.datastructures.prograDatastructures.generic.HashSet;
import com.hhu.util.DrawCalls;
import com.hhu.views.Application;

public class GraphvizApp {

    public static void main(String[] args) {
        startBinarySearchTreeApplicationWithFunnyDrawCalls();

    }

    public DrawCalls build() {
        DrawCalls drawCalls = new DrawCalls();

        HashSet<Integer> set = new HashSet<>();
        set.insert(1);
        drawCalls.record(set);
        set.insert(4);
        drawCalls.record(set);

        return drawCalls;
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

        Application.start(drawCalls);
    }
}
