package org.example.demo4.model;

import java.sql.Date;

public class Transaction {
    private int id;
    private double amount;
    private String description;
    private int typeId;
    private String typeName;
    private Date date;

    public Transaction() {
    }

    public Transaction(String description, double amount, int typeId, Date date) {
        this.description = description;
        this.amount = amount;
        this.typeId = typeId;
        this.date = date;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public double getAmount() {return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getTypeId() {
        return typeId;
    }
    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }
    public String getTypeName() {
        return typeName;
    }
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

}
