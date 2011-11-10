package controllers;

import java.util.List;

import play.*;
import play.data.validation.Valid;
import play.mvc.*;

import models.*;


public class Application extends Controller {

	 @Before
	    static void addUser() {
	        User user = connected();
	        if(user != null) {
	            renderArgs.put("user", user);
	        }
	    }
	
    static User connected() {
        if(renderArgs.get("user") != null) {
            return renderArgs.get("user", User.class);
        }
        String username = session.get("user");
        if(username != null) {
            return User.find("byUsername", username).first();
        } 
        return null;
    }
   
    public static void show(Long id) {
       Recording recording = Recording.findById(id);
       render(recording);
   
    }
    // ~~

    public static void index() {
        if(connected() != null) {
            Application.index();
        }
        render();
    }
    
      public static void register(String myName, String myPassword) {
        render(myName, myPassword);
    }
    
    public static void saveUser(@Valid User user, String verifyPassword) {
        validation.required(verifyPassword);
        validation.equals(verifyPassword, user.password).message("Your password doesn't match");
        if(validation.hasErrors()) {
            render("@register", user, verifyPassword);
        }
        user.create();
        session.put("user", user.username);
        flash.success("Welcome, " + user.name);
        Projects.index();
    }
    
     public static void login(String username, String password) {
        User user = User.find("byUsernameAndPassword", username, password).first();
        if(user != null) {
            session.put("user", user.username);
            flash.success("Welcome, " + user.name);
            Projects.index();         
        }
        // Oops
        flash.put("username", username);
        flash.error("Login failed");
        index();
    }
    
    public static void logout() {
        session.clear();
        index();
    }

}