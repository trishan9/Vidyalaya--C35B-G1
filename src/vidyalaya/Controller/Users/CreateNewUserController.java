/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vidyalaya.Controller.Users;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.logging.Level;
import java.util.logging.Logger;

import vidyalaya.Utils.Utils;

import vidyalaya.Model.AdminData;
import vidyalaya.Model.StudentData;
import vidyalaya.Model.TeacherData;
import vidyalaya.Model.ModelMessage;
import vidyalaya.SessionManagement.AdminSession;

import vidyalaya.Services.MailService;

import vidyalaya.DAO.AuthDAO.AuthDAO;
import vidyalaya.DAO.AuthDAO.AuthDAOImplementation;

import vidyalaya.Components.Modals.CreateNewUserForm;

/**
 *
 * @author trishan9
 */
public class CreateNewUserController {

    private final AuthDAO authDAO = new AuthDAOImplementation();
    private final CreateNewUserForm userView;

    public CreateNewUserController(CreateNewUserForm userView) {
        this.userView = userView;
        userView.addCreateUserListener(new CreateUserListener());
    }

    public void open() {
        this.userView.setVisible(true);
    }

    public void close() {
        this.userView.dispose();
    }

    class CreateUserListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String name = userView.getNameField().getText();
                String user_type = (String) userView.getUserType().getSelectedItem();
                String email = userView.getEmailField().getText();
                String password = new String(userView.getPasswordField().getPassword());

                AdminData currentAdmin = AdminSession.getCurrentUser();

                switch (user_type) {
                    case "Student":
                        StudentData student = new StudentData(currentAdmin.getId(), name, email, password);
                        StudentData createdStudent = authDAO.registerStudent(student);
                        sendStudentCredentials(createdStudent);
                        break;
                    case "Teacher":
                        TeacherData teacher = new TeacherData(currentAdmin.getId(), name, email, password);
                        TeacherData createdTeacher = authDAO.registerTeacher(teacher);
                        sendTeacherCredentials(createdTeacher);
                        break;
                    default:
                        break;
                }

                vidyalaya.View.Dashboard.Admin.UsersScreen usersView = new vidyalaya.View.Dashboard.Admin.UsersScreen();
                vidyalaya.Controller.Users.UsersController usersController = new vidyalaya.Controller.Users.UsersController(usersView);
                Utils.closeAllFrames();
                usersController.open();
                Utils.success(user_type + " created successfully: " + name);
            } catch (Exception ex) {
                Logger.getLogger(CreateNewUserController.class.getName()).log(Level.SEVERE, null, ex);
                Utils.error(ex.getMessage());
            }
        }
    }

    private void sendStudentCredentials(StudentData student) {
        new Thread(() -> {
            Utils.info("Sending credentials to the provided email address...");
            String subject = "Your Vidyalaya AMS Account Details";
            String body = "<html>"
                    + "<body style=\"font-family: Arial, sans-serif; line-height: 1.6; color: #333;\">"
                    + "<div style=\"max-width: 600px; margin: 0 auto; padding: 20px; border: 1px solid #ddd; border-radius: 10px;\">"
                    + "<img src=\"https://res.cloudinary.com/dnqet3vq1/image/upload/v1722913832/vidyalaya-large_crzt2v.png\" alt=\"Vidyalaya Logo\" style=\"display: block; margin: 0 auto 20px; max-width: 100%; height: auto;\"/>"
                    + "<h1 style=\"color: #333; text-align: center;\">Welcome to Vidyalaya AMS, " + student.getName() + "!</h1>"
                    + "<p>Dear " + student.getName() + ",</p>"
                    + "<p>Your admin at your institution has created an account for you in our Vidyalaya AMS. We are excited to have you on board!</p>"
                    + "<p style=\"font-size: 16px; font-weight: bold;\">Here are your login credentials:</p>"
                    + "<ul style=\"list-style: none; padding: 0;\">"
                    + "<li style=\"padding: 8px 0; border-bottom: 1px solid #ddd;\"><strong>Student ID:</strong> " + student.getStudentId() + "</li>"
                    + "<li style=\"padding: 8px 0; border-bottom: 1px solid #ddd;\"><strong>Password:</strong> " + student.getPassword() + "</li>"
                    + "</ul>"
                    + "<p>Please keep this information secure and do not share it with anyone.</p>"
                    + "<p>You can now log in to the student portal and start managing your account.</p>"
                    + "<p>If you have any questions or encounter any issues, please contact your respective admin.</p>"
                    + "<p>Best Regards,<br>Vidyalaya Team</p>"
                    + "<p style=\"text-align: center; color: #888; font-size: 12px; margin-top: 20px;\">© 2024 Vidyalaya | C35B Group 1. All rights reserved.</p>"
                    + "</div>"
                    + "</body>"
                    + "</html>";
            ModelMessage ms = new MailService().sendMain(student.getEmail(), subject, body);
            if (ms.isSuccess()) {
                Utils.success("Student credentials have been sent to the provided email address!");
            } else {
                Utils.error(ms.getMessage());
            }
        }).start();
    }

    private void sendTeacherCredentials(TeacherData teacher) {
        new Thread(() -> {
            Utils.info("Sending credentials to the provided email address...");
            String subject = "Your Vidyalaya AMS Account Details";
            String body = "<html>"
                    + "<body style=\"font-family: Arial, sans-serif; line-height: 1.6; color: #333;\">"
                    + "<div style=\"max-width: 600px; margin: 0 auto; padding: 20px; border: 1px solid #ddd; border-radius: 10px;\">"
                    + "<img src=\"https://res.cloudinary.com/dnqet3vq1/image/upload/v1722913832/vidyalaya-large_crzt2v.png\" alt=\"Vidyalaya Logo\" style=\"display: block; margin: 0 auto 20px; max-width: 100%; height: auto;\"/>"
                    + "<h1 style=\"color: #333; text-align: center;\">Welcome to Vidyalaya AMS, " + teacher.getName() + "!</h1>"
                    + "<p>Dear " + teacher.getName() + ",</p>"
                    + "<p>Your admin at your institution has created an account for you in our Vidyalaya AMS. We are thrilled to have you on board!</p>"
                    + "<p style=\"font-size: 16px; font-weight: bold;\">Here are your login credentials:</p>"
                    + "<ul style=\"list-style: none; padding: 0;\">"
                    + "<li style=\"padding: 8px 0; border-bottom: 1px solid #ddd;\"><strong>Teacher ID:</strong> " + teacher.getTeacherId() + "</li>"
                    + "<li style=\"padding: 8px 0; border-bottom: 1px solid #ddd;\"><strong>Password:</strong> " + teacher.getPassword() + "</li>"
                    + "</ul>"
                    + "<p>Please keep this information secure and do not share it with anyone.</p>"
                    + "<p>You can now log in to the teacher portal and start managing your account.</p>"
                    + "<p>If you have any questions or encounter any issues, please contact your respective admin.</p>"
                    + "<p>Best Regards,<br>Vidyalaya Team</p>"
                    + "<p style=\"text-align: center; color: #888; font-size: 12px; margin-top: 20px;\">© 2024 Vidyalaya | C35B Group 1. All rights reserved.</p>"
                    + "</div>"
                    + "</body>"
                    + "</html>";
            ModelMessage ms = new MailService().sendMain(teacher.getEmail(), subject, body);
            if (ms.isSuccess()) {
                Utils.success("Teacher credentials have been sent to the provided email address!");
            } else {
                Utils.error(ms.getMessage());
            }
        }).start();
    }
}
