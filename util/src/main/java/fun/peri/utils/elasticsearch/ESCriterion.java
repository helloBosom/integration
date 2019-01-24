package fun.peri.utils.elasticsearch;

import org.elasticsearch.index.query.QueryBuilder;

import java.util.List;

public interface ESCriterion {

    /**
     * TERM 等于
     * TERMS 一次匹配多个值
     * RANGE from X to X
     * FUZZY 模糊查询文档
     * QUERY_STRING 可传入字符串支持lucence语法
     * GT 大于
     * LT 小于
     */
    public enum Operator {
        TERM, TERMS, RANGE, FUZZY, QUERY_STRING, MISSING, GT, LT
    }

    public enum MatchMode {
        START, END, ANYWHERE
    }

    public enum Projection {
        MAX, MIN, AVG, LENGTH, SUM, COUNT
    }

    public List<QueryBuilder> listBuilders();

}
