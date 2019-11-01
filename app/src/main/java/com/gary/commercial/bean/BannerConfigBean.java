package com.gary.commercial.bean;

/**
 * online json editor:http://www.bejson.com/jsoneditoronline/
 */
public class BannerConfigBean {
    private String versionCode;
    private String updateDate;
    private String zipUrl;

    public BannerConfigBean(String versionCode, String updateDate, String zipUrl) {
        this.versionCode = versionCode;
        this.updateDate = updateDate;
        this.zipUrl = zipUrl;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public String getZipUrl() {
        return zipUrl;
    }
}
