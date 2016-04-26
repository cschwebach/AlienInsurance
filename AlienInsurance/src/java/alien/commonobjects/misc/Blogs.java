/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alien.commonobjects.misc;

import alien.commonobjects.models.Blog;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Trent
 */
public class Blogs implements Serializable {
    private Collection<Blog> blogs;
    
    public Blogs() {
        
    }
    
    public int getSize() {
        return blogs.size();
    }
    
    public boolean isEmpty() {
        return blogs.isEmpty();
    }

    public ArrayList<Blog> getBlogs() {
        return (ArrayList<Blog>)blogs;
    }

    public void setBlogs(Collection<Blog> blogs) {
        this.blogs = blogs;
    }
}
