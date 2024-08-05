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

import vidyalaya.Model.AdminData;

import vidyalaya.DAO.AuthDAO.AuthDAO;
import vidyalaya.DAO.AuthDAO.AuthDAOImplementation;

import vidyalaya.View.AdminLogin;
import vidyalaya.View.CreateAdmin;

/**
 *
 * @author trishan9
 */
public class CreateAdminController {

    private final AuthDAO authDAO = new AuthDAOImplementation();
    private final CreateAdmin userView;

    public CreateAdminController(CreateAdmin userView) {
        this.userView = userView;
        userView.addSignInRedirectListener(new AdminLoginRedirectListener());
        userView.addCreateAdminListener(new CreateAdminListener());
    }

    public void open() {
        this.userView.setVisible(true);
    }

    public void close() {
        this.userView.dispose();
    }

    class AdminLoginRedirectListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            redirectToAdminLogin();
        }
    }

    class CreateAdminListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String name = userView.getNameField().getText();
                String email = userView.getEmailField().getText();
                String institution_name = userView.getInstitutionNameField().getText();
                String password = new String(userView.getPasswordField().getPassword());

                AdminData admin = new AdminData(name, email, institution_name, password);
                authDAO.registerAdmin(admin);

                Utils.info(userView, "Admin created successfully with name: " + name);
                redirectToAdminLogin();
            } catch (Exception ex) {
                Logger.getLogger(CreateAdminController.class.getName()).log(Level.SEVERE, null, ex);
                Utils.error(userView, ex.getMessage());
            }
        }
    }

    public void redirectToAdminLogin() {
        AdminLogin loginView = new AdminLogin();
        AdminLoginController adminLoginController = new AdminLoginController(loginView);
        this.close();
        adminLoginController.open();
    }
}
