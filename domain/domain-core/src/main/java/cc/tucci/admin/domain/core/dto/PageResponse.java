package cc.tucci.admin.domain.core.dto;

import java.util.Collection;

/**
 * @author tucci
 */
public class PageResponse<T> extends Response {

    private static final long serialVersionUID = 1L;

    private PageData<T> data;

    protected PageResponse() {
    }

    public static <T> PageResponse<T> success(Collection<T> list, int total) {
        PageData<T> data = new PageData<>();
        data.setList(list);
        data.setTotal(total);
        PageResponse<T> response = new PageResponse<>();
        response.setStatus(Boolean.TRUE);
        response.setData(data);
        return response;
    }


    public static class PageData<T> {

        private Collection<T> list;

        private int total;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public Collection<T> getList() {
            return list;
        }

        public void setList(Collection<T> list) {
            this.list = list;
        }
    }

    public PageData<T> getData() {
        return data;
    }

    public void setData(PageData<T> data) {
        this.data = data;
    }
}
