package com.cdfg.thdfhcl.pojo.until;

import java.util.List;

public class RqzcEntity {

    public ZcsdEntity getTemporarystoragedate() {
        return temporarystoragedate;
    }

    public void setTemporarystoragedate(ZcsdEntity temporarystoragedate) {
        this.temporarystoragedate = temporarystoragedate;
    }

    public ThdEntity getPlaceofdelivery() {
        return placeofdelivery;
    }

    public void setPlaceofdelivery(ThdEntity placeofdelivery) {
        this.placeofdelivery = placeofdelivery;
    }

    public List<SCDetailEntity> getCargostatus() {
        return cargostatus;
    }

    public void setCargostatus(List<SCDetailEntity> cargostatus) {
        this.cargostatus = cargostatus;
    }

    public List<SCDetailEntity> getStore() {
        return store;
    }

    public void setStore(List<SCDetailEntity> store) {
        this.store = store;
    }

    private ZcsdEntity temporarystoragedate;
    private ThdEntity placeofdelivery;
    private List<SCDetailEntity> cargostatus;
    private List<SCDetailEntity> store;
}
