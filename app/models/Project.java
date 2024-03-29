package models;

import play.db.jpa.*;
import play.data.validation.*;

import javax.persistence.*;

import java.math.*;

@Entity
public class Project extends Model {
    
	@Required
    @ManyToOne
    public User user;
	
	@Required
    @MaxSize(50)
    public String name;
    
    @MaxSize(100)
    public String address;
    
    @Required
    @MaxSize(40)
    public String city;
    
    @Required
    @MaxSize(6) 
    @MinSize(2)
    public String state;
    
    @Required
    @MaxSize(6) 
    @MinSize(1)
    public String zip;
    
    @Required
    @MaxSize(40) 
    @MinSize(2)
    public String country;
    
    @Column(precision=6, scale=2)
    public BigDecimal price;

    public String toString() {
        return "Project(" + name + "," + address + "," + city + "," + zip + ")";
    }
    
}
