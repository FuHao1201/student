package com.project.student.common;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * <p>
 *  分页参数类
 * </p>
 *
 * @author YL
 * @since 2021-02-27
 */
public class PageModel<T> extends Page<T> {

    public long page;

    public long limit;

    public long getPage() {
        return page;
    }

    public void setPage(long page) {
        this.page = page;
        super.setCurrent(page);
    }

    public long getLimit() {
        return limit;
    }

    public void setLimit(long limit) {
        this.limit = limit;
        super.setSize(limit);
    }
}