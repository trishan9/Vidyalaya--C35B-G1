/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vidyalaya.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.logging.Level;
import java.util.logging.Logger;

import vidyalaya.DAO.AuthDAO.AuthDAO;
import vidyalaya.DAO.AuthDAO.AuthDAOImplementation;

import vidyalaya.Model.LoginRequest;
import vidyalaya.Utils.UIUtils;

import vidyalaya.View.UserLogin;

/**
 *
 * @author trishan9
 */
public class UserLoginController {

    private final AuthDAO authDAO = new AuthDAOImplementation();
    private final UserLogin userView;

    public UserLoginController(UserLogin userView) {
        this.userView = userView;
        userView.addUserLoginListener(new UserLoginListener());
    }

    public void open() {
        this.userView.setVisible(true);
    }

    public void close() {
        this.userView.dispose();
    }

    class UserLoginListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String username = userView.getUsernameField().getText();
                String password = new String(userView.getPasswordField().getPassword());
                String usernamePrefix = username.substring(0, 2);
                String userType;

                switch (usernamePrefix) {
                    case "ST":
                        userType = "student";
                        break;
                    case "TE":
                        userType = "teacher";
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid Username!");
                }

                LoginRequest user = new LoginRequest(username, password);
                handleLogin(user, userType);
            } catch (Exception ex) {
                Logger.getLogger(UserLoginController.class.getName()).log(Level.SEVERE, null, ex);
                UIUtils.error(userView, ex.getMessage());
            }
        }
    }

    public void handleLogin(LoginRequest user, String userType) throws Exception {
        switch (userType) {
            case "student":
                authDAO.loginStudent(user);
                redirectToStudentDashboard();
                UIUtils.info(new vidyalaya.View.Dashboard.Student.MyCoursesScreen(), "Logged in successfully as student!");
                break;
            case "teacher":
                authDAO.loginTeacher(user);
                redirectToTeacherDashboard();
                UIUtils.info(new vidyalaya.View.Dashboard.Teacher.MyCoursesScreen(), "Logged in successfully as teacher!");
                break;
            default:
                throw new IllegalArgumentException("Invalid user type: " + userType);
        }
    }

    public void redirectToStudentDashboard() {
        vidyalaya.View.Dashboard.Student.MyCoursesScreen myCoursesView = new vidyalaya.View.Dashboard.Student.MyCoursesScreen();
        vidyalaya.Controller.Courses.Student.MyCoursesController myCoursesController = new vidyalaya.Controller.Courses.Student.MyCoursesController(myCoursesView);
        close();
        myCoursesController.open();
    }

    public void redirectToTeacherDashboard() {
        vidyalaya.View.Dashboard.Teacher.MyCoursesScreen myCoursesView = new vidyalaya.View.Dashboard.Teacher.MyCoursesScreen();
        vidyalaya.Controller.Courses.Teacher.MyCoursesController myCoursesController = new vidyalaya.Controller.Courses.Teacher.MyCoursesController(myCoursesView);
        close();
        myCoursesController.open();
    }
}
