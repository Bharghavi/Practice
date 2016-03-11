package com.bhar.QueryBuilder.columnexpr;

import com.bhar.QueryBuilder.columnexpr.Column;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bharghav on 3/11/2016.
 */
public class ColumnExpression {

    private boolean all_Columns;
    private List<Column> columns;

    public ColumnExpression() {
        all_Columns = true;
    }

    public ColumnExpression(List<Column> columns) {
        all_Columns = false;
        this.columns = new ArrayList<Column>();
        this.columns.addAll(columns);
    }
}
