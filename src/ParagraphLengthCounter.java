import java.io.File;
import java.util.List;

public class ParagraphLengthCounter {
    public static void main(String[] args) {
        String folderPath = "C:\\Users\\User\\IdeaProjects\\ParagraphLengthCounter\\src\\test_files";
        List<File> files = FileUtils.parseFilesFromFolder(folderPath);
        if (files.isEmpty()) {
            System.out.println("У папці не знайдено текстових файлів");
            return;
        }

        StatisticsCollector collector = new StatisticsCollector();

        long singleTime = TimeMeasurer.measure(() ->
                new SingleThreadedProcessor(collector).processFiles(files)
        );

        long multiTime = TimeMeasurer.measure(() ->
                new MultiThreadedProcessor(collector).processFiles(files)
        );

        ResultPresenter.showResults(singleTime, multiTime, collector.getStatistics());
    }
}
