/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alien.businesslogic;

import alien.commonobjects.models.Claim;
import alien.dataaccess.ClaimAccessor;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ibrahim
 */
public class ClaimManager {
     
    private String message = "message test";
    private ArrayList<Claim> claimList;
    private Claim currentClaim;
    int claimId;
    
 /*   public static void main(String[] args) {
        
            ClaimBLL cMgr = new ClaimBLL();
            Scanner scan = new Scanner(System.in);
     
            System.out.println("Please enter Claims ID: ");
            cMgr.claimId = scan.nextInt(); 
            showClaim();    
    } */
    
    public ClaimManager(){
       
        claimList = ClaimListMethod();     
    }
    
     public ClaimManager(int clmId){
       
        currentClaim = getClaim(claimId);
    }

    public Claim getClaim(int claimId)
    {
        try {
            ClaimAccessor claimAcc = new ClaimAccessor();
            claimAcc.getClaimById(claimId);
           
        } catch (SQLException ex) {
            Logger.getLogger(ClaimManager.class.getName()).log(Level.SEVERE, null, ex);
             message = "SQLException " + ex.getMessage();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClaimManager.class.getName()).log(Level.SEVERE, null, ex);
             message = "ClassNotFoundException " + ex.getMessage();
        }
        return currentClaim;       
    } 
    
    public ArrayList<Claim> ClaimListMethod()
    {
        try {
            ClaimAccessor claimAcc = new ClaimAccessor();
            claimList = claimAcc.getClaimsList();
            message = "file size is: " + claimList.size();
           
        } catch (SQLException ex) {
            Logger.getLogger(ClaimManager.class.getName()).log(Level.SEVERE, null, ex);
             message = "SQLException " + ex.getMessage();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClaimManager.class.getName()).log(Level.SEVERE, null, ex);
             message = "ClassNotFoundException " + ex.getMessage();
        }
        
        return claimList;       
    } 
        
    public Claim UpdateClaim(int claimId, boolean approved, String processedBy, Date dateProcessed, boolean active)
      
    {
        ClaimAccessor claimAcc = new ClaimAccessor();
        
        try {
            
                 claimAcc.UpdateClaimById(claimId, approved, processedBy, dateProcessed, active);
           
        } catch (SQLException ex) {
            Logger.getLogger(ClaimManager.class.getName()).log(Level.SEVERE, null, ex);
             message = "SQLException " + ex.getMessage();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClaimManager.class.getName()).log(Level.SEVERE, null, ex);
             message = "ClassNotFoundException " + ex.getMessage();
        }
        return currentClaim;       
    } 
              
   
    /**
     * Displays the usage information for the user.
     */
    public static void showUsage() {
        System.out.println("USAGE: java claimsManager <claim ID>");
    }
    
    /**
     * There was no employee ID provided, so show an error message and the 
     * usage information
     */
    public static void showError() {
        System.out.println("ERROR: No customer ID was provided.");
        showUsage();
    }

    /**
     * @return the customerList
     */

    /**
     * @param customerList the customerList to set
     */
    public void setClaimList(ArrayList<Claim> claimList) {
        this.claimList = claimList;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }     

    /**
     * @return the currentClaim
     */
    public Claim getCurrentClaim() {
        return currentClaim;
    }

    /**
     * @param currentClaim the currentClaim to set
     */
    public void setCurrentClaim(Claim currentClaim) {
        this.currentClaim = currentClaim;
    }

    /**
     * @return the claimList
     */
    public ArrayList<Claim> getClaimList() {
        return claimList;
    }
}

