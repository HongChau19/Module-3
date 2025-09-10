package org.example.demo5.model;

import java.sql.Date;

public class Transaction {
    private int id;
    private double amount;
    private String categories;
    private String type;
    private Date date;
    private String description;

    public Transaction() {
    }

    public Transaction(String categories, double amount, String type, Date date, String description) {
        this.categories = categories;
        this.amount = amount;
        this.type = type;
        this.date = date;
        this.description = description;
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

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
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
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
