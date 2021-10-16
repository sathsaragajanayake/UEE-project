package com.project.litro.models;

public class pastOrdersModel {
    private String date;
    private String order;
    private String dealer;
    private String total;
    private String status;
    private String qty;


    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public pastOrdersModel(String date, String order, String dealer, String total, String status, String qty) {
        this.date = date;
        this.order = order;
        this.dealer = dealer;
        this.total = total;
        this.status = status;
        this.qty = qty;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getDealer() {
        return dealer;
    }

    public void setDealer(String dealer) {
        this.dealer = dealer;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public pastOrdersModel() {
    }

}
