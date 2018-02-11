package com.dbs.spreadsheet;

import com.dbs.spreadsheet.model.Sheet;

import java.util.List;

/**
 * Created by b on 11/2/18.
 */
public interface SheetFactory {
    Sheet newInstance(List<String> lines);
}
