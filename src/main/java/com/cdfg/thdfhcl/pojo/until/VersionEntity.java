package com.cdfg.thdfhcl.pojo.until;

public class VersionEntity {

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getBuild() {
        return build;
    }

    public void setBuild(String build) {
        this.build = build;
    }

    public String getAppname() {
        return appname;
    }

    public void setAppname(String appname) {
        this.appname = appname;
    }

    public String getDownloadpath() {
        return downloadpath;
    }

    public void setDownloadpath(String downloadpath) {
        this.downloadpath = downloadpath;
    }

    public String getReleasedatetime() {
        return releasedatetime;
    }

    public void setReleasedatetime(String releasedatetime) {
        this.releasedatetime = releasedatetime;
    }

    public String getUpdatecontent() {
        return updatecontent;
    }

    public void setUpdatecontent(String updatecontent) {
        this.updatecontent = updatecontent;
    }

    public String getNeed() {
        return need;
    }

    public void setNeed(String need) {
        this.need = need;
    }

    private String version;
    private String build;
    private String appname;
    private String downloadpath;
    private String releasedatetime;
    private String updatecontent;
    private String need;
}
