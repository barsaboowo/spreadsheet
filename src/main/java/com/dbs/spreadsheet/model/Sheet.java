package com.dbs.spreadsheet.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

@Slf4j
@AllArgsConstructor
@Getter
public class Sheet {

    private final Map<String, SheetCell> cellMap;
    private final List<List<SheetCell>> rowLists;

}
