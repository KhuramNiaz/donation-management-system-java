/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project;

import Donation.Pledge;
import Donation.Donation;
import Funding.Beneficiery;
import Funding.Donor;
import Volunteer.Assesor;
import Volunteer.Volunteer;
import Funding.Funding;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author muhammadkhuramniaz
 */
public class Project {
    int id;
    String name;
    String description;
    int budgetRequired;
    int currentBalance;
    ArrayList<Donation> donations;
    ArrayList<Pledge> pledges;
    ArrayList<Funding> fundings;
    ArrayList<Assesor> assesors;
    ArrayList<Volunteer> team;
    Volunteer teamLead;

    public Volunteer getTeamLead() {
        return teamLead;
    }

    public int getAmountDonated() {
        int amount = 0;
        for(int i=0; i<fundings.size(); ++i) {
            amount = amount + fundings.get(i).getFundingAmount();
        }
        return amount;
    }
    
    

    public Project(int id, String name, String description, int budgetRequired) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.budgetRequired = budgetRequired;
        this.currentBalance = 0;
        this.donations = new ArrayList<>();
        this.pledges = new ArrayList<>();
        this.fundings = new ArrayList<>();
        this.assesors = new ArrayList<>();
        this.team = new ArrayList<>();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getBudgetRequired() {
        return budgetRequired;
    }

