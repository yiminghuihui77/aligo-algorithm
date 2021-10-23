package com.huihui.aligo.hello;

import java.util.List;

/**
 * @author minghui.y
 * @create 2021-10-11 5:36 下午
 **/
public class ShopWarehouseBaseVo {
    private Integer provinceId;
    private String provinceName;
    private Integer cityId;
    private String cityName;
    private Integer areaId;
    private String areaName;
    private String whAddress;

//    private String deliveryAreaId;
//    private String deliveryAreaName;
//    private String deliveryAddress;
//    private String deliveryCityId;
//    private String deliveryCityName;
//    private String deliveryProvinceId;
//    private String deliveryProvinceName;

    private List<String> whTag;
    private String whExt;
    private String shopId;
    private String whName;

    private String managerName;
    private String managerPhone;

    @Override
    public String toString() {
        return "ShopWarehouseBaseVo{" +
                "provinceId=" + provinceId +
                ", provinceName='" + provinceName + '\'' +
                ", cityId=" + cityId +
                ", cityName='" + cityName + '\'' +
                ", areaId=" + areaId +
                ", areaName='" + areaName + '\'' +
                ", whAddress='" + whAddress + '\'' +
                ", whTag=" + whTag +
                ", whExt='" + whExt + '\'' +
                ", shopId='" + shopId + '\'' +
                ", whName='" + whName + '\'' +
                ", managerName='" + managerName + '\'' +
                ", managerPhone='" + managerPhone + '\'' +
                '}';
    }

    /**
     * Getter method for property provinceId.
     *
     * @return property value of provinceId
     */
    public Integer getProvinceId() {
        return provinceId;
    }

    /**
     * Setter method for property provinceId.
     *
     * @param provinceId value to be assigned to property provinceId
     */
    public void setProvinceId( Integer provinceId ) {
        this.provinceId = provinceId;
    }

    /**
     * Getter method for property provinceName.
     *
     * @return property value of provinceName
     */
    public String getProvinceName() {
        return provinceName;
    }

    /**
     * Setter method for property provinceName.
     *
     * @param provinceName value to be assigned to property provinceName
     */
    public void setProvinceName( String provinceName ) {
        this.provinceName = provinceName;
    }

    /**
     * Getter method for property cityId.
     *
     * @return property value of cityId
     */
    public Integer getCityId() {
        return cityId;
    }

    /**
     * Setter method for property cityId.
     *
     * @param cityId value to be assigned to property cityId
     */
    public void setCityId( Integer cityId ) {
        this.cityId = cityId;
    }

    /**
     * Getter method for property cityName.
     *
     * @return property value of cityName
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * Setter method for property cityName.
     *
     * @param cityName value to be assigned to property cityName
     */
    public void setCityName( String cityName ) {
        this.cityName = cityName;
    }

    /**
     * Getter method for property areaId.
     *
     * @return property value of areaId
     */
    public Integer getAreaId() {
        return areaId;
    }

    /**
     * Setter method for property areaId.
     *
     * @param areaId value to be assigned to property areaId
     */
    public void setAreaId( Integer areaId ) {
        this.areaId = areaId;
    }

    /**
     * Getter method for property areaName.
     *
     * @return property value of areaName
     */
    public String getAreaName() {
        return areaName;
    }

    /**
     * Setter method for property areaName.
     *
     * @param areaName value to be assigned to property areaName
     */
    public void setAreaName( String areaName ) {
        this.areaName = areaName;
    }

    /**
     * Getter method for property whAddress.
     *
     * @return property value of whAddress
     */
    public String getWhAddress() {
        return whAddress;
    }

    /**
     * Setter method for property whAddress.
     *
     * @param whAddress value to be assigned to property whAddress
     */
    public void setWhAddress( String whAddress ) {
        this.whAddress = whAddress;
    }

    /**
     * Getter method for property whTag.
     *
     * @return property value of whTag
     */
    public List<String> getWhTag() {
        return whTag;
    }

    /**
     * Setter method for property whTag.
     *
     * @param whTag value to be assigned to property whTag
     */
    public void setWhTag( List<String> whTag ) {
        this.whTag = whTag;
    }

    /**
     * Getter method for property whExt.
     *
     * @return property value of whExt
     */
    public String getWhExt() {
        return whExt;
    }

    /**
     * Setter method for property whExt.
     *
     * @param whExt value to be assigned to property whExt
     */
    public void setWhExt( String whExt ) {
        this.whExt = whExt;
    }

    /**
     * Getter method for property shopId.
     *
     * @return property value of shopId
     */
    public String getShopId() {
        return shopId;
    }

    /**
     * Setter method for property shopId.
     *
     * @param shopId value to be assigned to property shopId
     */
    public void setShopId( String shopId ) {
        this.shopId = shopId;
    }

    /**
     * Getter method for property whName.
     *
     * @return property value of whName
     */
    public String getWhName() {
        return whName;
    }

    /**
     * Setter method for property whName.
     *
     * @param whName value to be assigned to property whName
     */
    public void setWhName( String whName ) {
        this.whName = whName;
    }

    /**
     * Getter method for property managerName.
     *
     * @return property value of managerName
     */
    public String getManagerName() {
        return managerName;
    }

    /**
     * Setter method for property managerName.
     *
     * @param managerName value to be assigned to property managerName
     */
    public void setManagerName( String managerName ) {
        this.managerName = managerName;
    }

    /**
     * Getter method for property managerPhone.
     *
     * @return property value of managerPhone
     */
    public String getManagerPhone() {
        return managerPhone;
    }

    /**
     * Setter method for property managerPhone.
     *
     * @param managerPhone value to be assigned to property managerPhone
     */
    public void setManagerPhone( String managerPhone ) {
        this.managerPhone = managerPhone;
    }
}
