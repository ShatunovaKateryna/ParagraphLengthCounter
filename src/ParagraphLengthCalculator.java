import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
//Shatunova Kateryna KND-22
public class ParagraphLengthCalculator {
    Map<Integer, Integer> calculate(Reader reader) throws IOException {
        Map<Integer, Integer> result = new HashMap<>();
        try (BufferedReader br = new BufferedReader(reader)) {
            int currentLength = 0;
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    commitParagraph(currentLength, result);
                    currentLength = 0;
                } else {
                    currentLength += line.length();
                }
            }
            commitParagraph(currentLength, result);
        }
        return result;
    }

    private void commitParagraph(int length, Map<Integer, Integer> map) {
        if (length > 0) map.merge(length, 1, Integer::sum);
    }
}
