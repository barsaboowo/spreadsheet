package com.dbs.spreadsheet.parser;

import com.dbs.spreadsheet.SheetFactoryImpl;
import com.dbs.spreadsheet.SheetPrinterImpl;
import com.dbs.spreadsheet.SpreadSheetRunner;
import com.dbs.spreadsheet.model.Sheet;
import com.dbs.test.TestConstants;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by b on 11/2/18.
 */
public class SheetParserImplTest {

    private SheetFactoryImpl sheetFactory;
    private Sheet sheet;
    private SheetParserImpl sheetParser;

    @Before
    public void setUp() throws Exception {
        sheetFactory = new SheetFactoryImpl(SpreadSheetRunner.ALPHABET);
        sheet = sheetFactory.newInstance(TestConstants.SAMPLE_LINES);
        sheetParser = new SheetParserImpl(new CellParserImpl(sheet.getCellMap(), SpreadSheetRunner.CELL_NAME_PATTERN));

    }

    @Test
    public void parse() throws Exception {
        sheetParser.parse(sheet);
        List<String> print = new SheetPrinterImpl().print(sheet);
        assertEquals(TestConstants.SAMPLE_RESULT, print);
    }

}