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
import vidyalaya.Model.ModelMessage;

import vidyalaya.Services.MailService;

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

                if (name.isEmpty() || email.isEmpty() || institution_name.isEmpty() || password.isEmpty()) {
                    Utils.warning("All the fields are required!");
                    return;
                }

                AdminData admin = new AdminData(name, email, institution_name, password);
                AdminData createdAdmin = authDAO.registerAdmin(admin);

                sendMain(createdAdmin);
                Utils.success("Admin created successfully: " + name);
                redirectToAdminLogin();
            } catch (Exception ex) {
                Logger.getLogger(CreateAdminController.class.getName()).log(Level.SEVERE, null, ex);
                Utils.error(ex.getMessage());
            }
        }
    }

    private void sendMain(AdminData admin) {
        new Thread(() -> {
            Utils.info("Sending credentials to the provided email address...");
            String subject = "Welcome to Our Platform!";
            String body = "<html>"
                    + "<body style=\"font-family: Arial, sans-serif; line-height: 1.6; color: #333;\">"
                    + "<div style=\"max-width: 600px; margin: 0 auto; padding: 20px; border: 1px solid #ddd; border-radius: 10px;\">"
                    + "<img src=\"https://res.cloudinary.com/dnqet3vq1/image/upload/v1722913832/vidyalaya-large_crzt2v.png\" alt=\"Vidyalaya Logo\" style=\"display: block; margin: 0 auto 20px; max-width: 100%; height: auto;\"/>"
                    + "<h1 style=\"color: #333; text-align: center;\">Welcome to Vidyalaya AMS, " + admin.getName() + "!</h1>"
                    + "<p>Dear " + admin.getName() + ",</p>"
                    + "<p>Thank you for registering as an admin at <strong>" + admin.getInstitutionName() + "</strong> in our Vidyalaya AMS. We are thrilled to have you on board!</p>"
                    + "<p style=\"font-size: 16px; font-weight: bold;\">Here are your login credentials:</p>"
                    + "<ul style=\"list-style: none; padding: 0;\">"
                    + "<li style=\"padding: 8px 0; border-bottom: 1px solid #ddd;\"><strong>Username:</strong> " + admin.getUsername() + "</li>"
                    + "<li style=\"padding: 8px 0; border-bottom: 1px solid #ddd;\"><strong>Password:</strong> " + admin.getPassword() + "</li>"
                    + "</ul>"
                    + "<p>Please keep this information secure and do not share it with anyone.</p>"
                    + "<p>You can now log in to the admin portal and start managing your account.</p>"
                    + "<p>If you have any questions or need assistance, feel free to reach out to our support team at <a href=\"mailto:mailtotrishan@gmail.com\" style=\"color: #0056b3; text-decoration: none;\">mailtotrishan@gmail.com</a>.</p>"
                    + "<p>Best Regards,<br>Vidyalaya Team</p>"
                    + "<p style=\"text-align: center; color: #888; font-size: 12px; margin-top: 20px;\">© 2024 Vidyalaya | C35B Group 1. All rights reserved.</p>"
                    + "</div>"
                    + "</body>"
                    + "</html>";
            ModelMessage ms = new MailService().sendMain(admin.getEmail(), subject, body);
            if (ms.isSuccess()) {
                Utils.success("Credentials have been sent to the given email address!");
            } else {
                Utils.error(ms.getMessage());
            }
        }).start();
    }

    public void redirectToAdminLogin() {
        AdminLogin loginView = new AdminLogin();
        AdminLoginController adminLoginController = new AdminLoginController(loginView);
        this.close();
        adminLoginController.open();
    }
}
