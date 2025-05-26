import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
    static List<File> parseFilesFromFolder(String folderPath) {
        List<File> files = new ArrayList<>();
        try {
            Files.walk(Paths.get(folderPath))
                    .filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".txt"))
                    .forEach(path -> files.add(path.toFile()));
        } catch (IOException e) {
            System.err.println("Помилка читання папки: " + e.getMessage());
        }
        return files;
    }
}
