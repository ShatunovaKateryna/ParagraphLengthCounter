import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class StatisticsCollector {
    private final ConcurrentMap<Integer, Integer> globalStats = new ConcurrentHashMap<>();

    public void merge(Map<Integer, Integer> partialResult) {
        partialResult.forEach((key, value) ->
                globalStats.merge(key, value, Integer::sum)
        );
    }

    public Map<Integer, Integer> getStatistics() {
        return new HashMap<>(globalStats);
    }
}

interface FileProcessor {
    void processFiles(List<File> files);
}

