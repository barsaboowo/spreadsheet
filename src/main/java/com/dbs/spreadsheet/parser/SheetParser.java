package com.dbs.spreadsheet.parser;

import com.dbs.spreadsheet.model.Sheet;

@FunctionalInterface
public interface SheetParser {
    void parse(Sheet sheet);
}
