package com.moringaschool.pettycashmanagement.Models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "pettyCash_table")
public class PettyCashRequest {

    @PrimaryKey(autoGenerate = true)
    private int id; //Primary key.
    private String name;
    private String employee_ID;
    private String status;
    private String purpose;
    private String currency;
    private int amount;

    //constructor
    public PettyCashRequest(String name, String employee_ID, int amount, String status, String purpose) {
        this.name = name;
        this.status = status;
        this.purpose = purpose;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
