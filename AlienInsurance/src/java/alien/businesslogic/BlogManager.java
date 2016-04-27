/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alien.businesslogic;

import alien.commonobjects.models.Blog;
import alien.commonobjects.models.BlogComment;
import alien.dataaccess.BlogAccessor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import org.joda.time.DateTime;

/**
 *
 * @author Trent
 */
public class BlogManager {
    private final String userName;
    
    public BlogManager(String userName) {
        this.userName = userName;
    }
    
    public boolean createBlog(String title, String content, boolean disableComments) {
        boolean flag = false;
        
        Blog blog = new Blog();
        
        blog.setTitle(title);
        blog.setContent(content);
        blog.setDisableComments(disableComments);
        blog.setCreatedBy(userName);
        blog.setDateCreated(new DateTime());
        
        try {
            int blogId = BlogAccessor.createBlog(blog);
            
            if (flag = 0 != blogId) {
                blog.setBlogId(blogId);
            }
        } catch (Exception ex) { blog = null; }
        
        
        return flag;
    }
    
    public boolean deleteBlog(int blogId) {
        boolean flag = false;
        
        Blog blog = new Blog();
        
        blog.setBlogId(blogId);
        blog.setActive(false);
        
        try {
            flag = 1 == BlogAccessor.updateBlog(blog);
        } catch (Exception ex) { }
        
        return flag;
    }
    
    public boolean createBlogComment(int blogId, String content) {
        boolean flag = false;
        
        BlogComment blogComment = new BlogComment();
        
        blogComment.setBlogId(blogId);
        blogComment.setContent(content);
        blogComment.setCreatedBy(userName);
        blogComment.setDateCreated(new DateTime());
        
        try {
            int blogCommentId = BlogAccessor.createBlogComment(blogComment);
            
            if (flag = 0 != blogCommentId) {
                blogComment.setBlogCommentId(blogCommentId);
            }
        } catch (Exception ex) { blogComment = null; }
        
        return flag;
    }
    
    public boolean deleteBlogComment(int blogCommentId) {
        boolean flag = false;
        
        BlogComment blogComment = new BlogComment();
        
        blogComment.setBlogCommentId(blogCommentId);
        blogComment.setActive(false);
        
        try {
            flag = 1 == BlogAccessor.updateBlogComment(blogComment);
        } catch (Exception ex) { }
        
        return flag;
    }
    
    public Collection<Blog> retrieveUserBlogs() {
        Collection<Blog> blogs = null;
        
        try {
            blogs = BlogAccessor.retrieveUserBlogs(userName);
        } catch (Exception ex) { }
        
        return blogs;
    }
    
    public static Blog retrieveBlog(int blogId) {
        Blog blog = null;
        
        try {
            blog = BlogAccessor.retrieveBlog(blogId);
            
            ArrayList<BlogComment> blogComments = new ArrayList<>(BlogAccessor.retrieveBlogComments(blogId));
            
            Collections.sort(blogComments, (BlogComment bc1, BlogComment bc2) -> bc2.getDateCreated().compareTo(bc1.getDateCreated()));
            
            blog.setBlogComments(blogComments);
        } catch (Exception ex) { }
        
        return blog;
    }
    
    public static Collection<Blog> retrieveRelevantBlogs() throws Exception {
        Collection<Blog> blogs = null;
        
        try {
            blogs = BlogAccessor.retrieveBlogs();
        } catch (Exception ex) {
            throw ex;
        }
        
        return blogs;
    }
}
