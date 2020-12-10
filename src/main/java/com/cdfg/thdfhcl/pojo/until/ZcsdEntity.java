package com.cdfg.thdfhcl.pojo.until;

import java.util.List;

/**
 * 暂存时段
 */
public class ZcsdEntity {
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

    public List<SCDetailEntity> getOthertimeperiod() {
        return othertimeperiod;
    }

    public void setOthertimeperiod(List<SCDetailEntity> othertimeperiod) {
        this.othertimeperiod = othertimeperiod;
    }

    private List<SCDetailEntity> sanya;
    private List<SCDetailEntity> haikou;
    private List<SCDetailEntity> othertimeperiod;
}
