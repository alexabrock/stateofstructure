import com.hhu.datastructures.legacyDatastructures.VArrayList;
import com.hhu.datastructures.legacyDatastructures.VLinkedList;
import com.hhu.datastructures.legacyDatastructures.VStack;
import com.hhu.datastructures.legacyDatastructures.VTreeMap;

public class GraphvizAppOld {
    public static void main(String[] args) {
        //startVListApplication();

        //startVStackApplication();

        //startVTreeMapApplication();

        //startVArrayListApplication();

    }


    private static void startVArrayListApplication() {
        VArrayList<Integer> list = new VArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

       // Application.startApplication(list);
    }

    static void startVListApplication() {

        VLinkedList<String> list = new VLinkedList<>();
        list.add("Hello");
        list.add("Progra");
        list.add("2026");

        //Application.startApplication( list);
    }

    static void startVStackApplication() {


        VStack<String> stack = new VStack<>();
        stack.push("Hello");
        stack.push("Progra");
        stack.push("middleElement");
        stack.push("2026");
        stack.pop();
        stack.search("Hello");

        //Application.startApplication( stack);
    }

    static void startVTreeMapApplication() {


        VTreeMap<String, Integer> map = new VTreeMap<>();
        map.put("hello", 1);
        map.put("world", 2);
        map.put("java", 3);

       // Application.startApplication( map);
    }
}
