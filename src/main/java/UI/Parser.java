package UI;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

class Parser {
    Parser(String[] args) {
        this.cut = cut;
        String lastArg = args[args.length - 1];
        if (lastArg.matches("-\\d+")) {
            args[args.length - 1] = "0" + lastArg;
        }
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);
        } catch (CmdLineException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Argument()
    private String cut;

    @org.kohsuke.args4j.Option(name = "-c")
    private boolean chars = false;

    @Option(name = "-w")
    private boolean words = false;

    @Option(name = "-o")
    private String outputFileName = "";

    @Argument(index = 1)
    private String inputFileName = "";

    @Argument(index = 2, required = true)
    private String range = "";

    String getInputFileName() {
        return inputFileName;
    }

    String getOutputFileName() {
        return outputFileName;
    }

    private String[] getRange() {
        if (range.isEmpty() || !range.contains("-")) throw new IllegalArgumentException("Invalid range");
        return range.split("-");
    }

    int getStartIndex() {
        String[] rangeParts = getRange();
        return rangeParts[0].isEmpty() ? -1 : Integer.parseInt(rangeParts[0]);
    }

    int getEndIndex() {
        String[] rangeParts = getRange();
        if (rangeParts.length == 1) return -1;
        return rangeParts[1].isEmpty() ? -1 : Integer.parseInt(rangeParts[1]);
    }

    boolean cutOfWords() {
        if (chars && words) throw new IllegalArgumentException("Need single flag");
        return words;
    }

}