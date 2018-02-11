package com.dbs.spreadsheet.parser;

import com.dbs.spreadsheet.SpreadSheetRunner;
import com.dbs.spreadsheet.model.SheetCell;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;

public class CellParserImplTest {

    private final Map<String, SheetCell> cellMap = Maps.newHashMap();
    private final CellParserImpl cellParser = new CellParserImpl(cellMap, SpreadSheetRunner.CELL_NAME_PATTERN);


    @org.junit.Test
    public void evaluate() throws Exception {

        cellMap.put("A0", new SheetCell("=A1+2", "A0"));
        cellMap.put("A1", new SheetCell("1", "A1"));
        Double a0 = cellParser.evaluate(cellMap.get("A0"), Sets.newHashSet());
        assertEquals(Double.valueOf(3), a0);
    }

    @Test
    public void evaluateSameRefTwice() throws Exception{
        cellMap.put("A0", new SheetCell("=A1+A1+2", "A0"));
        cellMap.put("A1", new SheetCell("1", "A1"));
        Double a0 = cellParser.evaluate(cellMap.get("A0"), Sets.newHashSet());
        assertEquals(Double.valueOf(4), a0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void circularReference() throws Exception{
        cellMap.put("A0", new SheetCell("=A1+A1+2", "A0"));
        cellMap.put("A1", new SheetCell("=A0+3", "A1"));
        cellParser.evaluate(cellMap.get("A0"), Sets.newHashSet());
    }

}