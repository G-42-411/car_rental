package com.koko.pojo;

import java.util.Date;

public class Order {
    private Integer id;

    private String number;

    private Date startTime;

    private Date endTime;

    private Date rentEndTime;

    private String idNumber;

    private String name;

    private Integer status;

    private String carNumber;

    private Integer rentPrice;

    private Integer deposit;

    private String acceptionOfficer;

    private String rentAddress;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getRentEndTime() {
        return rentEndTime;
    }

    public void setRentEndTime(Date rentEndTime) {
        this.rentEndTime = rentEndTime;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber == null ? null : idNumber.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber == null ? null : carNumber.trim();
    }

    public Integer getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(Integer rentPrice) {
        this.rentPrice = rentPrice;
    }

    public Integer getDeposit() {
        return deposit;
    }

    public void setDeposit(Integer deposit) {
        this.deposit = deposit;
    }

    public String getAcceptionOfficer() {
        return acceptionOfficer;
    }

    public void setAcceptionOfficer(String acceptionOfficer) {
        this.acceptionOfficer = acceptionOfficer == null ? null : acceptionOfficer.trim();
    }

    public String getRentAddress() {
        return rentAddress;
    }

    public void setRentAddress(String rentAddress) {
        this.rentAddress = rentAddress == null ? null : rentAddress.trim();
    }
}