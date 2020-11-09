package cn.lzl.common.api;

public class BrandParam {
    private  Integer pageStart;//起始查询条数
    private  Integer pageSize;//每页显示多少条
    private  String name;//条件查询

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPageStart() {
        return pageStart;
    }

    public void setPageStart(Integer pageStart) {
        this.pageStart = pageStart;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
    public BrandParam(Integer pageStart, Integer pageSize, String name) {
        this.pageStart = pageStart;
        this.pageSize = pageSize;
        this.name = name;
    }

    public BrandParam() {
    }
}
