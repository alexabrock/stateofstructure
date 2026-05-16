import java.nio.file.Path;

import com.hhu.util.FileManager;
import com.hhu.util.Visualizer;
import com.hhu.util.compiler.Compiler;

public class Main {
    public static void main(String[] args) {
        String clazz = FileManager.fileToString(Path.of("src/main/resources/GraphvizAppDefault.txt"));
        Compiler.compile(clazz);
        Visualizer.show();
    }
}
