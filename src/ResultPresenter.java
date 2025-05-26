import java.util.Map;

public class ResultPresenter {
    static void showResults(long singleTime, long multiTime, Map<Integer, Integer> stats) {
        System.out.println("\nСтатистика довжин:");
        stats.forEach((length, count) ->
                System.out.println(length + " символів: " + count + " абзаців")
        );

        System.out.println("Результати:");
        System.out.println("Однопотоковий час: " + singleTime + " мс");
        System.out.println("Багатопотоковий час: " + multiTime + " мс");
        System.out.println("Різниця: " + (singleTime - multiTime) + " мс");
    }
}
