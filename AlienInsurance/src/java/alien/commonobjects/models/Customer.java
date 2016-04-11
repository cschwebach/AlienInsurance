/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alien.commonobjects.models;

import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author Trent
 */
public class Customer extends User implements Serializable {
    private Collection<CustomerProduct> customerProducts;
    private Collection<Claim> claims;
    
    public Customer(String userName) {
        super(userName);
    }

    public Collection<CustomerProduct> getCustomerProducts() {
        return customerProducts;
    }

    public void setCustomerProducts(Collection<CustomerProduct> customerProducts) {
        this.customerProducts = customerProducts;
    }

    public Collection<Claim> getClaims() {
        return claims;
    }

    public void setClaims(Collection<Claim> claims) {
        this.claims = claims;
    }
}
