package com.nandi.securityschedulingdo.bean;

import java.util.List;

/**
 * Created by qingsong on 2018/1/9.
 */

public class LocationPoint {

    private MetaBean meta;
    private List<DataBean> data;

    public MetaBean getMeta() {
        return meta;
    }

    public void setMeta(MetaBean meta) {
        this.meta = meta;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class MetaBean {
        /**
         * success : true
         * message : ok
         */

        private boolean success;
        private String message;

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    public static class DataBean {
        /**
         * id : 1
         * disNo : 5002322010050101
         * disName : 双桂湾滑坡
         * disType : null
         * disState : 1
         * disLocation : 重庆市武隆县和顺乡青木池村八一社
         * disLon : 107.41833333
         * disLat : 29.38333333
         * disX : 3251614
         * disY : 36443058
         * disCause : null
         * disTime : 2001-09-03 00:00:00
         * disStratum : 0
         * disSurfaceType : 28
         * disSlope : 25
         * disArea : 2.400000
         * disVolume : 12.000000
         * disBefore : 293.000000
         * disAfter : 174.000000
         * imperilFamilies : 20
         * imperilMan : 50
         * imperilHouse : 0
         * imperilArea : 0
         * mainObject : 居民
         * imperilMoney : 210.000000
         * stableLevel : 43
         * imperilLevel : 19
         * dealIdea : null
         * defenseLevel : 37
         * areaId : 530
         * qcqfryTel :
         * zsryTel : 18584579966
         * warnMobile :
         * hasMobile : 1
         * disRadius : 2
         * village : null
         * bulu : 1
         * groupNo : null
         * scale : 18
         * stateTime : 0
         * comeTime : 2013-09-01
         * operation : 1
         * statusNo : 0
         * videoName :
         * dealType : 1
         * dealStatus : 1
         * remark : null
         */

        private int id;
        private String disNo;
        private String disName;
        private Object disType;
        private int disState;
        private String disLocation;
        private double disLon;
        private double disLat;
        private String disX;
        private String disY;
        private String disCause;
        private String disTime;
        private String disStratum;
        private String disSurfaceType;
        private String disSlope;
        private String disArea;
        private String disVolume;
        private String disBefore;
        private String disAfter;
        private int imperilFamilies;
        private int imperilMan;
        private int imperilHouse;
        private String imperilArea;
        private String mainObject;
        private String imperilMoney;
        private int stableLevel;
        private int imperilLevel;
        private String dealIdea;
        private int defenseLevel;
        private int areaId;
        private String qcqfryTel;
        private String zsryTel;
        private String warnMobile;
        private int hasMobile;
        private int disRadius;
        private String village;
        private int bulu;
        private String groupNo;
        private int scale;
        private String stateTime;
        private String comeTime;
        private int operation;
        private int statusNo;
        private String videoName;
        private int dealType;
        private int dealStatus;
        private Object remark;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDisNo() {
            return disNo;
        }

        public void setDisNo(String disNo) {
            this.disNo = disNo;
        }

        public String getDisName() {
            return disName;
        }

        public void setDisName(String disName) {
            this.disName = disName;
        }

        public Object getDisType() {
            return disType;
        }

        public void setDisType(Object disType) {
            this.disType = disType;
        }

        public int getDisState() {
            return disState;
        }

        public void setDisState(int disState) {
            this.disState = disState;
        }

        public String getDisLocation() {
            return disLocation;
        }

        public void setDisLocation(String disLocation) {
            this.disLocation = disLocation;
        }

        public double getDisLon() {
            return disLon;
        }

        public void setDisLon(double disLon) {
            this.disLon = disLon;
        }

        public double getDisLat() {
            return disLat;
        }

        public void setDisLat(double disLat) {
            this.disLat = disLat;
        }

        public String getDisX() {
            return disX;
        }

        public void setDisX(String disX) {
            this.disX = disX;
        }

        public String getDisY() {
            return disY;
        }

        public void setDisY(String disY) {
            this.disY = disY;
        }

        public String getDisCause() {
            return disCause;
        }

        public void setDisCause(String disCause) {
            this.disCause = disCause;
        }

        public String getDisTime() {
            return disTime;
        }

        public void setDisTime(String disTime) {
            this.disTime = disTime;
        }

        public String getDisStratum() {
            return disStratum;
        }

        public void setDisStratum(String disStratum) {
            this.disStratum = disStratum;
        }

        public String getDisSurfaceType() {
            return disSurfaceType;
        }

        public void setDisSurfaceType(String disSurfaceType) {
            this.disSurfaceType = disSurfaceType;
        }

        public String getDisSlope() {
            return disSlope;
        }

        public void setDisSlope(String disSlope) {
            this.disSlope = disSlope;
        }

        public String getDisArea() {
            return disArea;
        }

        public void setDisArea(String disArea) {
            this.disArea = disArea;
        }

        public String getDisVolume() {
            return disVolume;
        }

        public void setDisVolume(String disVolume) {
            this.disVolume = disVolume;
        }

        public String getDisBefore() {
            return disBefore;
        }

        public void setDisBefore(String disBefore) {
            this.disBefore = disBefore;
        }

        public String getDisAfter() {
            return disAfter;
        }

        public void setDisAfter(String disAfter) {
            this.disAfter = disAfter;
        }

        public int getImperilFamilies() {
            return imperilFamilies;
        }

        public void setImperilFamilies(int imperilFamilies) {
            this.imperilFamilies = imperilFamilies;
        }

        public int getImperilMan() {
            return imperilMan;
        }

        public void setImperilMan(int imperilMan) {
            this.imperilMan = imperilMan;
        }

        public int getImperilHouse() {
            return imperilHouse;
        }

        public void setImperilHouse(int imperilHouse) {
            this.imperilHouse = imperilHouse;
        }

        public String getImperilArea() {
            return imperilArea;
        }

        public void setImperilArea(String imperilArea) {
            this.imperilArea = imperilArea;
        }

        public String getMainObject() {
            return mainObject;
        }

        public void setMainObject(String mainObject) {
            this.mainObject = mainObject;
        }

        public String getImperilMoney() {
            return imperilMoney;
        }

        public void setImperilMoney(String imperilMoney) {
            this.imperilMoney = imperilMoney;
        }

        public int getStableLevel() {
            return stableLevel;
        }

        public void setStableLevel(int stableLevel) {
            this.stableLevel = stableLevel;
        }

        public int getImperilLevel() {
            return imperilLevel;
        }

        public void setImperilLevel(int imperilLevel) {
            this.imperilLevel = imperilLevel;
        }

        public String getDealIdea() {
            return dealIdea;
        }

        public void setDealIdea(String dealIdea) {
            this.dealIdea = dealIdea;
        }

        public int getDefenseLevel() {
            return defenseLevel;
        }

        public void setDefenseLevel(int defenseLevel) {
            this.defenseLevel = defenseLevel;
        }

        public int getAreaId() {
            return areaId;
        }

        public void setAreaId(int areaId) {
            this.areaId = areaId;
        }

        public String getQcqfryTel() {
            return qcqfryTel;
        }

        public void setQcqfryTel(String qcqfryTel) {
            this.qcqfryTel = qcqfryTel;
        }

        public String getZsryTel() {
            return zsryTel;
        }

        public void setZsryTel(String zsryTel) {
            this.zsryTel = zsryTel;
        }

        public String getWarnMobile() {
            return warnMobile;
        }

        public void setWarnMobile(String warnMobile) {
            this.warnMobile = warnMobile;
        }

        public int getHasMobile() {
            return hasMobile;
        }

        public void setHasMobile(int hasMobile) {
            this.hasMobile = hasMobile;
        }

        public int getDisRadius() {
            return disRadius;
        }

        public void setDisRadius(int disRadius) {
            this.disRadius = disRadius;
        }

        public String getVillage() {
            return village;
        }

        public void setVillage(String village) {
            this.village = village;
        }

        public int getBulu() {
            return bulu;
        }

        public void setBulu(int bulu) {
            this.bulu = bulu;
        }

        public String getGroupNo() {
            return groupNo;
        }

        public void setGroupNo(String groupNo) {
            this.groupNo = groupNo;
        }

        public int getScale() {
            return scale;
        }

        public void setScale(int scale) {
            this.scale = scale;
        }

        public String getStateTime() {
            return stateTime;
        }

        public void setStateTime(String stateTime) {
            this.stateTime = stateTime;
        }

        public String getComeTime() {
            return comeTime;
        }

        public void setComeTime(String comeTime) {
            this.comeTime = comeTime;
        }

        public int getOperation() {
            return operation;
        }

        public void setOperation(int operation) {
            this.operation = operation;
        }

        public int getStatusNo() {
            return statusNo;
        }

        public void setStatusNo(int statusNo) {
            this.statusNo = statusNo;
        }

        public String getVideoName() {
            return videoName;
        }

        public void setVideoName(String videoName) {
            this.videoName = videoName;
        }

        public int getDealType() {
            return dealType;
        }

        public void setDealType(int dealType) {
            this.dealType = dealType;
        }

        public int getDealStatus() {
            return dealStatus;
        }

        public void setDealStatus(int dealStatus) {
            this.dealStatus = dealStatus;
        }

        public Object getRemark() {
            return remark;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
        }
    }
}
