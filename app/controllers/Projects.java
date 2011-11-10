package controllers;

import play.*;
import play.mvc.*;
import play.data.validation.*;

import java.util.*;

import models.*;

public class Projects extends Application {
    
    @Before
    static void checkUser() {
        if(connected() == null) {
            flash.error("Please log in first");
            Application.index();
        }
    }
    
    // ~~~
    
    public static void index() {
        List<Recording> recordings = Recording.find("byUser", connected()).fetch();
        render(recordings);
    }

    public static void list(String search, Integer size, Integer page) {
        List<Project> projects = null;
        page = page != null ? page : 1;
        if(search.trim().length() == 0) {
        	projects = Project.all().fetch(page, size);
        } else {
            search = search.toLowerCase();
            projects = Project.find("lower(name) like ? OR lower(city) like ?", "%"+search+"%", "%"+search+"%").fetch(page, size);
        }
        render(projects, search, size, page);
    }
    
    public static void show(Long id) {
    	Project project = Project.findById(id);
        render(project);
    }
    
    public static void record(Long id) {
    	Project project = Project.findById(id);
        render(project);
    }
    
    public static void confirmRecording(Long id, Recording recording) {
    	Project project = Project.findById(id);
        recording.project = project;
        recording.user = connected();
        validation.valid(recording);
        
        // Errors or revise
        if(validation.hasErrors() || params.get("revise") != null) {
            render("@record", project, recording);
        }
        
        // Confirm
        if(params.get("confirm") != null) {
            recording.create();
            flash.success("Thank you, %s, your confimation number for %s is %s", connected().name, project.name, recording.id);
            index();
        }
        
        // Display booking
        render(project, recording);
    }
    
    public static void cancelRecording(Long id) {
        Recording recording = Recording.findById(id);
        recording.delete();
        flash.success("Booking cancelled for confirmation number %s", recording.id);
        index();
    }
    
    public static void addRemarks(Long id) {
    	Recording recording = Recording.findById(id);	
    	index();
    }
    
    public static void settings() {
        render();
    }
    
    public static void saveSettings(String password, String verifyPassword) {
        User connected = connected();
        connected.password = password;
        validation.valid(connected);
        validation.required(verifyPassword);
        validation.equals(verifyPassword, password).message("Your password doesn't match");
        if(validation.hasErrors()) {
            render("@settings", connected, verifyPassword);
        }
        connected.save();
        flash.success("Password updated");
        index();
    }
    
}

