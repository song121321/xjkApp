package song.song121321.bean;

import java.io.Serializable;
import java.util.List;

public class WellBean implements Serializable {

    private String wellCode;
    private String wellName;
    // 0 power off
    // 1 running
    // -1 mistake
    private int state;
    private String location;
    private String updateTime;
    private int pointLan;
    private int pointLon;

    private List<WellAttrBean> attr;


    public int getPointLan() {
        return pointLan;
    }

    public void setPointLan(int pointLan) {
        this.pointLan = pointLan;
    }

    public int getPointLon() {
        return pointLon;
    }

    public void setPointLon(int pointLon) {
        this.pointLon = pointLon;
    }

    public String getWellCode() {
        return wellCode;
    }

    public void setWellCode(String wellCode) {
        this.wellCode = wellCode;
    }

    public String getWellName() {
        return wellName;
    }

    public void setWellName(String wellName) {
        this.wellName = wellName;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<WellAttrBean> getAttr() {
        return attr;
    }

    public void setAttr(List<WellAttrBean> attr) {
        this.attr = attr;
    }
}
