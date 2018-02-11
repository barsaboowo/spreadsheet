package com.dbs.spreadsheet;

import com.dbs.spreadsheet.model.Sheet;

import java.util.List;

@FunctionalInterface
public interface SheetPrinter {
    List<String> print(Sheet sheet);
}
