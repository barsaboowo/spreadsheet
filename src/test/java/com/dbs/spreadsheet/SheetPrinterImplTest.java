package com.dbs.spreadsheet;

import com.dbs.spreadsheet.model.Sheet;
import com.dbs.spreadsheet.model.SheetCell;
import com.dbs.test.TestConstants;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by b on 11/2/18.
 */
public class SheetPrinterImplTest {
    private final SheetPrinter sheetPrinter = new SheetPrinterImpl();
    private Sheet sheet;
    
    @Before
    public void setUp() throws Exception {
        List<SheetCell> cellMapA = Lists.newLinkedList();
        List<SheetCell> cellMapB = Lists.newLinkedList();
        
        cellMapA.add(new SheetCell("1", "A0"));
        cellMapA.add(new SheetCell("2", "A1"));
        cellMapA.add(new SheetCell("3", "A2"));
        cellMapA.add(new SheetCell("4", "A3"));
        cellMapA.add(new SheetCell("5", "A4"));
        
        cellMapB.add(new SheetCell("1", "B0"));
        cellMapB.add(new SheetCell("2", "B1"));
        cellMapB.add(new SheetCell("3", "B2"));
        cellMapB.add(new SheetCell("4", "B3"));
        cellMapB.add(new SheetCell("5", "B4"));

        cellMapA.forEach(s->{
            s.setResult(Double.valueOf(s.getInput()));
        });
        cellMapB.forEach(s->{
            s.setResult(Double.valueOf(s.getInput()));
        });

        sheet = new Sheet(null, ImmutableList.of(cellMapA, cellMapB));
    }

    @Test
    public void print() throws Exception {
        assertEquals(TestConstants.LINES, sheetPrinter.print(sheet));
    }

}