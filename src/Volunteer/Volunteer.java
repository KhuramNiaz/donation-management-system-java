/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Volunteer;

import javax.xml.bind.attachment.AttachmentUnmarshaller;

/**
 *
 * @author muhammadkhuramniaz
 */
public class Volunteer {
    int id;
    String name;
    Boolean status;
    //int projectid;

    public Volunteer(int id, String name, boolean status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }
    
    
    public int getId() {
        return this.id;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void print() {
        System.out.println("id: " + this.id + ", Name: " + this.name);
    }
    
    public boolean find(String name){
        boolean ans = false;
        if(this.name.equals(name)){
            ans = true;
        }
        return ans;
    }
}
