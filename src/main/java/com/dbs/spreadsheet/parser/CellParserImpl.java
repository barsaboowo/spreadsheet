package com.dbs.spreadsheet.parser;

import com.dbs.spreadsheet.model.SheetCell;
import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mariuszgromada.math.mxparser.Expression;

import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Slf4j
@AllArgsConstructor
public class CellParserImpl implements CellParser {

    private final Map<String, SheetCell> cellMap;
    private final Pattern pattern;


    @Override
    public Double evaluate(SheetCell cell, Set<String> history) {
        try {
            if (cell.getResult() == null) {

                if (cell.getInput().startsWith("=")) {
                    //Must be a formula
                    String expansion = expandValue(cell.getInput(), history);
                    Expression expression = new Expression(expansion);
                    cell.setResult(expression.calculate());
                } else {
                    //Assuming it's an integer
                    cell.setResult(Double.valueOf(cell.getInput()));
                }
            }
            return cell.getResult();

        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid cell value " + cell.getInput(), e);
        }
    }

    private String expandValue(String input, Set<String> history) {
        Matcher matcher = pattern.matcher(input);
        String finalValue = input;
        Map<String, Double> evals = Maps.newConcurrentMap();
        while (matcher.find()) {
            String group = matcher.group();
            evals.computeIfAbsent(group, s -> {
                if(history.contains(group) && cellMap.get(group).getResult() == null){
                    throw new IllegalArgumentException("Circular reference found");
                }
                history.add(group);
                return evaluate(cellMap.get(group), history);
            });
        }

        for(Map.Entry<String, Double> entry : evals.entrySet()){
            finalValue = finalValue.replaceAll(entry.getKey(), entry.getValue().toString());
        }

        //Remove the "="
        return finalValue.substring(1);
    }


}
