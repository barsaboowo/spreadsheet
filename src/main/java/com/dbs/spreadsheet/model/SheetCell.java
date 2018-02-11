package com.dbs.spreadsheet.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode

@Getter
@Setter
public class SheetCell {
    private final String input;
    private final String name;

    private volatile Double result;

    public SheetCell(String input, String name) {
        this.input = input;
        this.name = name;
    }
}
