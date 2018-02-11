package com.dbs.spreadsheet;

import com.dbs.spreadsheet.model.Sheet;
import com.dbs.spreadsheet.parser.CellParserImpl;
import com.dbs.spreadsheet.parser.SheetParserImpl;
import com.dbs.test.TestConstants;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class SpreadSheetRunnerTest {
    private SheetFactoryImpl sheetFactory;
    private Sheet sheet;
    private SheetParserImpl sheetParser;
    private SpreadSheetRunner spreadSheetRunner;

    @Before
    public void setUp() throws Exception {
        sheetFactory = new SheetFactoryImpl(SpreadSheetRunner.ALPHABET);
        sheet = sheetFactory.newInstance(TestConstants.SAMPLE_LINES);
        sheetParser = new SheetParserImpl(new CellParserImpl(sheet.getCellMap(), SpreadSheetRunner.CELL_NAME_PATTERN));
        spreadSheetRunner = new SpreadSheetRunner(sheet, sheetParser, new SheetPrinterImpl());
    }

    @Test
    public void print() throws Exception {
        spreadSheetRunner.init();
        List<String> print = spreadSheetRunner.print();
        assertEquals(TestConstants.SAMPLE_RESULT, print);
    }

}