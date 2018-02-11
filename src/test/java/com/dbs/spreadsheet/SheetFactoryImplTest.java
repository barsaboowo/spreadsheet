package com.dbs.spreadsheet;

import com.dbs.spreadsheet.model.Sheet;
import com.dbs.test.TestConstants;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static java.util.stream.IntStream.range;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by b on 11/2/18.
 */
public class SheetFactoryImplTest {
    private SheetFactoryImpl sheetFactory;

    @Before
    public void setUp() throws Exception {
        sheetFactory = new SheetFactoryImpl(SpreadSheetRunner.ALPHABET);
    }

    @Test
    public void newInstance() throws Exception {
        Sheet sheet = sheetFactory.newInstance(TestConstants.LINES);
        assertEquals(2, sheet.getRowLists().size());
        sheet.getRowLists().forEach(r -> assertEquals(5, r.size()));
        assertEquals(10, sheet.getCellMap().size());

        ImmutableList.of("A","B").forEach(c ->
                range(0, 4).forEach(i ->
                        assertNotNull(sheet.getCellMap().get(c + i))
                )
        );


    }

    @Test(expected = IllegalArgumentException.class)
    public void newInstanceNoRows() throws Exception {
        sheetFactory.newInstance(Collections.emptyList());
    }

    @Test(expected = IllegalArgumentException.class)
    public void newInstanceTooManyRows() throws Exception {
        List<String> twentySevenLines = Lists.newArrayList();
        range(0, 27).forEach(i-> twentySevenLines.add("1,2,3,4,5"));

        sheetFactory.newInstance(twentySevenLines);
    }
}