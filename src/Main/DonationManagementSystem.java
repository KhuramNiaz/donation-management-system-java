package Main;

import Donation.Donation;
import Funding.Beneficiery;
import Funding.Donor;
import Funding.Funding;
import GUIs.WelcomeScreen;
import Project.Project;
import Volunteer.Volunteer;
import Volunteer.Volunteerlist;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author muhammadkhuramniaz
 */
public class DonationManagementSystem {

    public static ArrayList<Project> projects = new ArrayList<>();
    public static ArrayList<Volunteer> volunteers = new ArrayList<>();
    public static ArrayList<Beneficiery> applicants = new ArrayList<>();
    
    @SuppressWarnings("empty-statement")
    
    public static void main(String[] args) {
        WelcomeScreen welcomeScreen = new WelcomeScreen();        
        welcomeScreen.setVisible(true);
    }
    
    public void addProject(int id, String projectName, String projectDescription, int budgetRequired) {
        Project project = new Project(id, projectName, projectDescription, budgetRequired);
        projects.add(project);
    }
    
    public void addVolunteer(Volunteer volunteer) {
        volunteers.add(volunteer);
    }
    
    public void allocateTeam(String projectName, String volunteerName) {     // allocate team members to a project
        Project project = searchProject(projectName);
        Volunteer  volunteer = searchVolunteer(volunteerName);
        
        project.addTeamMember(volunteer);
    }
    
    public void removeTeamMember(String projectName, String memberName) {  // removes a team member from project
        Project project = searchProject(projectName);
        Volunteer member = searchVolunteer(memberName);
        project.removeTeamMember(member);
    }
    
    public void replaceTeamMember(String projectName, String memberName, String replaceWith) { // reomve a team member from project and
        Project project = searchProject(projectName);
        Volunteer member = project.searchTeamMember(memberName);
        Volunteer replacer = searchVolunteer(replaceWith);
        
        if(project != null && member != null && replacer != null) {
            project.replaceTeamMember(member, replacer);
        }
    }
    
    public void setTeamLead(String projectName, String Leadname) {   // search project and search member and make him leader
        Project project = searchProject(projectName);
        
        Volunteer lead = project.searchTeamMember(Leadname);
        
        if(lead != null){
            project.setTeamLead(lead);
        }
    }
    
    public void addDonor(String projectName, Donor donor, Donation donation) {  // add a new donor in a project
        Project project = searchProject(projectName);
        
        donation.setDonor(donor);
        project.addDonation(donation);
        donor.addDonation(donation);
        project.setCurrentBalance(project.getCurrentBalance() + donation.getDonationAmount());
    }
    
    public void addBeneficiery(String projectName, Beneficiery beneficiery, Funding funding) {  // add a new donor in a project
        Project project = searchProject(projectName);
        funding.setBeneficiery(beneficiery);
        project.addFunding(funding);
        beneficiery.addFunding(funding);
        project.setCurrentBalance(project.getCurrentBalance()-funding.getFundingAmount());
    }
    
    public Project searchProject(String name) {  // search a project through project name
        Project project = null;              
        for(int i=0; i<projects.size(); ++i) {
            if(projects.get(i).find(name)){
                project = projects.get(i);
                break;
            }
        }
        return project;
    }
    
    public ArrayList<Project> searchProjectsOfDonor(String dName) {
        
        Donor donor = searchDonor(dName);
        
        ArrayList<Project> arr = new ArrayList<>();
        for(int i=0; i<projects.size(); ++i) {
            if(projects.get(i).searchDonor(donor)) {
                arr.add(projects.get(i));
            }
        }
        return arr;
    }
    
    public ArrayList<Project> searchProjectsOfBeneficiery(String bName) {
        Beneficiery beneficiery = searchBeneficiery(bName);
        
        ArrayList<Project> arr = new ArrayList<>();
        for(int i=0; i<projects.size(); ++i) {
            if(projects.get(i).searchBeneficiery(beneficiery)) {
                arr.add(projects.get(i));
            }
        }
        return arr;
    }
    
