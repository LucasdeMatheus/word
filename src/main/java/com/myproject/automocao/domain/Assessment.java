package com.myproject.automocao.domain;

public class Assessment {
    private int location;
    private int size;
    private int propertyPlan;
    private int quality;
    private int conservationState;
    private int commonAreas;
    private int price;
    private String likeMost;
    private String likeLeast;
    private Boolean buyAgain;




    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPropertyPlan() {
        return propertyPlan;
    }

    public void setPropertyPlan(int propertyPlan) {
        this.propertyPlan = propertyPlan;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public int getConservationState() {
        return conservationState;
    }

    public void setConservationState(int conservationState) {
        this.conservationState = conservationState;
    }

    public int getCommonAreas() {
        return commonAreas;
    }

    public void setCommonAreas(int commonAreas) {
        this.commonAreas = commonAreas;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getLikeMost() {
        return likeMost;
    }

    public void setLikeMost(String likeMost) {
        this.likeMost = likeMost;
    }

    public String getLikeLeast() {
        return likeLeast;
    }

    public void setLikeLeast(String likeLeast) {
        this.likeLeast = likeLeast;
    }

    public Boolean getBuyAgain() {
        return buyAgain;
    }

    public void setBuyAgain(Boolean buyAgain) {
        this.buyAgain = buyAgain;
    }
}
