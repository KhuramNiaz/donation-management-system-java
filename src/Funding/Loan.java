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
public class Loan extends Funding {
    Date returnDate;
    String description;
    
    public Loan(int id, int amount, Date date, Date returnDate, String description) {
        super(id, amount, date);
        this.returnDate = returnDate;
        this.description = description;
    }
    
}
