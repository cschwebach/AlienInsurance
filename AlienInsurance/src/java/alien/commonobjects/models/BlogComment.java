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
public class BlogComment implements Serializable {
    private int blogCommentId;
    private int blogId;
    private String content;
    private String createdBy;
    private DateTime dateCreated;
    private DateTime dateModified;
    private String modifiedBy;
    private boolean active = true;
    
    public BlogComment() {
        
    }

    public int getBlogCommentId() {
        return blogCommentId;
    }

    public void setBlogCommentId(int blogCommentId) {
        this.blogCommentId = blogCommentId;
    }

    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public DateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(DateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public DateTime getDateModified() {
        return dateModified;
    }

    public void setDateModified(DateTime dateModified) {
        this.dateModified = dateModified;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
