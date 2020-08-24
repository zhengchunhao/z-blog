package com.zch.common;

import com.zch.utils.ServletUtils;

public class TablePageSuport {

    public static final String PAGE_NUM="pageNum";

    public static final  String  PAGE_SIZE="pageSize";

    /**
     * 构建分页对象
     * */
    public static   TablePageModel getTablePageModel(){
       TablePageModel tablePageModel=new TablePageModel();
       tablePageModel.setPageNum(ServletUtils.getParameterToInt(PAGE_NUM));
       tablePageModel.setPageSize(ServletUtils.getParameterToInt(PAGE_SIZE));
      return  tablePageModel;
    }

}
