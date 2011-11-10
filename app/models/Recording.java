package models;

import play.db.jpa.*;
import play.data.validation.*;
import javax.persistence.*;
import java.util.*;
import java.text.*;
import java.math.*;

@Entity
public class Recording extends Model {
    
    @Required
    @ManyToOne
    public User user;
    
    @Required
    @ManyToOne
    public Project project;
    
    @Required
    @Temporal(TemporalType.DATE) 
    public Date checkinDate;
    
    @Required
    @Temporal(TemporalType.DATE)
    public Date checkoutDate;
    
    @Required(message="Credit card number is required")
    @Match(value="^\\d{6}$", message="Credit card number must be numeric and 6 digits long")
    public String creditCard;
    
    @Required(message="Credit card name is required")
    @MinSize(value=3, message="Credit card name is required")
    @MaxSize(value=70, message="Credit card name is required")
    public String creditCardName;
    public int creditCardExpiryMonth;
    public int creditCardExpiryYear;
    public boolean smoking;
    public int beds;

    public Recording(Project project, User user) {
        this.project = project;
        this.user = user;
    }
   
    public BigDecimal getTotal() {
        return project.price.multiply( new BigDecimal( getNights() ) );
    }

    public int getNights() {
        return (int) ( checkoutDate.getTime() - checkinDate.getTime() ) / 1000 / 60 / 60 / 24;
    }

    public String getDescription() {
        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
        return project==null ? null : project.name + 
            ", " + df.format( checkinDate ) + 
            " to " + df.format( checkoutDate );
    }

    public String toString() {
        return "Recording(" + user + ","+ project + ")";
    }

}
