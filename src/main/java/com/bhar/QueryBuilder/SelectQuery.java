package com.bhar.QueryBuilder;


import com.bhar.QueryBuilder.columnexpr.Column;
import com.bhar.QueryBuilder.columnexpr.ColumnExpression;
import com.bhar.QueryBuilder.tableexpr.TableExpression;
import com.bhar.QueryBuilder.whereCriteria.WhereCriteria;

import java.util.List;

/**
 * Created by bharghav on 3/11/2016.
 */
public class SelectQuery extends Query {

    ColumnExpression columnExpression;
    TableExpression tableExpression;

    WhereCriteria whereWhereCriteria;
    GroupBy groupBy;
    OrderBy orderBy;
    Limit limitCondition;

    public static class Builder {
        private SelectQuery selectQuery;

        public Builder(ColumnExpression columnExpression, TableExpression tableExpression) {
            selectQuery.columnExpression = columnExpression;
            selectQuery.tableExpression = tableExpression;
        }

        public Builder where(WhereCriteria whereWhereCriteria) {
            selectQuery.whereWhereCriteria = whereWhereCriteria;
            return this;
        }

        public Builder groupBy(List<Column> columnList) {
            selectQuery.groupBy = new GroupBy(columnList);
            return this;
        }

        public Builder orderBy(List<Column> columnList) {
            selectQuery.orderBy = new OrderBy(columnList);
            return this;
        }

        public Builder limit (int limit) {
            selectQuery.limitCondition = new Limit(limit);
            return this;
        }

        public SelectQuery build() {
            return selectQuery;
        }
    }

}
