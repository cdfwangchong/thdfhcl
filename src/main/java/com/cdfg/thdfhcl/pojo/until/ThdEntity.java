package com.cdfg.thdfhcl.pojo.until;

import java.util.List;

public class ThdEntity {
    private List<SCDetailEntity> sanya;
    private List<SCDetailEntity> haikou;
    private List<SCDetailEntity> otherlocation;

    public List<SCDetailEntity> getSanya() {
        return sanya;
    }

    public void setSanya(List<SCDetailEntity> sanya) {
        this.sanya = sanya;
    }

    public List<SCDetailEntity> getHaikou() {
        return haikou;
    }

    public void setHaikou(List<SCDetailEntity> haikou) {
        this.haikou = haikou;
    }

    public List<SCDetailEntity> getOtherlocation() {
        return otherlocation;
    }

    public void setOtherlocation(List<SCDetailEntity> otherlocation) {
        this.otherlocation = otherlocation;
    }
}
