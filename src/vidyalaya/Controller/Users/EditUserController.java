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
import vidyalaya.Model.UserData;
import vidyalaya.SessionManagement.AdminSession;

import vidyalaya.DAO.AuthDAO.AuthDAO;
import vidyalaya.DAO.AuthDAO.AuthDAOImplementation;

import vidyalaya.Components.Modals.EditUserForm;

/**
 *
 * @author trishan9
 */
public class EditUserController {

    private final AuthDAO authDAO = new AuthDAOImplementation();
    private final EditUserForm userView;
    private final int userId;
    private final String userType;

    public UserData currentUser;

    public EditUserController(int userId, String userType, EditUserForm userView) {
        this.userView = userView;
        this.userId = userId;
        this.userType = userType;

        userView.addEditUserListener(new EditUserListener());
        getCurrentUser();

        userView.getNameField().setText(currentUser.getName());
        userView.getEmailField().setText(currentUser.getEmail());

    }

    public void open() {
        this.userView.setVisible(true);
    }

    public void close() {
        this.userView.dispose();
    }

    public final void getCurrentUser() {
        try {
            switch (userType) {
                case "student":
                    currentUser = authDAO.getStudentById(userId);
                    break;
                case "teacher":
                    currentUser = authDAO.getTeacherById(userId);
                    break;
                default:
                    break;
            }
        } catch (Exception ex) {
            Logger.getLogger(EditUserController.class.getName()).log(Level.SEVERE, null, ex);
            Utils.error(userView, ex.getMessage());
            currentUser = null;
        }
    }

    class EditUserListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String name = userView.getNameField().getText();
                String user_type = userType;
                String email = userView.getEmailField().getText();

                AdminData currentAdmin = AdminSession.getCurrentUser();

                switch (user_type) {
                    case "student":
                        StudentData student = new StudentData(currentAdmin.getId(), name, email);
                        authDAO.updateStudent(userId, student);
                        break;
                    case "teacher":
                        TeacherData teacher = new TeacherData(currentAdmin.getId(), name, email);
                        authDAO.updateTeacher(userId, teacher);
                        break;
                    default:
                        break;
                }

                vidyalaya.View.Dashboard.Admin.UsersScreen usersView = new vidyalaya.View.Dashboard.Admin.UsersScreen();
                vidyalaya.Controller.Users.UsersController usersController = new vidyalaya.Controller.Users.UsersController(usersView);
                Utils.closeAllFrames();
                usersController.open();
                Utils.info(usersView, user_type + " updated successfully!");
            } catch (Exception ex) {
                Logger.getLogger(EditUserController.class.getName()).log(Level.SEVERE, null, ex);
                Utils.error(userView, ex.getMessage());
            }
        }
    }
}
