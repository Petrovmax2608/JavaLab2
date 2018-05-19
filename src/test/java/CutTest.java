import UI.UI;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CutTest {

    private List<String[]> normalCommands = Arrays.asList(
            new String[]{"cut", "-c", "-o", "outFile.txt", "file.txt", "2-10"},
            new String[]{"cut", "-w", "-o", "outFile.txt", "file.txt", "2-5"},
            new String[]{"cut", "-c", "-o", "outFile.txt", "file.txt", "-1"},
            new String[]{"cut", "-c", "-o", "outFile.txt", "file.txt", "5-"},
            new String[]{"cut", "-w", "-o", "outFile.txt", "file.txt", "2-"},
            new String[]{"cut", "-w", "-o", "outFile.txt", "file.txt", "-1"}
    );

    private List<List<String>> outputLines = Arrays.asList(
            Arrays.asList(
                    "остой те",
                    "стирован",
                    "которой ",
                    "так же с"
            ),
            Arrays.asList(
                    "для",
                    "",
                    "символов",
                    "же слов и"
            ),
            Arrays.asList(
                    "П",
                    "т",
                    "Н",
                    "а"
            ),
            Arrays.asList(
                    "ой текст для",
                    "рования",
                    "орой последовательности символов",
                    " же слов и слов и слов и слов"
            ),
            Arrays.asList(
                    "для",
                    "",
                    "символов",
                    "же слов и слов и слов и слов"
            ),
            Arrays.asList(
                    "Простой",
                    "тестирования",
                    "Некоторой",
                    "а"
            )
    );

    @Test
    void commandTest() throws IOException {
        for (int i = 0; i < 6; i++) {
            new UI(normalCommands.get(i));
            File file = new File("outFile.txt");
            assertEquals(Files.readAllLines(file.toPath()), outputLines.get(i));
            file.delete();
        }
    }
}