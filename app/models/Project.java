package models;

import play.*;
import play.data.validation.Email;
import play.data.validation.MaxSize;
import play.data.validation.MinSize;
import play.data.validation.Password;
import play.data.validation.Required;
import play.db.jpa.*;

import javax.persistence.*;
import java.util.*;

@Entity
public class Project extends Model {
 
	 @Required
	    @MinSize(8)
	    public String projectname;
	    
	    @Required
	    @MinSize(8)
	    @MaxSize(60)
	    public String company;
	    
	    @Required
	    @MinSize(8)
	    public String startdate;
		
	    @Required
	    @MinSize(8)
	    public String enddate;
	    
	    @Required
	    @MaxSize(1000)
	    public String companyaddress;
	    
	    @Required
	    @MaxSize(1000)
	    public String remarks;
		
	    }
		
	

