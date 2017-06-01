package com.wyjk.admin.common.pagination;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class PageResult<T> implements Serializable {

    private static final long  serialVersionUID = -8652652596351231066L;
    /**
     * 当前页
     */
    public int                 pageNumber       = 1;
    /**
     * 总记录数
     */
    public int                 total;
    /**
     * 总页数
     */
    public int                 totalPage;
    /**
     * 每页的记录条数
     */
    public int                 pageSize         = 10;
    /**
     * 显示的统计标题
     */
    public String              pageTitle        = "";
    /**
     * 当前页的记录
     */
    public List<T>             list;
    /**
     * 搜索条件
     */
    public Map<String, Object> conditions;

    public PageResult(int pageSize) {
        this.pageNumber = 1;
        this.pageSize = pageSize;
    }

    public PageResult() {
        this(10);
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber <= 0 ? 1 : pageNumber;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize <= 0 ? 10 : pageSize;
    }

    public int getFirst() {
        return 1;
    }

    public int getLast() {
        return getTotalPage();
    }

    public int getTotalPage() {
        totalPage = total / pageSize + (total % pageSize != 0 ? 1 : 0);
        return totalPage;
    }

    public int getFirstResult() {
        return (this.pageNumber - 1) * this.pageSize;
    }

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
    
    
}
