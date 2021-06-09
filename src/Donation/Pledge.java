/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Donation;

import Funding.Donor;

/**
 *
 * @author muhammadkhuramniaz
 */
public class Pledge {
    
    int id;
    int monthlyAmount;
    int duratinMonths;
    Donor donor;

    public Pledge(int id, int monthlyAmount, int duratinMonths) {
        this.id = id;
        this.monthlyAmount = monthlyAmount;
        this.duratinMonths = duratinMonths;
        donor = null;
    }
    
    public void setDonor(Donor donor) {
        this.donor = donor;
    }
    
}
