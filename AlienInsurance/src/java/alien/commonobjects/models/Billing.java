/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alien.commonobjects.models;
import java.io.Serializable;
import java.util.Collection;
import org.joda.time.DateTime;

/**
 *
 * @author Chris
 */
public class Billing implements Serializable {
    private int billingId;
    private String billerFirstName;
    private String billerLastName;
    private String createdBy;
    private int cardNumber;
    private int securityCode;
    private DateTime dateCreated;
    private boolean active = true;

    
    public Billing() {
        
    }

    /**
     * @return the billingId
     */
    public int getBillingId() {
        return billingId;
    }

    /**
     * @param billingId the billingId to set
     */
    public void setBillingId(int billingId) {
        this.billingId = billingId;
    }

    /**
     * @return the billerFirstName
     */
    public String getBillerFirstName() {
        return billerFirstName;
    }

    /**
     * @param billerFirstName the billerFirstName to set
     */
    public void setBillerFirstName(String billerFirstName) {
        this.billerFirstName = billerFirstName;
    }

    /**
     * @return the billerLastName
     */
    public String getBillerLastName() {
        return billerLastName;
    }

    /**
     * @param billerLastName the billerLastName to set
     */
    public void setBillerLastName(String billerLastName) {
        this.billerLastName = billerLastName;
    }

    /**
     * @return the createdBy
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * @param createdBy the createdBy to set
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @return the cardNumber
     */
    public int getCardNumber() {
        return cardNumber;
    }

    /**
     * @param cardNumber the cardNumber to set
     */
    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    /**
     * @return the securityCode
     */
    public int getSecurityCode() {
        return securityCode;
    }

    /**
     * @param securityCode the securityCode to set
     */
    public void setSecurityCode(int securityCode) {
        this.securityCode = securityCode;
    }

    /**
     * @return the dateCreated
     */
    public DateTime getDateCreated() {
        return dateCreated;
    }

    /**
     * @param dateCreated the dateCreated to set
     */
    public void setDateCreated(DateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    /**
     * @return the active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * @param active the active to set
     */
    public void setActive(boolean active) {
        this.active = active;
    }
}