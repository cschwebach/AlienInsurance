/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alien.dataaccess;

import alien.commonobjects.models.CustomerProduct;
import alien.commonobjects.models.Product;
import alien.commonobjects.models.User;
import alien.helpers.ConvertTime;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import org.joda.time.DateTime;

/**
 *
 * @author Trent
 */
public class ProductAccessor {
    
    private static ProductAccessor productAccessor = null;
    
    public static ProductAccessor getInstance() {
        if (null == productAccessor) {
            productAccessor = new ProductAccessor();
        }

        return productAccessor;
    }
    
    public int InsertCustCoverage(User user, Product product) throws SQLException {
        int rowsAffected = 0;
        
        Connection conn = DbConnection.getConnection();
        
        try {
            CallableStatement cmd = conn.prepareCall("{call sp_assign_customer_products(?, ?, ?)}");
            
            cmd.setString(1, 
                    user.getUserName());
            cmd.setInt(2, 
                    product.getProductId());
            cmd.setTimestamp(3, 
                    ConvertTime.getTimestamp(new DateTime())
            );
            
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
    
    public Product retrieveProduct(int productID) throws SQLException {
        Product product = null;
        
        Connection conn = DbConnection.getConnection();
        
        try {
            CallableStatement cmd = conn.prepareCall("{call sp_select_product(?)}");
            
            cmd.setInt(1, productID);
            
            ResultSet rs = cmd.executeQuery();
            
            if (rs.next()) {
                product = new Product();
                
                product.setTitle(
                        rs.getString("title"));
                product.setContent(
                        rs.getString("content"));
                product.setCost(
                        rs.getBigDecimal("cost"));
                product.setDateCreated(
                        ConvertTime.getDateTime(
                                rs.getTimestamp("date_created")
                        ));
                product.setDateModified(
                        ConvertTime.getDateTime(
                                rs.getTimestamp("date_modified")
                        ));
                product.setModifiedBy(
                        rs.getString("modified_by"));
                product.setActive(
                        rs.getBoolean("active"));
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            try { 
                if (null != conn) 
                { conn.close(); }
            } catch (Exception ex) { throw ex; }
        }
        
        return product;
    }
    
    
    
    
}
