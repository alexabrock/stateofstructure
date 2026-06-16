import java.nio.file.Path;

import com.hhu.util.FileManager;
import com.hhu.util.Visualizer;
import com.hhu.util.compiler.Compiler;

public class Main {
    public static void main() {
        String clazz = FileManager.fileToString(Path.of("StateOfStructureDefault.txt"));
        Compiler.compile(clazz);
        Visualizer.show();
    }
}
