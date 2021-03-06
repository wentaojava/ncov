package com.wentao.ncov.entity.mysql;

import java.util.Date;

public class CityData {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column city_data.id
     *
     * @mbggenerated Tue Mar 03 14:56:38 CST 2020
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column city_data.parent_location_Id
     *
     * @mbggenerated Tue Mar 03 14:56:38 CST 2020
     */
    private Integer parentLocationId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column city_data.city_Name
     *
     * @mbggenerated Tue Mar 03 14:56:38 CST 2020
     */
    private String cityName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column city_data.confirmed_Count
     *
     * @mbggenerated Tue Mar 03 14:56:38 CST 2020
     */
    private String confirmedCount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column city_data.suspected_Count
     *
     * @mbggenerated Tue Mar 03 14:56:38 CST 2020
     */
    private String suspectedCount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column city_data.cured_Count
     *
     * @mbggenerated Tue Mar 03 14:56:38 CST 2020
     */
    private String curedCount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column city_data.dead_Count
     *
     * @mbggenerated Tue Mar 03 14:56:38 CST 2020
     */
    private String deadCount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column city_data.location_Id
     *
     * @mbggenerated Tue Mar 03 14:56:38 CST 2020
     */
    private Integer locationId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column city_data.create_time
     *
     * @mbggenerated Tue Mar 03 14:56:38 CST 2020
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column city_data.current_Confirmed_Count
     *
     * @mbggenerated Tue Mar 03 14:56:38 CST 2020
     */
    private String currentConfirmedCount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column city_data.sure_new_cnt
     *
     * @mbggenerated Tue Mar 03 14:56:38 CST 2020
     */
    private String sureNewCnt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column city_data.sure_nzd_cnt
     *
     * @mbggenerated Tue Mar 03 14:56:38 CST 2020
     */
    private String sureNzdCnt;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column city_data.id
     *
     * @return the value of city_data.id
     *
     * @mbggenerated Tue Mar 03 14:56:38 CST 2020
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column city_data.id
     *
     * @param id the value for city_data.id
     *
     * @mbggenerated Tue Mar 03 14:56:38 CST 2020
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column city_data.parent_location_Id
     *
     * @return the value of city_data.parent_location_Id
     *
     * @mbggenerated Tue Mar 03 14:56:38 CST 2020
     */
    public Integer getParentLocationId() {
        return parentLocationId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column city_data.parent_location_Id
     *
     * @param parentLocationId the value for city_data.parent_location_Id
     *
     * @mbggenerated Tue Mar 03 14:56:38 CST 2020
     */
    public void setParentLocationId(Integer parentLocationId) {
        this.parentLocationId = parentLocationId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column city_data.city_Name
     *
     * @return the value of city_data.city_Name
     *
     * @mbggenerated Tue Mar 03 14:56:38 CST 2020
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column city_data.city_Name
     *
     * @param cityName the value for city_data.city_Name
     *
     * @mbggenerated Tue Mar 03 14:56:38 CST 2020
     */
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column city_data.confirmed_Count
     *
     * @return the value of city_data.confirmed_Count
     *
     * @mbggenerated Tue Mar 03 14:56:38 CST 2020
     */
    public String getConfirmedCount() {
        return confirmedCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column city_data.confirmed_Count
     *
     * @param confirmedCount the value for city_data.confirmed_Count
     *
     * @mbggenerated Tue Mar 03 14:56:38 CST 2020
     */
    public void setConfirmedCount(String confirmedCount) {
        this.confirmedCount = confirmedCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column city_data.suspected_Count
     *
     * @return the value of city_data.suspected_Count
     *
     * @mbggenerated Tue Mar 03 14:56:38 CST 2020
     */
    public String getSuspectedCount() {
        return suspectedCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column city_data.suspected_Count
     *
     * @param suspectedCount the value for city_data.suspected_Count
     *
     * @mbggenerated Tue Mar 03 14:56:38 CST 2020
     */
    public void setSuspectedCount(String suspectedCount) {
        this.suspectedCount = suspectedCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column city_data.cured_Count
     *
     * @return the value of city_data.cured_Count
     *
     * @mbggenerated Tue Mar 03 14:56:38 CST 2020
     */
    public String getCuredCount() {
        return curedCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column city_data.cured_Count
     *
     * @param curedCount the value for city_data.cured_Count
     *
     * @mbggenerated Tue Mar 03 14:56:38 CST 2020
     */
    public void setCuredCount(String curedCount) {
        this.curedCount = curedCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column city_data.dead_Count
     *
     * @return the value of city_data.dead_Count
     *
     * @mbggenerated Tue Mar 03 14:56:38 CST 2020
     */
    public String getDeadCount() {
        return deadCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column city_data.dead_Count
     *
     * @param deadCount the value for city_data.dead_Count
     *
     * @mbggenerated Tue Mar 03 14:56:38 CST 2020
     */
    public void setDeadCount(String deadCount) {
        this.deadCount = deadCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column city_data.location_Id
     *
     * @return the value of city_data.location_Id
     *
     * @mbggenerated Tue Mar 03 14:56:38 CST 2020
     */
    public Integer getLocationId() {
        return locationId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column city_data.location_Id
     *
     * @param locationId the value for city_data.location_Id
     *
     * @mbggenerated Tue Mar 03 14:56:38 CST 2020
     */
    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column city_data.create_time
     *
     * @return the value of city_data.create_time
     *
     * @mbggenerated Tue Mar 03 14:56:38 CST 2020
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column city_data.create_time
     *
     * @param createTime the value for city_data.create_time
     *
     * @mbggenerated Tue Mar 03 14:56:38 CST 2020
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column city_data.current_Confirmed_Count
     *
     * @return the value of city_data.current_Confirmed_Count
     *
     * @mbggenerated Tue Mar 03 14:56:38 CST 2020
     */
    public String getCurrentConfirmedCount() {
        return currentConfirmedCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column city_data.current_Confirmed_Count
     *
     * @param currentConfirmedCount the value for city_data.current_Confirmed_Count
     *
     * @mbggenerated Tue Mar 03 14:56:38 CST 2020
     */
    public void setCurrentConfirmedCount(String currentConfirmedCount) {
        this.currentConfirmedCount = currentConfirmedCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column city_data.sure_new_cnt
     *
     * @return the value of city_data.sure_new_cnt
     * @mbggenerated Tue Mar 03 14:56:38 CST 2020
     */
    public String getSureNewCnt() {
        return sureNewCnt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column city_data.sure_new_cnt
     *
     * @param sureNewCnt the value for city_data.sure_new_cnt
     * @mbggenerated Tue Mar 03 14:56:38 CST 2020
     */
    public void setSureNewCnt(String sureNewCnt) {
        this.sureNewCnt = sureNewCnt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column city_data.sure_nzd_cnt
     *
     * @return the value of city_data.sure_nzd_cnt
     * @mbggenerated Tue Mar 03 14:56:38 CST 2020
     */
    public String getSureNzdCnt() {
        return sureNzdCnt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column city_data.sure_nzd_cnt
     *
     * @param sureNzdCnt the value for city_data.sure_nzd_cnt
     * @mbggenerated Tue Mar 03 14:56:38 CST 2020
     */
    public void setSureNzdCnt(String sureNzdCnt) {
        this.sureNzdCnt = sureNzdCnt;
    }
}
