package com.company;

import java.math.BigDecimal;

public class Item {

    private Integer id;

    private String partNo;

    private String serialNo;

    private String name;

    private String description;

    private Integer numberInStock;

    private BigDecimal price;

    public Item(Integer id, String partNo,String name, BigDecimal price) {
        this.id = id;
        this.partNo = partNo;
        this.name = name;
        this.price = price;
    }

    public Item() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPartNo() {
        return partNo;
    }

    public void setPartNo(String partNo) {
        this.partNo = partNo;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getNumberInStock() {
        return numberInStock;
    }

    public void setNumberInStock(Integer numberInStock) {
        this.numberInStock = numberInStock;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "This item of " +
                "id = " + id +
                ", partNo = " + partNo +
                //", serialNo = " + serialNo +
                ", name = " + name +
                //", description = " + description +
                //", numberInStock = " + numberInStock +
                ", price = " + price +
                " CZK";
    }
}