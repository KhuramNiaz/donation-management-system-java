/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funding;

import java.util.Date;

/**
 *
 * @author muhammadkhuramniaz
 */
public class Funding {
    int id;
    int amount;
    Date date;
    Beneficiery beneficiery;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Funding(int id, int amount, Date date) {
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.beneficiery = null;
    }
    
    public void setBeneficiery(Beneficiery beneficiery) {
        this.beneficiery = beneficiery;
    }
    
    public String getBeneficieryName(){
        return this.beneficiery.getName();
    }

    public Beneficiery getBeneficiery() {
        return beneficiery;
    }
    
    public int getFundingAmount() {
        return this.amount;
    }
    
}
