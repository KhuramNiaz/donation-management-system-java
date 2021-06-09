/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funding;

import java.util.ArrayList;

/**
 *
 * @author muhammadkhuramniaz
 */
public class Beneficiery {
    int id;
    String name;
    String cnic;
    String address;
    int salary;
    ArrayList<Funding> fundingsReceived;

    public Beneficiery(int id, String name, String cnic, String address) {
        this.id = id;
        this.name = name;
        this.cnic = cnic;
        this.address = address;
        this.salary = 0;
        this.fundingsReceived = new ArrayList<>();
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
    
    
    
    public void addFunding(Funding funding) {
        this.fundingsReceived.add(funding);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCnic() {
        return cnic;
    }

    public String getAddress() {
        return address;
    } 
    
    public void print() {
        System.out.println("id: " + id + ", Name: " + this.name);
    }
}
