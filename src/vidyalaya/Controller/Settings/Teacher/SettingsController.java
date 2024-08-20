/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vidyalaya.Controller.Settings.Teacher;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

import vidyalaya.Utils.Utils;

import vidyalaya.Model.TeacherData;
import vidyalaya.SessionManagement.TeacherSession;

import vidyalaya.DAO.AuthDAO.AuthDAO;
import vidyalaya.DAO.AuthDAO.AuthDAOImplementation;

import vidyalaya.View.UserLogin;
import vidyalaya.View.Dashboard.Teacher.AttendanceScreen;
import vidyalaya.View.Dashboard.Teacher.MyCoursesScreen;
import vidyalaya.View.Dashboard.Teacher.NoticesScreen;
import vidyalaya.View.Dashboard.Teacher.RoutineScreen;
import vidyalaya.View.Dashboard.Teacher.SettingsScreen;

import vidyalaya.Controller.Users.UserLoginController;

/**
 *
 * @author trish
 */
public class SettingsController {

    private final AuthDAO authDAO = new AuthDAOImplementation();
    private final SettingsScreen userView;

    public SettingsController(SettingsScreen userView) {
        this.userView = userView;
        userView.addUpdateProfileListener(new UpdateProfileListener());
        userView.addChangePasswordListener(new ChangePasswordListener());
        userView.addCoursesRedirectListener(new CoursesRedirectListener());
        userView.addRoutineRedirectListener(new RoutineRedirectListener());
        userView.addNoticesRedirectListener(new NoticesRedirectListener());
        userView.addAttendanceRedirectListener(new AttendanceRedirectListener());
        userView.addLogoutListener(new LogoutListener());
    }

    public void open() {
        this.userView.setVisible(true);
    }

    public void close() {
        this.userView.dispose();
    }

    class UpdateProfileListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String name = userView.getNameField().getText();
                String emailAddress = userView.getEmailField().getText();

                if (name.isEmpty() || emailAddress.isEmpty()) {
                    Utils.warning("All the fields are required!");
                    return;
                }

                String currentName = TeacherSession.getCurrentUser().getName();
                String currenEmailAddress = TeacherSession.getCurrentUser().getEmail();

                if (name.equals(currentName) && emailAddress.equals(currenEmailAddress)) {
                    Utils.info("No changes detected. The name, and email address are the same as the current values.");
                } else {
                    TeacherData teacher = new TeacherData(name, emailAddress);
                    authDAO.updateTeacher(TeacherSession.getCurrentUser().getId(), teacher);

                    vidyalaya.View.Dashboard.Teacher.SettingsScreen settingsView = new vidyalaya.View.Dashboard.Teacher.SettingsScreen();
                    vidyalaya.Controller.Settings.Teacher.SettingsController settingsController = new vidyalaya.Controller.Settings.Teacher.SettingsController(settingsView);
                    Utils.closeAllFrames();
                    settingsController.open();
                    Utils.success("Profile updated successfully");
                }

            } catch (Exception ex) {
                Logger.getLogger(SettingsController.class.getName()).log(Level.SEVERE, null, ex);
                Utils.error(ex.getMessage());
            }
        }
    }

    class ChangePasswordListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String oldPassword = new String(userView.getCurrentPasswordField().getPassword());
                String newPassword = new String(userView.getNewPasswordField().getPassword());

                if (oldPassword.isEmpty() || newPassword.isEmpty()) {
                    Utils.warning("All the fields are required!");
                    return;
                }

                String currentPassword = TeacherSession.getCurrentUser().getPassword();

                if (!oldPassword.equals(currentPassword)) {
                    Utils.warning("The current password you entered does not match our records. Please ensure you have entered the correct password.");
                } else if (newPassword.equals(currentPassword)) {
                    Utils.info("No changes detected. The new password is the same as the current password.");
                } else {
                    authDAO.changePassword("teacher", TeacherSession.getCurrentUser().getId(), newPassword);

                    vidyalaya.View.Dashboard.Teacher.SettingsScreen settingsView = new vidyalaya.View.Dashboard.Teacher.SettingsScreen();
                    vidyalaya.Controller.Settings.Teacher.SettingsController settingsController = new vidyalaya.Controller.Settings.Teacher.SettingsController(settingsView);
                    Utils.closeAllFrames();
                    settingsController.open();
                    Utils.success("Password updated successfully");
                }

            } catch (Exception ex) {
                Logger.getLogger(SettingsController.class.getName()).log(Level.SEVERE, null, ex);
                Utils.error(ex.getMessage());
            }
        }
    }

    class CoursesRedirectListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            MyCoursesScreen coursesView = new MyCoursesScreen();
            vidyalaya.Controller.Courses.Teacher.MyCoursesController coursesController = new vidyalaya.Controller.Courses.Teacher.MyCoursesController(coursesView);
            Utils.closeAllFrames();
            coursesController.open();
        }
    }

    class RoutineRedirectListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            RoutineScreen routineView = new RoutineScreen();
            vidyalaya.Controller.Routine.Teacher.RoutineController routineController = new vidyalaya.Controller.Routine.Teacher.RoutineController(routineView);
            Utils.closeAllFrames();
            routineController.open();
        }
    }

    class NoticesRedirectListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            NoticesScreen noticesView = new NoticesScreen();
            vidyalaya.Controller.Notices.Teacher.NoticesController noticesController = new vidyalaya.Controller.Notices.Teacher.NoticesController(noticesView);
            Utils.closeAllFrames();
            noticesController.open();
        }
    }

    class AttendanceRedirectListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            AttendanceScreen attendanceView = new AttendanceScreen();
            vidyalaya.Controller.Attendance.Teacher.AttendanceController attendanceController = new vidyalaya.Controller.Attendance.Teacher.AttendanceController(attendanceView);
            Utils.closeAllFrames();
            attendanceController.open();
        }
    }

    class LogoutListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int result = Utils.confirm(userView, "Are you sure you want to logout?");
            if (result == JOptionPane.YES_OPTION) {
                UserLogin userLoginView = new UserLogin();
                UserLoginController userLoginController = new UserLoginController(userLoginView);
                Utils.closeAllFrames();
                userLoginController.open();
            }
        }
    }
}
