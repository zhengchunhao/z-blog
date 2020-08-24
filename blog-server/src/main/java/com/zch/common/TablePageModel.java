package com.zch.common;

public class TablePageModel {


    private  Integer pageNum;

    private  Integer pageSize;

    private  String orderBy;

    private  String oderColumn;



    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getOderColumn() {
        return oderColumn;
    }

    public void setOderColumn(String oderColumn) {
        this.oderColumn = oderColumn;
    }
}
