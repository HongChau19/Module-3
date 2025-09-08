package org.example.demo5.model;

import java.sql.Date;

public class Transaction {
    private int id;
    private double amount;
    private String description;
    private String type;
    private Date date;

    public Transaction() {
    }

    public Transaction(String description, double amount, String type, Date date) {
        this.description = description;
        this.amount = amount;
        this.type = type;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
