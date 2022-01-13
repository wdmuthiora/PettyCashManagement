package com.moringaschool.pettycashmanagement.Models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "pettyCash_table")
public class PettyCashRequest {

    @PrimaryKey(autoGenerate = true)
    private int id; //Primary key.

    private String name;
    private String employee_ID;
    private String priority;
    private String purpose;
    private String currency;
    private int amount;


    public PettyCashRequest(String name, String employee_ID, int amount, String priority, String purpose) {
        this.id = id;
        this.name = name;
        this.priority = priority;
        this.purpose = purpose;
        this.currency = currency;
        this.amount = amount;
        this. employee_ID=employee_ID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmployee_ID() {
        return employee_ID;
    }

    public void setEmployee_ID(String employee_ID) {
        this.employee_ID = employee_ID;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
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
