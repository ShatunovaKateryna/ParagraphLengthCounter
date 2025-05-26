import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MultiThreadedProcessor implements FileProcessor{
    private final StatisticsCollector collector;
    private final ExecutorService executor;
    private final ParagraphLengthCalculator calculator = new ParagraphLengthCalculator();

    MultiThreadedProcessor(StatisticsCollector collector) {
        this.collector = collector;
        this.executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }

    @Override
    public void processFiles(List<File> files) {
        List<Future<?>> futures = new ArrayList<>();

        files.forEach(file -> futures.add(executor.submit(() -> {
            try (FileReader reader = new FileReader(file)) {
                collector.merge(calculator.calculate(reader));
            } catch (IOException e) {
                System.err.println("Error processing " + file.getName());
            }
        })));

        awaitCompletion(futures);
        executor.shutdown();
    }

    private void awaitCompletion(List<Future<?>> futures) {
        futures.forEach(future -> {
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                Thread.currentThread().interrupt();
            }
        });
    }
}
