package com.dbs.spreadsheet.parser;

import com.dbs.spreadsheet.model.SheetCell;

import java.util.Set;

@FunctionalInterface
public interface CellParser {
    Double evaluate(SheetCell cell, Set<String> history);
}
