package cc.tucci.admin.domain.core.dto;

/**
 * @author tucci
 */
public class PageQuery extends Query {

    static final int DEFAULT_PAGE_NO = 1;
    static final int DEFAULT_PAGE_SIZE = 10;

    private int pageNo = DEFAULT_PAGE_NO;

    private int pageSize = DEFAULT_PAGE_SIZE;

    public int getPageNo() {
        return Math.max(pageNo, DEFAULT_PAGE_NO);
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        if (pageSize < 1 || pageSize > 200) {
            return DEFAULT_PAGE_SIZE;
        }
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getStartPageNo() {
        int pageNo = getPageNo();
        return (pageNo - 1) * getPageSize();
    }
}
