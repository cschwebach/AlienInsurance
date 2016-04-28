/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alien.dataaccess;

import alien.commonobjects.models.Claim;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import alien.helpers.ConvertTime;
import java.util.Date;

/**
 *
 * @author Ibrahim
 */
public class ClaimAccessor {

    private int claimID;

    public void createClaim(Claim claim)
            throws SQLException {

    }

    /**
     * Returns an customer record based on the customer ID.
     *
     * @param customerId
     * @return
     * @throws java.sql.SQLException
     */
    public Claim getClaimById(int claimId)
            throws SQLException, ClassNotFoundException {
        Claim claim = null;

        Connection conn = DbConnection.getConnection();

       
            CallableStatement callableStatement = conn.prepareCall(
                    "{call sp_select_claim(?)}");

               callableStatement.setInt(1, claim.getClaimId());
                       ResultSet resultSet = callableStatement.executeQuery();
            if (resultSet.next()) {
                claim = new Claim();
        
                callableStatement.setInt(1, claim.getClaimId());
                callableStatement.setString(2, claim.getContent());
                callableStatement.setTimestamp(3, ConvertTime.getTimestamp(claim.getOccuranceDate()));
                callableStatement.setString(4, claim.getClaimBy());       
                callableStatement.setTimestamp(5, ConvertTime.getTimestamp(claim.getDateCreated()));          
                callableStatement.setBoolean(6, claim.isApproved());     
                callableStatement.setString(7, claim.getProcessedBy());     
                callableStatement.setTimestamp(8, ConvertTime.getTimestamp(claim.getDateProcessed()));
             
            }

        return claim;
    }

    public Boolean UpdateClaimById(int claimId, boolean approved, String processedBy, Date dateProcessed, boolean active)
            throws SQLException, ClassNotFoundException {
        Claim claim = null;
        boolean ret = false;
        Connection conn = DbConnection.getConnection();

        //       try {
        CallableStatement callableStatement = conn.prepareCall(
                "{call sp_update_claim(?,?,?,?,?)}");

        callableStatement.setInt(1, claimId);
        callableStatement.setBoolean(2, approved);
        callableStatement.setString(3, processedBy);
        callableStatement.setString(4, dateProcessed.toString());
        callableStatement.setBoolean(5, active);

       
       ResultSet resultSet = callableStatement.executeQuery();
        if (resultSet.next()) {
            claim = new Claim();
      
                callableStatement.setInt(1, claim.getClaimId());
                callableStatement.setString(2, claim.getContent());
                callableStatement.setTimestamp(3, ConvertTime.getTimestamp(claim.getOccuranceDate()));
                callableStatement.setString(4, claim.getClaimBy());       
                callableStatement.setTimestamp(5, ConvertTime.getTimestamp(claim.getDateCreated()));          
                callableStatement.setBoolean(6, claim.isApproved());     
                callableStatement.setString(7, claim.getProcessedBy());     
                callableStatement.setTimestamp(8, ConvertTime.getTimestamp(claim.getDateProcessed()));
           
            }
         
        return ret;
    }

    public ArrayList<Claim> getClaimsList()
            throws SQLException, ClassNotFoundException {

        ArrayList<Claim> myClaim = new ArrayList();
        Claim claim = null;
        Connection conn = DbConnection.getConnection();

        CallableStatement callableStatement = conn.prepareCall(
                "{call sp_select_claims(?)}");

        callableStatement.setBoolean(1, true);
        ResultSet resultSet = callableStatement.executeQuery();
        while (resultSet.next()) {

            claim = new Claim();

         callableStatement.setInt(1, claim.getClaimId());
                callableStatement.setString(2, claim.getContent());
                callableStatement.setTimestamp(3, ConvertTime.getTimestamp(claim.getOccuranceDate()));
                callableStatement.setString(4, claim.getClaimBy());       
                callableStatement.setTimestamp(5, ConvertTime.getTimestamp(claim.getDateCreated()));          
                callableStatement.setBoolean(6, claim.isApproved());     
                callableStatement.setString(7, claim.getProcessedBy());     
                callableStatement.setTimestamp(8, ConvertTime.getTimestamp(claim.getDateProcessed()));
                
            myClaim.add(claim);
        }
        return myClaim;
    }

    /**
     * Returns a collection of all Customers in the database.
     *
     * @return
     * @throws java.sql.SQLException
     */
    public ArrayList<Claim> getClaims()
            throws SQLException {
        return null;
    }

    /**
     * Uses the data in the Customer object to update the associated record in
     * the database
     *
     * @param customer
     * @throws java.sql.SQLException
     */
    public void updateClaims(Claim claim)
            throws SQLException {
    }

    /**
     * Removes the Customer record from the database.
     *
     * @param customer
     * @throws java.sql.SQLException
     */
    public void deleteClaim(int claimID)
            throws SQLException {
    }

    /**
     * @return the claimID
     */
    public int getClaimID() {
        return claimID;
    }

    /**
     * @param claimID the claimID to set
     */
    public void setClaimID(int claimID) {
        this.claimID = claimID;
    }

}

