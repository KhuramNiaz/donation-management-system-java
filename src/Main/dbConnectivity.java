/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;
import Donation.Donation;
import Funding.Beneficiery;
import Funding.Donor;
import Funding.Funding;
import Project.Project;
import Volunteer.Volunteer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.*;
/**
 *
 * @author saqib
 */
public class dbConnectivity 
{
    Connection con;
    Statement stmt;
    
    public dbConnectivity() //cons
    {
        try
        {
            //jdbc:mysql://localhost:3306/ooad_lab
           // String s = "jdbc:sqlserver://localhost:1433;databaseName=ooad_lab";
            // jdbc:sqlserver://<Computer Name>\\SQLFULL:1433;databaseName=BA_ELTRUN;
            String s = "jdbc:sqlserver://localhost\\SAQIBKAMRAN:1433;databaseName=OOADProject;"; //working
          //  String s = "jdbc:sqlserver://Terminator\\SQLFULL:1433;databaseName=ooad_lab;"; // working
        //     String s = "jdbc:sqlserver://localhost:1433;databaseName=ooad_lab;"; // working
             con=DriverManager.getConnection(s,"saqib","123456");


            stmt = con.createStatement(); 
            
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
    public void getVolunteers(ArrayList<Volunteer> v)
    {
        
        try
        {
            Volunteer va;
            ResultSet rs=stmt.executeQuery("select * from volunteer");
             while(rs.next())
             {
                 va=new Volunteer(rs.getInt(1),rs.getString(2),true);
                 v.add(va);
             }
             
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        
    }
    
    public void getProjects(ArrayList<Project> projects)
    {
        
        try
        {
            Project project;
            ResultSet rs=stmt.executeQuery("select * from project");
             while(rs.next())
             {
                 project=new Project(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4));
                 projects.add(project);
             }
             
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        
    }
    
    public void getApplicants(ArrayList<Beneficiery> applicants)
    {
        
        try
        {
            Beneficiery applicant;
            ResultSet rs=stmt.executeQuery("select * from applicant");
             while(rs.next())
             {
                 applicant=new Beneficiery(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4));
                 applicants.add(applicant);
             }
             
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        
    }
    
    public void getProjectBeneficieries(ArrayList<Beneficiery> beneficieries, Project project)
    {
        ArrayList<Beneficiery> b = new ArrayList<>();
        ArrayList<String> s = new ArrayList<>();
        
        try
        {
            Beneficiery beneficiery;
            ResultSet rs=stmt.executeQuery("select a.id, a.name, a.cnic, a.address, p.name from applicant as a join beneficiery as b on b.applicantid = a.id join project as p on b.projectid = p.id");
             while(rs.next())
             {
                 beneficiery=new Beneficiery(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4));
                 b.add(beneficiery);
                 s.add(rs.getString(5));
             }
             for(int i=0; i<b.size(); ++i) {
                if(s.get(i).equals(project.getName())) {
                     beneficieries.add(b.get(i));
                }

            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        
    }
    
    public ArrayList<Donation> getProjectDonations(Project project)
    {
        ArrayList<Donation> d1 = new ArrayList<>();
        ArrayList<Donation> d2 = new ArrayList<>();
        ArrayList<String> s = new ArrayList<>();
        
        try
        {
            Donation donation;
            //ResultSet rs = stmt.executeQuery("select d.id, d.amount, d.date, p.name from donation as d join project as p on d.projectid = p.id");
             ResultSet rs = stmt.executeQuery("select d.id, d.amount, d.date, p.name, dn.id, dn.name, dn.cnic, dn.address from donation as d join project as p on d.projectid = p.id join donor as dn on d.donorid = dn.id");
            while(rs.next())
             {
                String d = rs.getString(3);  
                Date date = new SimpleDateFormat("dd-MM-yyyy").parse(d);
                donation = new Donation(rs.getInt(1), rs.getInt(2), date);
                
                s.add(rs.getString(4));
                Donor donor = new Donor(rs.getInt(5), rs.getString(6), rs.getString(7), rs.getString(8));
                donation.setDonor(donor);
                donor.addDonation(donation);
                
                d1.add(donation);
             }
             for(int i=0; i<d1.size(); ++i) {
                 if(s.get(i).equals(project.getName())) {
                     d2.add(d1.get(i));
                }
             }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return d2;
        
    }
    
    public void getProjectDonors(ArrayList<Donor> donors, Project project)
    {
        ArrayList<Donor> d = new ArrayList<>();
        ArrayList<String> s = new ArrayList<>();
        try
        {
            Donor donor;
            ResultSet rs=stmt.executeQuery("select d.id, d.name, d.cnic, d.address, p.name from donor as d join donation as dn on d.id = dn.donorid join project as p on dn.projectid = p.id");
           
            while(rs.next())
            {
                donor = new Donor (rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4));
                d.add(donor);
                s.add(rs.getString(5));
            }
            
            for(int i=0; i<d.size(); ++i) {
                if(s.get(i).equals(project.getName())) {
                     donors.add(d.get(i));
                }
            }
            
            System.out.println("fff");
             
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        
    }
    
    public ArrayList<Funding> getProjectFundings(Project project)
    {
        ArrayList<Funding> f1 = new ArrayList<>();
        ArrayList<Funding> f2 = new ArrayList<>();
        ArrayList<String> s = new ArrayList<>();
        try
        {
            Funding funding;
            //ResultSet rs=stmt.executeQuery("select d.id, d.amount, d.date, p.name from funding as d join project as p on d.projectid = p.id");
            ResultSet rs=stmt.executeQuery("select d.id, d.amount, d.date, p.name, a.id, a.name, a.cnic, a.address from funding as d join project as p on d.projectid = p.id join beneficiery as b on d.beneficieryId = b.applicantid join applicant as a on b.applicantid = a.id");
             while(rs.next())
             {
                String d = rs.getString(3);  
                Date date=new SimpleDateFormat("dd-MM-yyyy").parse(d);
                funding = new Funding (rs.getInt(1),rs.getInt(2), date);
                Beneficiery beneficiery = new Beneficiery(rs.getInt(5), rs.getString(6), rs.getString(7), rs.getString(8));
                funding.setBeneficiery(beneficiery);
                beneficiery.addFunding(funding);
                
                f1.add(funding);
                s.add(rs.getString(4));
             }
              for(int i=0; i<f1.size(); ++i) {
                 if(s.get(i).equals(project.getName())) {
                     f2.add(f1.get(i));
                }
             }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return f2;
    }
    
    public ArrayList<Volunteer> getProjectTeam(Project project)
    {
        ArrayList<Volunteer> team = new ArrayList<>();
        ArrayList<String> projectNames = new ArrayList<>();
        ArrayList<Volunteer> members = new ArrayList<>();
        
        try
        {
            Volunteer teamMember;
            ResultSet rs=stmt.executeQuery("select v.id, v.name, v.status, p.name from volunteer as v join projectTeam as pt on v.id = pt.volunteerid join project as p on pt.projectid = p.id");
             while(rs.next())
             {
                teamMember = new Volunteer (rs.getInt(1),rs.getString(2),true);
                members.add(teamMember);
                projectNames.add(rs.getString(4));
             }
             
            for(int i=0; i<members.size(); ++i) {
                if(projectNames.get(i).equals(project.getName())) {
                     team.add(members.get(i));
                }

            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return team;
    }
    
    public void deleteDB(){
        try
        {
            
            int rs=stmt.executeUpdate("DELETE FROM applicant");
            stmt.executeUpdate("DELETE FROM assesor");
            stmt.executeUpdate("DELETE FROM beneficiery");
            stmt.executeUpdate("DELETE FROM donation");
            stmt.executeUpdate("DELETE FROM donor");
            stmt.executeUpdate("DELETE FROM funding");
            stmt.executeUpdate("DELETE FROM pledge");
            stmt.executeUpdate("DELETE FROM project");
            stmt.executeUpdate("DELETE FROM projectTeam");
            stmt.executeUpdate("DELETE FROM volunteer");
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        
    }
    
    public void saveProjects(ArrayList<Project> projects) {
        int id, budgetRequired, currentBalance =0;
        String name, description = null;
        try
        {
            for(int i=0; i<projects.size(); ++i){
                id = projects.get(i).getId();
                name = projects.get(i).getName();
                description = projects.get(i).getDescription();
                budgetRequired = projects.get(i).getBudgetRequired();
                currentBalance = projects.get(i).getCurrentBalance();
                String a = "insert into project(id, name, description, budgetRequired, currentBalance) values(\'" + id + "\',\'"+ name + "\',\'"+ description + "\',\'" + budgetRequired +  "\',\'" + currentBalance +"'  )";
                stmt.executeUpdate(a);
              //  int rs = stmt.executeUpdate("insert into project(id, name, description, budgetRequired, currentBalance) values(" + String.valueOf(id) + "," + name + "," + description + "," + budgetRequired + "," + currentBalance+ ")");   
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
      public void saveProjectsAll(ArrayList<Project> projects, ArrayList<Beneficiery> applicants) {
        int id, budgetRequired, currentBalance =0;
        String name, description = null;
        try
        {
            for(int i=0; i<projects.size(); ++i){
                id = projects.get(i).getId();
                name = projects.get(i).getName();
                description = projects.get(i).getDescription();
                budgetRequired = projects.get(i).getBudgetRequired();
                currentBalance = projects.get(i).getCurrentBalance();
                
                ArrayList<Donation> donations = projects.get(i).getDonations();
                ArrayList<Donor> donors = projects.get(i).getDonors();
                
                ArrayList<Funding> fundings = projects.get(i).getFundings();
                ArrayList<Beneficiery> beneficierys = projects.get(i).getBeneficieries();
                
                
                String a = "insert into project(id, name, description, budgetRequired, currentBalance) values(\'" + id + "\',\'"+ name + "\',\'"+ description + "\',\'" + budgetRequired +  "\',\'" + currentBalance +"'  )";
                saveDonors(donors);
                for(int j=0; j<donations.size(); ++j) {
                    for(int k=0; k<donors.size(); ++k) {
                        if(donations.get(j).getDonor().getName().equals(donors.get(k).getName())) {
                            int donationId, donationAmount, donorId = 0;
                            String date = donations.get(j).getDate().toString();
                            donationId = donations.get(j).getId();
                            donationAmount = donations.get(j).getDonationAmount();
                            donorId = donors.get(k).getId();
                            
                            
                            
                            String b = "insert into donation(id, amount, donorid, date, projectid) values(\'" + donationId + "\',\'"+ donationAmount + "\',\'"+ donorId + "\',\'" + date +  "\',\'" + id +"'  )";
                            stmt.executeUpdate(b);
                            
                        }
                    }
                }
                
                for(int l=0; l<applicants.size(); ++l) {
                    if(projects.get(i).getBeneficieries().indexOf(applicants.get(l)) != -1) {
                        String n = "insert into beneficiery(projectid, applicantid) values(\'" + applicants.get(l).getId() + "\',\'"+ id +"'  )";
                        stmt.executeUpdate(n);
                    }
                }
                
                for(int j=0; j<fundings.size(); ++j) {
                    for(int k=0; k<beneficierys.size(); ++k) {
                        if(fundings.get(j).getBeneficiery().getName().equals(beneficierys.get(k).getName())) {
                            int fundingId, fundingAmount, beneficieryId = 0;
                            String date1 = donations.get(j).getDate().toString();
                            fundingId = fundings.get(j).getId();
                            fundingAmount = fundings.get(j).getAmount();
                            beneficieryId = beneficierys.get(k).getId();
                            
                            
                            
                            String c = "insert into funding(id, amount, date, beneficieryId, projectid) values(\'" + fundingId + "\',\'"+ fundingAmount + "\',\'"+ date1 + "\',\'" + beneficieryId +  "\',\'" + id +"'  )";
                            stmt.executeUpdate(c);
                            
                        }
                    }
                }
                
                for(int p=0; p<projects.get(i).getTeam().size(); ++p) {
                    String z = "insert into projectTeam(projectid, volunteerid) values(\'" + projects.get(i).getTeam().get(p).getId() + "\',\'"+ id +"'  )";
                    stmt.executeUpdate(z);
                }
                
                stmt.executeUpdate(a);
              //  int rs = stmt.executeUpdate("insert into project(id, name, description, budgetRequired, currentBalance) values(" + String.valueOf(id) + "," + name + "," + description + "," + budgetRequired + "," + currentBalance+ ")");   
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
    public void saveVolunteers(ArrayList<Volunteer> volunteers) {
        int id =0, status = 1;
        String name = null;
        try
        {
            for(int i=0; i<volunteers.size(); ++i){
                id = volunteers.get(i).getId();
                name = volunteers.get(i).getName();
                
                String a = "insert into volunteer(id, name, status) values(\'" + id + "\',\'"+ name + "\',\'"+ status + "\'  )";
                stmt.executeUpdate(a);
              //  int rs = stmt.executeUpdate("insert into project(id, name, description, budgetRequired, currentBalance) values(" + String.valueOf(id) + "," + name + "," + description + "," + budgetRequired + "," + currentBalance+ ")");   
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
    public void saveDonors(ArrayList<Donor> donors) {
        int id =0;
        String name, cnic, address = null;
        try
        {
            for(int i=0; i<donors.size(); ++i){
                id = donors.get(i).getId();
                name = donors.get(i).getName();
                cnic = donors.get(i).getCnic();
                address = donors.get(i).getAddress();
                
                String a = "insert into donor(id, name, cnic, address) values(\'" + id + "\',\'"+ name + "\',\'"+ cnic + "\',\'"+ address + "\'  )";
                stmt.executeUpdate(a);
              //  int rs = stmt.executeUpdate("insert into project(id, name, description, budgetRequired, currentBalance) values(" + String.valueOf(id) + "," + name + "," + description + "," + budgetRequired + "," + currentBalance+ ")");   
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
    public void saveApplicants(ArrayList<Beneficiery> applicants) {
        int id =0;
        String name, cnic, address = null;
        try
        {
            for(int i=0; i<applicants.size(); ++i){
                id = applicants.get(i).getId();
                name = applicants.get(i).getName();
                cnic = applicants.get(i).getCnic();
                address = applicants.get(i).getAddress();
                
                String a = "insert into applicant(id, name, cnic, address) values(\'" + id + "\',\'"+ name + "\',\'"+ cnic + "\',\'"+ address + "\'  )";
                stmt.executeUpdate(a);
              //  int rs = stmt.executeUpdate("insert into project(id, name, description, budgetRequired, currentBalance) values(" + String.valueOf(id) + "," + name + "," + description + "," + budgetRequired + "," + currentBalance+ ")");   
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
     
    public void saveDonations(ArrayList<Donation> donations) {
        
    }
}
