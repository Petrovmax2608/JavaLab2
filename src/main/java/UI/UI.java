package UI;

import logic.Cut;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UI {
    public UI(String[] args) throws IOException {

        Parser parser = new Parser(args);
        if (parser.getStartIndex() == -1 && parser.getEndIndex() == -1) {
            throw new IllegalArgumentException("Invalid range");
        }

        Cut cut = new Cut(getInputLines(parser.getInputFileName()), parser.cutOfWords(),
                parser.getStartIndex(), parser.getEndIndex());
        showResult(parser.getOutputFileName(), cut.cut());
    }

    private List<String> getInputLines(String fileName) throws IOException {
        if (!fileName.isEmpty()) return Files.readAllLines(Paths.get(fileName));
        Scanner scanner = new Scanner(System.in);
        List<String> inputLines = new ArrayList<>();
        String inputLine;
        do {
            inputLine = scanner.nextLine();
            inputLines.add(inputLine);
        } while (!inputLine.isEmpty());

        return inputLines.subList(0, inputLines.size() - 1);
    }

    private void showResult(String fileName, List<String> outputLines) throws IOException {
        if (!fileName.isEmpty()) {
            Files.write(Paths.get(fileName), outputLines);
        } else {
            outputLines.forEach(System.out::println);
        }
    }

}