/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vidyalaya.Controller.Admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vidyalaya.Controller.AdminLoginController;

import vidyalaya.DAO.AuthDAO.AuthDAO;
import vidyalaya.DAO.AuthDAO.AuthDAOImplementation;

import vidyalaya.View.AdminLogin;
import vidyalaya.View.Dashboard.Admin.AttendanceScreen;
import vidyalaya.View.Dashboard.Admin.CoursesScreen;
import vidyalaya.View.Dashboard.Admin.NoticesScreen;
import vidyalaya.View.Dashboard.Admin.RoutineScreen;
import vidyalaya.View.Dashboard.Admin.SettingsScreen;
import vidyalaya.View.Dashboard.Admin.UsersScreen;

/**
 *
 * @author trish
 */
public class AttendanceController {

    private final AuthDAO authDAO = new AuthDAOImplementation();
    private final AttendanceScreen userView;

    public AttendanceController(AttendanceScreen userView) {
        this.userView = userView;
        userView.addCoursesRedirectListener(new CoursesRedirectListener());
        userView.addRoutineRedirectListener(new RoutineRedirectListener());
        userView.addNoticesRedirectListener(new NoticesRedirectListener());
        userView.addUsersRedirectListener(new UsersRedirectListener());
        userView.addSettingsRedirectListener(new SettingsRedirectListener());
        userView.addLogoutListener(new LogoutListener());
    }

    public void open() {
        this.userView.setVisible(true);
    }

    public void close() {
        this.userView.dispose();
    }

    class CoursesRedirectListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            CoursesScreen coursesView = new CoursesScreen();
            CoursesController coursesController = new CoursesController(coursesView);
            close();
            coursesController.open();
        }
    }

    class RoutineRedirectListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            RoutineScreen routineView = new RoutineScreen();
            RoutineController routineController = new RoutineController(routineView);
            close();
            routineController.open();
        }
    }

    class NoticesRedirectListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            NoticesScreen noticesView = new NoticesScreen();
            NoticesController noticesController = new NoticesController(noticesView);
            close();
            noticesController.open();
        }
    }

    class UsersRedirectListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            UsersScreen usersView = new UsersScreen();
            UsersController usersController = new UsersController(usersView);
            close();
            usersController.open();
        }
    }

    class SettingsRedirectListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            SettingsScreen settingsView = new SettingsScreen();
            SettingsController settingsController = new SettingsController(settingsView);
            close();
            settingsController.open();
        }
    }

    class LogoutListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            AdminLogin adminLoginView = new AdminLogin();
            AdminLoginController adminLoginController = new AdminLoginController(adminLoginView);
            close();
            adminLoginController.open();
        }
    }
}
