import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class SingleThreadedProcessor implements FileProcessor{
    private final StatisticsCollector collector;
    private final ParagraphLengthCalculator calculator = new ParagraphLengthCalculator();

    SingleThreadedProcessor(StatisticsCollector collector) {
        this.collector = collector;
    }

    @Override
    public void processFiles(List<File> files) {
        files.forEach(file -> {
            try (FileReader reader = new FileReader(file)) {
                collector.merge(calculator.calculate(reader));
            } catch (IOException e) {
                System.err.println("Error processing " + file.getName());
            }
        });
    }
}
