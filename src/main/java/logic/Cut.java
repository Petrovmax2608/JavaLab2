package logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Cut {

    private List<String> lines;
    private boolean cutOfWords;
    private boolean toEndOfLine;
    private boolean fromStartOfLine;
    private int startIndex;
    private int endIndex;

    public Cut(List<String> lines, boolean cutOfWords, int startIndex, int endIndex) {
        this.lines = lines;
        this.cutOfWords = cutOfWords;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        toEndOfLine = endIndex == -1;
        fromStartOfLine = startIndex == -1;
    }

    public List<String> cut() {
        List<String> outputLines = new ArrayList<>();
        lines.forEach(line -> {
            String outputLine = "";
            if (!cutOfWords) {
                if (toEndOfLine) {
                    if (startIndex < line.length()) {
                        outputLine = line.substring(startIndex);
                    }
                } else if (fromStartOfLine) {
                    outputLine = line.substring(0, endIndex < line.length() ? endIndex : line.length());
                } else {
                    if (startIndex < endIndex) {
                        if (startIndex < line.length()) {
                            outputLine = line.substring(startIndex,
                                    endIndex < line.length() ? endIndex : line.length());
                        }
                    }
                }
            } else {
                List<String> wordsOfLine = Arrays.asList(line.split("\\s+"));
                if (toEndOfLine) {
                    if (startIndex < wordsOfLine.size()) {
                        outputLine = String.join(" ", wordsOfLine.subList(startIndex, wordsOfLine.size()));
                    }
                } else if (fromStartOfLine) {
                    outputLine = String.join(" ",
                            wordsOfLine.subList(0, endIndex < wordsOfLine.size() ? endIndex : wordsOfLine.size()));
                } else {
                    if (startIndex < endIndex) {
                        if (startIndex < wordsOfLine.size()) {
                            outputLine = String.join(" ",
                                    wordsOfLine.subList(startIndex,
                                            endIndex < wordsOfLine.size() ? endIndex : wordsOfLine.size()));
                        }
                    }
                }
            }
            outputLines.add(outputLine);
        });
        return outputLines;
    }

}