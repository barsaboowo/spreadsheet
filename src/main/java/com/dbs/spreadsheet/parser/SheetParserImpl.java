package com.dbs.spreadsheet.parser;

import com.dbs.spreadsheet.model.Sheet;
import com.dbs.spreadsheet.model.SheetCell;
import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
@AllArgsConstructor
public class SheetParserImpl implements SheetParser {

    private final CellParser cellParser;

    @Override
    public void parse(Sheet sheet) {
        Map<String, SheetCell> cellMap = sheet.getCellMap();
        cellMap.values().parallelStream().forEach(c -> c.setResult(cellParser.evaluate(c, Sets.newHashSet())));

    }
}
