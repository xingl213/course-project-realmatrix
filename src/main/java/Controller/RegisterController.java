package Controller;

import input_boundaries.LoginInputBoundary;
import input_boundaries.UserInputBoundary;
import manager.LoginManager;
import manager.UserManager;
import presenters.LoginPresenter;

public class RegisterController {
    UserInputBoundary um;
    LoginInputBoundary lm;

    /**
     * A register Controller that allow user to register
     */
    public RegisterController(){
        this.um = new UserManager();
        this.lm = new LoginManager((UserManager) um);
    }

    /**
     * User register method
     * @param username user's username
     * @param password user's password
     * @return User's username and tell he/she is already registered and login
     */
    public String register(String username, String password){
        boolean flag;
        String s = "";
        LoginPresenter lp = new LoginPresenter(username);
        try {
            um.createNewUser(username,password);
            flag = true;
        } catch (Exception e) {
            s= e.getMessage();
            flag = false;
        }
        try {
            lm.logInUser(username, password,lp);
        } catch (Exception e) {
            s += "\n" + e.getMessage();
            flag = false;
        }
        if(flag) {
            return "User" + username + "registered and already login";
        }else{
            return s;
        }
    }
}