    public void setBudgetRequired(int budgetRequired) {
        this.budgetRequired = budgetRequired;
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

    public ArrayList<Funding> getFundings() {
        return fundings;
    }

    public void setFundings(ArrayList<Funding> fundings) {
        this.fundings = fundings;
    }

    public ArrayList<Assesor> getAssesors() {
        return assesors;
    }

    public void setAssesors(ArrayList<Assesor> assesors) {
        this.assesors = assesors;
    }
    
    
    public int getCurrentBalance() {
        return currentBalance;
    }
    
    public void setCurrentBalance(int currentBalance) {
        this.currentBalance = currentBalance;
    }
    
    public void increaseBalance(int increament) {
        this.currentBalance = this.currentBalance + increament;
    }
    
    public void addDonation(Donation donation) {
        this.donations.add(donation);
    }
    
    public void addPledge(Pledge pledge) {
        this.pledges.add(pledge);
    }
    
    public void addFunding(Funding funding) {
        this.fundings.add(funding);
    }
    
    public boolean assessBeneficiery(Beneficiery beneficiery) {
        return true;
    }

    public void setTeam(ArrayList<Volunteer> team) {
        this.team = team;
    }
    
    public ArrayList<Volunteer> getTeam() {
        return this.team;
    }
    
    public void addTeamMember(Volunteer volunteer) {
        this.team.add(volunteer);
    }
    
    public Volunteer getTeamMember(int id) {
        Volunteer member = null;
        for(int i=0; i<this.team.size(); ++i) {
            if(this.team.get(i).getId()== id) {
                member = this.team.get(i);
                break;
            }   
        }
        return member;
    }
    
    public void removeTeamMember(Volunteer volunteer) {
        int i = this.team.indexOf(volunteer);
        if(i != -1){
            this.team.remove(i);
        }
    }
    
    public void replaceTeamMember(Volunteer replacee, Volunteer replacer) {
        int i = this.team.indexOf(replacee);
        if(i != -1){
            this.team.set(i, replacer);
        }
    }

    public void setTeamLead(Volunteer teamLead) {
        this.teamLead = teamLead;
    }
    
    public boolean find(String name, String lead){
        boolean ans = false;
        if(this.name.equals(name) || this.teamLead.getName().equals(lead)){
            ans = true;
        }
        return ans;
    }
    
    public boolean find(String name){
        boolean ans = false;
        if(this.name.equals(name)){
            ans = true;
        }
        return ans;
    }
    
    public void findAndDisplayDonor(String name) {
        for(int i=0; i<this.donations.size(); ++i) {
            if(this.donations.get(i).getDonorName().equals(name)) {
                this.donations.get(i).getDonor().print();
                System.out.println("Donation Amount: " + this.donations.get(i).getDonationAmount());
            }
        }
    }
    
    public Donor findAndGetDonor(String name) {
        
        Donor donor = null;
        for(int i=0; i<this.donations.size(); ++i) {
            if(this.donations.get(i).getDonorName().equals(name)) {
                donor = this.donations.get(i).getDonor();
            }
        }
        return donor;
    }
    
    public Beneficiery findAndGetBeneficiery(String beneficieryName) {
        Beneficiery beneficiery = null;
        for(int i=0; i<this.fundings.size(); ++i) {
            if(this.fundings.get(i).getBeneficiery().getName().equals(beneficieryName)) {
                beneficiery = this.fundings.get(i).getBeneficiery();
                break;
            }
        }
        return beneficiery;
    }
    
    public boolean searchDonor(Donor donor){
        boolean ans = false;
        
        for(int i=0; i<this.donations.size(); ++i) {
            if(this.donations.get(i).getDonor() == donor) {
                ans = true;
            }
        }
        return ans;
    }
    
    public boolean searchBeneficiery(Beneficiery beneficiery){
        boolean ans = false;
        
        for(int i=0; i<this.fundings.size(); ++i) {
            if(this.fundings.get(i).getBeneficiery() == beneficiery) {
                ans = true;
            }
        }
        return ans;
    }
    
    public int findDonorAndGetDonationAmount(String name) {
        int donationAmount = 0;
        for(int i=0; i<this.donations.size(); ++i) {
            if(this.donations.get(i).getDonorName().equals(name)) {
                donationAmount = this.donations.get(i).getDonationAmount();
                break;
            }
        }
        return donationAmount;
    }
    
    public void print() {
        System.out.println(this.id);
        System.out.println(this.name);
    }
    
    
    public void printProjectTeam() {
        System.out.println("Project Team:");
        for(int i=0; i<this.team.size(); ++i) {
            this.team.get(i).print();
        }
        
    }

    public void printAllInfo() {
        System.out.println(this.id);
        System.out.println(this.name);
        System.out.println(this.budgetRequired);
        System.out.println(this.currentBalance);
        
        if(this.teamLead != null) {
            System.out.println("Project Team Lead:");
            this.teamLead.print();
        }
        
        if(this.team != null) {
            System.out.println("Project Team:");
            for(int i=0; i<this.team.size(); ++i) {
                this.team.get(i).print();
            }
        }
        
    }
    
    public void findAndDisplayBeneficiery(String beneficieryName) {
        for(int i=0; i<this.fundings.size(); ++i) {
            if(this.fundings.get(i).getBeneficieryName().equals(beneficieryName)) {
                this.fundings.get(i).getBeneficiery().print();
                System.out.println("Funding Amount: " + this.fundings.get(i).getFundingAmount());
            }
        }
        
    }
    
    public int findBeneficieryAndGetFundingAmount(String name) {
        int fundingAmount = 0;
        for(int i=0; i<this.fundings.size(); ++i) {
            if(this.fundings.get(i).getBeneficieryName().equals(name)) {
                fundingAmount = this.fundings.get(i).getFundingAmount();
                break;
            }
        }
        return fundingAmount;
    }
    
    public Volunteer searchTeamMember(String name) {
        Volunteer ans = null;
        for(int i=0; i<this.team.size(); ++i) {
            if(this.team.get(i).getName().equals(name)){
                ans = this.team.get(i);
                break;
            }
        }
        return ans;
    }
    
    public ArrayList<Beneficiery> getBeneficieries() {
        ArrayList<Beneficiery> bArr = new ArrayList<>();
        
        for(int i=0; i<fundings.size(); ++i) {
            bArr.add(fundings.get(i).getBeneficiery());
        }
        
        return bArr;
    }
    
    public ArrayList<Donor> getDonors() {
        ArrayList<Donor> bArr = new ArrayList<>();
        
        for(int i=0; i<donations.size(); ++i) {
            bArr.add(donations.get(i).getDonor());
        }
        
        return bArr;
    }
    
}
