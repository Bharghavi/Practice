package com.bhar.QueryBuilder.tableexpr;

import com.bhar.QueryBuilder.tableexpr.Table;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bharghav on 3/11/2016.
 */
public class TableExpression {

    List<Table> tables;

    public TableExpression(List<Table> tables) {
        this.tables = new ArrayList<Table>();
        this.tables.addAll(tables);
    }
}
