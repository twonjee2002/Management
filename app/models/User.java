package models;

import play.*;
import play.db.jpa.*;
 
import javax.persistence.*;
import java.util.*;
 
import play.data.validation.*;
 
@Entity
public class User extends Model {
 	
    @Required
    @MinSize(8)
    public String firstname;
    
    @Required
    @MinSize(8)
    public String lastname;
    
    @Required
    @MinSize(5)
    @Password
    public String password;
	
    @Required
    @MinSize(8)
    public String createdate;
    
    @Required
    @MaxSize(5)
    @MinSize(4)
    public String role;
    
    @Required
    @MinSize(1)
    public String active;
 
    @Required
    @MaxSize(1000)
    public String address;
	
    @Required
    @Email
    public String email;
    
    public String toString() {
        return email;
    }
	
}