package com.bhar.QueryBuilder;

import com.bhar.QueryBuilder.columnexpr.Column;
import com.bhar.QueryBuilder.columnexpr.ColumnExpression;

import java.util.List;

/**
 * Created by bharghav on 3/11/2016.
 */
public class OrderBy {

    ColumnExpression columnExpression;

    public OrderBy(List<Column> columnList) {
        this.columnExpression = new ColumnExpression(columnList);
    }
}
