package com.xie.tsa.entity;

public class UserLocationRecord {
    /**
     * 系统维护一个大的hashmap
     */
    private String telPhone;
    private String userLongitude;//用户经度
    private String userLatitude;//用户纬度

    private int state;

    /**
     * 不提供经度纬度的改变方便
     * @return
     */
    public String getUserLatitude() {
        return userLatitude;
    }

    public String getUserLongitude() {
        return userLongitude;
    }

    public void updateLocation(String userLongitude,String userLatitude){
        if(userLongitude == null | userLatitude == null){
            return;
        }
        this.userLongitude = userLongitude;
        this.userLatitude = userLatitude;
    }
    public UserLocationRecord(String telPhone,String userLongitude,String userLatitude){
        this.telPhone = telPhone;
        this.userLongitude = userLongitude;
        this.userLatitude = userLatitude;
    }

    public String getTelPhone(){
        return telPhone;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "UserLocationRecord{" +
                "telPhone='" + telPhone + '\'' +
                ", userLongitude='" + userLongitude + '\'' +
                ", userLatitude='" + userLatitude + '\'' +
                ", state=" + state +
                '}';
    }
}
