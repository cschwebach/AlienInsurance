/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alien.commonobjects.models;

import java.io.Serializable;
import org.joda.time.DateTime;

/**
 *
 * @author Trent
 */
public class Claim implements Serializable {
    private int claimId;
    private String content;
    private DateTime occuranceDate;
    private String claimBy;
    private DateTime dateCreated;
    private boolean active = true;
    private boolean approved;
    private String processedBy;
    private DateTime dateProcessed;
    
    public Claim() {
        
    }
    public int getClaimId() {
        return claimId;
    }

    public void setClaimId(int claimId) {
        this.claimId = claimId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public DateTime getOccuranceDate() {
        return occuranceDate;
    }

    public void setOccuranceDate(DateTime occuranceDate) {
        this.occuranceDate = occuranceDate;
    }

    public String getClaimBy() {
        return claimBy;
    }

    public void setClaimBy(String claimBy) {
        this.claimBy = claimBy;
    }

    public DateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(DateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public String getProcessedBy() {
        return processedBy;
    }

    public void setProcessedBy(String processedBy) {
        this.processedBy = processedBy;
    }

    public DateTime getDateProcessed() {
        return dateProcessed;
    }

    public void setDateProcessed(DateTime dateProcessed) {
        this.dateProcessed = dateProcessed;
    }

}
