package ifactory.module.common;

import java.util.Hashtable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.pagehelper.PageInfo;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PageModel<T> {
	
	private int code;
    private String comment;
	
    private Integer pageNum;
    private Integer pageSize;
    private String sortColumn;
    private String sortType;
    
    private PageInfo<T> page;
    
    public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

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

    public String getSortColumn() {
		return sortColumn;
	}

	public void setSortColumn(String sortColumn) {
		this.sortColumn = sortColumn;
	}

	public String getSortType() {
		return sortType;
	}

	public void setSortType(String sortType) {
		this.sortType = sortType;
	}

	public PageInfo<T> getPage() {
        return page;
    }

    public void setPage(PageInfo<T> page) {
        this.page = page;
    }
	
}
