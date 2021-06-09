/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funding;

import Donation.Donation;
import Donation.Pledge;
import java.util.ArrayList;

/**
 *
 * @author muhammadkhuramniaz
 */
public class Donor {
    int id;
    String name;
    String cnic;
    String address;
    ArrayList<Donation> donations;

    public String getCnic() {
        return cnic;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList<Donation> getDonations() {
        return donations;
    }

    public void setDonations(ArrayList<Donation> donations) {
        this.donations = donations;
    }

    public ArrayList<Pledge> getPledges() {
        return pledges;
    }

    public void setPledges(ArrayList<Pledge> pledges) {
        this.pledges = pledges;
    }
    ArrayList<Pledge> pledges;

    public Donor(int id, String name, String cnic, String address) {
        this.id = id;
        this.name = name;
        this.cnic = cnic;
        this.address = address;
        this.donations = new ArrayList<Donation>();
        this.pledges = new ArrayList<Pledge>();
    }
    
    public String getName() {
        return this.name;
    }
    
    public void addDonation(Donation donation) {
        this.donations.add(donation);
    }
    
    public void addPledge(Pledge pledge) {
        this.pledges.add(pledge);
    }

    public int getId() {
        return id;
    }
    
    public void print() {
        System.out.println("id: " + id + ", Name: " + this.name);
    }
}
