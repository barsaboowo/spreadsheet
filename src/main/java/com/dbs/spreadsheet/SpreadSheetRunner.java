package com.dbs.spreadsheet;

import com.dbs.spreadsheet.model.Sheet;
import com.dbs.spreadsheet.parser.CellParserImpl;
import com.dbs.spreadsheet.parser.SheetParser;
import com.dbs.spreadsheet.parser.SheetParserImpl;
import com.google.common.collect.ImmutableList;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.lang.System.out;

@Slf4j
@AllArgsConstructor
public class SpreadSheetRunner {
    public static final List<String> ALPHABET = ImmutableList.<String>builder()
            .add("A").add("B").add("C").add("D").add("E").add("F").add("G").add("H").add("I").add("J").add("K")
            .add("L").add("M").add("N").add("O").add("P").add("Q").add("R").add("S").add("T").add("U").add("V")
            .add("W").add("X").add("Y").add("Z").build();

    public static final Pattern CELL_NAME_PATTERN = Pattern.compile("[A-Z]{1}[0-9]+");

    private final Sheet sheet;
    private final SheetParser sheetParser;
    private final SheetPrinter sheetPrinter;

    public static void main(String[] args) {
        testArgs(args);
        final String fileName = args[1];
        final String outFile = args[3];

        final List<String> lines = getLines(fileName);

        final Sheet sheet = new SheetFactoryImpl(ALPHABET).newInstance(lines);
        final CellParserImpl cellParser = new CellParserImpl(sheet.getCellMap(), CELL_NAME_PATTERN);
        final SheetParserImpl sheetParser = new SheetParserImpl(cellParser);
        final SheetPrinter sheetPrinter = new SheetPrinterImpl();

        final SpreadSheetRunner spreadSheetRunner = new SpreadSheetRunner(sheet, sheetParser, sheetPrinter);
        spreadSheetRunner.init();

        writeLines(outFile, spreadSheetRunner.print());
        spreadSheetRunner.print().forEach(out::println);

    }

    public List<String> print() {
        return sheetPrinter.print(sheet);
    }

    public void init() {
        sheetParser.parse(sheet);
    }

    private static void testArgs(String[] args) {
        if (4 != args.length) {
            throw new IllegalArgumentException("Usage: java -jar spreadsheet.jar -i inputfile.csv -o output.csv");
        }
    }

    private static void writeLines(String fileName, List<String> lines){
        Path path = Paths.get(fileName);
        try {
            Files.write(path, lines);
        } catch (Exception e) {
            throw new RuntimeException("Unable to write file " + fileName, e);
        }
    }

    private static List<String> getLines(String fileName) {
        try {
            return Files.lines(Paths.get(fileName)).collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Unable to open file " + fileName, e);
        }
    }
}
