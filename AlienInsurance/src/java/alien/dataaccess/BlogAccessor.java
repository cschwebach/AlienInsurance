/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alien.dataaccess;

import alien.commonobjects.models.Blog;
import alien.commonobjects.models.BlogComment;
import alien.helpers.ConvertTime;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Trent
 */
public class BlogAccessor {
    
    public static int createBlog(Blog blog) throws SQLException {
        int blogId = 0;
        
        Connection conn = DbConnection.getConnection();
        
        try {
            CallableStatement cmd = conn.prepareCall("{call sp_insert_blog(?, ?, ?, ?, ?)}");
            
            cmd.setString(1, 
                    blog.getTitle());
            cmd.setString(2, 
                    blog.getContent());
            cmd.setString(3, 
                    blog.getCreatedBy());
            cmd.setBoolean(4, 
                    blog.isDisableComments());
            cmd.setTimestamp(5, 
                    ConvertTime.getTimestamp(
                            blog.getDateCreated()
                    ));
            
            ResultSet rs = cmd.executeQuery();
            
            if (rs.next()) {
                blogId = rs.getInt(1);
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            try { 
                if (null != conn) 
                { conn.close(); }
            } catch (Exception ex) { throw ex; }
        }
        
        return blogId;
    }
    
    public static int updateBlog(Blog blog) throws SQLException {
        int rowsAffected = 0;
        
        Connection conn = DbConnection.getConnection();
        
        try {
            CallableStatement cmd = conn.prepareCall("{call sp_update_blog(?, ?, ?, ?, ?, ?, ?)}");
            
            cmd.setInt(1, 
                    blog.getBlogId());
            cmd.setString(2, 
                    blog.getTitle());
            cmd.setString(3, 
                    blog.getContent());
            cmd.setBoolean(4, 
                    blog.isDisableComments());
            cmd.setTimestamp(5, 
                    ConvertTime.getTimestamp(
                            blog.getDateModified()
                    ));
            cmd.setString(6, 
                    blog.getModifiedBy());
            cmd.setBoolean(7, 
                    blog.isActive());
            
            rowsAffected = cmd.executeUpdate();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            try { 
                if (null != conn) 
                { conn.close(); }
            } catch (Exception ex) { throw ex; }
        }
        
        return rowsAffected;
    }
    
    public static int createBlogComment(BlogComment blogComment) throws SQLException {
        int blogCommentId = 0;
        
        Connection conn = DbConnection.getConnection();
        
        try {
            CallableStatement cmd = conn.prepareCall("{call sp_assign_blog_comment(?, ?, ?, ?)}");
            
            cmd.setInt(1, 
                    blogComment.getBlogId());
            cmd.setString(2, 
                    blogComment.getContent());
            cmd.setString(3, 
                    blogComment.getCreatedBy());
            cmd.setTimestamp(4, 
                    ConvertTime.getTimestamp(
                            blogComment.getDateCreated()
                    ));
            
            ResultSet rs = cmd.executeQuery();
            
            if (rs.next()) {
                blogCommentId = rs.getInt(1);
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            try { 
                if (null != conn) 
                { conn.close(); }
            } catch (Exception ex) { throw ex; }
        }
        
        return blogCommentId;
    }
    
    public static int updateBlogComment(BlogComment blogComment) throws SQLException {
        int rowsAffected = 0;
        
        Connection conn = DbConnection.getConnection();
        
        try {
            CallableStatement cmd = conn.prepareCall("{call sp_update_blog_comment(?, ?, ?, ?, ?)}");
            
            cmd.setInt(1, 
                    blogComment.getBlogCommentId());
            cmd.setString(2, 
                    blogComment.getContent());
            cmd.setTimestamp(3, 
                    ConvertTime.getTimestamp(
                            blogComment.getDateModified()
                    ));
            cmd.setString(4, 
                    blogComment.getModifiedBy());
            cmd.setBoolean(5, 
                    blogComment.isActive());
            
            rowsAffected = cmd.executeUpdate();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            try { 
                if (null != conn) 
                { conn.close(); }
            } catch (Exception ex) { throw ex; }
        }
        
        return rowsAffected;
    }
    
    public static Collection<Blog> retrieveUserBlogs(String userName) throws SQLException {
        Collection<Blog> blogs = new ArrayList<>();
        
        Connection conn = DbConnection.getConnection();
        
        try {
            CallableStatement cmd = conn.prepareCall("{call sp_select_user_blogs(?, ?)}");
            
            cmd.setString(1, userName);
            cmd.setBoolean(2, true);
            
            ResultSet rs = cmd.executeQuery();
            
            if (rs.first()) {
                Blog blog;
                
                while (rs.next()) {
                    blog = new Blog();
                    
                    blog.setBlogId(
                            rs.getInt("blog_id"));
                    blog.setTitle(
                            rs.getString("title"));
                    blog.setContent(
                            rs.getString("content"));
                    blog.setCreatedBy(
                            rs.getString("created_by"));
                    blog.setDisableComments(
                            rs.getBoolean("disable_comments"));
                    blog.setDateCreated(
                            ConvertTime.getDateTime(
                                    rs.getTimestamp("date_created")
                            ));
                    blog.setDateModified(
                            ConvertTime.getDateTime(
                                    rs.getTimestamp("date_modified")
                            ));
                    blog.setModifiedBy(
                            rs.getString("modified_by"));
                    
                    blogs.add(blog);
                }
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            try { 
                if (null != conn) 
                { conn.close(); }
            } catch (Exception ex) { throw ex; }
        }
        
        return blogs;
    }
    
    public static Collection<BlogComment> retrieveUserBlogComments(String userName) throws SQLException {
        Collection<BlogComment> blogComments = new ArrayList<>();
        
        Connection conn = DbConnection.getConnection();
        
        try {
            CallableStatement cmd = conn.prepareCall("{call sp_select_user_blog_comments(?, ?)}");
            
            cmd.setString(1, userName);
            cmd.setBoolean(2, true);
            
            ResultSet rs = cmd.executeQuery();
            
            if (rs.first()) {
                BlogComment blogComment;
                
                while (rs.next()) {
                    blogComment = new BlogComment();
                    
                    blogComment.setBlogCommentId(
                            rs.getInt("blog_comment_id"));
                    blogComment.setBlogId(
                            rs.getInt("blog_id"));
                    blogComment.setContent(
                            rs.getString("content"));
                    blogComment.setDateCreated(
                            ConvertTime.getDateTime(
                                    rs.getTimestamp("date_created")
                            ));
                    blogComment.setDateModified(
                            ConvertTime.getDateTime(
                                    rs.getTimestamp("date_modified")
                            ));
                    blogComment.setModifiedBy(
                            rs.getString("modified_by"));
                    
                    blogComments.add(blogComment);
                }
            } 
        } catch (SQLException ex) {
            throw ex;
        } finally {
            try { 
                if (null != conn) 
                { conn.close(); }
            } catch (Exception ex) { throw ex; }
        }
        
        return blogComments;
    }
    
    public static Blog retrieveBlog(int blogId) throws SQLException {
        Blog blog = null;
        
        Connection conn = DbConnection.getConnection();
        
        try {
            CallableStatement cmd = conn.prepareCall("{call sp_select_blog(?)}");
            
            cmd.setInt(1, blogId);
            
            ResultSet rs = cmd.executeQuery();
            
            if (rs.next()) {
                blog = new Blog();
                
                blog.setBlogId(
                        blogId);
                blog.setTitle(
                        rs.getString("title"));
                blog.setContent(
                        rs.getString("content"));
                blog.setCreatedBy(
                        rs.getString("created_by"));
                blog.setDisableComments(
                        rs.getBoolean("disable_comments"));
                blog.setDateCreated(
                        ConvertTime.getDateTime(
                                rs.getTimestamp("date_created")
                        ));
                blog.setDateModified(
                        ConvertTime.getDateTime(
                                rs.getTimestamp("date_modified")
                        ));
                blog.setModifiedBy(
                        rs.getString("modified_by"));
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            try { 
                if (null != conn) 
                { conn.close(); }
            } catch (Exception ex) { throw ex; }
        }
        
        return blog;
    }
    
    public static Collection<BlogComment> retrieveBlogComments(int blogId) throws SQLException {
        Collection<BlogComment> blogComments = new ArrayList<>();
        
        Connection conn = DbConnection.getConnection();
        
        try {
            CallableStatement cmd = conn.prepareCall("{call sp_select_blog_blog_comments(?, ?)}");
            
            cmd.setInt(1, blogId);
            cmd.setBoolean(2, true);
            
            ResultSet rs = cmd.executeQuery();
            
            BlogComment blogComment;

            while (rs.next()) {
                blogComment = new BlogComment();

                blogComment.setBlogId(
                        blogId);
                blogComment.setBlogCommentId(
                        rs.getInt("blog_comment_id"));
                blogComment.setContent(
                        rs.getString("content"));
                blogComment.setCreatedBy(
                        rs.getString("created_by"));
                blogComment.setDateCreated(
                        ConvertTime.getDateTime(
                                rs.getTimestamp("date_created")
                        ));
                blogComment.setDateModified(
                        ConvertTime.getDateTime(
                                rs.getTimestamp("date_modified")
                        ));
                blogComment.setModifiedBy(
                        rs.getString("modified_by"));

                blogComments.add(blogComment);
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            try { 
                if (null != conn) 
                { conn.close(); }
            } catch (Exception ex) { throw ex; }
        }
        
        return blogComments;
    }
    
    public static Collection<Blog> retrieveBlogs() throws SQLException {
        Collection<Blog> blogs = new ArrayList<>();
        
        Connection conn = DbConnection.getConnection();
        
        try {
            CallableStatement cmd = conn.prepareCall("{call sp_select_blogs(?)}");
            
            cmd.setBoolean(1, true);
            
            ResultSet rs = cmd.executeQuery();
            
            Blog blog;
                
            while (rs.next()) {
                blog = new Blog();

                blog.setBlogId(
                        rs.getInt("blog_id"));
                blog.setTitle(
                        rs.getString("title"));
                blog.setContent(
                        rs.getString("content"));
                blog.setCreatedBy(
                        rs.getString("created_by"));
                blog.setDisableComments(
                        rs.getBoolean("disable_comments"));
                blog.setDateCreated(
                        ConvertTime.getDateTime(
                                rs.getTimestamp("date_created")
                        ));
                blog.setDateModified(
                        ConvertTime.getDateTime(
                                rs.getTimestamp("date_modified")
                        ));
                blog.setModifiedBy(
                        rs.getString("modified_by"));

                blogs.add(blog);
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            try { 
                if (null != conn) 
                { conn.close(); }
            } catch (Exception ex) { throw ex; }
        }
        
        return blogs;
    }
}
