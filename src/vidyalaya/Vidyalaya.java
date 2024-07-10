/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package vidyalaya;

import vidyalaya.Controller.AdminLoginController;
import vidyalaya.Controller.UserLoginController;

import vidyalaya.View.AdminLogin;
import vidyalaya.View.UserLogin;

/**
 *
 * @author trishan9
 */
public class Vidyalaya {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        AdminLogin userView = new AdminLogin();
        AdminLoginController userController = new AdminLoginController(userView);
        userController.open();

//        UserLogin userView = new UserLogin();
//        UserLoginController userController = new UserLoginController(userView);
//        userController.open();
    }

}
