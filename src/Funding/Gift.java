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
public class Gift extends Funding {
    String description;

    public Gift(int id, int amount, Date date, String description) {
        super(id, amount, date);
        this.description = description;
    }
    
}
