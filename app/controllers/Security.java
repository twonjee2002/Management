package controllers;

import models.User;
import play.mvc.*;

public class Security extends Secure.Security {

    static boolean authentificate(String email, String password) {
       User user = User.find("byEmail", email).first();
       return user != null && user.password.equals(password); 
    }
    static User userConnected() {
		String email = Secure.Security.connected();
		User user = User.find("byEmail", email).first();

		return user;
	}
}
