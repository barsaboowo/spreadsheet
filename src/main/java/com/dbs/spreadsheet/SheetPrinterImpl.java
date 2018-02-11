package com.dbs.spreadsheet;

import com.dbs.spreadsheet.model.Sheet;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class SheetPrinterImpl implements SheetPrinter {
    @Override
    public List<String> print(Sheet sheet) {
        return sheet.getRowLists().parallelStream().map(row -> {
            return row.parallelStream().map(c -> String.format("%.5f", c.getResult())).reduce((s1, s2) -> s1 + "," + s2)
                    .orElse("");
        }).collect(Collectors.toList());
    }
}