    public Project searchProjectByLead(String name) {  // search a project through project name
        Project project = null;              
        for(int i=0; i<projects.size(); ++i) {
            if(projects.get(i).getTeamLead().getName().equals(name)) {
                project = projects.get(i);
                break;
            }
        }
        return project;
    }
    
    public Volunteer searchVolunteer(String name) {  // search a volunteer through volunteer name
        Volunteer volunteer = null;              
        for(int i=0; i<volunteers.size(); ++i) {
            if(volunteers.get(i).find(name)){
                volunteer = volunteers.get(i);
                break;
            }
        }
        return volunteer;
    }
    
    public Donor searchDonor(String donorName) {  // search a donor in all projects and shoe details (for
        
        Donor donor = null;
        for(int i=0; i<projects.size(); ++i) {                                  // all projects)
            if(projects.get(i).findAndGetDonor(donorName) != null){
                donor = projects.get(i).findAndGetDonor(donorName);
                break;
            }
        }
        return donor;
    }
     
    public Beneficiery searchBeneficiery(String beneficieryName) {
        Beneficiery beneficiery = null;
        for(int i=0; i<projects.size(); ++i) {                                  // all projects)
            if(projects.get(i).findAndGetBeneficiery(beneficieryName) != null){
                beneficiery = projects.get(i).findAndGetBeneficiery(beneficieryName);
                break;
            }
        }
        return beneficiery;
    } 
     
    public int showTotalDonation(String name) {    // shows total donations made by a donor in all
        int sum = 0;                                                                    // projects
        for(int i=0; i<projects.size(); ++i) {
            
            sum = sum + projects.get(i).findDonorAndGetDonationAmount(name);
        }
        return sum;
    }
    
    public int showTotalFundings(String bName) {    // shows total donations made by a donor in all
        int sum = 0;                                                                    // projects
        for(int i=0; i<projects.size(); ++i) {
            
            sum = sum + projects.get(i).findBeneficieryAndGetFundingAmount(bName);
        }
        return sum;
    }
    
    public void addApplicant(Beneficiery applicant, int salary) {  // add a new applicants in a project
        applicant.setSalary(salary);
        applicants.add(applicant);
    }

    public ArrayList<Project> getProjects() {
        return projects;
    }

    public ArrayList<Volunteer> getVolunteers() {
        return volunteers;
    }

    public ArrayList<Beneficiery> getApplicants() {
        return applicants;
    }
    
    public Beneficiery searchApplicant(String name) {
        Beneficiery beneficiery = null;
        for(int i=0; i<applicants.size(); ++i) {
            if(applicants.get(i).getName().equals(name)) {
               beneficiery = applicants.get(i);
               break;
            }
        }
        return beneficiery;
    }
    
    public void removeApplicant(String aplicantName) {
        Beneficiery applicant = searchApplicant(aplicantName);
        if(applicant != null) {
            applicants.remove(applicant);
        }
    }
    
    public void loadFromDB() {
        dbConnectivity db = new dbConnectivity();
        db.getVolunteers(volunteers);
        db.getApplicants(applicants);
        db.getProjects(projects);
        for(int i=0; i<projects.size(); ++i) {
            projects.get(i).setDonations(db.getProjectDonations(projects.get(i)));
            projects.get(i).setFundings(db.getProjectFundings(projects.get(i)));
            projects.get(i).setTeam(db.getProjectTeam(projects.get(i)));
        }
    }
    
    public void syncChanges() {
        dbConnectivity dbConnectivity1 = new dbConnectivity();
        
        dbConnectivity1.deleteDB();
        dbConnectivity1.saveVolunteers(volunteers);
        dbConnectivity1.saveApplicants(applicants);
        dbConnectivity1.saveProjectsAll(projects, applicants);
    }
}

    
    


