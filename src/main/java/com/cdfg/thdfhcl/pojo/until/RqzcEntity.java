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

    public List<SCDetailEntity> getAllstore() {
        return allstore;
    }

    public void setAllstore(List<SCDetailEntity> allstore) {
        this.allstore = allstore;
    }

    public List<SCDetailEntity> getAllplaceofdelivery() {
        return allplaceofdelivery;
    }

    public void setAllplaceofdelivery(List<SCDetailEntity> allplaceofdelivery) {
        this.allplaceofdelivery = allplaceofdelivery;
    }

    public List<SCDetailEntity> getJcstoragelocation() {
        return jcstoragelocation;
    }

    public void setJcstoragelocation(List<SCDetailEntity> jcstoragelocation) {
        this.jcstoragelocation = jcstoragelocation;
    }

    private ZcsdEntity temporarystoragedate;
    private ThdEntity placeofdelivery;
    private List<SCDetailEntity> cargostatus;
    private List<SCDetailEntity> allstore;
    private List<SCDetailEntity> allplaceofdelivery;
    private List<SCDetailEntity> jcstoragelocation;

}
