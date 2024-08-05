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
import vidyalaya.SessionManagement.AdminSession;

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
                        authDAO.registerStudent(student);
                        break;
                    case "Teacher":
                        TeacherData teacher = new TeacherData(currentAdmin.getId(), name, email, password);
                        authDAO.registerTeacher(teacher);
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
}
