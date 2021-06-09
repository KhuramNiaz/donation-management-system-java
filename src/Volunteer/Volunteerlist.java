/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Volunteer;

/**
 *
 * @author saqib
 */
import Main.dbConnectivity;
import java.util.*;
public class Volunteerlist {
    List<Volunteer> vlist=new ArrayList<Volunteer>();

    public Volunteerlist() {
    }

    public List<Volunteer> getVlist() {
        return vlist;
    }

    public void setVlist(List<Volunteer> vlist) {
        this.vlist = vlist;
    }

    public void insert(Volunteer v){
        vlist.add(v);
                
    
    }
    
} 
