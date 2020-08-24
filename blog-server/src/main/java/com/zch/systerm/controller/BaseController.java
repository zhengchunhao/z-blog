package com.zch.systerm.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zch.common.TablePageModel;
import com.zch.common.TablePageResult;
import com.zch.common.TablePageSuport;
import com.zch.utils.MyResponse;
import org.apache.commons.lang3.time.DateUtils;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;


import javax.servlet.http.HttpServletRequest;
import java.beans.PropertyEditorSupport;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class BaseController {

    /**
     * 将前台传递过来的日期格式的字符串，自动转化为Date类型
     */
    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        simpleDateFormat.setLenient(false);
        CustomDateEditor dateEditor = new CustomDateEditor(simpleDateFormat, true);
        binder.registerCustomEditor(Date.class, dateEditor);
    }

    ;

    /**
     * 统一返回
     */
    protected MyResponse result(int liens) {
        return liens > 0 ? MyResponse.success() : MyResponse.error();
    }

    /**
     * 请求分页方法
     */

    protected void pageIng() {
        TablePageModel tablePageModel = TablePageSuport.getTablePageModel();
        Integer pageNum = tablePageModel.getPageNum();
        Integer pageSize = tablePageModel.getPageSize();
        if (pageNum != null && pageSize != null) {
            PageHelper.startPage(pageNum, pageSize);
        }
    }


    /**
     * 返回分页后的对象
     */
    protected TablePageResult tablePageResult(List<?> list) {
        TablePageResult tablePageResult = new TablePageResult();
        tablePageResult.setCode(200);
        tablePageResult.setMsg("查询成功");
        tablePageResult.setTotal(new PageInfo<>(list).getTotal());
        tablePageResult.setRows(list);
        return tablePageResult;
    }


}
