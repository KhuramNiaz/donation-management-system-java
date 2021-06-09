/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Donation;

import Funding.Donor;
import java.util.Date;

/**
 *
 * @author muhammadkhuramniaz
 */
public class Donation {
    int id;
    int amount;
    Date date;
    Donor donor;

    public int getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }

    public Donation(int id, int amount, Date date) {
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.donor = null;
    }
    
    public void setDonor(Donor donor) {
        this.donor = donor;
    }
    
    public Donor getDonor() {
        return this.donor;
    }
    
    public String getDonorName() {
        return this.donor.getName();
    }
    
    public int getDonationAmount() {
        return this.amount;
    }
    
    public int getDonorId() {
        return this.donor.getId();
    }
}
