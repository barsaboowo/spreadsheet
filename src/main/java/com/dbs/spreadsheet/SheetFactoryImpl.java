package com.dbs.spreadsheet;

import com.dbs.spreadsheet.model.Sheet;
import com.dbs.spreadsheet.model.SheetCell;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

import static java.util.stream.IntStream.range;

public class SheetFactoryImpl implements SheetFactory {
    private final List<String> alphabet;

    public SheetFactoryImpl(List<String> alphabet) {
        this.alphabet = alphabet;
    }

    @Override
    public Sheet newInstance(List<String> lines) {
        validateInput(lines);
        final Map<String, SheetCell> cellMap = Maps.newHashMap();
        final List<List<SheetCell>> rowLists = Lists.newLinkedList();

        range(0, lines.size()).forEach(i -> {

            final String rowName = alphabet.get(i);
            final List<String> cellStrings = Splitter.on(",").omitEmptyStrings().trimResults().splitToList(lines.get(i));
            final List<SheetCell> rowList = Lists.newLinkedList();

            range(0, cellStrings.size()).forEach(c -> {
                        final String cellName = rowName.concat(String.valueOf(c));
                        SheetCell cell = new SheetCell(cellStrings.get(c).replace(" ", ""), cellName);
                        cellMap.put(cellName, cell);
                        rowList.add(cell);
                    }

            );
            rowLists.add(rowList);

        });

        return new Sheet(cellMap, rowLists);
    }

    private void validateInput(List<String> lines) {
        java.util.Objects.requireNonNull(lines);
        if (lines.size() == 0 || lines.size() > alphabet.size()) {
            throw new IllegalArgumentException("Supported line count is 1 to " + alphabet.size());
        }
    }
}
