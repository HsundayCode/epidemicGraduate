package com.hfz.epidemicmanage.Entity;

public class Page {

    private int limit = 5;//限制
    private int current = 1;//当前页码
    private int rows;//行数
    private String path;//

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        if (current > 1)
            this.current = current;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    //当前页得起始行
    public int getoffset() {
        return (current-1) *limit;//为什么-1
    }
    //总页数
    public int getTotal() {
        if((rows%limit) == 0)
        {
            return rows/limit;
        }else {
            return rows/limit+1;
        }
    }
//    //首页
//    public int getform() {
//
//    }
//    //尾页
//    public int getto() {
//    }
}

