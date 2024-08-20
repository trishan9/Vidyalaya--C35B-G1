/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vidyalaya.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.logging.Level;
import java.util.logging.Logger;

import vidyalaya.Utils.Utils;

import vidyalaya.Model.LoginRequest;

import vidyalaya.DAO.AuthDAO.AuthDAO;
import vidyalaya.DAO.AuthDAO.AuthDAOImplementation;

import vidyalaya.View.AdminLogin;
import vidyalaya.View.CreateAdmin;
import vidyalaya.View.UserLogin;

import vidyalaya.Controller.Users.UserLoginController;
import vidyalaya.View.Dashboard.Admin.DashboardScreen;

/**
 *
 * @author trishan9
 */
public class AdminLoginController {

    private final AuthDAO authDAO = new AuthDAOImplementation();
    private final AdminLogin userView;

    public AdminLoginController(AdminLogin userView) {
        this.userView = userView;
        userView.addSignUpRedirectListener(new CreateAdminRedirectListener());
        userView.addSignInAsUserRedirectListener(new LoginUserRedirectListener());
        userView.addAdminLoginListener(new AdminLoginListener());
    }

    public void open() {
        this.userView.setVisible(true);
    }

    public void close() {
        this.userView.dispose();
    }

    class CreateAdminRedirectListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            redirectToCreateAdmin();
        }
    }

    class LoginUserRedirectListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            redirectToUserLogin();
        }
    }

    class AdminLoginListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String username = userView.getUsernameField().getText();
                String password = new String(userView.getPasswordField().getPassword());

                LoginRequest admin = new LoginRequest(username, password);
                authDAO.loginAdmin(admin);

                redirectToAdminDashboard();
                Utils.success("Logged in sucessfully!");
            } catch (Exception ex) {
                Logger.getLogger(AdminLoginController.class.getName()).log(Level.SEVERE, null, ex);
                Utils.error(ex.getMessage());
            }
        }
    }

    public void redirectToCreateAdmin() {
        CreateAdmin createAdminView = new CreateAdmin();
        CreateAdminController createAdminController = new CreateAdminController(createAdminView);
        close();
        createAdminController.open();
    }

    public void redirectToUserLogin() {
        UserLogin userLoginView = new UserLogin();
        UserLoginController userLoginController = new UserLoginController(userLoginView);
        close();
        userLoginController.open();
    }

    public void redirectToAdminDashboard() {
        DashboardScreen dashboardView = new DashboardScreen();
        vidyalaya.Controller.Dashboard.DashboardController dashboardController = new vidyalaya.Controller.Dashboard.DashboardController(dashboardView);
        close();
        dashboardController.open();
    }
}
