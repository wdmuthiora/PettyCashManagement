package com.moringaschool.pettycashmanagement.Models;

import java.util.Date;

public class PettyCashRequest {
    private int id;
    private String title;
    private String priority;
    private String description;
    private Date date;
    private String currency;
    private int amount;

    public PettyCashRequest(String title, String priority, String description, Date date, String currency, int amount) {
        this.id = id;
        this.title = title;
        this.priority = priority;
        this.description = description;
        this.date = date;
        this.currency = currency;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
